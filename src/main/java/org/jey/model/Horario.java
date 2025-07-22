package org.jey;
public class Horario {
    public String diaHora;     // Ex: Segunda-08
    public Turma turma;
    public Professor professor;

    public String toString() {

        return diaHora + ": " + turma.nome + " com " + professor.nome;
    }
}
