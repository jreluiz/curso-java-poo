/*
 * Aula 09 — por que toda classe que entra numa coleção precisa de equals/hashCode.
 * Rode com: java BuscaComEquals.java
 */
import java.util.*;

class AlunoSemEquals {
    String matricula;
    AlunoSemEquals(String matricula) { this.matricula = matricula; }
}

class AlunoComEquals {
    String matricula;
    AlunoComEquals(String matricula) { this.matricula = matricula; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AlunoComEquals outro)) return false;
        return matricula.equals(outro.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}

public class BuscaComEquals {
    public static void main(String[] args) {

        List<AlunoSemEquals> semEquals = new ArrayList<>();
        semEquals.add(new AlunoSemEquals("1001"));
        System.out.println("sem equals → contains: "
                + semEquals.contains(new AlunoSemEquals("1001")));   // false 😱

        List<AlunoComEquals> comEquals = new ArrayList<>();
        comEquals.add(new AlunoComEquals("1001"));
        System.out.println("com equals → contains: "
                + comEquals.contains(new AlunoComEquals("1001")));   // true ✅

        // O padrão de busca que você vai repetir o curso inteiro
        AlunoComEquals encontrado = buscarPorMatricula(comEquals, "1001");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.matricula);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    static AlunoComEquals buscarPorMatricula(List<AlunoComEquals> lista, String matricula) {
        for (AlunoComEquals aluno : lista) {
            if (aluno.matricula.equals(matricula)) {
                return aluno;
            }
        }
        return null;      // não achou (na Aula 14 isso vira Optional)
    }
}
