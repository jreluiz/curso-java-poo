# 🤝 Trabalho em Dupla — Sistema via Pull Request

> 📅 Lançado no Bloco 3 (após a aula 11)
> 👥 Chame um colega — o trabalho é feito em dupla

## Objetivo

Construir, **em dupla e via Pull Requests**, um sistema de console orientado a objetos. Além da POO, este trabalho exercita o **fluxo de colaboração Git** do curso de Git/GitHub — que é como equipes de verdade trabalham.

## A dinâmica de colaboração

1. O **Aluno A** cria o repositório `sistema-<tema>` e adiciona o Aluno B como *collaborator* (Settings → Collaborators);
2. **Ninguém commita direto no `main`.** Toda contribuição segue: branch → commits → push → **Pull Request** → o **outro** aluno revisa (comenta de verdade!) e faz o merge;
3. Divisão sugerida — **duas rodadas**, para os dois exercitarem tudo:

   | Rodada | Aluno A | Aluno B |
   |:---:|---------|---------|
   | 1ª | classes de `model` (herança, interface, encapsulamento) | `service` (coleções, buscas, cadastro) |
   | 2ª | menu do `app` e tratamento de erros | exceções personalizadas e relatórios |

4. **Mínimo de 3 PRs por aluno**, cada um revisado pelo colega. PR aprovado sem nenhum comentário não conta como revisão — procure algo a melhorar (nome confuso, método longo, regra no lugar errado);
5. Registrem a divisão combinada no README.

## Escolha do tema

Qualquer domínio com **2 ou 3 entidades relacionadas** e uma hierarquia natural:

| Tema | Hierarquia | Regra interessante |
|------|-----------|--------------------|
| Locadora de jogos | `Midia` → físico, digital | multa por atraso |
| Petshop | `Servico` → banho, tosa, consulta | agenda sem choque de horário |
| Cantina escolar | `Produto` → lanche, bebida, combo | combo com desconto |
| Oficina mecânica | `OrdemServico` → revisão, reparo | orçamento aprovado antes de executar |
| Biblioteca de HQs | `Item` → HQ, encadernado | limite por usuário |

## Requisitos funcionais

O sistema deve:

- [ ] Ter pelo menos **4 classes próprias** organizadas em pacotes `model`, `service` e `app`;
- [ ] Usar **herança** (uma classe abstrata com método abstrato) **e** pelo menos **uma interface**;
- [ ] Ter todos os atributos `private`/`protected`, com **validação** nos construtores/setters;
- [ ] Guardar os dados numa `List` (ou `Map`) **dentro do service** — o `app` nunca toca na coleção direto;
- [ ] Ter **pelo menos uma exceção personalizada**, lançada pelo service e tratada no `app`;
- [ ] Oferecer um menu em `do-while` com no mínimo: cadastrar, listar, executar a operação principal e sair;
- [ ] **Não quebrar com nenhuma entrada**: letra onde se espera número, código inexistente, campo vazio, `Enter` puro;
- [ ] Sobrescrever `toString()` em todas as classes de modelo, e `equals`/`hashCode` nas que entram em coleções.

## Requisitos técnicos

- [ ] Repositório com histórico limpo: **mínimo de 3 PRs por aluno**, todos revisados pelo colega;
- [ ] `.gitignore` de Java (nada de `.class` ou `out/` versionados);
- [ ] `README.md` com: descrição do sistema, **diagrama de classes em Mermaid**, como executar, divisão de tarefas e link para os PRs;
- [ ] Código no estilo Java: `PascalCase` em classes, `camelCase` em métodos, constantes em maiúsculas, indentação consistente.

## Extras para ir além 🌶️

- Persistência em arquivo (antecipando a Aula 13);
- Relatórios com totais e filtros;
- `enum` para status, com transições válidas controladas;
- Javadoc completo nos métodos públicos do service.

## Entrega

Trabalho pronto = repositório público + README completo + histórico de PRs revisados.

Combinem, dividam, conversem: o `git log` e a aba de Pull Requests mostram **quem fez o quê** — e colaborar é metade do objetivo deste trabalho.

> 💡 Dica que evita dor de cabeça: **puxem `main` antes de criar cada branch nova** (`git checkout main && git pull`). Conflito de merge quase sempre nasce de uma branch criada a partir de um `main` desatualizado.

---

🏠 [Voltar ao início](../README.md)
