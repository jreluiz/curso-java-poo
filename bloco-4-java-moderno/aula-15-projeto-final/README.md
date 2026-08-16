# Aula 15 — Laboratório: do requisito ao sistema

> 🎯 Objetivos: escolher um tema viável, sair do requisito para o diagrama de classes, desenvolver com commits que contam a história e revisar o código de um colega.
> 🎬 Slides da aula: [apresentacao-15-projeto-final.pdf](apresentacao/apresentacao-15-projeto-final.pdf)

Nesta aula você não aprende conceito novo: você **usa todos**. A tarefa é construir um sistema de console que sobrevive ao próprio uso — e a régua é esta:

- **Mínimo de 5 classes próprias**, separadas em `model`, `service` e `app`;
- Uma **classe abstrata** com método abstrato implementado de formas diferentes, e pelo menos **uma interface** atravessando hierarquias distintas;
- **Polimorfismo de verdade**: uma coleção percorrida por um laço só, sem `if` de tipo;
- Atributos `private`/`protected` com **validação** que impeça objeto em estado inválido;
- Pelo menos **uma exceção personalizada**, lançada no `service` e tratada no `app`;
- **Nenhuma entrada derruba o programa**, **persistência em arquivo** e **um relatório com stream**.

## 1. Escolhendo o tema (e o escopo)

Um bom tema para este projeto tem: **2 a 4 entidades relacionadas**, uma **hierarquia natural** (algo que se divide em tipos) e **regras de negócio de verdade** (limites, cálculos, estados que mudam).

| Tema | Hierarquia natural | Regra que dá sal ao projeto |
|------|--------------------|-----------------------------|
| Academia | `Plano` → mensal, trimestral, anual | Bloquear check-in de plano vencido |
| Lanchonete | `ItemCardapio` → lanche, bebida, combo | Combo com desconto; pedido muda de status |
| Locadora de jogos | `Midia` → físico, digital | Multa por atraso; limite por cliente |
| Clínica | `Consulta` → primeira vez, retorno | Não permitir dois agendamentos no mesmo horário |
| Estacionamento | `Veiculo` → carro, moto, caminhão | Cobrança por hora com tolerância de 15 min |
| Escala de plantão | `Funcionario` → efetivo, temporário | Ninguém em dois plantões no mesmo dia |

> ⚠️ **O erro nº 1 é escolher grande demais.** "Uma rede social completa" não termina. Prefira **um** fluxo bem feito: cadastrar → operar → consultar → relatório. Terminado o básico, você acrescenta extras à vontade.

## 2. Do requisito ao diagrama

Antes de escrever a primeira classe, faça o percurso da [Aula 11](../../bloco-3-poo-na-pratica/aula-11-organizacao-pacotes/README.md):

1. **Escreva 5 frases** sobre o que o sistema faz — em português, do ponto de vista de quem usa;
2. **Sublinhe os substantivos** → candidatos a classe. **Circule os verbos** → candidatos a método;
3. **Pergunte "é um" ou "tem um"** em cada par de classes: `é um` vira herança, `tem um` vira atributo;
4. **Procure a capacidade transversal** (algo que classes sem parentesco fazem: emprestável, pagável, exportável) → vira interface;
5. **Desenhe** em Mermaid e coloque no README **antes** de programar.

Exemplo de percurso, tema estacionamento:

> "O sistema registra a **entrada** de um **veículo**, calcula o **valor** na **saída** conforme o **tipo** e o **tempo**, e emite um **relatório** de faturamento do dia."

- Substantivos: `Veiculo`, `Ticket` (entrada/saída), `Estacionamento`, `Relatorio`;
- `Carro` **é um** `Veiculo` → herança; `Ticket` **tem um** `Veiculo` → composição;
- Verbos: `registrarEntrada`, `registrarSaida`, `calcularValor`, `emitirRelatorio`;
- `calcularValor()` diferente por tipo de veículo → **método abstrato** em `Veiculo`.

