# Aula 01 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 01 — Introdução ao Java e à JVM](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A01-01

Qual é a diferença entre os comandos `javac` e `java`?

- **a)** `javac` executa o programa e `java` compila o código-fonte;
- **b)** são sinônimos: qualquer um dos dois compila e executa;
- **c)** `javac` compila o `.java` em bytecode (`.class`) e `java` executa esse bytecode na JVM;
- **d)** `javac` apenas verifica a sintaxe sem gerar arquivo algum, e `java` compila e executa de uma vez.

↩︎ *Aula 01, seção 2 — JDK, JVM e o ciclo compilar → executar*

---

### Q-A01-02

O que é a JVM?

- **a)** O editor de código usado para escrever programas Java;
- **b)** o compilador que transforma código-fonte em bytecode;
- **c)** o pacote de instalação que reúne compilador, bibliotecas e ferramentas;
- **d)** a máquina virtual que executa o bytecode, com uma versão para cada sistema operacional.

↩︎ *Aula 01, seção 2 — JDK, JVM e o ciclo compilar → executar*

---

### Q-A01-03

Depois de compilar com sucesso `javac Ola.java`, o aluno digita `java Ola.class`. O que acontece?

- **a)** O programa executa normalmente, porque `.class` é o arquivo compilado;
- **b)** falha: o comando `java` recebe o **nome da classe**, sem a extensão — o certo é `java Ola`;
- **c)** falha, porque é preciso compilar de novo antes de cada execução;
- **d)** o programa executa, mas imprime um aviso de extensão desnecessária.

↩︎ *Aula 01, seção 3 — Primeiro programa*

---

### Q-A01-04

Um arquivo chamado `Pessoa.java` contém `public class Aluno { ... }`. O que acontece ao compilar?

- **a)** Não compila: `class Aluno is public, should be declared in a file named Aluno.java`;
- **b)** compila normalmente — o nome do arquivo é irrelevante para o compilador;
- **c)** compila e gera `Pessoa.class`, ignorando o nome da classe;
- **d)** compila com um aviso, mas o programa só falha se tentar ser executado.

↩︎ *Aula 01, seção 3 — Primeiro programa*

---

### Q-A01-05

O que este trecho imprime?

```java
System.out.print("A");
System.out.println("B");
System.out.print("C");
```

- **a)** `AB` na primeira linha e `C` na segunda;
- **b)** `A`, `B` e `C`, cada um em uma linha;
- **c)** `ABC` numa única linha;
- **d)** `A` na primeira linha e `BC` na segunda.

↩︎ *Aula 01, seção 4 — Imprimir, comentar e o ponto e vírgula*

---

### Q-A01-06

O que `System.out.println("Ele disse \"oi\"");` imprime?

- **a)** `Ele disse \"oi\"`
- **b)** Nada: falha com erro de compilação por causa das aspas internas;
- **c)** `Ele disse "oi"`
- **d)** `Ele disse oi`, com as aspas removidas e um espaço a mais.

↩︎ *Aula 01, seção 4 — Imprimir, comentar e o ponto e vírgula*

---

### Q-A01-07

Você esqueceu um `;` no fim de uma linha. O que é típico acontecer?

- **a)** O programa compila e só falha quando aquela linha for executada;
- **b)** o compilador lança `NullPointerException` apontando a linha;
- **c)** o compilador aceita e assume o ponto e vírgula automaticamente;
- **d)** o programa nem compila: aparece `error: ';' expected`, muitas vezes apontando a **linha seguinte**.

↩︎ *Aula 01, seção 4 — Imprimir, comentar e o ponto e vírgula*

---

### Q-A01-08

Por que o método `main` precisa ser `static`?

- **a)** Porque ele devolve um valor de saída para o sistema operacional;
- **b)** porque a JVM precisa chamá-lo **sem criar nenhum objeto** antes;
- **c)** porque apenas métodos `static` podem receber um `String[]` como parâmetro;
- **d)** porque `static` faz o método executar mais rápido que os demais.

↩︎ *Aula 01, seção 3 — Primeiro programa*

---

⬅️ [Voltar à Aula 01](../README.md) | ➡️ [Revisão da Aula 02](../../aula-02-variaveis-tipos/revisao/README.md)
