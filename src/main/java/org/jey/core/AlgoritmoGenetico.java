package org.jey;

import java.util.*;

//***
public class AlgoritmoGenetico {
    private final List<Professor> professores;
    private final List<Turma> turmas;

    private final int tamanhoPopulacao = 50;
    private final int maxGeracoes = 100;
    private final double taxaMutacao = 0.2;

    public AlgoritmoGenetico(List<Professor> professores, List<Turma> turmas) {
        this.professores = professores;
        this.turmas = turmas;
    }

    public Solucao executar() {
        List<Solucao> populacao = gerarPopulacaoInicial();

        for (int geracao = 0; geracao < maxGeracoes; geracao++) {
            populacao.sort(Comparator.comparingInt(s -> s.fitness));
            Solucao melhor = populacao.get(0);
            System.out.println("Geração " + geracao + " - Melhor fitness: " + melhor.fitness);

            if (melhor.fitness == 0) break; // Solução ideal

            List<Solucao> novaPopulacao = new ArrayList<>();
            novaPopulacao.add(melhor); // elitismo: preserva o melhor

            Random rand = new Random();
            while (novaPopulacao.size() < tamanhoPopulacao) {
                Solucao pai1 = selecionar(populacao);
                Solucao pai2 = selecionar(populacao);
                Solucao filho = cruzar(pai1, pai2);
                if (rand.nextDouble() < taxaMutacao) {
                    mutar(filho);
                }
                filho.calcularFitness();
                novaPopulacao.add(filho);
            }

            populacao = novaPopulacao;
        }

        populacao.sort(Comparator.comparingInt(s -> s.fitness));
        return populacao.get(0);
    }

    private List<Solucao> gerarPopulacaoInicial() {
        List<Solucao> populacao = new ArrayList<>();
        for (int i = 0; i < tamanhoPopulacao; i++) {
            populacao.add(new Solucao(turmas, professores));
        }
        return populacao;
    }

    private Solucao selecionar(List<Solucao> populacao) {
        // Torneio simples
        Random rand = new Random();
        Solucao s1 = populacao.get(rand.nextInt(populacao.size()));
        Solucao s2 = populacao.get(rand.nextInt(populacao.size()));
        return s1.fitness < s2.fitness ? s1 : s2;
    }

    private Solucao cruzar(Solucao pai1, Solucao pai2) {
        Random rand = new Random();
        List<Horario> novos = new ArrayList<>();

        for (int i = 0; i < pai1.horarios.size(); i++) {
            Horario escolhido = rand.nextBoolean() ? pai1.horarios.get(i) : pai2.horarios.get(i);
            Horario novo = new Horario();
            novo.diaHora = escolhido.diaHora;
            novo.professor = escolhido.professor;
            novo.turma = escolhido.turma;
            novos.add(novo);
        }

        Solucao filho = new Solucao(turmas, professores); // Apenas para estrutura
        filho.horarios = novos;
        return filho;
    }

    private void mutar(Solucao s) {
        Random rand = new Random();
        if (s.horarios.isEmpty()) return;

        Horario alvo = s.horarios.get(rand.nextInt(s.horarios.size()));
        Professor novoProfessor = professores.get(rand.nextInt(professores.size()));
        if (!novoProfessor.horariosDisponiveis.isEmpty()) {
            alvo.professor = novoProfessor;
            alvo.diaHora = novoProfessor.horariosDisponiveis.get(rand.nextInt(novoProfessor.horariosDisponiveis.size()));
        }
    }
}
