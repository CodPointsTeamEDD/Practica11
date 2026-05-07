package estructuras.listas;
import estructuras.tdas.Coleccion;

/**
 * Interfaz {@code Lista}
 * Extiende de la clase {@link Coleccion}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */
public interface Lista<T> extends Coleccion<T> {

    /**
     * Metodo que dado un indice elimina un elemeno de la lista
     * 
     * @param indice
     */
    public void eliminar(int indice);

    /**
     * Metodo que accede dado un indice a un elemento T de la lista
     * 
     * @param indice indice al cual queremos acceder
     * @return elemento T de la lista
     */
    public T acceder(int indice);

    /**
     * Metodo que dado un elemento de tipo T devuelve su indice asociado
     * 
     * @param elemento elemento de tipo T
     * @return indice asociado al elemento de tipo T
     */
    public int devolverIndiceElemento(T elemento);

    /**
     * Metodo que devuelve la longitud de la lista
     * 
     * @return la longitud de la lista
     */
    public int devolverLongitud();

}