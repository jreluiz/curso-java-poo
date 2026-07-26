# Aula 04 — Laços, Arrays e Métodos

> 🎯 Objetivos: repetir tarefas com laços, guardar vários valores em arrays e quebrar o programa em métodos com responsabilidade própria.
> 🎬 Slides da aula: [apresentacao-04-lacos-arrays-metodos.pdf](apresentacao/apresentacao-04-lacos-arrays-metodos.pdf)

Esta é a última aula antes da POO. Ao final dela você já escreve programas completos — e vai sentir na pele o problema que a Aula 05 vem resolver.

## 1. `while` e `do-while`

```java
int contador = 1;

while (contador <= 5) {          // testa ANTES de cada repetição
    System.out.println("Repetição " + contador);
    contador++;                  // 😱 esquecer esta linha = laço infinito
}
```

O `do-while` executa **pelo menos uma vez** e só depois testa — perfeito para menus:

```java
Scanner scanner = new Scanner(System.in);
int opcao;

do {
    System.out.println("1 - Cadastrar | 2 - Listar | 0 - Sair");
    opcao = scanner.nextInt();
} while (opcao != 0);
```

> ⚠️ **Laço infinito** = programa travado, cursor piscando para sempre. Interrompa com `Ctrl + C` e procure o que deveria mudar a condição.

## 2. `for`: quando você sabe quantas vezes

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Repetição " + i);
}
//   ↑ início    ↑ condição  ↑ passo
```

Tudo o que o `while` faz em quatro linhas, o `for` faz em uma — por isso ele domina quando existe um contador.

```java
// Tabuada do 7
for (int i = 1; i <= 10; i++) {
    System.out.printf("7 x %d = %d%n", i, 7 * i);
}
```

`break` sai do laço na hora; `continue` pula para a próxima repetição:

```java
for (int i = 1; i <= 10; i++) {
    if (i % 2 != 0) continue;    // ímpar? pula
    if (i > 8) break;            // passou de 8? encerra
    System.out.println(i);       // 2 4 6 8
}
```

## 3. Arrays: muitos valores, um nome

Declarar 30 variáveis para 30 notas é insustentável. O array guarda vários valores do **mesmo tipo**, com **tamanho fixo**:

```java
double[] notas = new double[4];        // 4 posições, todas valendo 0.0
notas[0] = 8.5;                        // índices começam em ZERO
notas[1] = 7.0;
notas[2] = 9.5;
notas[3] = 6.0;

String[] nomes = {"Ana", "Bruno", "Carla"};   // criando já com valores

System.out.println(notas[0]);          // 8.5
System.out.println(nomes.length);      // 3  ← length é PROPRIEDADE (sem parênteses!)
System.out.println(notas[4]);          // 💥 ArrayIndexOutOfBoundsException
```

> ⚠️ Array de tamanho 4 tem índices **0, 1, 2 e 3**. O último índice é sempre `length - 1` — por isso a condição do `for` usa `<` e não `<=`.

Percorrendo:

```java
// for clássico: quando você precisa do índice
for (int i = 0; i < notas.length; i++) {
    System.out.printf("Nota %d: %.1f%n", i + 1, notas[i]);
}

// for-each: quando você só quer os valores (mais limpo)
double soma = 0;
for (double nota : notas) {            // lê-se "para cada nota em notas"
    soma += nota;
}
System.out.printf("Média: %.2f%n", soma / notas.length);
```

> 💡 O array tem **tamanho fixo**: `new double[4]` são 4 posições para sempre. Cadastro que cresce pede `ArrayList` — Aula 09.

## 4. Métodos: dando nome a um pedaço de lógica

Um `main` de 200 linhas é impossível de entender e de testar. Método é um bloco de código **com nome**, que recebe dados e (talvez) devolve um resultado:

```java
public class Media {

    // ┌ visível de fora
    // │      ┌ pertence à classe (não a um objeto) — por enquanto, sempre static
    // │      │      ┌ tipo do que devolve (void = não devolve nada)
    // │      │      │      ┌ nome (verbo, camelCase)     ┌ parâmetros
    public static double calcularMedia(double[] valores) {
        double soma = 0;
        for (double v : valores) {
            soma += v;
        }
        return soma / valores.length;      // devolve o resultado
    }

    public static void imprimirSituacao(double media) {
        System.out.println(media >= 7 ? "Aprovado" : "Reprovado");
    }

