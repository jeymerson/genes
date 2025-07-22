package org.jey.niveis;

import java.util.ArrayList;

public class TestNivel {

    ArrayList<String> niveis = new ArrayList<>();

    //Cria o objeto com um Array vazio
    public TestNivel() {
        this.niveis = new ArrayList<>();
    }
    // o primeiro elemento, indice 0 será o idioma
     public void AdicionarIdioma(String idioma){
        niveis.add(idioma);
     }

     // Função para adicionar os idiomas
     public void AdicionarNivel(String nivel){
        //pequena validação
        if (niveis.isEmpty()){
            System.out.println("Por favor adicione um idioma primeiro");
        } else {
            niveis.add(nivel);
        }
     }
     //Função para determinar quais os níveis que o professor poderá ensinar com base no nivel passado
     public ArrayList podeEnsinar(String nivelDoProfessor){
        //cria um array vaio provisoriamente
        ArrayList podeEsinar = new ArrayList<>();
        //valida se existe tal nível
        int existeNivel = niveis.indexOf(nivelDoProfessor);

        // Se existir o nível, então vamos começar
        if(existeNivel != -1){
            for(String nivel: niveis){
                // se o nivel passado for igual ao nivel atual da interação
                if (niveis.get(existeNivel) == nivel) {
                    //adiciona
                    podeEsinar.add(nivel);
                    // para
                    break;
                } else{
                    // adiciona
                    podeEsinar.add(nivel);
                }

            }
        } else{
            // quer dizer que o valor passado não está na lista de níveis
            podeEsinar.add("Professor não capacitado para ensinar");
        }
        // retorna o objeto
        return podeEsinar;
     }

     // exibe os dados do idioma em questão
     public void ExibirDados(){
         if (niveis.isEmpty()) {
             System.out.println("lista Vazia");
             return;
         }
         int i = 1;
         System.out.println("Idoma: " + niveis.get(0));
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


    @Override
    public String toString() {
        return "TestNivel{" +
                "niveis=" + niveis +
                '}';
    }
}
