# Aula 10 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 10 — Exceções](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A10-01

O que acontece quando uma exceção é lançada e **ninguém** a trata?

- **a)** O programa continua normalmente, ignorando a instrução que falhou;
- **b)** a execução é **encerrada** e o *stack trace* é impresso no terminal;
- **c)** o compilador se recusa a gerar o `.class`;
- **d)** o método devolve `null` e a execução segue.

↩︎ *Aula 10, seção 1 — O programa que quebra na primeira digitação errada*

---

### Q-A10-02

Quando o bloco `finally` executa?

- **a)** **Sempre** — tendo ocorrido exceção ou não;
- **b)** apenas quando nenhuma exceção é lançada;
- **c)** apenas quando alguma exceção é lançada;
- **d)** apenas quando o `catch` não consegue tratar a exceção.

↩︎ *Aula 10, seção 2 — `try` / `catch` / `finally`*

---

### Q-A10-03

O que acontece se `catch (Exception e)` for escrito **antes** de `catch (InputMismatchException e)`?

- **a)** Funciona normalmente: o Java escolhe sempre o `catch` mais específico;
- **b)** os dois blocos são executados em sequência;
- **c)** não compila: o `catch` mais genérico torna o específico inalcançável e deve vir por último;
- **d)** compila, mas nenhum dos dois é executado.

↩︎ *Aula 10, seção 2 — `try` / `catch` / `finally`*

---

### Q-A10-04

Qual é a diferença entre exceções *checked* e *unchecked*?

- **a)** *Checked* ocorrem em execução e *unchecked* em compilação;
- **b)** *checked* podem ser tratadas e *unchecked* sempre encerram o programa;
- **c)** *checked* são as do pacote `java.util` e *unchecked* as do `java.io`;
- **d)** o compilador **obriga** a tratar ou declarar as *checked*; as *unchecked* (`RuntimeException` e filhas) ficam a critério do programador.

↩︎ *Aula 10, seção 3 — Checked × unchecked*

---

### Q-A10-05

Qual exceção `Integer.parseInt("abc")` lança?

- **a)** `IllegalArgumentException`
- **b)** `InputMismatchException`
- **c)** `NumberFormatException`
- **d)** `ClassCastException`

↩︎ *Aula 10, seção 2 — `try` / `catch` / `finally`*

---

### Q-A10-06

O que muda ao trocar um `System.out.println("Saldo insuficiente")` por um `throw` dentro do método `sacar()`?

- **a)** Nada: as duas formas apenas avisam o usuário;
- **b)** o método é **interrompido** e a decisão sobre o que fazer passa para quem o chamou;
- **c)** o programa é encerrado obrigatoriamente;
- **d)** o saldo é revertido automaticamente pela JVM.

↩︎ *Aula 10, seção 4 — `throw`: lançando as suas*

---

### Q-A10-07

Por que a maioria das exceções personalizadas de regra de negócio estende `RuntimeException`?

- **a)** Porque só `RuntimeException` aceita mensagem personalizada;
- **b)** porque exceções que estendem `Exception` não podem ser capturadas;
- **c)** porque `RuntimeException` é executada mais rapidamente pela JVM;
- **d)** porque assim ela é *unchecked*: o compilador não obriga cada chamada a tratá-la, o que se adequa a violações de regra de negócio.

↩︎ *Aula 10, seção 5 — Exceções personalizadas*

---

### Q-A10-08

Qual é a vantagem do `try-with-resources`?

- **a)** Os recursos declarados nos parênteses são **fechados automaticamente**, mesmo se ocorrer uma exceção;
- **b)** ele impede que qualquer exceção seja lançada dentro do bloco;
- **c)** ele dispensa o uso de `catch` para exceções *checked*;
- **d)** ele reexecuta o bloco automaticamente em caso de falha.

↩︎ *Aula 10, seção 6 — `try-with-resources`*

---

⬅️ [Voltar à Aula 10](../README.md) | ➡️ [Revisão da Aula 11](../../aula-11-organizacao-pacotes/revisao/README.md)
