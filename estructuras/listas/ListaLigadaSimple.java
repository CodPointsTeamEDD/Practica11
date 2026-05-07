package estructuras.listas;

/**
 * Clase concreta {@code ListaLigadaSimple}
 * Implementa la interfaz {@link Lista}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

import java.util.Iterator;

/**
 * Implementación de una lista enlazada simple genérica.
 *
 * @param <T> El tipo de elementos almacenados en la lista.
 */
public class ListaLigadaSimple<T> implements Lista<T> {

    public class Nodo {
        /**
         * Elemento almacenado en el nodo.
         */
        public T elemento;

        /**
         * Apuntador al siguiente nodo en la lista.
         */
        public Nodo siguiente;

        /**
         * Crea un nuevo nodo con el elemento proporcionado.
         *
         * @param elemento El elemento a almacenar en el nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /**
     * Un iterador para recorrer la lista enlazada simple.
     */
    private class IteradorListaSimple implements Iterator<T> {

        /**
         * El nodo siguiente al que se moverá el iterador.
         */
        private Nodo iteradorLista;

        /**
         * Crea un nuevo iterador y lo inicializa en el primer nodo de la lista.
         */
        public IteradorListaSimple() {
            iteradorLista = new Nodo(null);
            iteradorLista.siguiente = cabeza;
        }

        /**
         * Verifica si hay un siguiente elemento en la lista.
         *
         * @return true si hay un siguiente elemento, false de lo contrario.
         */
        public boolean hasNext() {
            return iteradorLista.siguiente != null;
        }

        /**
         * Obtiene el siguiente elemento en la lista y mueve el iterador al siguiente
         * nodo.
         *
         * @return El siguiente elemento en la lista.
         */
        public T next() {
            iteradorLista = iteradorLista.siguiente;
            return iteradorLista.elemento;
        }
    }

    private Nodo cabeza;

    private int longitud;

    /**
     * Constructor de una lista ligada simple
     * inicializa cabeza como null
     * inicializa la longitud en 0
     */
    public ListaLigadaSimple() {
        this.cabeza = null;
        this.longitud = 0;
    }

    /**
     * Devuelve un iterador sobre los elementos de la lista ligada simple.
     * 
     * @return un iterador que perimite recorrer los elementos de la lista en orden
     */
    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple();
    }

    /**
     * Metodo que agrega un elemento de tipo T a una lista ligada simple
     * 
     * @param elemento un elemento de tipo T que se quiere agregar a la lista ligada
     *                 simple
     */
    @Override
    public void agregar(T elemento) throws IllegalArgumentException {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser vacio");
        }

        Nodo nuevoNodo = new Nodo(elemento);

        if (this.longitud == 0) {
            Nodo cabezaN = new Nodo(elemento);
            this.cabeza = cabezaN;
            this.longitud++;
        } else {
            nuevoNodo.siguiente = this.cabeza;
            this.cabeza = nuevoNodo;
        }

        this.longitud++;
    }

    /**
     * Metodo que determina si un elemento de tipo T se encuentra en una lista
     * ligada simple
     * 
     * @param elemento elemento de tipo T que se quiere determinar si esta en la
     *                 lista ligada simple
     * @return {true} en caso de encontrarse en los elementos de la lista, {false}
     *         en otro caso
     */
    @Override
    public boolean buscar(T elemento) {
        boolean estaElemento = false;

        for (Object aux : this) {
            if (elemento.equals(aux)) {
                estaElemento = true;
                break;
            }
        }

        return estaElemento;
    }

    /**
     * Metodo que accede a un elemento de una lista ligada simple segun un indice
     * dado
     * 
     * @param i indice al cual se quiere acceder
     * @return elemento de tipo T en el indice dado
     */
    @Override
    public T acceder(int i) throws IllegalArgumentException {
        if (i < 1 || i > this.longitud) {
            throw new IllegalArgumentException("El indice indicado no se encunetra en la lista");
        }

        int contador = 0;
        Nodo actual = this.cabeza;

        while (contador != i) {
            actual = actual.siguiente;
            contador++;
        }

        return actual.elemento;
    }

    /**
     * Metodo que elimina el i-esimo elemento de la lista ligada simple
     * 
     * @param i indice el cual se quiere eliminar de la lista ligada simple
     */
    @Override
    public void eliminar(int i) {

        if (i < 1 || i > this.longitud) {
            throw new IndexOutOfBoundsException("El indice indicado no se encuentra en la lista");
        }

        if (i == 1) {
            this.cabeza = this.cabeza.siguiente;
            this.longitud--;
            return;
        }

        int contador = 1;
        Nodo actual = this.cabeza;

        while (contador < i - 1) {
            actual = actual.siguiente;
            contador++;
        }

        actual.siguiente = actual.siguiente.siguiente;
        this.longitud--;
    }

    /**
     * Metodo que elimina la primera aparicion del elemento en la lista
     * 
     * @param elemento elemento a eliminar de la lista
     */
    public void eliminar(T elemento) {
        if (this.longitud == 0) {
            return;
        }

        if (elemento.equals(this.cabeza.elemento)) {
            this.cabeza = this.cabeza.siguiente;
            this.longitud--;
        }

        Nodo anterior = null;
        Nodo aEliminar = this.cabeza;

        while (aEliminar != null) {
            if ((aEliminar.elemento).equals(elemento)) {
                anterior.siguiente = aEliminar.siguiente;
                aEliminar.siguiente = null;
                this.longitud--;
            }

            anterior = aEliminar;
            aEliminar = aEliminar.siguiente;
        }
    }

    /**
     * Metodo que devuelve el indice de la primera aparcion del elemento usado como
     * parametro
     * 
     * @param elemento elemento el cual queremos devolver su indice
     * @return el indice de la primera aparicion del elemento usado como parametro
     */
    public int devolverIndiceElemento(T elemento) throws IllegalArgumentException {
        if (!buscar(elemento)) {
            throw new IllegalArgumentException("El elemento no esta en la lista");
        }

        int contador = 0;
        for (Object aux : this) {
            if (elemento == aux) {
                return contador;
            }

            contador++;
        }

        return contador;
    }

    /**
     * Obtiene la longitud actual de la lista.
     *
     * @return La longitud de la lista.
     */
    public int devolverLongitud() {
        return this.longitud - 1;
    }

    /**
     * Obtiene el primer nodo de la lista.
     *
     * @return El primer nodo de la lista.
     */
    public Nodo darCabeza() {
        return this.cabeza;
    }

    /**
     * Devuelve una representación en cadena de la lista ligada.
     * Los elementos se muestran en orden, separados por comas y encerrados entre
     * corchetes.
     * 
     * @return una cadena con los elementos de la lista.
     */
    @Override
    public String toString() {
        String resultado = "[";
        boolean primero = true;
        for (T elemento : this) {
            if (!primero) {
                resultado += ", ";
            } else {
                primero = false;
            }
            resultado += elemento.toString();
        }

        resultado += "]";
        return resultado;
    }

}