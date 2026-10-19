package co.edu.poligran.paradigmas.restaurante;

/**
 * Centraliza todos los textos que se muestran en la consola para el sistema del restaurante.
 */
public final class Mensajes {

    private Mensajes() {
    }

    private static final String NL = System.lineSeparator();

    // ---------------------------------------------------------------- Menús
    public static final String BIENVENIDA
            = "==========================================" + NL
            + "    BIENVENIDO AL SISTEMA DE RESTAURANTE  " + NL
            + "==========================================";

    public static final String MENU_PRINCIPAL
            = NL + "------------- MENÚ PRINCIPAL -------------" + NL
            + " 1. Gestionar Clientes" + NL
            + " 2. Gestionar Administradores" + NL
            + " 3. Gestionar Meseros" + NL
            + " 4. Gestionar Cocineros" + NL
            + " 5. Gestionar Domiciliarios" + NL
            + " 6. Gestionar Productos y Categorías" + NL
            + " 7. Gestionar Pedidos" + NL
            + " 0. Salir" + NL
            + "------------------------------------------";

    public static final String MENU_CLIENTES
            = NL + "---------------- CLIENTES ----------------" + NL
            + " 1. Registrar cliente" + NL
            + " 2. Listar clientes" + NL
            + " 3. Buscar cliente por ID" + NL
            + " 4. Actualizar cliente" + NL
            + " 5. Eliminar cliente" + NL
            + " 0. Volver" + NL
            + "------------------------------------------";

    public static final String MENU_ADMINISTRADORES
            = NL + "------------ ADMINISTRADORES -------------" + NL
            + " 1. Registrar administrador" + NL
            + " 2. Listar administradores" + NL
            + " 3. Buscar administrador por ID" + NL
            + " 4. Actualizar administrador" + NL
            + " 5. Eliminar administrador" + NL
            + " 0. Volver" + NL
            + "------------------------------------------";

    public static final String MENU_MESEROS
            = NL + "----------------- MESEROS ----------------" + NL
            + " 1. Registrar mesero" + NL
            + " 2. Listar meseros" + NL
            + " 3. Buscar mesero por ID" + NL
            + " 4. Actualizar mesero" + NL
            + " 5. Eliminar mesero" + NL
            + " 0. Volver" + NL
            + "------------------------------------------";

    public static final String MENU_COCINEROS
            = NL + "---------------- COCINEROS ---------------" + NL
            + " 1. Registrar cocinero" + NL
            + " 2. Listar cocineros" + NL
            + " 3. Buscar cocinero por ID" + NL
            + " 4. Actualizar cocinero" + NL
            + " 5. Eliminar cocinero" + NL
            + " 0. Volver" + NL
            + "------------------------------------------";

    public static final String MENU_DOMICILIARIOS
            = NL + "-------------- DOMICILIARIOS -------------" + NL
            + " 1. Registrar domiciliario" + NL
            + " 2. Listar domiciliarios" + NL
            + " 3. Buscar domiciliario por ID" + NL
            + " 4. Actualizar domiciliario" + NL
            + " 5. Eliminar domiciliario" + NL
            + " 0. Volver" + NL
            + "------------------------------------------";

    public static final String MENU_PRODUCTOS
            = NL + "---------- PRODUCTOS Y CATEGORÍAS --------" + NL
            + " 1. Registrar categoría" + NL
            + " 2. Registrar producto" + NL
            + " 3. Listar productos" + NL
            + " 4. Buscar producto por ID" + NL
            + " 5. Actualizar producto" + NL
            + " 6. Eliminar producto" + NL
            + " 0. Volver" + NL
            + "------------------------------------------";

    public static final String MENU_PEDIDOS
            = NL + "----------------- PEDIDOS ----------------" + NL
            + " 1. Crear pedido" + NL
            + " 2. Listar pedidos" + NL
            + " 3. Buscar pedido por ID" + NL
            + " 4. Actualizar estado de pedido" + NL
            + " 5. Cancelar pedido" + NL
            + " 0. Volver" + NL
            + "------------------------------------------";

