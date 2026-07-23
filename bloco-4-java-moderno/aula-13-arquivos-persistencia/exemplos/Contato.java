/*
 * Aula 13 — a classe sabe virar linha de texto e voltar a ser objeto.
 */
public class Contato {

    private final String nome;
    private final String telefone;
    private final int idade;

    public Contato(String nome, String telefone, int idade) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
    }

    /** Objeto → linha de CSV. */
    public String toCsv() {
        return String.join(";", nome, telefone, String.valueOf(idade));
    }

    /**
     * Linha de CSV → objeto. É static porque CRIA o objeto:
     * não existe instância para chamá-lo antes.
     */
    public static Contato fromCsv(String linha) {
        String[] campos = linha.split(";");
        if (campos.length != 3) {
            throw new IllegalArgumentException("Esperados 3 campos, vieram " + campos.length);
        }
        return new Contato(campos[0], campos[1], Integer.parseInt(campos[2]));
    }

    @Override
    public String toString() {
        return String.format("%s (%d anos) - %s", nome, idade, telefone);
    }
}
