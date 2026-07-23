package biblioteca.service.excecoes;

public class LimiteExcedidoException extends RuntimeException {

    private final int limite;

    public LimiteExcedidoException(String nomeUsuario, int limite) {
        super(nomeUsuario + " já está com o limite de " + limite + " itens.");
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }
}
