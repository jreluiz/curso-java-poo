package biblioteca.service.excecoes;

public class ItemIndisponivelException extends RuntimeException {

    public ItemIndisponivelException(String titulo) {
        super("O item \"" + titulo + "\" já está emprestado.");
    }
}