    // ------------------------------------------------------------- Generales
    public static final String SELECCIONE_OPCION = "Seleccione una opción: ";
    public static final String OPCION_INVALIDA = "Opción no válida. Intente de nuevo.";
    public static final String NUMERO_INVALIDO = "Debe ingresar un número válido.";
    public static final String NUMERO_NEGATIVO = "El valor no puede ser negativo.";
    public static final String CAMPO_OBLIGATORIO = "Este campo es obligatorio.";
    public static final String OPERACION_CANCELADA = "Operación cancelada.";
    public static final String DESPEDIDA = "¡Hasta pronto!";
    public static final String SEPARADOR = "------------------------------------------";

    // -------------------------------------------------------------- Persona (Atributos comunes)
    public static final String PEDIR_ID = "ID: ";
    public static final String PEDIR_NOMBRE = "Nombre: ";
    public static final String PEDIR_APELLIDO = "Apellido: ";
    
    // Nuevo apartado de Rol numerado con las 5 funcionalidades/roles
    public static final String PEDIR_ROL
            = "Seleccione el rol de la persona:" + NL
            + " 1. Cliente" + NL
            + " 2. Administrador" + NL
            + " 3. Mesero" + NL
            + " 4. Cocinero" + NL
            + " 5. Domiciliario" + NL
            + "Seleccione una opción (1-5): ";

    public static final String PEDIR_TIPO_DOC 
            = "Tipo de Documento:" + NL
            + " 1. Cédula de Ciudadanía (C.C.)" + NL
            + " 2. NIT" + NL
            + " 3. Tarjeta de Identidad (T.I.)" + NL
            + " 4. Cédula de Extranjería (C.E.)" + NL
            + "Seleccione una opción (1-4): ";
            
    public static final String PEDIR_NUM_DOC = "Número de Documento: ";
    public static final String PEDIR_CORREO = "Correo electrónico: ";
    public static final String PEDIR_CONTRASENA = "Contraseña: ";
    public static final String PEDIR_CELULAR = "Celular: ";
    public static final String PEDIR_NOMBRE_USUARIO = "Nombre de usuario: ";

    // -------------------------------------------------------------- Pedido
    public static final String PEDIR_NUMERO_MESA = "Número de mesa: ";
    public static final String PEDIR_TOTAL = "Total: ";
    public static final String PEDIR_ESTADO 
            = "Estado del pedido:" + NL
            + " 1. PENDIENTE" + NL
            + " 2. EN PREPARACIÓN" + NL
            + " 3. LISTO" + NL
            + " 4. ENTREGADO" + NL
            + " 5. CANCELADO" + NL
            + "Seleccione una opción (1-5): ";
            
    public static final String PEDIR_FORMA_PAGO 
            = "Forma de pago:" + NL
            + " 1. Efectivo" + NL
            + " 2. Tarjeta" + NL
            + "Seleccione una opción (1-2): ";
            
    public static final String PEDIDO_CREADO = "Pedido creado exitosamente con ID: ";
    public static final String PEDIDO_ACTUALIZADO = "Pedido actualizado correctamente.";
    public static final String PEDIDO_ELIMINADO = "Pedido eliminado o cancelado correctamente.";
    public static final String PEDIDO_NO_ENCONTRADO = "No se encontró el pedido.";
    public static final String SIN_PEDIDOS = "No hay pedidos registrados.";

    // -------------------------------------------------------------- Producto y Categoría
    public static final String PEDIR_DESCRIPCION = "Descripción: ";
    public static final String PEDIR_PRECIO = "Precio: ";
    public static final String PRODUCTO_REGISTRADO = "Producto registrado con ID: ";
    public static final String CATEGORIA_REGISTRADA = "Categoría registrada con ID: ";
    public static final String PRODUCTO_NO_ENCONTRADO = "No se encontró el producto.";
    public static final String SIN_PRODUCTOS = "No hay productos registrados.";
}