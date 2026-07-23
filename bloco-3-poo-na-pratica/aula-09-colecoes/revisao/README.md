# Aula 09 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 09 — Coleções](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A09-01

Qual é a principal vantagem de `ArrayList` sobre um array?

- **a)** Permite guardar objetos de tipos diferentes na mesma estrutura;
- **b)** é a única estrutura que pode ser percorrida com `for-each`;
- **c)** **cresce e encolhe sozinha**, sem tamanho fixo e sem deixar posições vazias após remoções;
- **d)** dispensa a sobrescrita de `equals` para buscas.

↩︎ *Aula 09, seção 1 — Onde o array não dá conta*

---

### Q-A09-02

O que acontece ao declarar `List<int> numeros = new ArrayList<>();`?

- **a)** Não compila: generics não aceitam tipos primitivos — o correto é `List<Integer>`;
- **b)** compila normalmente e aceita apenas inteiros;
- **c)** compila, mas a lista aceita qualquer tipo;
- **d)** compila com aviso e converte os valores para `double`.

↩︎ *Aula 09, seção 2 — `List` e `ArrayList`*

---

### Q-A09-03

Como se obtém a quantidade de elementos de uma `List` chamada `nomes`?

- **a)** `nomes.length`
- **b)** `nomes.length()`
- **c)** `nomes.count()`
- **d)** `nomes.size()`

↩︎ *Aula 09, seção 3 — As operações do dia a dia*

---

### Q-A09-04

O que costuma acontecer ao remover elementos de uma lista **dentro** de um `for-each` sobre ela?

- **a)** Nada de especial: é a forma recomendada de remover;
- **b)** o programa lança `ConcurrentModificationException`;
- **c)** o compilador recusa o código;
- **d)** a lista é esvaziada por completo.

↩︎ *Aula 09, seção 3 — As operações do dia a dia*

---

### Q-A09-05

`turma` contém um `Aluno` de matrícula `"1001"`. Criando **outro** objeto com os mesmos dados, `turma.contains(procurado)` devolve `false`. Por quê?

- **a)** Porque `contains` usa `equals`, e sem sobrescrita ele compara **referências**, não conteúdo;
- **b)** porque `contains` só funciona com `String` e tipos primitivos;
- **c)** porque a lista precisa estar ordenada para que `contains` funcione;
- **d)** porque `contains` compara apenas o primeiro atributo declarado na classe.

↩︎ *Aula 09, seção 4 — `contains` e `remove` dependem do seu `equals`*

---

### Q-A09-06

O que acontece ao chamar `map.put("1001", novoAluno)` quando a chave `"1001"` já existe?

- **a)** Lança `IllegalArgumentException`;
- **b)** a inserção é ignorada e o valor antigo permanece;
- **c)** os dois valores passam a coexistir sob a mesma chave;
- **d)** o valor antigo é **substituído** pelo novo.

↩︎ *Aula 09, seção 5 — `Map`: quando existe uma chave*

---

### Q-A09-07

O que `map.get("9999")` devolve quando essa chave não existe?

- **a)** Uma string vazia;
- **b)** `null`;
- **c)** lança `NoSuchElementException`;
- **d)** o primeiro valor do mapa.

↩︎ *Aula 09, seção 5 — `Map`: quando existe uma chave*

---

### Q-A09-08

Qual é a característica que define um `Set`?

- **a)** Mantém os elementos sempre em ordem alfabética;
- **b)** permite acessar elementos por índice, como um array;
- **c)** **não admite elementos repetidos**;
- **d)** guarda pares de chave e valor.

↩︎ *Aula 09, seção 6 — `Set`: coleção sem repetição*

---

⬅️ [Voltar à Aula 09](../README.md) | ➡️ [Revisão da Aula 10](../../aula-10-excecoes/revisao/README.md)
