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

        // Control de acceso obligatorio previo: fuerza Iniciar Sesión o Registrar Persona
        Persona2 usuarioActivo = autenticarORegistrar();
        if (usuarioActivo == null) {
            System.out.println(Mensajes.DESPEDIDA);
            return;
        }

        System.out.println("\n==========================================");
        System.out.println(" ¡Acceso concedido! Bienvenid@, " + usuarioActivo.getNombre() + " [" + usuarioActivo.getRol() + "]");
        System.out.println("==========================================");

        int op = -1;
        do {
            System.out.println(Mensajes.MENU_PRINCIPAL);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                switch (op) {
                    case 1: menuClientes(usuarioActivo); break;
                    case 2: menuAdministradores(usuarioActivo); break;
                    case 3: menuMeseros(usuarioActivo); break;
                    case 4: menuCocineros(usuarioActivo); break;
                    case 5: menuDomiciliarios(usuarioActivo); break;
                    case 6: menuProductos(usuarioActivo); break;
                    case 7: menuPedidos(usuarioActivo); break;
                    case 0: System.out.println(Mensajes.DESPEDIDA); break;
                    default: System.out.println(Mensajes.OPCION_INVALIDA);
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
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
                    Persona2 registrada = anaManager.registrarPersona(capturarPersona(rol));
                    System.out.println("\n✅ Persona registrada exitosamente como " + registrada.getRol() + ".");
                    System.out.println("Ahora puede iniciar sesión con sus credenciales.");
                } else if (op == 0) {
                    return null;
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }
    }

    private static void menuClientes(Persona2 usuario) {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_CLIENTES);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    anaManager.registrarPersona(capturarPersona("Cliente"));
                    System.out.println("¡Cliente registrado exitosamente!");
                } else if (op == 2) anaManager.listarPersonas().forEach(System.out::println);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuAdministradores(Persona2 usuario) {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_ADMINISTRADORES);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    daniloManager.administradorManager(2, null); // Conecta con DaniloManager para administradores
                } else if (op == 2) {
                    daniloManager.mostrar(2);
                } else if (op == 3) {
                    // Opciones de gestión completa de usuarios provista por DaniloManager
                    daniloManager.gestionUsuarios();
                } else if (op == 4) {
                    daniloManager.gestionMesas();
                } else if (op == 0) {
                    break;
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuMeseros(Persona2 usuario) {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_MESEROS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    daniloManager.meseroManager(1, null); // Conecta con DaniloManager para meseros
                } else if (op == 2) {
                    daniloManager.mostrar(1);
                } else if (op == 0) {
                    break;
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuCocineros(Persona2 usuario) {
        int op = -1;
        Cocinero cocinero = new Cocinero();
        do {
            System.out.println(Mensajes.MENU_COCINEROS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    anaManager.registrarPersona(capturarPersona("Cocinero"));
                    System.out.println("¡Cocinero registrado!");
                } else if (op == 4) {
                    if (!usuario.getRol().equalsIgnoreCase("Cocinero") && !usuario.getRol().equalsIgnoreCase("Administrador")) {
                        System.out.println("❌ Solo Cocineros o Administradores pueden gestionar la cocina.");
                        continue;
                    }
                    System.out.print(Mensajes.PEDIR_ID);
                    int id = Integer.parseInt(scanner.nextLine());
                    Pedido p = cristianManager.buscarPedidoPorId(id);
                    if (p != null) {
                        cocinero.verPedido(p);
                        String nuevoEstado = capturarEstadoPedido();
                        cocinero.estadoPedido(p, nuevoEstado);
                        cristianManager.actualizarEstadoPedido(id, nuevoEstado);
                        System.out.println(Mensajes.PEDIDO_ACTUALIZADO);
                    } else {
                        System.out.println(Mensajes.PEDIDO_NO_ENCONTRADO);
                    }
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuDomiciliarios(Persona2 usuario) {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_DOMICILIARIOS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    anaManager.registrarPersona(capturarPersona("Domiciliario"));
                    System.out.println("¡Domiciliario registrado!");
                } else if (op == 2) anaManager.listarPersonas().forEach(System.out::println);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuProductos(Persona2 usuario) {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_PRODUCTOS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1 || op == 2) {
                    if (!usuario.getRol().equalsIgnoreCase("Administrador")) {
                        System.out.println("❌ Solo un Administrador puede gestionar productos.");
                        continue;
                    }
                    daniloManager.gestionProductos();
                } else if (op == 3) {
                    // Listado general provisto por Cristian o Danilo
                    cristianManager.listarProductos().forEach(System.out::println);
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuPedidos(Persona2 usuario) {
        int op = -1;
        Cliente clienteAux = new Cliente();
        do {
            System.out.println(Mensajes.MENU_PEDIDOS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) { // Crear pedido
                    if (!usuario.getRol().equalsIgnoreCase("Mesero") && !usuario.getRol().equalsIgnoreCase("Administrador")) {
                        System.out.println("❌ Solo un Mesero o Administrador pueden crear un pedido.");
                        continue;
                    }
                    daniloManager.crearPedido(cristianManager.listarProductos());

                } else if (op == 2) { // Listar pedidos
                    var peds = cristianManager.listarPedidos();
                    if (peds.isEmpty()) System.out.println(Mensajes.SIN_PEDIDOS);
                    else peds.forEach(System.out::println);

                } else if (op == 4) { // Actualizar estado / Pagar
                    System.out.print(Mensajes.PEDIR_ID);
                    int id = Integer.parseInt(scanner.nextLine());
                    Pedido p = cristianManager.buscarPedidoPorId(id);
                    if (p != null) {
                        String nuevoEstado = capturarEstadoPedido();
                        cristianManager.actualizarEstadoPedido(id, nuevoEstado);
                        System.out.println(Mensajes.PEDIDO_ACTUALIZADO);
                    } else {
                        System.out.println(Mensajes.PEDIDO_NO_ENCONTRADO);
                    }
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    // =========================================================================
    // CAPTURAS AUXILIARES CON VALIDACIONES
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