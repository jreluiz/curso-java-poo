# Aula 13 — Revisão: Múltipla Escolha

> 🎯 8 questões sobre a [Aula 13 — Arquivos e Persistência](../README.md). Só uma alternativa está correta em cada uma.

**Sem gabarito, de propósito.** Cada questão termina com a seção da aula onde a resposta está. Responda **tudo primeiro**, sem consultar — só depois volte às seções indicadas e corrija.

---

### Q-A13-01

Por que os dados cadastrados somem ao fechar o programa?

- **a)** Porque a JVM apaga os objetos por segurança ao encerrar;
- **b)** porque faltou chamar `scanner.close()` antes de sair;
- **c)** porque eles estavam apenas na memória RAM, que é volátil;
- **d)** porque `ArrayList` tem um limite de tempo de vida.

↩︎ *Aula 13, seção 1 — Por que os dados somem*

---

### Q-A13-02

`IOException` é uma exceção *checked*. O que isso significa na prática?

- **a)** Que ela só pode ser lançada dentro de um bloco `finally`;
- **b)** que ela nunca precisa ser tratada, por ser rara;
- **c)** que ela encerra o programa mesmo se for capturada;
- **d)** que o compilador obriga você a tratá-la com `try/catch` ou a declarar `throws` na assinatura.

↩︎ *Aula 13, seção 3 — Escrevendo*

---

### Q-A13-03

O que `Files.write(arquivo, linhas)` faz com o conteúdo que já existia no arquivo?

- **a)** Sobrescreve: para acrescentar ao final é preciso usar `StandardOpenOption.APPEND`;
- **b)** acrescenta as novas linhas ao final, preservando o conteúdo anterior;
- **c)** lança `IOException` se o arquivo não estiver vazio;
- **d)** cria um segundo arquivo com sufixo numérico.

↩︎ *Aula 13, seção 3 — Escrevendo*

---

### Q-A13-04

Por que verificar `Files.exists(arquivo)` antes de ler os dados salvos?

- **a)** Porque `readAllLines` não funciona com caminhos relativos;
- **b)** porque na primeira execução o arquivo ainda não existe — e isso é normal, não é erro;
- **c)** porque a verificação acelera a leitura de arquivos grandes;
- **d)** porque sem ela o arquivo é criado vazio e o conteúdo antigo se perde.

↩︎ *Aula 13, seção 4 — Lendo*

---

### Q-A13-05

Por que o método `fromCsv(String linha)` é declarado como `static`?

- **a)** Porque métodos que recebem `String` precisam ser `static`;
- **b)** porque ele não usa nenhum atributo da classe, e isso é apenas uma otimização;
- **c)** porque `static` permite que ele seja sobrescrito pelas subclasses;
- **d)** porque ele cria o objeto: não existe instância para chamá-lo antes que o objeto exista.

↩︎ *Aula 13, seção 5 — De objeto para linha, e de volta*

---

### Q-A13-06

O que acontece quando o separador escolhido (`;`) aparece **dentro** de um dos campos gravados?

- **a)** O `split` ignora os separadores excedentes automaticamente;
- **b)** a linha é dividida em campos a mais e passa a ser interpretada errado na leitura;
- **c)** o arquivo não é gravado e um `IOException` é lançado;
- **d)** o Java troca o separador por vírgula sozinho.

↩︎ *Aula 13, seção 5 — De objeto para linha, e de volta*

---

### Q-A13-07

Uma linha do arquivo traz `oito` no campo do ano. O que `Integer.parseInt` faz, e qual é a conduta recomendada?

- **a)** Lança `NumberFormatException`; o certo é tratar a linha, avisar e seguir com as demais, em vez de derrubar a carga inteira;
- **b)** devolve `0` silenciosamente; nada precisa ser feito;
- **c)** devolve `null`; basta testar o resultado antes de usar;
- **d)** lança `IOException`, que deve subir até o `main`.

↩︎ *Aula 13, seção 5 — De objeto para linha, e de volta*

---

### Q-A13-08

Por que preferir caminhos **relativos** (`dados/acervo.csv`) nos projetos do curso?

- **a)** Porque caminhos absolutos não funcionam no Windows;
- **b)** porque só caminhos relativos podem ser usados com `Path.of`;
- **c)** porque o relativo parte da pasta em que o programa está rodando, e o projeto continua funcionando na máquina de outra pessoa;
- **d)** porque caminhos relativos dispensam o tratamento de `IOException`.

↩︎ *Aula 13, seção 2 — `Path` e `Files`*

---

⬅️ [Voltar à Aula 13](../README.md) | ➡️ [Revisão da Aula 14](../../aula-14-lambdas-streams/revisao/README.md)
