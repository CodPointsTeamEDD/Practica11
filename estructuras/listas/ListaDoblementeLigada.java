package estructuras.listas;

/**
 * Clase concreta {@code ListaDoblementeLigada}
 * Implementa la interfaz {@link Lista}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementación de una lista doblemente enlazada genérica.
 *
 * @param <T> El tipo de elementos almacenados en la lista.
 */

public class ListaDoblementeLigada<T> implements Lista<T> {

     /**
      * Clase privada de un nodo de la lista
      */
     public class Nodo {
          /**
           * Elemento almacenado en el nodo
           */
          public T elemento;

          /**
           * apuntador al siguiente nodo en la lista
           */
          public Nodo siguiente;

          /**
           * Apuntador al anterior nodo en la lista
           */
          public Nodo anterior;

          /**
           * Crea un nuevo nodo con el elemento proporcionado
           * 
           * @param e el elemento a almacenar en el nodo
           */
          public Nodo(T e) {
               this.elemento = e;
          }
     }

     /**
      * Un iterador para recorrer la lista doblemente ligada
      */
     private class IteradorDoubleLinkedList implements Iterator<T> {

          /**
           * EL nodo anterior al que se movera el iterador
           */
          public Nodo anterior;

          /**
           * EL nodo siguiente al que se movera el iterador
           */
          public Nodo siguiente;

          /**
           * Crea un nuevo iteradir y lo inicializa en el primer nodo de la lista
           */
          public IteradorDoubleLinkedList() {
               siguiente = cabeza;
          }

          @Override

          /**
           * Verifica si hat un siguiente elemento en la lista
           * 
           * @returntrue si hay un siguiente elemento, false de lo contario
           */
          public boolean hasNext() {
               return siguiente != null;
          }

          @Override

          /**
           * Regresa el siguiente elemento del iterador.
           *
           * @return el siguiente elemento en la lista
           * @throws NoSuchElementException si no hay más elementos
           */
          public T next() {
               if (siguiente == null) {
                    throw new NoSuchElementException("El elemento es null");
               }
               anterior = siguiente;
               siguiente = siguiente.siguiente;
               return anterior.elemento;
          }
     }

     /**
      * Primer nodo de la lista
      */
     public Nodo cabeza;

     /**
      * Ultimo nodo de la lista
      */
     public Nodo rabo;

     /**
      * Cantidad de nodos que hay en la lista
      */
     private int longitud;

     /**
      * Devuelve un iterador sobre los elementos de la lista doblemente ligada.
      * 
      * @return un iterador que perimite recorrer los elementos de la lista en orden
      */
     public Iterator<T> iterator() {
          return new IteradorDoubleLinkedList();
     }

     /**
      * Constructor de una lista ligada simple
      * inicializa cabeza como null
      * inicializa el rabo como null
      * inicializa la longitud en 0
      */
     public ListaDoblementeLigada() {
          this.cabeza = null;
          this.rabo = null;
          this.longitud = 0;
     }

     /**
      * Metodo que agrega un elemento de tipo T a una lista doblemente ligada
      * 
      * @param elemento un elemento de tipo T que se quiere agregar a la lista
      *                 doblemente ligada
      * @throws IllegalArgumentException si el elemento a agregar es nulo
      * 
      */
     @Override
     public void agregar(T elemento) throws IllegalArgumentException {
          if (elemento == null) {
               throw new IllegalArgumentException("El elemento a agregar no puede ser nulo");
          }

          Nodo nuevo = new Nodo(elemento);

          if (this.longitud == 0) {
               this.cabeza = nuevo;
               this.rabo = nuevo;
          } else {
               this.cabeza.anterior = nuevo;
               nuevo.siguiente = this.cabeza;
               this.cabeza = nuevo;
          }

          this.longitud++;
     }

     /**
      * Metodo que dado un elemento de tipo T, elimina a dicho elemento de la lista
      * doblemente liagda
      * 
      * @param elemento elemetno a eliminar de la lista doblemente ligada
      */
     @Override
     public void eliminar(T elemento) {
          if (this.longitud == 0) {
               return;
          }

          if (elemento.equals(this.cabeza.elemento)) {
               this.cabeza = this.cabeza.siguiente;

               if (this.cabeza != null) {
                    this.cabeza.anterior = null;
                    this.longitud--;
                    return;
               } else {
                    this.rabo = null;
                    this.longitud--;
                    return;
               }
          }

          if (elemento.equals(this.rabo.elemento)) {
               this.rabo = this.rabo.anterior;
               this.rabo.siguiente = null;
               return;
          }

          Nodo actual = this.cabeza.siguiente;
          while (actual != null) {
               if ((actual.elemento).equals(elemento)) {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    this.longitud--;
                    break;
               }

               actual = actual.siguiente;
          }
     }

     /**
      * 
      * Metodo que dado un elemento de tipo T determina si el elemento esta en la
      * lista doblemente ligada
      * 
      * @param elemento elemento a determinar si esta en la lista doblemente ligada
      * @return {true} en caso de encontrarse en los elementos de la lista, {false}
      *         en otro caso
      */
     @Override
     public boolean buscar(T elemento) {
          boolean estaElemento = false;

          for (T aux : this) {
               if (elemento.equals(aux)) {
                    estaElemento = true;
                    break;
               }
          }

          return estaElemento;
     }

