package biblioteca.model;

/**
 * Dicionários e enciclopédias ficam na sala de leitura: existem no acervo,
 * mas recusam empréstimo. O resto do sistema não precisa saber que esta
 * classe existe — é o polimorfismo trabalhando.
 */
public class ObraDeReferencia extends ItemAcervo {

    private final String area;

    public ObraDeReferencia(String codigo, String titulo, int ano, String area) {
        super(codigo, titulo, ano);
        this.area = area;
    }

    @Override
    public void emprestar() {
        throw new UnsupportedOperationException(
                "Obra de referência não pode ser emprestada: " + titulo);
    }

    @Override
    public String descricao() {
        return String.format("%s - obra de referência em %s (%d)", titulo, area, ano);
    }
}
