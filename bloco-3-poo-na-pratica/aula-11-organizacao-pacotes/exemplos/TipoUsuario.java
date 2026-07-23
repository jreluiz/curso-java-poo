/*
 * Aula 11 — enum com atributos: as regras moram no próprio tipo.
 */
public enum TipoUsuario {

    ALUNO(3, 7),          // 3 itens, 7 dias
    PROFESSOR(10, 30),
    VISITANTE(1, 3);

    private final int limiteItens;
    private final int diasEmprestimo;

    TipoUsuario(int limiteItens, int diasEmprestimo) {
        this.limiteItens = limiteItens;
        this.diasEmprestimo = diasEmprestimo;
    }

    public int getLimiteItens() {
        return limiteItens;
    }

    public int getDiasEmprestimo() {
        return diasEmprestimo;
    }
}
