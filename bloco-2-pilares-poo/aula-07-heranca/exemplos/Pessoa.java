/*
 * Aula 07 — a superclasse: o que Aluno e Professor têm em comum.
 */
public class Pessoa {

    protected String nome;      // protected: visível para as SUBCLASSES
    protected String cpf;
    protected int idade;

    public Pessoa(String nome, String cpf, int idade) {
        System.out.println("1 - construtor de Pessoa");
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public boolean ehMaiorDeIdade() {
        return idade >= 18;
    }

    @Override
    public String toString() {
        return nome + " (" + cpf + ")";
    }
}
