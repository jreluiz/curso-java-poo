# Aula 05 — Classes e Objetos

> 🎯 Objetivos: entender por que a POO existe, escrever sua primeira classe com atributos e métodos, criar objetos com `new` e dominar construtores e `this`.

## 1. O problema que a POO resolve

Você terminou a Aula 04 com três alunos em arrays paralelos:

```java
String[] nomes = new String[3];
String[] matriculas = new String[3];
double[][] notas = new double[3][3];
```

Os dados de **um** aluno estão espalhados por três lugares, e é você quem precisa lembrar que `nomes[2]`, `matriculas[2]` e `notas[2]` são a mesma pessoa. Some um telefone: mais um array. Ordene por nome: bagunçou tudo.

A Programação Orientada a Objetos propõe outra pergunta. Em vez de *"quais dados eu preciso guardar?"*, ela pergunta:

> **"Quais coisas existem no meu problema, e o que cada uma sabe e sabe fazer?"**

No problema do boletim existe uma coisa chamada **aluno**. Ele *sabe* um nome, uma matrícula e algumas notas; e *sabe fazer* uma coisa: calcular a própria média. Dados e comportamento **juntos** — este é o objeto.

## 2. Classe é a planta; objeto é a construção

```
        CLASSE Aluno                          OBJETOS (instâncias)
   ┌─────────────────────┐            ┌──────────────┐  ┌──────────────┐
   │ nome                │            │ nome: "Ana"  │  │ nome: "Léo"  │
   │ matricula           │  ──new──▶  │ mat.: "1001" │  │ mat.: "1002" │
   │ notas[]             │            │ notas: 8,7,9 │  │ notas: 5,6,4 │
   │ calcularMedia()     │            └──────────────┘  └──────────────┘
   └─────────────────────┘              cada um com seus próprios valores
     escrita UMA vez                     e capaz de calcular sua média
```

A **classe** é escrita uma vez e descreve o formato. O **objeto** é cada exemplar criado a partir dela, com seus próprios valores. Uma planta de casa, muitas casas.

## 3. Sua primeira classe

Crie o arquivo `Aluno.java` — **sem `main`**, porque esta classe não é um programa, é um modelo:

```java
public class Aluno {
    // ATRIBUTOS: o que o aluno SABE (o estado dele)
    String nome;
    String matricula;
    double[] notas = new double[3];

    // MÉTODOS: o que o aluno SABE FAZER (o comportamento dele)
    double calcularMedia() {
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.length;
    }

    boolean estaAprovado() {
        return calcularMedia() >= 7;      // um método pode usar outro da mesma classe
    }

    void imprimirBoletim() {
        System.out.printf("%s (%s) - média %.2f - %s%n",
                nome, matricula, calcularMedia(),
                estaAprovado() ? "APROVADO" : "REPROVADO");
    }
}
```

Repare no que **sumiu** em relação à Aula 04: nenhum método recebe o array de notas por parâmetro. `calcularMedia()` já sabe de quais notas está falando — as **do próprio objeto**. É essa a diferença entre um método `static` solto e um método de instância.

Agora um segundo arquivo, `Escola.java`, que **usa** a classe:

```java
public class Escola {
    public static void main(String[] args) {
        Aluno ana = new Aluno();          // cria um objeto na memória
        ana.nome = "Ana";                 // o ponto acessa o que é do objeto
        ana.matricula = "1001";
        ana.notas[0] = 8.0;
        ana.notas[1] = 7.0;
        ana.notas[2] = 9.0;

        Aluno leo = new Aluno();          // outro objeto, independente
        leo.nome = "Léo";
        leo.matricula = "1002";
        leo.notas[0] = 5.0;
        leo.notas[1] = 6.0;
        leo.notas[2] = 4.0;

        ana.imprimirBoletim();            // Ana (1001) - média 8,00 - APROVADO
        leo.imprimirBoletim();            // Léo (1002) - média 5,00 - REPROVADO
    }
}
```

Dois arquivos, mesma pasta. Compile e rode:

```bash
java Escola.java     # o Java encontra e compila Aluno.java junto
```

> 💡 **Onde foi parar o `static`?** `calcularMedia()` não é `static` porque depende de **qual** aluno. `main` é `static` porque precisa existir antes de qualquer objeto. Chamar um método de instância direto do `main` sem objeto dá o erro `non-static method cannot be referenced from a static context` — o compilador está perguntando: *"a média de quem?"*.

## 4. `new`, referência e `null`

```java
Aluno ana = new Aluno();
```

Essa linha faz três coisas: `new Aluno()` **cria o objeto** na memória, `Aluno ana` **declara uma variável** capaz de apontar para um aluno, e o `=` **guarda o endereço** do objeto nela.

A variável não guarda o objeto — guarda uma **referência** a ele. E isso muda tudo:

```java
Aluno a = new Aluno();
a.nome = "Ana";

Aluno b = a;              // NÃO copia o aluno: dá um segundo nome ao mesmo objeto
b.nome = "Beatriz";

System.out.println(a.nome);   // Beatriz 😱
```

```
   a ──┐
       ├──▶ [ Aluno: nome="Beatriz" ]      um objeto, dois apelidos
   b ──┘
```

Para ter dois alunos de verdade, são dois `new`. E uma referência que não aponta para nada vale `null`:

```java
Aluno c = null;
System.out.println(c.nome);   // 💥 NullPointerException
```

`NullPointerException` é o erro de execução mais comum do Java, e a causa é quase sempre a mesma: **faltou um `new`** (ou uma busca não encontrou nada e devolveu `null`).

