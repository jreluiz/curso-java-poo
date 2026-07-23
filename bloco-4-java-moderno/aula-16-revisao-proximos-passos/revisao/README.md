# Aula 16 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 16 — Revisão e Próximos Passos](../README.md) e sobre o curso como um todo. Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A16-01

No exemplo dos quatro pilares, o que a linha `public abstract double calcularSalario();` representa?

- **a)** **Abstração**: a superclasse define **o que** todo funcionário faz, sem dizer **como**;
- **b)** encapsulamento, porque o método é declarado sem corpo;
- **c)** herança, porque só subclasses podem declarar métodos abstratos;
- **d)** sobrecarga, porque cada subclasse terá sua própria versão.

↩︎ *Aula 16, seção 2 — Os quatro pilares num exemplo só*

---

### Q-A16-02

E o `private double salarioBase` com validação dentro do setter, qual pilar exemplifica?

- **a)** Abstração;
- **b)** encapsulamento;
- **c)** herança;
- **d)** polimorfismo.

↩︎ *Aula 16, seção 2 — Os quatro pilares num exemplo só*

---

### Q-A16-03

Um `List<Funcionario>` percorrido por um único laço que chama `calcularSalario()` e obtém valores calculados de formas diferentes demonstra:

- **a)** Encapsulamento;
- **b)** sobrecarga de métodos;
- **c)** **polimorfismo**;
- **d)** composição.

↩︎ *Aula 16, seção 2 — Os quatro pilares num exemplo só*

---

### Q-A16-04

O que `assertThrows(IllegalArgumentException.class, () -> new Gerente("Léo", 500))` verifica?

- **a)** Que o objeto foi criado com sucesso;
- **b)** que o valor `500` é menor que o salário mínimo;
- **c)** que o construtor devolve `null` nesse caso;
- **d)** que aquele trecho **lança** a exceção esperada — se não lançar, o teste falha.

↩︎ *Aula 16, seção 3 — Degustação: testes automatizados com JUnit*

---

### Q-A16-05

Por que testes automatizados mudam a forma de trabalhar?

- **a)** Porque substituem a necessidade de tratar exceções;
- **b)** porque permitem **refatorar sem medo**: você roda a suíte e descobre na hora se quebrou algo;
- **c)** porque tornam o programa mais rápido em produção;
- **d)** porque dispensam a revisão de código por outra pessoa.

↩︎ *Aula 16, seção 3 — Degustação: testes automatizados com JUnit*

---

### Q-A16-06

Qual é o caminho indicado para substituir os arquivos CSV por um armazenamento profissional?

- **a)** JavaFX;
- **b)** JUnit e Mockito;
- **c)** **SQL + JDBC**, e depois um banco de dados de verdade;
- **d)** Android com Kotlin.

↩︎ *Aula 16, seção 4 — Para onde ir agora*

---

### Q-A16-07

Qual conselho de estudo é coerente com o fechamento do curso?

- **a)** Estudar as sete tecnologias da tabela em paralelo, para ter uma visão ampla;
- **b)** só voltar a programar quando surgir um projeto profissional;
- **c)** evitar ler código de outras pessoas até dominar o próprio estilo;
- **d)** **terminar e publicar** um projeto vale mais que manter cinco pela metade.

↩︎ *Aula 16, seção 5 — Como continuar estudando*

---

### Q-A16-08

No mapa do curso, o `equals`/`hashCode` aprendido no Bloco 2 volta a ser essencial em qual contexto?

- **a)** Nas **coleções** do Bloco 3, que dependem deles para localizar objetos em `List`, `Map` e `Set`;
- **b)** na leitura de arquivos do Bloco 4, ao converter linhas em objetos;
- **c)** no tratamento de exceções, ao comparar mensagens de erro;
- **d)** na compilação, para verificar tipos incompatíveis.

↩︎ *Aula 16, seção 1 — O mapa do curso em uma tela*

---

⬅️ [Voltar à Aula 16](../README.md) | 🏠 [Início do curso](../../../README.md)
