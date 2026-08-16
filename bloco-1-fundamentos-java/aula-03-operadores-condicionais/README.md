# Aula 03 — Operadores e Condicionais

> 🎯 Objetivos: combinar valores com operadores, tomar decisões com `if` e `switch`, e comparar textos do jeito certo.
> 🎬 Slides da aula: [apresentacao-03-operadores-condicionais.pdf](apresentacao/apresentacao-03-operadores-condicionais.pdf)

## 1. Operadores aritméticos e precedência

```java
int a = 7, b = 2;

System.out.println(a + b);    // 9
System.out.println(a - b);    // 5
System.out.println(a * b);    // 14
System.out.println(a / b);    // 3   ← divisão inteira (Aula 02)
System.out.println(a % b);    // 1   ← resto: essencial para "é par?" e "é múltiplo?"
```

A precedência é a da matemática: `*`, `/` e `%` antes de `+` e `-`; parênteses mandam em tudo.

```java
System.out.println(2 + 3 * 4);      // 14
System.out.println((2 + 3) * 4);    // 20
```

Atalhos que você vai usar o curso inteiro:

```java
int contador = 10;

contador++;          // contador = contador + 1  → 11
contador--;          // volta para 10
contador += 5;       // contador = contador + 5  → 15
contador *= 2;       // → 30
```

## 2. Operadores relacionais e lógicos

Comparações sempre resultam em `boolean`:

```java
int nota = 7;

System.out.println(nota > 6);     // true
System.out.println(nota >= 7);    // true
System.out.println(nota == 10);   // false  ← IGUALDADE é ==, com dois sinais
System.out.println(nota != 10);   // true   ← diferente
```

> ⚠️ `=` **atribui**, `==` **compara**. Em Java, escrever `if (nota = 10)` nem compila (o `if` exige um `boolean`) — o compilador salva sua pele aqui.

Para combinar condições:

```java
boolean temIdade = true;
boolean temDocumento = false;

System.out.println(temIdade && temDocumento);   // false — E: exige as DUAS verdadeiras
System.out.println(temIdade || temDocumento);   // true  — OU: basta UMA verdadeira
System.out.println(!temIdade);                  // false — NÃO: inverte
```

## 3. `if`, `else if`, `else`

```java
int nota = 7;

if (nota >= 7) {
    System.out.println("Aprovado");
} else if (nota >= 5) {
    System.out.println("Recuperação");
} else {
    System.out.println("Reprovado");
}
```

Regras de ouro:

- A condição do `if` **precisa** ser `boolean` — não existe "0 é falso" em Java;
- As condições são testadas **em ordem**, e o primeiro `if` verdadeiro encerra a cadeia. Por isso `nota >= 5` só é avaliado se `nota >= 7` já falhou;
- **Sempre use chaves**, mesmo com uma linha só. Sem elas, este bug clássico aparece:

```java
if (aprovado)
    System.out.println("Parabéns!");
    System.out.println("Você passou!");   // 😱 imprime SEMPRE — não está no if!
```

Condições podem aninhar, mas cuidado com o excesso — três níveis de `if` dentro de `if` já pedem outra solução:

```java
if (idade >= 18 && temDocumento) {
    System.out.println("Pode entrar");
}
```

## 4. `switch`: quando é o mesmo valor comparado a várias opções

```java
int diaDaSemana = 3;

switch (diaDaSemana) {
    case 1:
        System.out.println("Domingo");
        break;                            // sem o break, "vaza" para o próximo case!
    case 2:
        System.out.println("Segunda");
        break;
    case 3:
        System.out.println("Terça");
        break;
    default:
        System.out.println("Dia inválido");
}
```

> ⚠️ **Esquecer o `break` é o bug clássico do `switch`.** Sem ele, a execução continua nos casos seguintes — o famoso *fall-through*.

Desde o Java 14 existe a forma moderna, com `->`, que **não precisa de `break`** e ainda pode devolver um valor:

```java
String nomeDoDia = switch (diaDaSemana) {
    case 1 -> "Domingo";
    case 2 -> "Segunda";
    case 3 -> "Terça";
    default -> "Dia inválido";
};

System.out.println(nomeDoDia);   // Terça
```

