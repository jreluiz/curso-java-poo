/*
 * Aula 09 — List, Map e Set na prática.
 * Rode com: java Colecoes.java
 */
import java.util.*;

public class Colecoes {
    public static void main(String[] args) {

        System.out.println("--- List: ordem importa, repetição permitida ---");
        List<String> nomes = new ArrayList<>();      // declare pela interface List
        nomes.add("Ana");
        nomes.add("Bruno");
        nomes.add("Carla");
        nomes.add(1, "Beto");                        // insere na posição 1

        System.out.println(nomes);                   // [Ana, Beto, Bruno, Carla]
        System.out.println("size: " + nomes.size()); // 4 — size() é MÉTODO
        System.out.println("get(0): " + nomes.get(0));
        System.out.println("contains(Ana): " + nomes.contains("Ana"));
        System.out.println("indexOf(Carla): " + nomes.indexOf("Carla"));

        nomes.set(0, "Ana Paula");
        nomes.remove("Beto");                        // remove pelo objeto
        nomes.remove(0);                             // remove pela posição
        System.out.println(nomes);                   // [Bruno, Carla]

        System.out.println("--- percorrendo ---");
        for (String nome : nomes) {
            System.out.println("  " + nome);
        }
        for (int i = 0; i < nomes.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + nomes.get(i));
        }

        System.out.println("--- removendo em bloco: use removeIf ---");
        List<Integer> numeros = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        numeros.removeIf(n -> n % 2 != 0);           // remove os ímpares
        System.out.println(numeros);                 // [2, 4, 6]
        // for (Integer n : numeros) { numeros.remove(n); }
        //   ↑ descomente: ConcurrentModificationException

        System.out.println("--- Map: busca por chave ---");
        Map<String, String> agenda = new HashMap<>();
        agenda.put("Ana", "27 99999-0001");
        agenda.put("Bruno", "27 99999-0002");
        agenda.put("Ana", "27 98888-0001");          // chave repetida SUBSTITUI

        System.out.println(agenda.get("Ana"));                  // 27 98888-0001
        System.out.println(agenda.get("Ninguém"));              // null
        System.out.println(agenda.containsKey("Bruno"));        // true
        System.out.println("size: " + agenda.size());           // 2

        for (Map.Entry<String, String> entrada : agenda.entrySet()) {
            System.out.println("  " + entrada.getKey() + " → " + entrada.getValue());
        }

        System.out.println("--- Set: sem repetição ---");
        Set<String> cursos = new HashSet<>();
        cursos.add("Java");
        cursos.add("Python");
        cursos.add("Java");                          // ignorado
        System.out.println(cursos.size());           // 2
    }
}
