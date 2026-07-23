/*
 * Aula 03 — a armadilha mais importante do bloco: == x .equals()
 * Rode com: java IgualdadeStrings.java
 */
public class IgualdadeStrings {
    public static void main(String[] args) {
        String a = "Java";
        String b = "Java";
        String c = new String("Java");

        System.out.println(a == b);          // true  ← funcionou por acidente (mesmo literal)
        System.out.println(a == c);          // false ← MESMO texto, objetos diferentes
        System.out.println(a.equals(c));     // true  ← compara o CONTEÚDO ✅

        // O caso real: texto montado em tempo de execução (como o que vem do Scanner)
        String digitado = "Ja" + "va".toUpperCase().toLowerCase();
        System.out.println("digitado == a  ? " + (digitado == a));        // false
        System.out.println("digitado.equals(a)? " + digitado.equals(a));  // true

        // Formas seguras
        System.out.println("Java".equals(digitado));              // evita NullPointerException
        System.out.println("JAVA".equalsIgnoreCase(digitado));    // ignora maiúsculas

        // Regra do curso: == só para primitivos; para objetos, sempre .equals()
        int x = 5, y = 5;
        System.out.println(x == y);          // true — aqui o == é o correto
    }
}
