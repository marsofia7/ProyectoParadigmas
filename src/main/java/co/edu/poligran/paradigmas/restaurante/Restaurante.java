/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante;

import co.edu.poligran.paradigmas.restaurante.Manager.AnaManager;
import co.edu.poligran.paradigmas.restaurante.Manager.CristianManager;
import co.edu.poligran.paradigmas.restaurante.modelo.*;

import java.util.Scanner;

public class Restaurante {

    private static final CristianManager cristianManager = new CristianManager();
    private static final AnaManager anaManager = new AnaManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Mensajes.BIENVENIDA);

        // Control de acceso obligatorio previo: permite registrar N personas
        // hasta que el usuario decida Iniciar Sesión exitosamente o Salir.
        Persona2 usuario = autenticarORegistrar();
        if (usuario == null) {
            System.out.println(Mensajes.DESPEDIDA);
            return;
        }

        System.out.println("\n==========================================");
        System.out.println(" ¡Acceso concedido! Bienvenid@, " + usuario.getNombre() + " (" + usuario.getRol() + ")");
        System.out.println("==========================================");

        int op = -1;
        do {
            System.out.println(Mensajes.MENU_PRINCIPAL);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                switch (op) {
                    case 1: menuClientes(); break;
                    case 2: menuAdministradores(); break;
                    case 3: menuMeseros(); break;
                    case 4: menuCocineros(); break;
                    case 5: menuDomiciliarios(); break;
                    case 6: menuProductos(); break;
                    case 7: menuPedidos(); break;
                    case 0: System.out.println(Mensajes.DESPEDIDA); break;
                    default: System.out.println(Mensajes.OPCION_INVALIDA);
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static Persona2 autenticarORegistrar() {
        int op = -1;
        while (true) {
            System.out.println("\n--- ACCESO REQUERIDO ---");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrar Persona");
            System.out.println("0. Salir");
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) { // Iniciar sesión
                    System.out.print(Mensajes.PEDIR_NOMBRE_USUARIO);
                    String u = scanner.nextLine();
                    System.out.print(Mensajes.PEDIR_CONTRASENA);
                    String c = scanner.nextLine();
                    Persona2 p = anaManager.iniciarSesion(u, c);
                    if (p != null) {
                        return p; // Retorna la persona logueada y habilita la gestión
                    }
                    System.out.println(" Credenciales incorrectas o el usuario no está registrado.");
                } else if (op == 2) { // Registrar persona
                    String rolSeleccionado = capturarRol();
                    Persona2 registrada = anaManager.registrarPersona(capturarPersona(rolSeleccionado));
                    System.out.println("\n ¡Persona registrada exitosamente con el rol de " + registrada.getRol() + "!");
                    System.out.println("Ahora puede registrar otra persona o iniciar sesión.");
                    // Continúa el bucle while para pedir de nuevo el menú inicial
                } else if (op == 0) {
                    return null;
                } else {
                    System.out.println(Mensajes.OPCION_INVALIDA);
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        }
    }

    private static void menuClientes() {
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

    private static void menuAdministradores() {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_ADMINISTRADORES);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    anaManager.registrarPersona(capturarPersona("Administrador"));
                    System.out.println("¡Administrador registrado exitosamente!");
                } else if (op == 2) anaManager.listarPersonas().forEach(System.out::println);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuMeseros() {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_MESEROS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    anaManager.registrarPersona(capturarPersona("Mesero"));
                    System.out.println("¡Mesero registrado exitosamente!");
                } else if (op == 2) anaManager.listarPersonas().forEach(System.out::println);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuCocineros() {
        int op = -1;
        Cocinero cocinero = new Cocinero();
        do {
            System.out.println(Mensajes.MENU_COCINEROS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    anaManager.registrarPersona(capturarPersona("Cocinero"));
                    System.out.println("¡Cocinero registrado exitosamente!");
                } else if (op == 4) {
                    System.out.print(Mensajes.PEDIR_ID);
                    int id = Integer.parseInt(scanner.nextLine());
                    Pedido p = cristianManager.buscarPedidoPorId(id);
                    if (p != null) {
                        cocinero.verPedido(p);
                        String nuevoEstado = capturarEstadoPedido();
                        cocinero.estadoPedido(p, nuevoEstado);
                        cristianManager.actualizarEstadoPedido(id, p.getEstado());
                    }
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuDomiciliarios() {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_DOMICILIARIOS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    anaManager.registrarPersona(capturarPersona("Domiciliario"));
                    System.out.println("¡Domiciliario registrado exitosamente!");
                } else if (op == 2) anaManager.listarPersonas().forEach(System.out::println);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuProductos() {
        int op = -1;
        do {
            System.out.println(Mensajes.MENU_PRODUCTOS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 2) {
                    System.out.print(Mensajes.PEDIR_NOMBRE);
                    String n = scanner.nextLine();
                    System.out.print(Mensajes.PEDIR_PRECIO);
                    long pr = Long.parseLong(scanner.nextLine());
                    System.out.print(Mensajes.PEDIR_DESCRIPCION);
                    String desc = scanner.nextLine();
                    Producto prod = cristianManager.registrarProducto(n, pr, desc);
                    System.out.println(Mensajes.PRODUCTO_REGISTRADO + prod.getIdProducto());
                } else if (op == 3) cristianManager.listarProductos().forEach(System.out::println);
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

    private static void menuPedidos() {
        int op = -1;
        Cliente cliente = new Cliente();
        do {
            System.out.println(Mensajes.MENU_PEDIDOS);
            System.out.print(Mensajes.SELECCIONE_OPCION);
            try {
                op = Integer.parseInt(scanner.nextLine());
                if (op == 1) {
                    System.out.print(Mensajes.PEDIR_NUMERO_MESA);
                    int m = Integer.parseInt(scanner.nextLine());
                    System.out.print(Mensajes.PEDIR_TOTAL);
                    long t = Long.parseLong(scanner.nextLine());
                    Pedido ped = cristianManager.crearPedido(m, t);
                    System.out.println(Mensajes.PEDIDO_CREADO + ped.getId());
                } else if (op == 2) cristianManager.listarPedidos().forEach(System.out::println);
                else if (op == 4) {
                    System.out.print(Mensajes.PEDIR_ID);
                    int id = Integer.parseInt(scanner.nextLine());
                    Pedido p = cristianManager.buscarPedidoPorId(id);
                    if (p != null) {
                        String formaPago = capturarFormaPago();
                        cliente.pagar(p, formaPago);
                        cristianManager.actualizarEstadoPedido(id, p.getEstado());
                    }
                }
            } catch (Exception e) { System.out.println(Mensajes.NUMERO_INVALIDO); }
        } while (op != 0);
    }

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

        // Captura de correo corregida sin bucles infinitos
        while (true) {
            System.out.print(Mensajes.PEDIR_CORREO);
            String mail = scanner.nextLine().trim();
            if (!mail.isEmpty() && mail.contains("@") && mail.contains(".")) { 
                p.setCorreo(mail); 
                break; 
            }
            System.out.println(" El correo debe incluir '@' y un punto (ej: usuario@correo.com).");
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