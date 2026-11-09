package co.edu.poligran.paradigmas.restaurante.util;

/**
 * Validaciones de formato compartidas por las dos interfaces de usuario
 * (la de consola y la gráfica).
 * <p>
 * Es una clase de utilidad: no se puede instanciar y todos sus métodos son
 * estáticos.
 * </p>
 *
 * @version 1.0
 */
public final class Validaciones {

    /**
     * Patrón de un número telefónico: inicia con un dígito o con {@code +} y
     * continúa con dígitos, espacios o guiones, para un total de 7 a 15
     * caracteres.
     */
    public static final String REGEX_TELEFONO = "^[+\\d][\\d\\s-]{6,14}$";

    /**
     * Patrón simplificado de una cuenta de correo electrónico, del estilo
     * {@code usuario@dominio.com}.
     */
    public static final String REGEX_CORREO = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$";

    /**
     * Constructor privado: esta clase no está pensada para instanciarse.
     */
    private Validaciones() {
    }

    /**
     * Indica si el texto recibido tiene formato de número telefónico.
     *
     * @param numero texto a validar; puede ser {@code null}
     * @return {@code true} si cumple el patrón {@link #REGEX_TELEFONO}
     */
    public static boolean esTelefono(String numero) {
        return numero != null && numero.matches(REGEX_TELEFONO);
    }

    /**
     * Indica si el texto recibido tiene formato de cuenta de correo.
     *
     * @param cuenta texto a validar; puede ser {@code null}
     * @return {@code true} si cumple el patrón {@link #REGEX_CORREO}
     */
    public static boolean esCorreo(String cuenta) {
        return cuenta != null && cuenta.matches(REGEX_CORREO);
    }

    /**
     * Indica si el texto tiene contenido una vez descartados los espacios.
     *
     * @param texto texto a validar; puede ser {@code null}
     * @return {@code true} si el texto no es nulo ni está vacío
     */
    public static boolean tieneContenido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
}
