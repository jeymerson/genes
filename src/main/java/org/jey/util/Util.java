package org.jey;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

public class Util {
    public static List<Professor> carregarProfessores(String caminho) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Files.readString(Paths.get(caminho)),
                new TypeReference<List<Professor>>() {});
    }

    public static List<Turma> carregarTurmas(String caminho) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Files.readString(Paths.get(caminho)),
                new TypeReference<List<Turma>>() {});
    }
    public static String nivelParaTexto(int n) {
        return switch (n) {
            case 1 -> "A1";
            case 2 -> "B1";
            case 3 -> "C1";
            default -> "X";
        };

    }
}
