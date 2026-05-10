import java.util.Iterator;

/**
 * Clase abstracta {@code TablaHash}
 * Implementa la interfaz {@link Iterable}
 *
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

public abstract class TablaHash<K, V> implements Iterable<V> {

    /**
     * Clase abstracta {@code TablaHash}
     * Implementa la interfaz {@link Iterable}
     */
    protected class Entrada {

        /** Atributo que repreesenta la llave de la entrada */
        public K llave;

        /** Atributo que representa el valor de la entrada */
        public V valor;

        /**
         * Construye una nueva entrada con una llave y un valor.
         * 
         * @param llave la llave de la entrada.
         * @param valor el valor asociado a la llave.
         */
        public Entrada(K llave, V valor) {
            this.llave = llave;
            this.valor = valor;
        }

        /**
         * Devuelve una representación en cadena de la entrada.
         * 
         * @return una cadena con la llave y el valor de la entrada.
         */
        @Override
        public String toString() {
            return "(" + this.llave + " , " + this.valor + ")";
        }
    }

    /** Máxima carga permitida antes de redimensionar. */
    protected static final double MAXIMA_CARGA = 0.72;

    /** Capacidad mínima inicial (potencia de 2). */
    protected static final int MINIMA_CAPACIDAD = 64;

    /** Dispersor utilizado por la tabla. */
    protected Dispersor<K> dispersor;

    /** Número actual de elementos almacenados. */
    protected int elementos;

    protected int calcularNuevoTamanio(int x) {
        x = Math.max(x, MINIMA_CAPACIDAD);
        int log2 = (int) (Math.log(x) / Math.log(2));
        return (int) (Math.pow(2, log2 + 1));
    }

    /**
     * Devuelve la carga actual de la tabla hash.
     * 
     * @return la carga de la tabla.
     */
    public abstract double devolverCarga();

    /**
     * Agrega una llave y su valor a la tabla hash.
     * 
     * @param llave la llave a agregar.
     * @param valor el valor asociado a la llave.
     */
    public abstract void agregar(K llave, V valor);

    /**
     * Elimina la entrada asociada a una llave.
     * 
     * @param llave la llave a eliminar.
     */

    public abstract void eliminar(K llave);

    /**
     * Devuelve el valor asociado a una llave.
     * 
     * @param llave la llave a buscar.
     * @return el valor asociado a la llave.
     */
    public abstract V obtenerValorLlave(K llave);

    /**
     * Verifica si una llave se encuentra en la tabla hash.
     * 
     * @param llave la llave a buscar.
     * @return verdadero si la llave existe, falso en otro caso.
     */
    public abstract boolean buscar(K llave);

    /**
     * Redimensiona el arreglo interno de la tabla hash.
     */
    protected abstract void redimencionaArreglo();

    /**
     * Devuelve un iterador para las llaves de la tabla hash.
     * 
     * @return un iterador de llaves.
     */
    public abstract Iterator<K> iteradorLlaves();

    /**
     * Devuelve un iterador para los valores de la tabla hash.
     * 
     * @return un iterador de valores.
     */
    @Override
    public abstract Iterator<V> iterator();
}
