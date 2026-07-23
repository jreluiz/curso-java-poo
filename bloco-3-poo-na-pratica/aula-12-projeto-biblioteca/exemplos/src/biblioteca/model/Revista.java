package biblioteca.model;

public class Revista extends ItemAcervo {

    private final int edicao;

    public Revista(String codigo, String titulo, int ano, int edicao) {
        super(codigo, titulo, ano);
        this.edicao = edicao;
    }

    @Override
    public String descricao() {
        return String.format("%s - edição %d (%d)", titulo, edicao, ano);
    }
}
