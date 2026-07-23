package biblioteca.service.excecoes;

public class ItemNaoEncontradoException extends RuntimeException {

    public ItemNaoEncontradoException(String codigo) {
        super("Não existe item com o código " + codigo);
    }
}
