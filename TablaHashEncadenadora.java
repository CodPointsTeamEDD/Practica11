import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import estructuras.listas.*;

public class TablaHashEncadenadora<K, V> extends TablaHash<K, V> {

    /* Clase privada para iteradores de TablaHash. */
    private class Iterador {

        /* En qué lista estamos. */
        private int indice;
        /* Iterador auxiliar. */
        private Iterator<Entrada> iterador;

        /* Construye un nuevo iterador, auxiliándose de las listas de la * TablaHash. */
        public Iterador() {
            ListaDoblementeLigada<Entrada> listini = new ListaDoblementeLigada<Entrada>();
            for (int i = 0; i < entradas.length; i++) {
                if (entradas[i] != null) {
                    for (Entrada entrada : entradas[i]) {
                        listini.agregar(entrada);
                    }
                }
            }
            this.iterador = listini.iterator();
        }

        /* Nos dice si hay una siguiente entrada. */
        public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa la siguiente entrada. */
        public Entrada siguiente() {
            return iterador.next();
        }
    }

    /* Clase privada para iteradores de llaves de TablaHash. */
    private class IteradorLlaves extends Iterador implements Iterator<K> {

        /* Construye un nuevo iterador de llaves del TablaHash. */
        public IteradorLlaves() {
            super();
        }

        /* Regresa el siguiente elemento. */
        @Override
        public K next() {
            return siguiente().llave;
        }
    }

    /* Clase privada para iteradores de valores de una TablaHash. */
    private class IteradorValores extends Iterador implements Iterator<V> {

        /* Construye un nuevo iterador de llaves de la TablaHash. */
        public IteradorValores() {
            super();
        }

        /* Regresa el siguiente elemento. */
        @Override
        public V next() {
            return siguiente().valor;
        }
    }

    /*
     * Arreglo de listas que representa la tabla hash que utiliza el método de
     * encadenamiento para manejar coliciones.
     */
    private ListaDoblementeLigada<Entrada>[] entradas;

    /*
     * Truco para crear un arreglo genérico. Es necesario hacerlo así por cómo
     * Java implementa sus genéricos; de otra forma obtenemos advertencias del
     * compilador.
     */
    @SuppressWarnings("unchecked")
    private ListaDoblementeLigada<Entrada>[] crearNuevoArreglo(int n) {
        return (ListaDoblementeLigada<Entrada>[]) Array.newInstance(ListaDoblementeLigada.class, n);
    }

    /**
     * Construye una TablaHash con una capacidad inicial y dispersor
     * predeterminados.
     */
    public TablaHashEncadenadora() {
        this(MINIMA_CAPACIDAD, (K p) -> p.hashCode());
    }

    /**
     * Construye una TablaHash con una capacidad inicial definida por el
     * usuario, y un dispersor predeterminado.
     * 
     * @param capacidad la capacidad a utilizar.
     */
    public TablaHashEncadenadora(int capacidad) {
        this(capacidad, (K p) -> p.hashCode());
    }

    /**
     * Construye una TablaHash con una capacidad inicial predeterminada, y un
     * dispersor definido por el usuario.
     * 
     * @param dispersor el dispersor a utilizar.
     */
    public TablaHashEncadenadora(Dispersor<K> dispersor) {
        entradas = crearNuevoArreglo(MINIMA_CAPACIDAD);
        this.dispersor = dispersor;
    }

    /**
     * Construye una TablaHash con una capacidad inicial y un método de
     * dispersor definidos por el usuario.
     * 
     * @param capacidad la capacidad inicial de una TablaHash.
     * @param dispersor el dispersor a utilizar.
     */
    public TablaHashEncadenadora(int capacidad, Dispersor<K> dispersor) {
        this.dispersor = dispersor;
        if (capacidad < MINIMA_CAPACIDAD) {
            entradas = crearNuevoArreglo(MINIMA_CAPACIDAD);
        } else {
            capacidad = calcularNuevoTamanio(capacidad);
            entradas = crearNuevoArreglo(capacidad);
        }
        elementos = 0;
    }

    @Override
    public void agregar(K llave, V valor) {
        if (llave == null || valor == null) {
            throw new IllegalArgumentException("No puede haber valores nulos");
        }

        int i = dispersor.dispersa(llave) & (entradas.length - 1);

        if (this.entradas[i] == null) {
            ListaDoblementeLigada<Entrada> L = new ListaDoblementeLigada<>();
            entradas[i] = L;
        }

        for (Entrada e : entradas[i]) {
            if (e.llave.equals(llave)) {
                e.valor = valor;
                return;
            }
        }

        Entrada entrada = new Entrada(llave, valor);

        entradas[i].agregar(entrada);

        elementos++;

        if (this.devolverCarga() >= MAXIMA_CARGA) {
            redimencionaArreglo();
        }

    }

    @Override
    public V obtenerValorLlave(K llave) throws IllegalArgumentException {
        if(llave == null){
            throw new IllegalArgumentException("La llave no puede ser nula");
        }

        int i = dispersor.dispersa(llave) & (entradas.length - 1);

        if(this.entradas[i] == null){
            throw new IllegalArgumentException("No puede ser nula la entrada");
        }

        for(Entrada e : entradas[i]){
            
            if(e.llave.equals(llave)){
                return e.valor;
            }
        }

        throw new IllegalArgumentException("No existe la llave");
    }

    @Override
    public boolean buscar(K llave) {
        if (llave == null) {
            return false;
        }

        int i = dispersor.dispersa(llave) & (entradas.length - 1);

        if (this.entradas[i] == null) {
            return false;
        }

        for (Entrada e : entradas[i]) {
            if (e.llave.equals(llave)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void eliminar(K llave) {
        if (llave == null || !this.buscar(llave)) {
            throw new IllegalArgumentException("Debe haber una llave valida para buscar");
        }

        int i = dispersor.dispersa(llave) & (entradas.length - 1);

        for (Entrada e : entradas[i]) {
            if (e.llave.equals(llave)) {
                this.entradas[i].eliminar(e);
            }
        }

        this.elementos--;
    }

    public int devolverElementos() {
        return this.elementos;
    }

    public Iterator<K> iteradorLlaves() {
        return new IteradorLlaves();
    }

    @Override
    public Iterator<V> iterator() {
        return new IteradorValores();
    }

    @Override
    public double devolverCarga() {
        return (this.elementos) / (this.entradas.length);
    }

    @Override
    protected void redimencionaArreglo() {
        int tamanio = calcularNuevoTamanio(this.entradas.length);
        ListaDoblementeLigada<Entrada>[] viejasEntradas = this.entradas;
        ListaDoblementeLigada<Entrada> nuevoArreglo[] = crearNuevoArreglo(tamanio);

        this.entradas = nuevoArreglo;
        this.elementos = 0;

        for (ListaDoblementeLigada<Entrada> lista : viejasEntradas) {
            if (lista != null) {
                for (Entrada e : lista) {
                    this.agregar(e.llave, e.valor);
                }
            }
        }
    }

    @Override
    public String toString() {
        String cadena = "";
        for (int i = 0; i < this.entradas.length; i++) {
            cadena += "entradas[" + i + "] = " + this.entradas[i] + "\n";
        }
        return cadena;
    }
}
