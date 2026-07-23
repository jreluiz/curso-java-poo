package biblioteca.service;

import biblioteca.model.Emprestimo;
import biblioteca.model.ItemAcervo;
import biblioteca.model.Usuario;
import biblioteca.service.excecoes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Orquestra as regras do sistema e guarda as coleções.
 * Não imprime nada: quem conversa com o usuário é a camada app.
 */
public class BibliotecaService {

    private final List<ItemAcervo> acervo = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    // ── Acervo ──────────────────────────────────────────────────────

    /**
     * @param item item a cadastrar
     * @throws IllegalArgumentException se já existir item com o mesmo código
     */
    public void cadastrarItem(ItemAcervo item) {
        if (buscarItem(item.getCodigo()) != null) {
            throw new IllegalArgumentException("Já existe item com o código " + item.getCodigo());
        }
        acervo.add(item);
    }

    /** @return o item, ou {@code null} se não houver item com esse código */
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

    // ── Usuários ────────────────────────────────────────────────────

    public void cadastrarUsuario(Usuario usuario) {
        if (buscarUsuario(usuario.getId()) != null) {
            throw new IllegalArgumentException("Já existe usuário com o id " + usuario.getId());
        }
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equalsIgnoreCase(id)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    // ── Operações ───────────────────────────────────────────────────

    /**
     * Registra o empréstimo de um item para um usuário.
     *
     * @param codigoItem código do item desejado
     * @param idUsuario  identificador do usuário solicitante
     * @return o empréstimo criado, com a data de devolução já calculada
     * @throws ItemNaoEncontradoException    se não existir item com o código
     * @throws UsuarioNaoEncontradoException se não existir usuário com o id
     * @throws ItemIndisponivelException     se o item já estiver emprestado
     * @throws LimiteExcedidoException       se o usuário estiver no limite
     */
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

        item.emprestar();     // pode lançar UnsupportedOperationException (obra de referência)
        usuario.registrarRetirada(item);

        Emprestimo emprestimo = new Emprestimo(item, usuario);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    /**
     * Registra a devolução de um item.
     *
     * @return o valor da multa (0 se estiver em dia)
     * @throws ItemNaoEncontradoException se o item não existir ou não estiver emprestado
     */
    public double devolver(String codigoItem) {
        Emprestimo ativo = buscarEmprestimoAtivo(codigoItem);
        if (ativo == null) {
            throw new ItemNaoEncontradoException(codigoItem);
        }
        double multa = ativo.registrarDevolucao();
        ativo.getItem().devolver();
        ativo.getUsuario().registrarDevolucao(ativo.getItem());
        return multa;
    }

    private Emprestimo buscarEmprestimoAtivo(String codigoItem) {
        for (Emprestimo e : emprestimos) {
            if (e.estaAtivo() && e.getItem().getCodigo().equalsIgnoreCase(codigoItem)) {
                return e;
            }
        }
        return null;
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.estaAtivo()) {
                ativos.add(e);
            }
        }
        return ativos;
    }

    /** Empréstimos ativos cuja data prevista já passou. */
    public List<Emprestimo> listarAtrasados() {
        List<Emprestimo> atrasados = new ArrayList<>();
        for (Emprestimo e : listarEmprestimosAtivos()) {
            if (e.calcularMulta() > 0) {
                atrasados.add(e);
            }
        }
        return atrasados;
    }
}
