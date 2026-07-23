package biblioteca.model;

/** As regras de limite e prazo moram no próprio tipo, não espalhadas em ifs. */
public enum TipoUsuario {

    ALUNO(3, 7),
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