     /**
      * Metodo que dado un indice, elimina a dicho indice de la lista doblemente
      * ligada
      * 
      * @param i indice a eliminar de la lista doblemente ligada
      * @throws IndexOutOfBoundsException en caso de de dar un indice fuera de rango
      */
     @Override
     public void eliminar(int i) {
          if (i < 1 || i > this.longitud - 1) {
               throw new IndexOutOfBoundsException("El indice indicado no se encuentra en la lista");
          }

          if (i == 0) {
               this.cabeza = this.cabeza.siguiente;
               if (this.cabeza != null) {
                    this.cabeza.anterior = null;
                    this.longitud--;
                    return;
               } else {
                    this.rabo = null;
                    return;
               }
          }

          if (i == this.longitud - 1) {
               this.rabo = this.rabo.anterior;
               this.rabo.siguiente = null;
               this.longitud--;
               return;
          }

          Nodo actual;

          if (i <= (this.longitud) / 2) {
               actual = this.cabeza;
               int contador = 0;

               while (contador < i) {
                    actual = actual.siguiente;
                    contador++;
               }

          } else {

               actual = this.rabo;
               int contador = this.longitud - 1;
               while (contador > i) {
                    actual = actual.anterior;
                    contador--;
               }
          }

          actual.anterior.siguiente = actual.siguiente;
          actual.siguiente.anterior = actual.anterior;
          this.longitud--;
     }

     /**
      * Metodo que dado un indice, accede al elemento del nodo asociado al indice de
      * la lista doblemente ligada
      * 
      * @param i indice al cual queremos acceder
      * @return elemento de tipo T en el nodo del indice indicado
      * @throws IllegalArgumentException en caso de dar un indice fuera de rango
      */
     @Override
     public T acceder(int i) throws IllegalArgumentException {
          if (i < 0 || i > this.longitud) {
               throw new IllegalArgumentException("El indice dado no esta en la lista");
          }

          Nodo actual = null;
          int contador = 0;

          if (i < (this.longitud / 2)) {
               actual = this.cabeza;

               while (contador != i) {
                    actual = actual.siguiente;
                    contador++;
               }
          } else {
               actual = this.rabo;
               contador = this.longitud - 1;

               while (contador != i) {
                    actual = actual.anterior;
                    contador--;
               }
          }

          return actual.elemento;
     }

     /**
      * Metodo que dado un elemento de tipo T, devuelve el indice del nodo en el cual se encuentra dicho elemento
      * @param elemento elemento el cual queremos saber su indice
      * @return un entero que representa el indice asociado al elemento
      * @thorws IllegalArgumentException en caso de que el elemento no se encuentre en la lista
      */
     @Override
     public int devolverIndiceElemento(T elemento) throws IllegalArgumentException {
          if (!buscar(elemento)) {
               throw new IllegalArgumentException("El elemento no esta en la lista");
          }

          int contador = 0;

          for (T aux : this) {
               if (elemento == aux) {
                    break;
               } else {
                    contador++;
               }
          }
          return contador;
     }

     /**
      * Metodo que devuelve la longitud de la lista doblemente ligada
      * @return un enetero que representa la longitud de la lista doblemente ligada
      */
     @Override
     public int devolverLongitud() {
          return this.longitud;
     }

     /**
      * Metodo que agrega un elemento de tipo T al final de la lista doblemente ligada
      * @param elemento elemento agregar a la lista doblemente ligada
      * @throws IllegalArgumentException en caso de que el elemento a agregar sea null
      */
     public void agregarFinal(T elemento) throws IllegalArgumentException {
          if (elemento == null) {
               throw new IllegalArgumentException("El elemento no puede ser nulo");
          }

          Nodo nuevo = new Nodo(elemento);

          if (this.longitud == 0) {
               this.cabeza = nuevo;
               this.rabo = nuevo;
               this.longitud++;
          } else {
               this.rabo.siguiente = nuevo;
               nuevo.anterior = this.rabo;
               this.rabo = nuevo;
               this.longitud++;
          }
     }

     /**
      * Metodo que devuelve una nueva lista doblemente ligada con los elementos en orden inverso respecto a la lista original
      * @return la lista doblemenete ligada con los elementos en orden inverso
      */
     public ListaDoblementeLigada<T> reversa() {
          ListaDoblementeLigada<T> reversa = new ListaDoblementeLigada<>();
          Nodo actual = this.rabo;

          while (actual != null) {
               reversa.agregarFinal(actual.elemento);
               actual = actual.anterior;
          }

          return reversa;
     }

     /**
      * Metodo que dado un indice accede al nodo asociado al indice
      * @param i indice que representa el nodo al cual queremos acceder
      * @return Nodo con el indice asociado
      */
     private Nodo accederNodo(int i) {
          if (i < 0 || i > this.longitud) {
               throw new IllegalArgumentException("El indice dado no esta en la lista");
          }

          Nodo actual = null;
          int contador;

          if (i < (this.longitud / 2)) {
               actual = this.cabeza;
               contador = 0;

               while (contador < i) {
                    actual = actual.siguiente;
                    contador++;
               }
          } else {
               actual = this.rabo;
               contador = this.longitud - 1;

               while (contador > i) {
                    actual = actual.anterior;
                    contador--;
               }
          }

          return actual;
     }

    /**
     * Devuelve una representación en cadena de la lista doblemente ligada
     * Los elementos se muestran en orden, separados por comas y encerrados entre
     * corchetes.
     * 
     * @return una cadena con los elementos de la lista.
     */
     @Override
     public String toString() {
          String s = "[";
          int cont = 0;
          for (T elem : this) {
               if (cont == 0) {
                    s = s + elem.toString();
                    cont++;
               } else {
                    s = s + "," + elem.toString();
               }
          }
          s = s + "]";
          return s;
     }
}