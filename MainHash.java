import java.util.Iterator;

/**
 * Clase abstracta {@code TablaHashEncadenadora}
 * Extiende de la clase {@link TablaHash}
 *
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */
public class MainHash {
    public static void main(String[] args) {
        // Crea un dispersor usando el método hashCode de Pokemon.
        Dispersor<Pokemon> dispersor = (Pokemon p) -> p.hashCode();

        // Crea una nueva tabla hash para almacenar pokemon y entrenadores.
        TablaHash<Pokemon, String> pokedex = new TablaHashEncadenadora<>(dispersor);

        // Agrega varios pokemon junto con su entrenador.
        pokedex.agregar(new Pokemon("Pikachu", 25, "Eléctrico"), "Ash");
        pokedex.agregar(new Pokemon("Charmander", 15, "Fuego"), "Ash");
        pokedex.agregar(new Pokemon("Bulbasaur", 10, "Planta"), "Ash");
        pokedex.agregar(new Pokemon("Squirtle", 12, "Agua"), "Misty");
        pokedex.agregar(new Pokemon("Gyarados", 30, "Agua/Volador"), "Misty");
        pokedex.agregar(new Pokemon("Eevee", 8, "Normal"), "Brock");

        // Muestra el contenido completo de la tabla hash.
        System.out.println(pokedex);

        // Crea un pokemon para buscar su entrenador.
        Pokemon p = new Pokemon("Pikachu", 25, "Eléctrico");

        // Obtiene el entrenador asociado a Pikachu.
        System.out.println("Entrenador de Pikachu: " + pokedex.obtenerValorLlave(p));

        // Verifica si Charmander está en la tabla hash.
        System.out
                .println("Está Charmander en la TablaHash? " + pokedex.buscar(new Pokemon("Charmander", 15, "Fuego")));

        // Recorre e imprime todos los entrenadores almacenados.
        System.out.println("\nTodos slos entrenadores:");
        for (String entrenador : pokedex) {
            System.out.println(entrenador);
        }
        // Recorre e imprime todas las llaves de la tabla hash.
        System.out.println("\nTodos los pokemon:");
        Iterator<Pokemon> iterador = pokedex.iteradorLlaves();
        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
    }
}
