package org.jey.niveis;

public class TestInit {
    public static void main(String[] args) {

        //Criando os níveis e os idiomas
        TestNivel ingles = new TestNivel();
        ingles.AdicionarIdioma("Ingês");
        ingles.AdicionarNivel("Básico-1");
        ingles.AdicionarNivel("Básico-2");
        ingles.AdicionarNivel("Intermediário-1");
        ingles.AdicionarNivel("Intermediário-2");

        TestNivel alemao = new TestNivel();
        alemao.AdicionarIdioma("Alemão");
        alemao.AdicionarNivel("A1");
        alemao.AdicionarNivel("A2");
        alemao.AdicionarNivel("B1");
        alemao.AdicionarNivel("B2");

        TestNivel italiano = new TestNivel();
        italiano.AdicionarIdioma("italiano");
        italiano.AdicionarNivel("1");
        italiano.AdicionarNivel("2");
        italiano.AdicionarNivel("3");
        italiano.AdicionarNivel("4");

        TestNivel frances = new TestNivel();
        frances.AdicionarIdioma("Frances");
        frances.AdicionarNivel("10");
        frances.AdicionarNivel("20");
        frances.AdicionarNivel("30");
        frances.AdicionarNivel("40");

        //Criando os professores com nome, o idioma e o nivel do professor.
        TestProfessor professor1 = new TestProfessor("João", ingles.podeEnsinar("Básico-2"));
        //Mostra o nome do professor e os niveis que o mesmo poderá ensinar
        professor1.ProfessorPodeEnsinar();

//        TestProfessor professor2 = new TestProfessor("Carlos", italiano.podeEnsinar("4"));
//        professor2.ProfessorPodeEnsinar();
//
//        TestProfessor professor3 = new TestProfessor("Lucas", alemao.podeEnsinar("A1"));
//        professor3.ProfessorPodeEnsinar();
//
//        TestProfessor professor4 = new TestProfessor("Maria", frances.podeEnsinar("20"));
//        professor4.ProfessorPodeEnsinar();


        //Exibição dos dados dos idiomas
//        ingles.ExibirDados();
//        alemao.ExibirDados();
//        italiano.ExibirDados();
//        frances.ExibirDados();


    }
}
