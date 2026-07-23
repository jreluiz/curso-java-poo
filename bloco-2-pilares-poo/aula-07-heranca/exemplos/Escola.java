/*
 * Aula 07 — herança em ação.
 * Rode com: java Escola.java
 */
public class Escola {
    public static void main(String[] args) {

        System.out.println("--- ordem dos construtores: mãe primeiro ---");
        Aluno ana = new Aluno("Ana", "111", 19, "1001");
        ana.lancarNotas(8.0, 7.0, 9.0);

        Professor carlos = new Professor("Carlos", "999", 40, 6000);

        System.out.println("--- métodos herdados ---");
        System.out.println(ana.getNome());          // herdado de Pessoa
        System.out.println(ana.ehMaiorDeIdade());   // herdado de Pessoa
        System.out.println(ana.calcularMedia());    // próprio de Aluno

        System.out.println("--- toString sobrescrito ---");
        System.out.println(ana);
        System.out.println(carlos);

        System.out.println("--- equals: mesma matrícula = mesmo aluno ---");
        Aluno copia = new Aluno("Ana", "111", 19, "1001");
        System.out.println("ana == copia      ? " + (ana == copia));       // false
        System.out.println("ana.equals(copia) ? " + ana.equals(copia));    // true

        System.out.println("--- polimorfismo (gancho da Aula 08) ---");
        Pessoa[] pessoas = {ana, carlos};
        for (Pessoa p : pessoas) {
            System.out.println(p);      // cada uma imprime do SEU jeito
        }
    }
}
