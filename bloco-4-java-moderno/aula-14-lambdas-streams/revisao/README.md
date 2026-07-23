# Aula 14 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 14 — Lambdas e Streams](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A14-01

O que é uma **interface funcional**?

- **a)** Uma interface que só declara constantes;
- **b)** uma interface com **um único método abstrato**, que por isso pode ser implementada por uma lambda;
- **c)** uma interface implementada por mais de uma classe;
- **d)** uma classe abstrata sem atributos.

↩︎ *Aula 14, seção 1 — Interface funcional e lambda*

---

### Q-A14-02

O que a expressão `x -> x * 2` representa?

- **a)** Uma comparação entre `x` e o dobro de `x`;
- **b)** a declaração de uma variável `x` com valor `x * 2`;
- **c)** uma **lambda** que recebe `x` como parâmetro e devolve o dobro dele;
- **d)** um laço que multiplica `x` por 2 repetidamente.

↩︎ *Aula 14, seção 1 — Interface funcional e lambda*

---

### Q-A14-03

A que corresponde `lista.forEach(System.out::println);`?

- **a)** A `lista.forEach(System.out.println());`
- **b)** a um laço que imprime apenas o primeiro elemento;
- **c)** a `System.out.println(lista);`
- **d)** a `lista.forEach(item -> System.out.println(item));`

↩︎ *Aula 14, seção 1 — Interface funcional e lambda*

---

### Q-A14-04

O que a operação `filter` faz numa stream?

- **a)** Mantém apenas os elementos para os quais a condição é verdadeira;
- **b)** transforma cada elemento em outro valor;
- **c)** ordena os elementos segundo o critério informado;
- **d)** remove da coleção original os elementos que não passam no teste.

↩︎ *Aula 14, seção 3 — Streams: descrever o resultado, não o passo a passo*

---

### Q-A14-05

O que este trecho imprime?

```java
List<String> nomes = List.of("Ana", "Léo", "Duda");
List<String> resultado = nomes.stream()
        .filter(n -> n.length() > 3)
        .toList();
System.out.println(resultado);
```

- **a)** `[Duda]`
- **b)** `[Ana, Léo]`
- **c)** `[Ana, Léo, Duda]`
- **d)** `[]`

↩︎ *Aula 14, seção 3 — Streams: descrever o resultado, não o passo a passo*

---

### Q-A14-06

O que acontece com a coleção original depois de `acervo.stream().filter(...).toList()`?

- **a)** Ela fica apenas com os elementos filtrados;
- **b)** ela é esvaziada e precisa ser recarregada;
- **c)** ela é ordenada como efeito colateral do filtro;
- **d)** ela **não muda**: a stream produz um resultado novo, e aquela stream não pode ser reutilizada.

↩︎ *Aula 14, seção 3 — Streams: descrever o resultado, não o passo a passo*

---

### Q-A14-07

Para que serve `Optional`?

- **a)** Para tornar opcional a implementação de um método de interface;
- **b)** para permitir que uma variável guarde tipos diferentes;
- **c)** para representar um valor que **pode ou não existir**, obrigando quem recebe a considerar a ausência em vez de esbarrar num `null`;
- **d)** para converter automaticamente `null` em string vazia.

↩︎ *Aula 14, seção 5 — `Optional`: dizer "pode não haver" sem `null`*

---

### Q-A14-08

O que `Collectors.groupingBy(Livro::getCategoria, Collectors.counting())` devolve?

- **a)** Uma `List` com as categorias distintas, em ordem alfabética;
- **b)** um `Map` em que cada categoria é a chave e o valor é a **quantidade** de livros dela;
- **c)** o total de livros do acervo, somando todas as categorias;
- **d)** a categoria com maior número de livros.

↩︎ *Aula 14, seção 6 — Agrupando com `Collectors`*

---

⬅️ [Voltar à Aula 14](../README.md) | ➡️ [Revisão da Aula 15](../../aula-15-projeto-final/revisao/README.md)
