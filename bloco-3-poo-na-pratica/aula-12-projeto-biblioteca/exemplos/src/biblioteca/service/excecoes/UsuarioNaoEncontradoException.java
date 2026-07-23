package biblioteca.service.excecoes;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String id) {
        super("Não existe usuário com o id " + id);
    }
}
