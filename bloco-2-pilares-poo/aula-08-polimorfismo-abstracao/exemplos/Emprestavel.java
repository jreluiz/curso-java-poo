/*
 * Aula 08 — INTERFACE: contrato de capacidade, não de parentesco.
 * Um livro e um projetor não têm nada em comum — mas os dois podem ser emprestados.
 */
public interface Emprestavel {
    void emprestar(String responsavel);
    void devolver();
    boolean estaDisponivel();
}
