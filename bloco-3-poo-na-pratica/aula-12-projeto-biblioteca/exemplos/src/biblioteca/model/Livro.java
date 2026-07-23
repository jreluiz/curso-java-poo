package biblioteca.model;

public class Livro extends ItemAcervo {

    private final String autor;
    private final int paginas;

    public Livro(String codigo, String titulo, int ano, String autor, int paginas) {
        super(codigo, titulo, ano);
        this.autor = autor;
        this.paginas = paginas;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String descricao() {
        return String.format("%s, de %s (%d) - %d páginas", titulo, autor, ano, paginas);
    }
}