## 5. Construtores: nascer já pronto

Criar o objeto e preencher atributo por atributo é ruim por dois motivos: é verboso e permite objetos **pela metade** (um aluno sem nome). O **construtor** resolve os dois:

```java
public class Aluno {
    String nome;
    String matricula;
    double[] notas = new double[3];

    // CONSTRUTOR: mesmo nome da classe, SEM tipo de retorno (nem void!)
    public Aluno(String nome, String matricula) {
        this.nome = nome;               // this.nome = o atributo; nome = o parâmetro
        this.matricula = matricula;
    }

    // ... métodos ...
}
```

Agora criar um aluno sem nome **não compila**:

```java
Aluno ana = new Aluno("Ana", "1001");    // ✅
Aluno x = new Aluno();                   // ❌ constructor Aluno cannot be applied to given types
```

### O que é `this`?

`this` é a referência ao **objeto atual** — "eu mesmo". Ele é obrigatório quando o parâmetro tem o mesmo nome do atributo, para desfazer a ambiguidade:

```java
public Aluno(String nome) {
    this.nome = nome;      // "o MEU nome recebe o nome que veio de fora"
    nome = nome;           // ❌ sem this: o parâmetro atribui a si mesmo, o atributo fica null
}
```

> ⚠️ **Ao escrever qualquer construtor, o construtor vazio deixa de existir.** Antes você podia dar `new Aluno()`; depois de declarar `Aluno(String, String)`, não pode mais — a não ser que você declare também um construtor sem parâmetros (Aula 06).

Com construtor, o `main` fica assim:

```java
Aluno ana = new Aluno("Ana", "1001");
ana.notas[0] = 8.0;
ana.notas[1] = 7.0;
ana.notas[2] = 9.0;
ana.imprimirBoletim();
```

E agora o pulo do gato: alunos cabem num array **como qualquer outro valor**.

```java
Aluno[] turma = new Aluno[3];
turma[0] = new Aluno("Ana", "1001");
turma[1] = new Aluno("Léo", "1002");
turma[2] = new Aluno("Duda", "1003");

for (Aluno aluno : turma) {
    aluno.imprimirBoletim();      // cada objeto usa os PRÓPRIOS dados
}
```

Um array, três objetos completos, zero sincronização manual. Compare com o exercício 5 da Aula 04.

## 6. O primeiro diagrama de classes

Antes de programar, desenhe. A classe `Aluno` fica assim:

```
┌────────────────────────────┐
│           Aluno            │
├────────────────────────────┤
│ + nome: String             │
│ + matricula: String        │
│ + notas: double[]          │
├────────────────────────────┤
│ + calcularMedia(): double  │
│ + estaAprovado(): boolean  │
│ + imprimirBoletim(): void  │
└────────────────────────────┘
```

Aquele `+` significa `public` — e na próxima aula você vai descobrir por que deixar tudo público é uma péssima ideia. Detalhes de notação no [guia de diagrama de classes](../../recursos/diagrama-de-classes.md).

> 💡 **Como decidir o que vira classe?** Procure os **substantivos** do enunciado (`Livro`, `Cliente`, `Produto`) e os **verbos** que pertencem a eles (`emprestar`, `calcularTotal`). Se um substantivo tem dados *e* comportamento próprios, ele merece uma classe.

> 💻 **Código desta aula pronto para rodar:** [`Aluno.java`](exemplos/Aluno.java) + [`Escola.java`](exemplos/Escola.java)

## 🏋️ Exercícios da aula

Na pasta `aula-05/` do seu repositório (cada classe em seu próprio arquivo!):

1. **`Livro.java` + `Estante.java`** — a classe `Livro` tem `titulo`, `autor`, `paginas` e `disponivel`, mais os métodos `emprestar()`, `devolver()` e `exibirFicha()`. No `main` de `Estante`, crie 3 livros, empreste um e exiba as três fichas;
2. **`ContaBancaria.java` + `Banco.java`** — atributos `titular`, `numero` e `saldo`; métodos `depositar(double valor)`, `sacar(double valor)` (que só saca se houver saldo, imprimindo aviso caso contrário) e `exibirExtrato()`. Teste os dois cenários de saque;
3. **`Referencia.java`** — crie um objeto `ContaBancaria`, atribua a uma segunda variável, deposite pela segunda variável e imprima o saldo pela primeira. Explique **num comentário** o resultado. Depois provoque um `NullPointerException` de propósito e copie a mensagem;
4. **`Retangulo.java` + `Geometria.java`** — a classe recebe `base` e `altura` **pelo construtor** e oferece `calcularArea()`, `calcularPerimetro()` e `ehQuadrado()`. Crie um `Retangulo[]` com 3 objetos e imprima a área de todos com um `for-each`;
5. **Desafio 🌶️ `Aluno.java` + `Turma.java`** — refaça o Desafio da Aula 04 (boletim de 3 alunos) usando a classe `Aluno` com construtor, um `Aluno[]` e um laço. No fim do arquivo, escreva num comentário **quantas linhas** cada versão tem e o que ficou mais fácil de mudar.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

## ✅ Entrega

```bash
git add aula-05/
git commit -m "Resolve exercícios da aula 05 (classes e objetos)"
git push
```

---

⬅️ [Aula 04](../../bloco-1-fundamentos-java/aula-04-lacos-arrays-metodos/README.md) | ➡️ [Aula 06 — Encapsulamento](../aula-06-encapsulamento/README.md)
