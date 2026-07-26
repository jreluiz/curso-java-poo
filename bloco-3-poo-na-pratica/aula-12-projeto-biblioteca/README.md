# Aula 12 — Projeto Guiado: Sistema de Biblioteca

> 🎯 Objetivos: construir, do zero e em 6 etapas, um sistema completo que usa **tudo** do curso até aqui — classes, encapsulamento, herança, interface, polimorfismo, coleções, exceções e pacotes.
> 🎬 Slides da aula: [apresentacao-12-projeto-biblioteca.pdf](apresentacao/apresentacao-12-projeto-biblioteca.pdf)

Esta aula é diferente: não há conceito novo. Há um sistema para construir, **uma etapa por vez**, com um commit ao fim de cada uma. É a ponte entre "sei o conceito" e "consigo fazer".

## O que vamos construir

Um sistema de biblioteca em console que permite cadastrar itens (livros e revistas), cadastrar usuários, emprestar, devolver e listar. As regras:

- Cada usuário tem um **tipo** que define quantos itens pode pegar e por quantos dias;
- Um item emprestado não pode ser emprestado de novo;
- Devolução com atraso gera **multa** de R$ 1,00 por dia;
- Nenhuma entrada do usuário pode derrubar o programa.

A estrutura de destino:

```
biblioteca/
├── model/
│   ├── Emprestavel.java        (interface — Etapa 3)
│   ├── ItemAcervo.java         (abstrata)
│   ├── Livro.java
│   ├── Revista.java
│   ├── ObraDeReferencia.java   (Etapa 3)
│   ├── Usuario.java
│   ├── TipoUsuario.java        (enum)
│   └── Emprestimo.java
├── service/
│   ├── BibliotecaService.java
│   └── excecoes/
│       ├── ItemNaoEncontradoException.java
│       ├── UsuarioNaoEncontradoException.java
│       ├── ItemIndisponivelException.java
│       └── LimiteExcedidoException.java
└── app/
    └── Main.java
```

O diagrama de classes está na [Aula 11, seção 6](../aula-11-organizacao-pacotes/README.md) — abra numa aba ao lado.

> 💡 **Como usar esta aula:** faça uma etapa, rode, veja funcionando, **commite**. Só então avance. Se pular etapas, a próxima não fecha.

---

## Etapa 1 — O modelo básico

Comece pelo domínio, sem menu e sem serviço. Crie `ItemAcervo` como classe **abstrata**: todo item tem código, título e disponibilidade, mas "item genérico" não existe na prateleira.

```java
package biblioteca.model;

public abstract class ItemAcervo {
    protected String codigo;
    protected String titulo;
    protected int ano;
    protected boolean disponivel = true;

    public ItemAcervo(String codigo, String titulo, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
    }

    /** Cada tipo de item se descreve à sua maneira. */
    public abstract String descricao();

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public boolean isDisponivel() { return disponivel; }

    @Override
    public String toString() {
        return String.format("[%s] %s %s", codigo, descricao(),
                disponivel ? "✅ disponível" : "❌ emprestado");
    }
}
```

Agora `Livro` e `Revista`, cada um com seus atributos e sua `descricao()`:

```java
package biblioteca.model;

public class Livro extends ItemAcervo {
    private String autor;
    private int paginas;

    public Livro(String codigo, String titulo, int ano, String autor, int paginas) {
        super(codigo, titulo, ano);
        this.autor = autor;
        this.paginas = paginas;
    }

    @Override
    public String descricao() {
        return String.format("%s, de %s (%d) - %d páginas", titulo, autor, ano, paginas);
    }
}
```

**Faça você:** `Revista` (com `edicao`), o enum `TipoUsuario` (`ALUNO(3, 7)`, `PROFESSOR(10, 30)`, `VISITANTE(1, 3)`, como na Aula 11) e `Usuario` (id, nome, tipo, uma `List<ItemAcervo>` com os itens em mãos).

✅ **Checkpoint:** um `main` temporário cria 2 livros e 1 revista, guarda num `List<ItemAcervo>` e imprime todos num `for-each`. Uma chamada `toString()`, três descrições diferentes: polimorfismo funcionando.

```bash
git add . && git commit -m "Etapa 1: modelo do acervo (ItemAcervo, Livro, Revista, Usuario)"
```

---

## Etapa 2 — Encapsulamento e regras no lugar certo

Feche o modelo: todos os atributos `private`/`protected`, getters onde faz sentido, **setters só onde alguém realmente pode alterar**.

Coloque em `Usuario` a regra que é dele:

```java
public boolean podePegarMais() {
    return itensEmMaos.size() < tipo.getLimiteItens();
}

public void registrarRetirada(ItemAcervo item) {
    itensEmMaos.add(item);
}

public void registrarDevolucao(ItemAcervo item) {
    itensEmMaos.remove(item);
}
```

E em `ItemAcervo`, o controle de disponibilidade — **ninguém de fora mexe no `disponivel` direto**:

