# Genes - Gerador de Horários com Algoritmo Genético

**Genes** é um projeto Java para geração automática de horários escolares (timetabling), voltado para escolas de idiomas.

O sistema utiliza **algoritmos genéticos** para alocar professores em turmas e horários, respeitando critérios como nível de proficiência, disponibilidade e carga horária.

A saída principal é feita para demonstrar os dados brutos da analise e uma saída visivelmente agradável, com tons de cores diferentes (dependendo do seu terminal).

Esta apresentação está separada em duas partes importantes, uma é a explicação do algoritmo genético e a  [outra uma solução para problemas relacionados a nomenclatura de níveis](https://www.notion.so/Genes-Gerador-de-Hor-rios-com-Algoritmo-Gen-tico-238d2bb749e98093818eede7b5dcee77?pvs=21).

---

## 🎯 Objetivo

Criar uma solução que distribua as aulas da melhor forma possível, considerando:

- Um professor não pode lecionar duas aulas no mesmo horário.
- Um professor só pode lecionar para turmas de **nível igual ou inferior** ao seu.
- Cada turma precisa ter uma quantidade mínima de aulas por semana.
- Professores têm horários disponíveis limitados.
- Preferencialmente, um professor não deve ensinar uma mesma turma **duas vezes por semana.**

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Maven
- JSON (entrada de dados)
- Console (visualização colorida com ANSI)

---

## 📁 Estrutura do Projeto

genes/

├── model/          ← Representa as entidades: Professor, Turma, Aula, Horário

├── core/             ← Contém o algoritmo genético (indivíduos, fitness, crossover etc.)

├── util/                ← Funções auxiliares, como leitura de arquivos JSON e formatação

├── view/              ← Impressão visual dos horários no terminal (colorido)

├── TestNiveis/    ← Exemplo para soluções de diferentes níveis para ensino de idiomas.

└── Main.java       ← Ponto de entrada principal

---

## 🚀 Como funciona

1. **Entrada de dados**:
    - `professores.json`: contém professores, seus níveis e horários disponíveis.
    - `turmas.json`: contém turmas, idioma, nível e quantidade de aulas semanais.
2. **Processo evolutivo**:
    - Geração inicial de soluções aleatórias.
    - Avaliação de fitness com base nas restrições.
    - Cruzamento e mutação para gerar novas soluções.
    - Seleção da melhor solução após um número de gerações.
3. **Saída**:
    - Solução impressa no terminal em forma de lista e tabela colorida por dia da semana.

---

## 🧪 Exemplo de saída

Geração 0 - Melhor fitness: 85

Geração 1 - Melhor fitness: 80

….

----- Solução -----

Segunda-08: Turma A com João

Terça-10: Turma A com Bruno

Sexta-08: Turma B com Maria

Quarta-10: Turma B com Paula

Sexta-14: Turma C com Carlos

Quarta-09: Turma C com Carlos

Sexta-09: Turma D com Bruno

Segunda-14: Turma D com Lucas

Terça-14: Turma E com Lucas

Quarta-14: Turma E com Laura

Fitness: 5

<img width="473" height="322" alt="image" src="https://github.com/user-attachments/assets/324c32b8-bd7c-4333-9505-c8cbad9b4cd4" />


## 📄 Formato dos arquivos JSON

### professores.json

```json
[
  {
    "nome": "João",
    "nivel": 2,
    "disponibilidade": ["Segunda-09", "Terça-10", "Quarta-11"]
  }
]

```

### turmas.json

```json
[
  {
    "nome": "A1",
    "idioma": "Inglês",
    "nivel": 2,
    "aulasPorSemana": 2
  }
]

```

---

## 📚 **Objetivo do Módulo `TestNiveis`**

Esse módulo foi criado para **testar a lógica de compatibilidade entre professores e níveis de ensino**, considerando que cada **instituição pode ter seus próprios níveis** (internos ou baseados em classificações como A1, B1 etc.). O sistema deve:

- Permitir que um professor ensine **apenas o seu nível e os inferiores**.
- Tratar diferentes idiomas com níveis distintos.
- Permitir fácil adaptação sem depender de nomes fixos de níveis.

## 🔧 Classes e Funcionalidades

### ✅ `TestNivel` — Gerencia níveis por idioma

Essa classe tem duas funções principais:

1. **Registrar um idioma e seus níveis.**
2. **Determinar quais níveis um professor pode ensinar**, dado seu nível atual.

### 📌 Atributos:

```java
ArrayList<String> niveis;

```

A `niveis` armazena:

- O primeiro item: nome do **idioma**.
- Os demais itens: **níveis ordenados**, do mais básico ao mais avançado.

### 🔩 Métodos importantes:

### `AdicionarIdioma(String idioma)`

Adiciona o nome do idioma como **primeiro elemento** da lista.

### `AdicionarNivel(String nivel)`

Adiciona um nível na ordem desejada. Só permite adicionar se o idioma já tiver sido definido.

### `podeEnsinar(String nivelDoProfessor)`

Dado o nível do professor, retorna **todos os níveis anteriores e o dele próprio**, em ordem.

**Funcionamento**:

1. Busca o índice do nível informado.
2. Percorre a lista do idioma e adiciona todos até esse índice.
3. Se não encontrar, avisa que o professor não é qualificado.

### `ExibirDados()`

Mostra todos os níveis cadastrados daquele idioma, organizados com índice.

---

### ✅ `TestProfessor` — Representa um professor

Essa classe tem:

- Um nome.
- A lista de níveis que o professor pode ensinar (gerada pela lógica de `TestNivel.podeEnsinar()`).

### 🔩 Métodos importantes:

### `ProfessorPodeEnsinar()`

Exibe:

- O nome do professor.
- O idioma e os níveis que ele pode ensinar.

## 💡 Exemplo de funcionamento

### Definindo níveis para o idioma:

```java
TestNivel ingles = new TestNivel();
ingles.AdicionarIdioma("Inglês");
ingles.AdicionarNivel("Básico-1");
ingles.AdicionarNivel("Básico-2");
ingles.AdicionarNivel("Intermediário-1");
ingles.AdicionarNivel("Intermediário-2");
```

### Criando um professor:

```java
TestProfessor professor1 = new TestProfessor("João", ingles.podeEnsinar("Básico-2"));
```

### Resultado:

```
O Professor João tem o Nivel: 'Básico-2', pode ensinar o idioma: Inglês, nos níveis:
1 | Básico-1
2 | Básico-2
```

## 🧠 Lógica de hierarquia dos níveis

A mágica está em:

```java
int existeNivel = niveis.indexOf(nivelDoProfessor);
```

Isso retorna **a posição do nível** na lista. Como a lista está ordenada do mais simples ao mais complexo, então:

- Se `nível == índice 3` → pode ensinar os níveis `0`, `1`, `2`, `3`.

Essa abordagem permite:

- Cada idioma ter **sua própria escala**.
- Comparações apenas com base na **ordem inserida**, sem depender de nomenclatura padronizada.

---

## 📈 Vantagens dessa estrutura

- Flexível para qualquer nomenclatura (ex: "A1", "Básico", "10", etc.).
- Suporta múltiplos idiomas, cada um com seus próprios níveis.
- Facilita comparações entre o nível do professor e os níveis das turmas.
- Fácil expansão e integração com sistemas maiores, como um motor de alocaçã# Genes - Gerador de Horários com Algoritmo Genético

  
**TODO:**
  
Adaptar esse projeto para usar OptaPlanner:

| Genes (atual) | OptaPlanner | Descrição |
| --- | --- | --- |
| `Horario` | **PlanningEntity** | Cada alocação (aula) é uma entidade que será decidida pelo planner |
| `Professor`, `Turma`, `Horario disponível` | **ProblemFacts** | São dados fixos fornecidos ao problema |
| `Solucao` | **PlanningSolution** | A solução completa: conjunto de `Horarios` válidos |
| Algoritmo Genético | OptaPlanner Solver | Substitui a execução do seu `AlgoritmoGenetico.executar()` |
| Fitness manual | **ConstraintProvider** | Define as regras (restrições) do problema |