    public static void main(String[] args) {
        double[] notas = {8.5, 7.0, 9.5, 6.0};

        double media = calcularMedia(notas);      // chama e guarda o retorno
        System.out.printf("Média: %.2f%n", media);
        imprimirSituacao(media);
    }
}
```

Por que vale a pena:

1. **Nome = documentação.** `calcularMedia(notas)` se explica sozinho;
2. **Reuso.** Escreveu uma vez, chama quantas quiser;
3. **Conserto local.** Bug na média? Existe um único lugar para olhar.

> ⚠️ Método com retorno **precisa** de `return` em todos os caminhos, senão: `error: missing return statement`.

### Sobrecarga: mesmo nome, assinaturas diferentes

```java
public static double somar(double a, double b) {
    return a + b;
}

public static double somar(double a, double b, double c) {
    return a + b + c;
}

somar(2, 3);        // usa a primeira
somar(2, 3, 4);     // usa a segunda
```

O compilador escolhe pelo **número e tipo** dos argumentos. É por isso que `System.out.println` aceita texto, número ou boolean: são várias versões sobrecarregadas.

## 5. Escopo: onde cada variável existe

Uma variável vive dentro das chaves em que foi declarada — e morre ao fechar:

```java
public static void exemplo() {
    int fora = 10;

    for (int i = 0; i < 3; i++) {
        int dentro = i * 2;
        System.out.println(fora + dentro);   // ✅ enxerga as duas
    }

    System.out.println(dentro);   // ❌ cannot find symbol — morreu com o for
    System.out.println(i);        // ❌ idem
}
```

Cada método tem seu próprio espaço: `nome` dentro de `calcularMedia` não tem relação alguma com `nome` dentro do `main`. Métodos conversam por **parâmetros e retorno**, não por variáveis compartilhadas.

## 6. O limite do que fizemos até aqui

Imagine cadastrar 3 alunos com nome, matrícula e três notas. Com o que você sabe:

```java
String[] nomes = new String[3];
String[] matriculas = new String[3];
double[][] notas = new double[3][3];      // 😖
```

Três arrays paralelos que **precisam ficar sincronizados na mão**: se você ordenar os nomes, as notas ficam trocadas; se remover um aluno, tem que lembrar de remover em três lugares. E a média de um aluno é um método solto, longe dos dados dele.

Falta uma forma de dizer: *"um aluno **é** um nome, uma matrícula e três notas, e **sabe** calcular a própria média"*. É exatamente isso que a próxima aula traz. 👉

> 💻 **Código desta aula pronto para rodar:** [`LacosEArrays.java`](exemplos/LacosEArrays.java) e [`Media.java`](exemplos/Media.java)

## 🏋️ Exercícios da aula

Na pasta `aula-04/` do seu repositório:

1. **`Tabuada.java`** — leia um número e imprima sua tabuada de 1 a 10 formatada com `printf`; depois imprima as tabuadas de 1 a 10 usando **`for` dentro de `for`**;
2. **`Estatisticas.java`** — dado `double[] notas = {8.5, 6.0, 9.5, 7.0, 4.5};`, calcule e imprima maior, menor, soma e média — **sem** usar biblioteca pronta, e cada cálculo em seu próprio método `static`;
3. **`Menu.java`** — menu em `do-while` com as opções *1 - Somar*, *2 - Ver histórico de resultados*, *0 - Sair*; guarde os resultados num array de 10 posições e não deixe o programa quebrar ao passar do limite;
4. **`Refatorar.java`** — copie o código abaixo (que faz tudo dentro do `main`) e **extraia pelo menos 3 métodos** com nomes claros; o `main` final deve ter no máximo 6 linhas:
   ```java
   public static void main(String[] args) {
       int[] valores = {4, 7, 2, 9, 3};
       int soma = 0;
       for (int v : valores) soma += v;
       int maior = valores[0];
       for (int v : valores) if (v > maior) maior = v;
       int pares = 0;
       for (int v : valores) if (v % 2 == 0) pares++;
       System.out.println(soma + " " + maior + " " + pares);
   }
   ```
5. **Desafio 🌶️ `Boletim.java`** — leia o nome e 3 notas de **3 alunos** usando arrays paralelos (`String[] nomes`, `double[][] notas`), calcule a média de cada um e imprima um boletim alinhado com `printf`. Ao terminar, escreva num comentário **o que foi mais chato** nessa abordagem — na próxima aula você resolve isso com uma classe.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

## ✅ Entrega

```bash
git add aula-04/
git commit -m "Resolve exercícios da aula 04 (laços, arrays e métodos)"
git push
```

---

⬅️ [Aula 03](../aula-03-operadores-condicionais/README.md) | ➡️ [Aula 05 — Classes e Objetos](../../bloco-2-pilares-poo/aula-05-classes-objetos/README.md)