```java
public void marcarEmprestado() {
    this.disponivel = false;
}

public void marcarDisponivel() {
    this.disponivel = true;
}
```

Sobrescreva `equals` e `hashCode` em `ItemAcervo` (por `codigo`) e em `Usuario` (por `id`) — sem isso, `contains` e `remove` das listas não funcionam (Aula 09).

✅ **Checkpoint:** tente `livro.disponivel = false;` no `main` e confirme que **não compila**.

```bash
git commit -am "Etapa 2: encapsulamento e regras de domínio"
```

---

## Etapa 3 — A interface `Emprestavel`

Nem todo item do acervo precisa ser emprestável (uma obra de referência fica na sala de leitura). Declare a capacidade separadamente:

```java
package biblioteca.model;

public interface Emprestavel {
    void emprestar();
    void devolver();
    boolean estaDisponivel();
}
```

Faça `ItemAcervo` implementar `Emprestavel`, movendo `marcarEmprestado`/`marcarDisponivel` para `emprestar()`/`devolver()` e trocando `isDisponivel()` pelo `estaDisponivel()` exigido pela interface (o compilador aponta cada lugar que precisa mudar — deixe ele guiar a refatoração).

**Faça você:** crie `ObraDeReferencia extends ItemAcervo`, que sobrescreve `emprestar()` lançando `UnsupportedOperationException("Obra de referência não pode ser emprestada")`. Repare que o resto do sistema continua funcionando sem saber que essa classe existe.

✅ **Checkpoint:** um `List<Emprestavel>` com livro, revista e obra de referência; um laço tenta emprestar todos e trata a exceção do último.

```bash
git commit -am "Etapa 3: interface Emprestavel e ObraDeReferencia"
```

---

## Etapa 4 — O `BibliotecaService`

Agora a camada que **orquestra**: guarda as coleções e implementa as operações do sistema. Nenhum `System.out` aqui.

```java
package biblioteca.service;

import biblioteca.model.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    private final List<ItemAcervo> acervo = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarItem(ItemAcervo item) {
        if (buscarItem(item.getCodigo()) != null) {
            throw new IllegalArgumentException("Já existe item com o código " + item.getCodigo());
        }
        acervo.add(item);
    }

    public ItemAcervo buscarItem(String codigo) {
        for (ItemAcervo item : acervo) {
            if (item.getCodigo().equalsIgnoreCase(codigo)) {
                return item;
            }
        }
        return null;
    }

    /** @return cópia da lista, para ninguém alterar o acervo por fora */
    public List<ItemAcervo> listarAcervo() {
        return new ArrayList<>(acervo);
    }

    public List<ItemAcervo> listarDisponiveis() {
        List<ItemAcervo> disponiveis = new ArrayList<>();
        for (ItemAcervo item : acervo) {
            if (item.estaDisponivel()) {
                disponiveis.add(item);
            }
        }
        return disponiveis;
    }
}
```

**Faça você:** `cadastrarUsuario`, `buscarUsuario` e `listarUsuarios`, no mesmo padrão.

> 💡 Repare no `return new ArrayList<>(acervo)`: devolver a lista interna direto permitiria que o `main` fizesse `service.listarAcervo().clear()` — e lá se foi o encapsulamento.

✅ **Checkpoint:** cadastro de item duplicado é recusado; busca por código inexistente devolve `null`.

```bash
git commit -am "Etapa 4: BibliotecaService com cadastro e busca"
```

---

## Etapa 5 — Empréstimo, devolução e exceções

Primeiro a classe `Emprestimo`, que registra quem pegou o quê e quando:

```java
package biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private final ItemAcervo item;
    private final Usuario usuario;
    private final LocalDate dataRetirada;
    private final LocalDate dataPrevista;
    private LocalDate dataDevolucao;      // null enquanto não devolvido

    public Emprestimo(ItemAcervo item, Usuario usuario) {
        this.item = item;
        this.usuario = usuario;
        this.dataRetirada = LocalDate.now();
        this.dataPrevista = dataRetirada.plusDays(usuario.getTipo().getDiasEmprestimo());
    }

    public boolean estaAtivo() {
        return dataDevolucao == null;
    }

    public double registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
        return calcularMulta();
    }

    public double calcularMulta() {
        LocalDate referencia = (dataDevolucao != null) ? dataDevolucao : LocalDate.now();
        if (!referencia.isAfter(dataPrevista)) {
            return 0;
        }
        long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(dataPrevista, referencia);
        return diasAtraso * 1.0;      // R$ 1,00 por dia
    }
}
```

Depois as três exceções personalizadas, em `service/excecoes/` (todas estendendo `RuntimeException`, como na Aula 10):

```java
package biblioteca.service.excecoes;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String codigo) {
        super("Não existe item com o código " + codigo);
    }
}
```

E o método que reúne tudo — **leia com atenção: cada `if` é uma regra do enunciado**:

