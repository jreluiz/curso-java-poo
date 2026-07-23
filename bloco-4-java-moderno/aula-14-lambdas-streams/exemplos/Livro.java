/*
 * Aula 14 — classe simples, só para servir de dado nos exemplos de stream.
 */
public class Livro {

    private final String titulo;
    private final String autor;
    private final String categoria;
    private final int ano;
    private final boolean disponivel;

    public Livro(String titulo, String autor, String categoria, int ano, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ano = ano;
        this.disponivel = disponivel;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public int getAno() { return ano; }
    public boolean estaDisponivel() { return disponivel; }

    @Override
    public String toString() {
        return titulo + " (" + ano + ")";
    }
}