Use `switch` quando testar **um mesmo valor** contra várias opções fixas; use `if/else if` quando as condições forem diferentes entre si (`nota >= 7`, `idade < 18`...).

## 5. `==` vs `.equals()`: a armadilha das Strings

Esta é a pegadinha mais importante do bloco:

```java
String a = "Java";
String b = "Java";
System.out.println(a == b);        // true  ← funcionou... por acidente!

String c = new String("Java");
System.out.println(a == c);        // false 😱 mesmo texto, resposta falsa
System.out.println(a.equals(c));   // true  ✅ compara o CONTEÚDO
```

Por quê? Porque `String` é **objeto**: `==` pergunta *"são o mesmo objeto na memória?"*, e `.equals()` pergunta *"têm o mesmo conteúdo?"*. Literais idênticos escritos no código são otimizados para o mesmo objeto — mas texto vindo do `Scanner`, de um arquivo ou de uma concatenação **não é**.

```java
Scanner scanner = new Scanner(System.in);
String senha = scanner.nextLine();

if (senha == "1234") { ... }             // ❌ nunca vai ser true
if (senha.equals("1234")) { ... }        // ✅
if ("1234".equals(senha)) { ... }        // ✅✅ ainda evita erro se senha for null
if (senha.equalsIgnoreCase("Sim")) { }   // ignora maiúsculas/minúsculas
```

**Regra do curso:** `==` só para primitivos (`int`, `double`, `boolean`, `char`). Para objetos, **sempre** `.equals()`.

## 6. Operador ternário

Um `if/else` que devolve valor, em uma linha:

```java
int nota = 7;
String situacao = (nota >= 7) ? "Aprovado" : "Reprovado";
//                 condição      ? se true  : se false

System.out.println(situacao);   // Aprovado
```

Ótimo para escolhas curtas; péssimo para lógica comprida (não aninhe ternários dentro de ternários).

> 💻 **Código desta aula pronto para rodar:** [`Condicionais.java`](exemplos/Condicionais.java) e [`IgualdadeStrings.java`](exemplos/IgualdadeStrings.java)

## 🏋️ Exercícios da aula

Na pasta `aula-03/` do seu repositório:

1. **`FaixaEtaria.java`** — leia a idade do teclado e classifique: criança (0–12), adolescente (13–17), adulto (18–59), idoso (60+). Valide idade negativa;
2. **`Calculadora.java`** — leia dois números e um operador (`+`, `-`, `*`, `/`) e use **`switch` moderno** para calcular. Trate a divisão por zero com uma mensagem em vez de deixar quebrar;
3. **`Igualdade.java`** — leia uma palavra do teclado e compare com `"java"` de três formas: `==`, `.equals()` e `.equalsIgnoreCase()`. Imprima os três resultados e explique **num comentário** por que o primeiro deu o que deu;
4. **`Bissexto.java`** — leia um ano e diga se é bissexto: divisível por 4, **exceto** se divisível por 100, **a não ser** que seja divisível por 400. Monte a expressão com `&&`, `||` e `%` (teste com 2024 ✅, 1900 ❌, 2000 ✅);
5. **Desafio 🌶️ `Ingresso.java`** — leia idade, se é estudante (`s`/`n`) e o dia da semana; calcule o preço a partir de `final double INTEIRA = 40.0`: meia para menores de 18, idosos e estudantes; terça-feira tem 20% de desconto adicional sobre o valor já calculado. Imprima com `printf` e uma linha explicando quais descontos foram aplicados.

### 📤 Entrega

Estes exercícios são feitos em sala e vão para o **seu repositório** `exercicios-java-poo`:

```bash
cd ..                 # da pasta da aula para a raiz do repositório
git add aula-03/
git commit -m "Resolve exercícios da aula 03"
git push
```

Confira no navegador que a pasta apareceu em `github.com/SEU-USUARIO/exercicios-java-poo`.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

---

⬅️ [Aula 02](../aula-02-variaveis-tipos/README.md) | ➡️ [Aula 04 — Laços, Arrays e Métodos](../aula-04-lacos-arrays-metodos/README.md)
