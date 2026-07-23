public class Livro implements Emprestavel {

    private String titulo;
    private boolean disponivel = true;
    private String comQuem;

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public void emprestar(String responsavel) {
        if (!disponivel) {
            System.out.println(titulo + " já está com " + comQuem);
            return;
        }
        this.disponivel = false;
        this.comQuem = responsavel;
        System.out.println(titulo + " emprestado para " + responsavel);
    }

    @Override
    public void devolver() {
        this.disponivel = true;
        this.comQuem = null;
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + (disponivel ? " (disponível)" : " (com " + comQuem + ")");
    }
}
