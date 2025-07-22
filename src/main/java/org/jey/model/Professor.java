package org.jey;

import java.util.List;

public class Professor {
    public String nome;
    public int nivel;
    public List<String> idiomas;
    public List<String> horariosDisponiveis;

    // Avaliação o professor poderá ensinar a turma
    public boolean podeEnsinar(Turma turma) {
        return nivel >= turma.nivel && idiomas.contains(turma.idioma);
    }

    public String toString() {
        return nome + " (Nível " + nivel + ")";
    }
}