```java
public Emprestimo emprestar(String codigoItem, String idUsuario) {
    ItemAcervo item = buscarItem(codigoItem);
    if (item == null) {
        throw new ItemNaoEncontradoException(codigoItem);
    }

    Usuario usuario = buscarUsuario(idUsuario);
    if (usuario == null) {
        throw new UsuarioNaoEncontradoException(idUsuario);
    }

    if (!item.estaDisponivel()) {
        throw new ItemIndisponivelException(item.getTitulo());
    }

    if (!usuario.podePegarMais()) {
        throw new LimiteExcedidoException(usuario.getNome(),
                usuario.getTipo().getLimiteItens());
    }

    item.emprestar();
    usuario.registrarRetirada(item);

    Emprestimo emprestimo = new Emprestimo(item, usuario);
    emprestimos.add(emprestimo);
    return emprestimo;
}
```

**Faça você:** `devolver(String codigoItem)` — encontra o empréstimo **ativo** daquele item, chama `registrarDevolucao()`, libera o item, atualiza o usuário e **devolve o valor da multa** para o `app` mostrar.

✅ **Checkpoint:** empréstimo do mesmo item duas vezes lança `ItemIndisponivelException`; o quarto empréstimo de um `ALUNO` lança `LimiteExcedidoException`.

```bash
git commit -am "Etapa 5: empréstimo, devolução, multa e exceções"
```

---

## Etapa 6 — O menu

A última camada é a única que fala com o usuário. Todo `catch` mora aqui.

```java
package biblioteca.app;

import biblioteca.model.*;
import biblioteca.service.BibliotecaService;
import biblioteca.service.excecoes.*;
import java.util.Scanner;

public class Main {

    private static final BibliotecaService service = new BibliotecaService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDadosDeTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Opção: ");
            executar(opcao);
        } while (opcao != 0);

        System.out.println("Até logo!");
    }

    private static void exibirMenu() {
        System.out.println("""
                
                ═══════ BIBLIOTECA ═══════
                1 - Cadastrar livro
                2 - Cadastrar usuário
                3 - Emprestar
                4 - Devolver
                5 - Listar acervo
                6 - Listar disponíveis
                0 - Sair""");
    }

    private static void executar(int opcao) {
        try {
            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> emprestar();
                case 4 -> devolver();
                case 5 -> service.listarAcervo().forEach(System.out::println);
                case 6 -> service.listarDisponiveis().forEach(System.out::println);
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
        } catch (ItemNaoEncontradoException | ItemIndisponivelException
                 | LimiteExcedidoException e) {
            System.out.println("⚠️  " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️  Erro inesperado: " + e.getMessage());
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número.");
            }
        }
    }

    // ... os demais métodos: cadastrarLivro(), emprestar(), devolver() ...
}
```

**Faça você:** os métodos que faltam. Cada um lê os dados com `scanner.nextLine()`, chama **um** método do service e imprime a confirmação. Se aparecer um `if` de regra de negócio aqui, ele está no lugar errado.

> 💡 `service.listarAcervo().forEach(System.out::println)` é uma degustação da Aula 14 — leia como "para cada item, imprima".

✅ **Checkpoint final:** rode o sistema e faça o roteiro completo — cadastrar, emprestar, tentar emprestar de novo, devolver, listar. Depois tente **derrubar** o programa: letras onde se espera número, código inexistente, campos vazios, `Enter` puro. Ele precisa aguentar tudo.

```bash
git commit -am "Etapa 6: menu completo com tratamento de erros"
git push
```

---

> 💻 **Código desta aula pronto para rodar:** o **projeto completo** em [`exemplos/`](exemplos/README.md) — consulte quando travar, mas construa o seu

## 🏋️ Para fechar

1. **README.md do projeto** — descrição, como executar, o diagrama de classes em Mermaid e a lista de regras implementadas;
2. **Javadoc** nos métodos públicos do `BibliotecaService`;
3. **Três melhorias à sua escolha** 🌶️ — por exemplo: relatório de itens em atraso, histórico de empréstimos por usuário, busca por título parcial (`contains`), suspensão de usuário com multa em aberto, `Map<String, ItemAcervo>` no lugar da lista (compare o código de busca antes e depois).

> 💡 Guarde este projeto. Na Aula 13 ele ganha **persistência em arquivo** — e passa a lembrar dos dados entre execuções.

## 🧠 Revisão

[8 questões de múltipla escolha](revisao/README.md) para conferir se os conceitos ficaram sólidos. Responda sem consultar a aula — depois volte e corrija.

## ✅ Entrega

```bash
git add .
git commit -m "Projeto guiado da aula 12 concluído (sistema de biblioteca)"
git push
```

---

⬅️ [Aula 11](../aula-11-organizacao-pacotes/README.md) | ➡️ [Aula 13 — Arquivos e Persistência](../../bloco-4-java-moderno/aula-13-arquivos-persistencia/README.md)
