package biblioteca.app;

import biblioteca.model.*;
import biblioteca.service.BibliotecaService;
import biblioteca.service.excecoes.*;

import java.util.Scanner;

/**
 * Camada de interface: a ÚNICA que lê teclado e imprime na tela.
 * Todo catch mora aqui; nenhuma regra de negócio mora aqui.
 */
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
                7 - Empréstimos em aberto
                8 - Atrasados
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
                case 7 -> service.listarEmprestimosAtivos().forEach(System.out::println);
                case 8 -> service.listarAtrasados().forEach(System.out::println);
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
        } catch (ItemNaoEncontradoException | UsuarioNaoEncontradoException
                 | ItemIndisponivelException | LimiteExcedidoException
                 | IllegalArgumentException | UnsupportedOperationException e) {
            System.out.println("⚠️  " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️  Erro inesperado: " + e.getMessage());
        }
    }

    // ── Ações do menu: leem, chamam UM método do service, confirmam ──

    private static void cadastrarLivro() {
        String codigo = lerTexto("Código: ");
        String titulo = lerTexto("Título: ");
        int ano = lerInteiro("Ano: ");
        String autor = lerTexto("Autor: ");
        int paginas = lerInteiro("Páginas: ");

        service.cadastrarItem(new Livro(codigo, titulo, ano, autor, paginas));
        System.out.println("✅ Livro cadastrado.");
    }

    private static void cadastrarUsuario() {
        String id = lerTexto("Id: ");
        String nome = lerTexto("Nome: ");
        System.out.println("Tipos: ALUNO | PROFESSOR | VISITANTE");
        String tipoDigitado = lerTexto("Tipo: ").toUpperCase();

        TipoUsuario tipo;
        try {
            tipo = TipoUsuario.valueOf(tipoDigitado);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo inválido: " + tipoDigitado);
        }

        service.cadastrarUsuario(new Usuario(id, nome, tipo));
        System.out.println("✅ Usuário cadastrado.");
    }

    private static void emprestar() {
        String codigo = lerTexto("Código do item: ");
        String idUsuario = lerTexto("Id do usuário: ");

        Emprestimo emprestimo = service.emprestar(codigo, idUsuario);
        System.out.println("✅ Emprestado! Devolver até " + emprestimo.getDataPrevista());
    }

    private static void devolver() {
        String codigo = lerTexto("Código do item: ");
        double multa = service.devolver(codigo);

        if (multa > 0) {
            System.out.printf("✅ Devolvido com atraso. Multa: R$ %.2f%n", multa);
        } else {
            System.out.println("✅ Devolvido em dia. Obrigado!");
        }
    }

    // ── Entrada à prova de bala ─────────────────────────────────────

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = scanner.nextLine().trim();
            if (!valor.isBlank()) {
                return valor;
            }
            System.out.println("Não pode ficar vazio.");
        }
    }

    private static void carregarDadosDeTeste() {
        service.cadastrarItem(new Livro("L001", "Dom Casmurro", 1899, "Machado de Assis", 256));
        service.cadastrarItem(new Livro("L002", "Clean Code", 2008, "Robert Martin", 464));
        service.cadastrarItem(new Revista("R001", "Superinteressante", 2026, 480));
        service.cadastrarItem(new ObraDeReferencia("O001", "Dicionário Houaiss", 2009, "Língua portuguesa"));

        service.cadastrarUsuario(new Usuario("U001", "Ana", TipoUsuario.ALUNO));
        service.cadastrarUsuario(new Usuario("U002", "Prof. Carlos", TipoUsuario.PROFESSOR));

        System.out.println("(dados de teste carregados: 4 itens, 2 usuários)");
    }
}
