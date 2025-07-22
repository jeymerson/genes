package org.jey;

import java.util.*;

public class Solucao {
    public List<Horario> horarios;
    public int fitness;

    public Solucao(List<Turma> turmas, List<Professor> professores) {
        this.horarios = new ArrayList<>();
        Random random = new Random();

        for (Turma turma : turmas) {
            int aulasPorSemana = 2;
            Set<String> usados = new HashSet<>();

            for (int i = 0; i < aulasPorSemana; i++) {
                Professor prof = professores.get(random.nextInt(professores.size()));
                List<String> possiveis = new ArrayList<>(prof.horariosDisponiveis);
                possiveis.removeAll(usados);
                if (possiveis.isEmpty()) continue;

                String horario = possiveis.get(random.nextInt(possiveis.size()));
                usados.add(horario);

                Horario h = new Horario();
                h.diaHora = horario;
                h.professor = prof;
                h.turma = turma;
                horarios.add(h);
            }
        }
        calcularFitness();
    }

    /** Resumo do calculo do Fitness
     *  Solução baseada em penalização:
     *      +10 se um professor for alocado duas vezes no mesmo horário.
     *      +20 se o professor não tiver nível ou idioma compatível com a turma.
     *      +5 se o mesmo professor estiver atribuído mais de uma vez à mesma turma.
     *  Quanto menor o fitness, melhor a solução.
     */


    public void calcularFitness() {
        fitness = 0;
        Set<String> conflitos = new HashSet<>();
        Map<String, Integer> repeticoes = new HashMap<>();

        for (Horario h : horarios) {
            String key = h.diaHora + "-" + h.professor.nome;
            if (conflitos.contains(key)) {
                fitness += 10; // conflito de horário
            } else {
                conflitos.add(key);
            }

            if (!h.professor.podeEnsinar(h.turma)) {
                fitness += 20;
            }

            String chave = h.turma.nome + "-" + h.professor.nome;
            repeticoes.put(chave, repeticoes.getOrDefault(chave, 0) + 1);
        }

        for (int count : repeticoes.values()) {
            if (count > 1) fitness += 5;
        }
    }

    public void imprimir() {
        System.out.println("----- Solução -----");
        for (Horario h : horarios) {
            System.out.println(h);
        }
        System.out.println("Fitness: " + fitness);
    }
}