## 3. Estratégia de commits

Um projeto entregue em **um** commit gigante na véspera não conta história nenhuma — e o `git log` é parte da entrega. A sequência que funciona:

```
1.  Estrutura de pastas e pacotes
2.  Classes de modelo (sem regras ainda)
3.  Encapsulamento e validações
4.  Herança/interface do domínio
5.  Service: cadastro e busca
6.  Service: a operação principal
7.  Exceções personalizadas
8.  Menu básico funcionando
9.  Persistência em arquivo
10. Relatório com streams
11. README com diagrama
12. Ajustes finais
```

Doze commits, cada um deixando o programa **rodando**. Regra prática: se você não consegue descrever o commit em uma frase curta, ele está grande demais.

## 4. Checkpoint da aula (faça agora)

Antes de sair desta aula, você precisa ter:

- [ ] Repositório criado no GitHub, com README e `.gitignore` de Java;
- [ ] Tema e escopo definidos em uma frase no README;
- [ ] Diagrama de classes em Mermaid no README (mesmo que mude depois);
- [ ] Pacotes `model`, `service`, `app` criados;
- [ ] As classes de modelo com atributos e construtores — **compilando**;
- [ ] Pelo menos 3 commits.

> 💡 **Diagrama muda, e tudo bem.** Ele é ponto de partida, não contrato. O que não pode é programar sem nenhum.

## 5. Revisão em par

Troque o link do repositório com um colega e revise o dele em 15 minutos, olhando **estes cinco pontos**:

1. **Nomes** — classes são substantivos? Métodos são verbos? Alguém precisa perguntar o que `proc2()` faz?
2. **Encapsulamento** — algum atributo público? Setter que aceita qualquer coisa? Regra de negócio que dá para burlar de fora?
3. **Camadas** — tem `System.out.println` em `model` ou `service`? Tem regra de negócio dentro do menu?
4. **Repetição** — o mesmo bloco de código aparece em dois lugares? Podia ser um método (ou uma superclasse)?
5. **Robustez** — o que acontece se o usuário digitar letra onde se espera número? E se buscar um código que não existe?

Anote o retorno recebido **em issues** do seu repositório (uma por ponto). Resolver issue por issue, com um commit cada, é exatamente como o trabalho acontece de verdade.

## 6. Os erros que atrasam projeto

- **Deixar a persistência para o fim.** É a parte que mais quebra; faça na metade do caminho, não na véspera;
- **Perfeccionismo no menu.** Emoji e moldura não valem nota nenhuma; a regra de negócio, sim;
- **Programar sem rodar.** Escreveu 200 linhas antes do primeiro `run`? Você vai depurar às cegas. Rode a cada método novo;
- **Não commitar.** Sem commits, um erro grande significa recomeçar;
- **Copiar código que você não entende.** Se não sabe explicar linha por linha, não use — e na apresentação isso aparece na primeira pergunta.

## 🏋️ Trabalho da aula

1. Cumpra **integralmente** o checkpoint da seção 4;
2. Faça a revisão em par e abra as issues do retorno recebido;
3. Escreva no README a seção **"O que ainda falta"** com sua lista de tarefas — e vá riscando.

### 📤 Entrega

Estes exercícios são feitos em sala e vão para o **seu repositório** `exercicios-java-poo`:

```bash
cd ..                 # da pasta da aula para a raiz do repositório
git add aula-15/
git commit -m "Resolve exercícios da aula 15"
git push
```

Confira no navegador que a pasta apareceu em `github.com/SEU-USUARIO/exercicios-java-poo`.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) sobre modelagem, camadas e as decisões de projeto do curso. Responda sem consultar as aulas — depois volte e corrija.

---

⬅️ [Aula 14](../aula-14-lambdas-streams/README.md) | ➡️ [Aula 16 — Revisão e Próximos Passos](../aula-16-revisao-proximos-passos/README.md)
