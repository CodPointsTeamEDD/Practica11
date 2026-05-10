/**
 * Interfaz {@code Dispersor<K>}
 * 
 * @author Luis Fernando Quintana López
 * @author Erick Xavier Martinez Briones
 * @version 1.0.0
 * @since 2026
 * 
 */

@FunctionalInterface
public interface Dispersor<K> {

    /**
     * Calcula y devuelve el valor hash asociado a una llave.
     * 
     * @param llave la llave a dispersar.
     * @return el valor hash de la llave.
     */
    public int dispersa(K llave);
}
