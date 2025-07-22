package org.jey;

import java.util.*;
import java.util.stream.Collectors;

public class TabelaHorario {

    private static final List<String> DIAS_SEMANA = List.of("Segunda", "Terça", "Quarta", "Quinta", "Sexta");

    public static void exibirTabelaHorarios(List<Aula> aulas) {
        // Inicializa o mapa com os dias da semana
        Map<String, List<Aula>> aulasPorDia = new HashMap<>();
        DIAS_SEMANA.forEach(dia -> aulasPorDia.put(dia, new ArrayList<>()));

        // Agrupa as aulas por dia da semana, com capitalização
        for (Aula aula : aulas) {
            String dia = capitalizar(aula.dia.trim());
            aulasPorDia.computeIfPresent(dia, (k, v) -> { v.add(aula); return v; });
        }

        for (String dia : DIAS_SEMANA) {
            System.out.println(ConsoleColors.BOLD + ConsoleColors.BLUE + dia + ":" + ConsoleColors.RESET);

            List<Aula> aulasDoDia = aulasPorDia.get(dia).stream()
                    .sorted(Comparator.comparing(a -> a.hora))
                    .collect(Collectors.toList());

            if (aulasDoDia.isEmpty()) {
                System.out.println("  (Sem aulas)");
            } else {
                for (Aula aula : aulasDoDia) {
                    String linha = String.format("  %s:00: %s (%s, %s%s%s) com %s%s%s",
                            aula.hora,
                            ConsoleColors.GREEN + aula.turma + ConsoleColors.RESET,
                            aula.idioma,
                            ConsoleColors.getLevelColor(aula.nivel),
                            aula.nivel,
                            ConsoleColors.RESET,
                            ConsoleColors.YELLOW,
                            aula.professor,
                            ConsoleColors.RESET
                    );
                    System.out.println(linha);
                }
            }
            System.out.println(); // Espaço entre dias
        }
    }

    private static String capitalizar(String s) {
        return (s == null || s.isEmpty()) ? s : s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
