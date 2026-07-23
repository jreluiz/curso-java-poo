/*
 * Aula 05 — criando e usando objetos.
 * Rode com: java Escola.java   (o Java encontra e compila Aluno.java junto)
 */
public class Escola {
    public static void main(String[] args) {

        // ── Um objeto por aluno, com seus próprios valores ──────────
        Aluno ana = new Aluno("Ana", "1001");
        ana.notas[0] = 8.0;
        ana.notas[1] = 7.0;
        ana.notas[2] = 9.0;

        Aluno leo = new Aluno("Léo", "1002");
        leo.notas[0] = 5.0;
        leo.notas[1] = 6.0;
        leo.notas[2] = 4.0;

        ana.imprimirBoletim();
        leo.imprimirBoletim();

        // ── Referência: a variável guarda o ENDEREÇO, não o objeto ──
        Aluno apelido = ana;               // NÃO copia: dá um segundo nome ao mesmo objeto
        apelido.nome = "Ana Paula";
        System.out.println(ana.nome);      // Ana Paula 😱

        // ── null: referência que não aponta para nada ───────────────
        Aluno ninguem = null;
        // System.out.println(ninguem.nome);   ← descomente: NullPointerException

        // ── Objetos cabem em arrays como qualquer outro valor ───────
        Aluno[] turma = new Aluno[3];
        turma[0] = ana;
        turma[1] = leo;
        turma[2] = new Aluno("Duda", "1003");
        turma[2].notas[0] = 10.0;
        turma[2].notas[1] = 9.0;
        turma[2].notas[2] = 8.0;

        System.out.println("--- Boletim da turma ---");
        for (Aluno aluno : turma) {
            aluno.imprimirBoletim();       // cada objeto usa os PRÓPRIOS dados
        }
    }
}
