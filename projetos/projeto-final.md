# 🚀 Projeto Final — Sistema com Persistência

> 📅 Lançado no Bloco 4 (após a aula 14) · Individual

## Objetivo

Construir um sistema orientado a objetos, em console, que **guarda seus dados em arquivo** e integra tudo o que foi visto no curso: modelagem, os quatro pilares, coleções, exceções, camadas, persistência e streams.

## Ideias de projeto (escolha uma ou proponha a sua)

| Projeto | Hierarquia natural | Núcleo do sistema |
|---------|--------------------|-------------------|
| Academia | `Plano` → mensal, trimestral, anual | matrícula, check-in, bloqueio de plano vencido |
| Lanchonete | `ItemCardapio` → lanche, bebida, combo | pedido com status, cálculo do total, relatório do dia |
| Estacionamento | `Veiculo` → carro, moto, caminhão | entrada/saída, cobrança por tempo, faturamento |
| Locadora | `Midia` → físico, digital | locação, devolução, multa por atraso |
| Clínica | `Consulta` → primeira vez, retorno | agenda sem conflito de horário, histórico do paciente |
| Escala de plantão | `Funcionario` → efetivo, temporário | alocação sem duplicidade, horas por pessoa |
| Tema livre | — | **combine antes**: precisa caber nos requisitos abaixo |

## Requisitos obrigatórios

### De modelagem e POO

- [ ] Mínimo de **5 classes próprias**, organizadas nos pacotes `model`, `service` e `app`;
- [ ] Uma **classe abstrata** com pelo menos um **método abstrato** implementado de formas diferentes pelas subclasses;
- [ ] Pelo menos **uma interface** implementada por classes que não pertencem à mesma hierarquia;
- [ ] **Polimorfismo de verdade**: uma coleção do tipo genérico (ou abstrato) percorrida por um único laço, sem `if` de tipo;
- [ ] Todos os atributos `private`/`protected`, com **validação** que impeça objeto em estado inválido;
- [ ] `toString()` em todas as classes de modelo; `equals`/`hashCode` nas que entram em coleções.

### De funcionamento

- [ ] `List` e/ou `Map` como armazenamento, **encapsulados dentro do service**;
- [ ] Pelo menos **uma exceção personalizada**, lançada pelo service e tratada na camada `app`;
- [ ] **Nenhuma entrada derruba o programa** — letra onde se espera número, código inexistente, campo vazio, `Enter` puro;
- [ ] **Persistência em arquivo**: os dados sobrevivem ao fechar e reabrir o programa;
- [ ] Pelo menos **um relatório gerado com stream** (filtro, ordenação ou agregação);
- [ ] Menu funcional cobrindo o fluxo completo: cadastrar → operar → consultar → relatório → sair.

### De entrega

- [ ] Repositório próprio com **mínimo de 10 commits** distribuídos ao longo do desenvolvimento (não tudo na véspera — o `git log` conta a história do projeto);
- [ ] `.gitignore` de Java, incluindo a pasta de dados gerados;
- [ ] `README.md` com: descrição, **diagrama de classes em Mermaid**, regras de negócio implementadas, **como executar**, prints do sistema rodando e uma seção "o que eu faria com mais tempo";
- [ ] Código organizado: métodos curtos com nomes claros, sem código morto, sem trecho comentado "para o caso de precisar".

## Extras para ir além 🌶️

- **Testes JUnit** para as regras de negócio (o extra que mais impressiona);
- `enum` com atributos controlando transições de status;
- Relatório exportado para arquivo (`.txt` ou `.csv`);
- Busca por texto parcial, ordenações múltiplas com `Comparator`;
- `Optional` no lugar de retornos `null`;
- Javadoc completo nos métodos públicos do service.

## Entrega e apresentação

- Projeto pronto = repositório público com README completo e o sistema rodando a partir de um `git clone`;
- Faça uma **demonstração de 5 minutos** para alguém (ou grave um vídeo): mostre o fluxo principal, **um caso de erro sendo tratado**, o arquivo de dados no disco, e o trecho de código de que você mais se orgulha.

## Como você mesmo pode avaliar seu projeto

Antes de entregar, responda honestamente:

1. Se eu apagar o arquivo de dados e rodar de novo, o sistema abre normalmente? *(persistência robusta)*
2. Se eu digitar besteira em **todas** as perguntas, ele sobrevive? *(tratamento de erros)*
3. Consigo acrescentar um tipo novo (mais um plano, mais um veículo) criando **uma classe** e sem alterar o service? *(polimorfismo de verdade)*
4. Existe algum `System.out.println` fora do pacote `app`? *(camadas)*
5. Consigo explicar, linha por linha, qualquer trecho do meu código? *(o teste que importa)*

> 💡 Comece simples e **termine** o básico antes de partir para extras. Um sistema pequeno funcionando, persistindo e bem commitado vale mais que um sistema ambicioso pela metade.

---

🏠 [Voltar ao início](../README.md)
