package biblioteca.model;

/**
 * Capacidade de ser emprestado. É uma interface (e não uma superclasse) porque
 * itens sem parentesco algum podem cumprir o mesmo contrato.
 */
public interface Emprestavel {

    void emprestar();

    void devolver();

    boolean estaDisponivel();
}
