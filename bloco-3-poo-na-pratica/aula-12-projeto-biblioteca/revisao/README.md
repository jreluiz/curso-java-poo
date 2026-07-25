# Aula 12 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 12 — Projeto Guiado: Sistema de Biblioteca](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A12-01

Por que `ItemAcervo` foi declarada como classe **abstrata**?

- **a)** Porque "item genérico" não existe na prateleira: a classe existe para ser herdada e para obrigar cada tipo a implementar sua `descricao()`;
- **b)** porque classes com atributos `protected` precisam ser abstratas;
- **c)** para que ela possa ser usada dentro de uma `List`;
- **d)** porque toda superclasse em Java precisa ser abstrata.

↩︎ *Aula 12, Etapa 1 — O modelo básico*

---

### Q-A12-02

Por que `listarAcervo()` devolve `new ArrayList<>(acervo)` em vez da própria lista?

- **a)** Porque devolver a lista original causaria `ConcurrentModificationException`;
- **b)** porque o tipo de retorno declarado exige uma instância nova;
- **c)** para que ninguém de fora possa alterar a coleção interna do serviço (um `clear()` apagaria o acervo);
- **d)** porque uma cópia é percorrida mais rapidamente pelo `for-each`.

↩︎ *Aula 12, Etapa 4 — O `BibliotecaService`*

---

### Q-A12-03

Em qual camada os blocos `catch` do sistema devem ficar?

- **a)** Na `model`, junto das classes que representam o domínio;
- **b)** na `app`, que é a única camada que conversa com o usuário;
- **c)** na `service`, para que o erro nunca chegue ao menu;
- **d)** distribuídos igualmente entre as três camadas.

↩︎ *Aula 12, Etapa 6 — O menu*

---

### Q-A12-04

Por que sobrescrever `equals` e `hashCode` em `ItemAcervo` (por `codigo`) e em `Usuario` (por `id`)?

- **a)** Porque sem eles as classes não podem ser abstratas;
- **b)** porque o `toString()` depende dos dois para funcionar;
- **c)** porque o compilador exige isso de toda classe dentro de um pacote `model`;
- **d)** porque `contains` e `remove` das listas usam `equals` — sem sobrescrita, não localizam o objeto.

↩︎ *Aula 12, Etapa 2 — Encapsulamento e regras no lugar certo*

---

### Q-A12-05

`ObraDeReferencia` sobrescreve `emprestar()` lançando `UnsupportedOperationException`. Qual é a consequência disso para o resto do sistema?

- **a)** O `BibliotecaService` precisa de um `if` novo para o tipo `ObraDeReferencia`;
- **b)** o restante do sistema continua funcionando sem conhecer a nova classe — basta o `app` tratar a exceção;
- **c)** todas as demais subclasses precisam sobrescrever o mesmo método;
- **d)** a interface `Emprestavel` deixa de ser necessária.

↩︎ *Aula 12, Etapa 3 — A interface `Emprestavel`*

---

### Q-A12-06

Por que o `BibliotecaService` **não** imprime nada?

- **a)** Para que a interface com o usuário possa ser trocada (menu de terminal, tela gráfica, web) sem alterar nenhuma regra de negócio;
- **b)** porque `System.out.println` não funciona fora da classe que contém o `main`;
- **c)** porque imprimir dentro de um `service` causa erro de compilação;
- **d)** porque a camada `model` já se encarrega de exibir os dados.

↩︎ *Aula 12, Etapa 4 — O `BibliotecaService`*

---

### Q-A12-07

Um usuário do tipo `ALUNO` (limite de 3 itens) tenta pegar o quarto. O que o método `emprestar` faz?

- **a)** Empresta assim mesmo e registra um aviso no console;
- **b)** devolve `null` para sinalizar a recusa;
- **c)** devolve o empréstimo mais antigo automaticamente;
- **d)** lança `LimiteExcedidoException`, e a camada `app` traduz isso em mensagem amigável.

↩︎ *Aula 12, Etapa 5 — Empréstimo, devolução e exceções*

---

### Q-A12-08

Por que o projeto foi construído em etapas com um commit ao fim de cada uma?

- **a)** Porque o Git recusa commits com muitos arquivos alterados;
- **b)** porque cada etapa precisa ser revisada por um colega antes da seguinte;
- **c)** porque cada etapa termina com o sistema rodando, o que facilita localizar o que quebrou quando algo dá errado;
- **d)** porque commits pequenos ocupam menos espaço no repositório.

↩︎ *Aula 12, Etapa 1 — O modelo básico*

---

⬅️ [Voltar à Aula 12](../README.md) | ➡️ [Revisão da Aula 13](../../../bloco-4-java-moderno/aula-13-arquivos-persistencia/revisao/README.md)
