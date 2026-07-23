/*
 * Nenhum parentesco com Livro — mas cumpre o mesmo contrato.
 * É isso que a interface permite.
 */
public class Projetor implements Emprestavel {

    private String modelo;
    private int lumens;
    private boolean disponivel = true;

    public Projetor(String modelo, int lumens) {
        this.modelo = modelo;
        this.lumens = lumens;
    }

    @Override
    public void emprestar(String responsavel) {
        if (!disponivel) {
            System.out.println("Projetor " + modelo + " indisponível.");
            return;
        }
        this.disponivel = false;
        System.out.println("Projetor " + modelo + " emprestado para " + responsavel);
    }

    @Override
    public void devolver() {
        this.disponivel = true;
    }

    @Override
    public boolean estaDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Projetor: " + modelo + " (" + lumens + " lumens)";
    }
}
