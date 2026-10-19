/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante;

import co.edu.poligran.paradigmas.restaurante.Manager.AnaManager;
import co.edu.poligran.paradigmas.restaurante.Manager.CristianManager;
import co.edu.poligran.paradigmas.restaurante.Manager.DaniloManager;
import co.edu.poligran.paradigmas.restaurante.modelo.*;

import java.util.Scanner;

public class Restaurante {

    private static final CristianManager cristianManager = new CristianManager();
    private static final AnaManager anaManager = new AnaManager();
    private static final DaniloManager daniloManager = new DaniloManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Mensajes.BIENVENIDA);

        // Control de acceso obligatorio previo
        Persona2 usuarioActivo = autenticarORegistrar();
        if (usuarioActivo == null) {
            System.out.println(Mensajes.DESPEDIDA);
            return;
        }

        System.out.println("\n==========================================");
        System.out.println(" ¡Acceso concedido! Bienvenid@, " + usuarioActivo.getNombre() + " [" + usuarioActivo.getRol() + "]");
        System.out.println("==========================================");

        // Enrutamiento directo al menú correspondiente según el rol del usuario autenticado
        String rol = usuarioActivo.getRol().toLowerCase();
        
        switch (rol) {
            case "cliente":
                menuCliente(usuarioActivo);
                break;
            case "mesero":
                menuMesero(usuarioActivo);
                break;
            case "cocinero":
                menuCocinero(usuarioActivo);
                break;
            case "domiciliario":
                menuDomiciliario(usuarioActivo);
                break;
            case "administrador":
                menuAdministrador(usuarioActivo);
                break;
            default:
                // Menú general por defecto si el rol es genérico
                menuGeneral(usuarioActivo);
                break;
        }
        
        System.out.println("\n" + Mensajes.DESPEDIDA);
    }

    private static Persona2 autenticarORegistrar() {
        while (true) {
            System.out.println("\n--- ACCESO Y REGISTRO REQUERIDO ---");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrar Persona");
            System.out.println("0. Salir");
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                int op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    System.out.print(Mensajes.PEDIR_NOMBRE_USUARIO);
                    String u = scanner.nextLine();
                    System.out.print(Mensajes.PEDIR_CONTRASENA);
                    String c = scanner.nextLine();
                    Persona2 p = anaManager.iniciarSesion(u, c);
                    if (p != null) return p;
                    System.out.println("❌ Credenciales incorrectas o el usuario no existe.");
                } else if (op == 2) {
                    String rol = capturarRol();
                    Persona2 nueva = capturarPersona(rol);
                    Persona2 registrada = anaManager.registrarPersona(nueva);
                    if (registrada != null) {
                        System.out.println("\n✅ Persona registrada exitosamente como " + registrada.getRol() + ".");
                        System.out.println("Ahora puede iniciar sesión con sus credenciales.");
                    }
                } else if (op == 0) {
                    return null;
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }
    }

    // =========================================================================
    // MENÚS ESPECÍFICOS POR CADA ROL
    // =========================================================================

    private static void menuCliente(Persona2 clienteUser) {
        int op = -1;
        Cliente clienteObj = new Cliente();
        do {
            System.out.println("\n---------------- MENÚ CLIENTE ----------------");
            System.out.println(" 1. Ver Pedidos / Consultar Estado");
            System.out.println(" 2. Pagar Pedido");
            System.out.println(" 0. Salir / Cerrar Sesión");
            System.out.println("----------------------------------------------");
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    var peds = cristianManager.listarPedidos();
                    if (peds.isEmpty()) System.out.println(Mensajes.SIN_PEDIDOS);
                    else peds.forEach(System.out::println);
                } else if (op == 2) {
                    System.out.print(Mensajes.PEDIR_ID);
                    int id = Integer.parseInt(scanner.nextLine());
                    Pedido p = cristianManager.buscarPedidoPorId(id);
                    if (p != null) {
                        String formaPago = capturarFormaPago();
                        clienteObj.pagar(p, formaPago);
                        System.out.println("✅ Pago realizado con éxito mediante " + formaPago);
                    } else {
                        System.out.println(Mensajes.PEDIDO_NO_ENCONTRADO);
                    }
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuMesero(Persona2 meseroUser) {
        int op = -1;
        do {
            System.out.println("\n---------------- MENÚ MESERO -----------------");
            System.out.println(" 1. Crear Pedido (con selección de productos y mesa)");
            System.out.println(" 2. Listar Pedidos");
            System.out.println(" 3. Marcar Pedido como Entregado");
            System.out.println(" 4. Eliminar Pedido");
            System.out.println(" 0. Salir / Cerrar Sesión");
            System.out.println("----------------------------------------------");
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    daniloManager.crearPedido(cristianManager.listarProductos());
                } else if (op == 2) {
                    var peds = cristianManager.listarPedidos();
                    if (peds.isEmpty()) System.out.println(Mensajes.SIN_PEDIDOS);
                    else peds.forEach(System.out::println);
                } else if (op == 3) {
                    daniloManager.marcarEntregado();
                } else if (op == 4) {
                    daniloManager.eliminarPedido(cristianManager.listarPedidos());
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuCocinero(Persona2 cocineroUser) {
        int op = -1;
        Cocinero cocineroObj = new Cocinero();
        do {
            System.out.println("\n--------------- MENÚ COCINERO ----------------");
            System.out.println(" 1. Ver Pedidos en Curso");
            System.out.println(" 2. Cambiar Estado de Pedido (En Preparación / Listo)");
            System.out.println(" 0. Salir / Cerrar Sesión");
            System.out.println("----------------------------------------------");
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    var peds = cristianManager.listarPedidos();
                    if (peds.isEmpty()) System.out.println(Mensajes.SIN_PEDIDOS);
                    else peds.forEach(System.out::println);
                } else if (op == 2) {
                    System.out.print(Mensajes.PEDIR_ID);
                    int id = Integer.parseInt(scanner.nextLine());
                    Pedido p = cristianManager.buscarPedidoPorId(id);
                    if (p != null) {
                        cocineroObj.verPedido(p);
                        String nuevoEstado = capturarEstadoPedido();
                        cocineroObj.estadoPedido(p, nuevoEstado);
                        cristianManager.actualizarEstadoPedido(id, nuevoEstado);
                        System.out.println(Mensajes.PEDIDO_ACTUALIZADO);
                    } else {
                        System.out.println(Mensajes.PEDIDO_NO_ENCONTRADO);
                    }
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuDomiciliario(Persona2 domiciliarioUser) {
        int op = -1;
        do {
            System.out.println("\n------------- MENÚ DOMICILIARIO --------------");
            System.out.println(" 1. Listar Pedidos Listos para Entrega");
            System.out.println(" 2. Marcar Pedido como Entregado");
            System.out.println(" 0. Salir / Cerrar Sesión");
            System.out.println("----------------------------------------------");
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    var peds = cristianManager.listarPedidos();
                    if (peds.isEmpty()) System.out.println(Mensajes.SIN_PEDIDOS);
                    else peds.forEach(System.out::println);
                } else if (op == 2) {
                    daniloManager.marcarEntregado();
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuAdministrador(Persona2 adminUser) {
        int op = -1;
        do {
            System.out.println("\n------------ MENÚ ADMINISTRADOR --------------");
            System.out.println(" 1. Gestión de Productos (Crear, Modificar, Eliminar)");
            System.out.println(" 2. Gestión de Mesas (Asignación y reasignación)");
            System.out.println(" 3. Gestión de Usuarios (Meseros, Admins, Cocineros, etc.)");
            System.out.println(" 4. Ver Tiempos y Estados de Pedidos");
            System.out.println(" 0. Salir / Cerrar Sesión");
            System.out.println("----------------------------------------------");
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    daniloManager.gestionProductos();
                } else if (op == 2) {
                    daniloManager.gestionMesas();
                } else if (op == 3) {
                    daniloManager.gestionUsuarios();
                } else if (op == 4) {
                    daniloManager.verTiempos();
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuGeneral(Persona2 usuario) {
        // Menú genérico de respaldo si el rol no coincide exactamente
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_PRINCIPAL);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 6) cristianManager.listarProductos().forEach(System.out::println);
                else if (op == 7) cristianManager.listarPedidos().forEach(System.out::println);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    // =========================================================================
    // CAPTURAS AUXILIARES
    // =========================================================================

    private static String capturarRol() {
        while (true) {
            try {
                System.out.print(Mensajes.PEDIR_ROL);
                int op = Integer.parseInt(scanner.nextLine());
                switch (op) {
                    case 1: return "Cliente";
                    case 2: return "Administrador";
                    case 3: return "Mesero";
                    case 4: return "Cocinero";
                    case 5: return "Domiciliario";
                    default: System.out.println(Mensajes.OPCION_INVALIDA);
                }
            } catch (NumberFormatException e) {
                System.out.println(Mensajes.NUMERO_INVALIDO);
            }
        }
    }

    private static Persona2 capturarPersona(String rol) {
        Persona2 p = new Persona2();
        p.setRol(rol);
        System.out.print(Mensajes.PEDIR_NOMBRE); p.setNombre(scanner.nextLine());
        System.out.print(Mensajes.PEDIR_APELLIDO); p.setApellido(scanner.nextLine());

        int tDoc = 0;
        while (true) {
            try {
                System.out.print(Mensajes.PEDIR_TIPO_DOC);
                tDoc = Integer.parseInt(scanner.nextLine());
                if (tDoc >= 1 && tDoc <= 4) { p.setTipoDoc(tDoc); break; }
                System.out.println(Mensajes.OPCION_INVALIDA);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }

        int nDoc = 0;
        while (true) {
            try {
                System.out.print(Mensajes.PEDIR_NUM_DOC);
                nDoc = Integer.parseInt(scanner.nextLine());
                if (nDoc > 100000 && nDoc < 2000000000) { p.setNumDoc(nDoc); break; }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }

        while (true) {
            System.out.print(Mensajes.PEDIR_CORREO);
            String mail = scanner.nextLine().trim();
            if (!mail.isEmpty() && mail.contains("@") && mail.contains(".")) { 
                p.setCorreo(mail); 
                break; 
            }
            System.out.println("❌ El correo debe incluir '@' y un punto.");
        }

        System.out.print(Mensajes.PEDIR_CONTRASENA); p.setContraseña(scanner.nextLine());

        long cel = 0;
        while (true) {
            try {
                System.out.print(Mensajes.PEDIR_CELULAR);
                cel = Long.parseLong(scanner.nextLine());
                if (cel >= 3000000000L && cel <= 3999999999L) { p.setCelular(cel); break; }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }

        System.out.print(Mensajes.PEDIR_NOMBRE_USUARIO); p.setNombreUsuario(scanner.nextLine());
        return p;
    }

    private static String capturarFormaPago() {
        while (true) {
            try {
                System.out.print(Mensajes.PEDIR_FORMA_PAGO);
                int op = Integer.parseInt(scanner.nextLine());
                if (op == 1) return "Efectivo";
                if (op == 2) return "Tarjeta";
                System.out.println(Mensajes.OPCION_INVALIDA);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }
    }

    private static String capturarEstadoPedido() {
        while (true) {
            try {
                System.out.print(Mensajes.PEDIR_ESTADO);
                int op = Integer.parseInt(scanner.nextLine());
                switch (op) {
                    case 1: return "PENDIENTE";
                    case 2: return "EN PREPARACIÓN";
                    case 3: return "LISTO";
                    case 4: return "ENTREGADO";
                    case 5: return "CANCELADO";
                    default: System.out.println(Mensajes.OPCION_INVALIDA);
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }
    }
}