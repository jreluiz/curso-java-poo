# Aula 15 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 15 — Projeto Final](../README.md) e as decisões de modelagem do curso. Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A15-01

Qual é o erro nº 1 na escolha do tema do projeto?

- **a)** Escolher um tema que já foi feito por outro aluno;
- **b)** escolher um tema com poucas classes;
- **c)** escolher um tema sem interface gráfica;
- **d)** escolher **grande demais** — um escopo que não tem como ser terminado.

↩︎ *Aula 15, seção 1 — Escolhendo o tema (e o escopo)*

---

### Q-A15-02

No percurso do requisito ao diagrama, o que fazer com os substantivos e os verbos do enunciado?

- **a)** Substantivos são candidatos a **classe**; verbos são candidatos a **método**;
- **b)** substantivos viram métodos; verbos viram atributos;
- **c)** substantivos viram pacotes; verbos viram classes;
- **d)** ambos viram atributos, e os métodos surgem depois, na implementação.

↩︎ *Aula 15, seção 2 — Do requisito ao diagrama*

---

### Q-A15-03

Como decidir entre herança e composição ao relacionar duas classes?

- **a)** Herança quando as classes estão no mesmo pacote; composição quando estão em pacotes diferentes;
- **b)** testando a frase: **"é um"** indica herança; **"tem um"** indica um atributo (composição);
- **c)** herança sempre que houver atributos em comum, sem exceção;
- **d)** composição só quando a hierarquia passar de três níveis.

↩︎ *Aula 15, seção 2 — Do requisito ao diagrama*

---

### Q-A15-04

Uma capacidade compartilhada por classes **sem parentesco** entre si (emprestável, pagável, exportável) deve virar:

- **a)** Uma superclasse abstrata que todas passam a estender;
- **b)** um `enum` com os tipos possíveis;
- **c)** uma **interface** implementada por cada uma delas;
- **d)** um conjunto de métodos `static` numa classe utilitária.

↩︎ *Aula 15, seção 2 — Do requisito ao diagrama*

---

### Q-A15-05

Qual estratégia de commits o curso recomenda para o projeto final?

- **a)** Um commit por dia, independentemente do que foi feito;
- **b)** um único commit final, com o projeto pronto e testado;
- **c)** commits **pequenos e frequentes**, cada um deixando o programa em estado funcional;
- **d)** um commit por arquivo criado, para facilitar a revisão.

↩︎ *Aula 15, seção 3 — Estratégia de commits*

---

### Q-A15-06

Na revisão em par, o que caracteriza um problema de **camadas**?

- **a)** Uma classe com mais de dez métodos;
- **b)** `System.out.println` dentro de `model` ou `service`, ou regra de negócio dentro do menu;
- **c)** atributos declarados como `protected` em vez de `private`;
- **d)** o uso de `for` clássico onde caberia um `for-each`.

↩︎ *Aula 15, seção 5 — Revisão em par*

---

### Q-A15-07

Por que não deixar a persistência em arquivo para o fim do projeto?

- **a)** Porque é a parte que **mais quebra** — descobrir os problemas na véspera da entrega custa caro;
- **b)** porque ela precisa ser implementada antes das classes de modelo;
- **c)** porque o Git não versiona arquivos de dados criados depois do primeiro commit;
- **d)** porque a leitura de arquivos exige um pacote separado, criado no início.

↩︎ *Aula 15, seção 6 — Os erros que atrasam projeto*

---

### Q-A15-08

O que o checkpoint desta aula exige que já exista?

- **a)** O sistema completo, faltando apenas os relatórios;
- **b)** o diagrama de classes, sem nenhum código escrito ainda;
- **c)** as classes de modelo compilando, mas ainda sem repositório criado;
- **d)** repositório com README e `.gitignore`, tema definido, diagrama em Mermaid, pacotes criados, classes de modelo **compilando** e ao menos 3 commits.

↩︎ *Aula 15, seção 4 — Checkpoint da aula*

---

⬅️ [Voltar à Aula 15](../README.md) | ➡️ [Revisão da Aula 16](../../aula-16-revisao-proximos-passos/revisao/README.md)
