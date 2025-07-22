package org.jey.niveis;

import java.util.List;

public class TestProfessor {
    private String nome;
    private List<String> niveis;

    public TestProfessor(String nome, List<String> niveis) {
        this.nome = nome;
        this.niveis = niveis;
    }

    public void ProfessorPodeEnsinar(){
        System.out.print("O Professor " + this.nome + " tem o Nivel: '" + niveis.get(niveis.size() -1) + "', pode ensinar o idioma: ");
        if (niveis.isEmpty()) {
            System.out.println("lista Vazia");
            return;
        }
        int i = 1;
        System.out.println(niveis.get(0)+ ", nos níveis: ");
        System.out.println("__________________");
        for(String nivel: niveis){
            if (nivel.equals(niveis.get(0))){
                continue;
            }
            System.out.println(i + " | " + nivel);
            System.out.println("__________________");
            i++;
        }
    }
}
