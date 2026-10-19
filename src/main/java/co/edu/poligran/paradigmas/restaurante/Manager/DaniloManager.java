/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DaniloManager {

    private final Scanner tecla;
    private final List<Producto> productosPedido;
    private final List<Pedido> listaPedidos;

    public DaniloManager(Scanner tecla, List<Producto> productos, List<Pedido> pedidos) {
        this.tecla = tecla;
        this.productosPedido = productos;
        this.listaPedidos = pedidos;
    }

    public List<Producto> getProductosPedido() {
        return productosPedido;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void gestionProductos() {
        int opcion = 0;
        boolean salir = false;

        do {
            System.out.println("\nOpciones de Gestión de Producto:");
            System.out.println("1. Crear Producto.");
            System.out.println("2. Eliminar Producto.");
            System.out.println("3. Modificar Producto.");
            System.out.println("4. Volver al Menú Anterior.");
            System.out.print("\nSeleccione una Opción: ");

            try {
                opcion = Integer.parseInt(tecla.nextLine().trim());
            } catch (Exception e) {
                System.out.println("\nSolo se aceptan números enteros.");
                continue;
            }

            switch (opcion) {
                case 1:
                    crearProducto(productosPedido);
                    break;
                case 2:
                    eliminarProducto(productosPedido);
                    break;
                case 3:
                    modificarProducto(productosPedido);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("\nOpción no válida.");
                    break;
            }
        } while (!salir);
    }

    public void crearProducto(List<Producto> productosRestaurante) {
        System.out.print("Ingrese el Nombre del Producto: ");
        String nombre = tecla.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.print("El nombre no puede estar vacío. Ingrese el Nombre: ");
            nombre = tecla.nextLine().trim();
        }

        System.out.print("Ingrese la Descripción del Producto: ");
        String descripcion = tecla.nextLine().trim();

        long precio = 0;
        boolean control = false;

        do {
            try {
                System.out.print("Ingrese el Precio del Producto: ");
                precio = Long.parseLong(tecla.nextLine().trim());
                if (precio <= 0) {
                    System.out.println("\nNo se admiten precios menores o iguales a cero.\n");
                } else {
                    control = true;
                }
            } catch (Exception e) {
                System.out.println("\nSolo se aceptan números enteros para el precio.\n");
            }
        } while (!control);

        int maxId = 0;
        for (Producto p : productosRestaurante) {
            if (p.getIdProducto() > maxId) {
                maxId = p.getIdProducto();
            }
        }
        int nuevoId = maxId + 1;

        Producto nuevo = new Producto(nuevoId, precio, nombre, descripcion);
        productosRestaurante.add(nuevo);
        System.out.println("\nProducto creado y registrado con éxito con el ID #" + nuevoId);
    }

    public boolean eliminarProducto(List<Producto> productosRestaurante, int idProducto) {
        Iterator<Producto> it = productosRestaurante.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getIdProducto() == idProducto) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void eliminarProducto(List<Producto> productosRestaurante) {
        if (productosRestaurante.isEmpty()) {
            System.out.println("\nNo hay productos registrados para eliminar.");
            return;
        }

        mostrarMenuProductos(productosRestaurante);

        int idBuscado = 0;
        boolean control = false;

        do {
            try {
                System.out.print("\nIngrese el ID del Producto que desea eliminar: ");
                idBuscado = Integer.parseInt(tecla.nextLine().trim());
                if (idBuscado <= 0) {
                    System.out.println("\nEl ID debe ser un número entero positivo.");
                } else {
                    control = true;
                }
            } catch (Exception e) {
                System.out.println("\nSolo se admiten números.");
            }
        } while (!control);

        boolean eliminado = eliminarProducto(productosRestaurante, idBuscado);
        if (eliminado) {
            System.out.println("\nProducto con ID #" + idBuscado + " eliminado exitosamente.");
        } else {
            System.out.println("\nNo se encontró ningún producto con el ID #" + idBuscado);
        }
    }

    public void modificarProducto(List<Producto> productosRestaurante) {
        if (productosRestaurante.isEmpty()) {
            System.out.println("\nNo hay productos registrados para modificar.");
            return;
        }

        mostrarMenuProductos(productosRestaurante);

        int idBuscado = 0;
        boolean control = false;

        do {
            try {
                System.out.print("\nIngrese el ID del Producto que desea modificar: ");
                idBuscado = Integer.parseInt(tecla.nextLine().trim());
                if (idBuscado <= 0) {
                    System.out.println("\nEl ID debe ser un número entero positivo.");
                } else {
                    control = true;
                }
            } catch (Exception e) {
                System.out.println("\nSolo se admiten números.");
            }
        } while (!control);

        Producto productoEncontrado = null;
        for (Producto p : productosRestaurante) {
            if (p.getIdProducto() == idBuscado) {
                productoEncontrado = p;
                break;
            }
        }

        if (productoEncontrado == null) {
            System.out.println("\nNo se encontró ningún producto con el ID #" + idBuscado);
            return;
        }

        System.out.println("\nModificando producto: " + productoEncontrado.getNombresProducto());
        System.out.println("Deje el campo vacío y presione Enter si desea conservar el valor actual.");

        System.out.print("Nuevo Nombre [" + productoEncontrado.getNombresProducto() + "]: ");
        String nuevoNombre = tecla.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            productoEncontrado.setNombresProducto(nuevoNombre);
        }

        System.out.print("Nueva Descripción [" + productoEncontrado.getDescripcionProducto() + "]: ");
        String nuevaDesc = tecla.nextLine().trim();
        if (!nuevaDesc.isEmpty()) {
            productoEncontrado.setDescripcionProducto(nuevaDesc);
        }

        System.out.print("Nuevo Precio [$" + productoEncontrado.getPrecioProducto() + "]: ");
        String nuevoPrecioStr = tecla.nextLine().trim();
        if (!nuevoPrecioStr.isEmpty()) {
            try {
                long nuevoPrecio = Long.parseLong(nuevoPrecioStr);
                if (nuevoPrecio > 0) {
                    productoEncontrado.setPrecioProducto(nuevoPrecio);
                } else {
                    System.out.println("Precio inválido. Se conserva el valor anterior.");
                }
            } catch (Exception e) {
                System.out.println("Formato de precio inválido. Se conserva el valor anterior.");
            }
        }

        System.out.println("\nProducto modificado exitosamente.");
    }

    public void mostrarMenuProductos(List<Producto> productosRestaurante) {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        if (productosRestaurante.isEmpty()) {
            System.out.println("No hay productos disponibles.");
            return;
        }
        for (Producto p : productosRestaurante) {
            System.out.println("ID [" + p.getIdProducto() + "] - " 
                    + p.getNombresProducto() 
                    + " | Precio: $" + p.getPrecioProducto() 
                    + " | Desc: " + p.getDescripcionProducto());
        }
    }

    public void crearPedido(List<Producto> productosDisponibles) {
        if (productosDisponibles.isEmpty()) {
            System.out.println("\nNo hay productos creados en el restaurante. Registre productos antes de crear un pedido.");
            return;
        }

        int numMesa = 0;
        boolean controlMesa = false;
        do {
            try {
                System.out.print("\nIngrese el Número de Mesa: ");
                numMesa = Integer.parseInt(tecla.nextLine().trim());
                if (numMesa <= 0) {
                    System.out.println("El número de mesa debe ser mayor a 0.");
                } else {
                    controlMesa = true;
                }
            } catch (Exception e) {
                System.out.println("Solo se admiten números.");
            }
        } while (!controlMesa);

        List<Producto> productosSeleccionados = new ArrayList<>();
        boolean agregarMas = true;

        do {
            mostrarMenuProductos(productosDisponibles);
            System.out.print("\nIngrese el ID del Producto que desea agregar al pedido: ");
            int idProd = 0;
            try {
                idProd = Integer.parseInt(tecla.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Solo se admiten números.");
                continue;
            }

            Producto seleccionado = null;
            for (Producto p : productosDisponibles) {
                if (p.getIdProducto() == idProd) {
                    seleccionado = p;
                    break;
                }
            }

            if (seleccionado != null) {
                productosSeleccionados.add(seleccionado);
                System.out.println("Producto '" + seleccionado.getNombresProducto() + "' agregado al pedido.");
            } else {
                System.out.println("No existe ningún producto con el ID #" + idProd);
            }

            System.out.print("¿Desea agregar otro producto? (S/N): ");
            String resp = tecla.nextLine().trim().toLowerCase();
            if (!resp.equals("s") && !resp.equals("si")) {
                agregarMas = false;
            }
        } while (agregarMas);

        if (productosSeleccionados.isEmpty()) {
            System.out.println("\nNo se agregaron productos. El pedido no fue creado.");
            return;
        }

        long total = 0;
        for (Producto p : productosSeleccionados) {
            total += p.getPrecioProducto();
        }

        int maxId = 0;
        for (Pedido p : listaPedidos) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        int nuevoIdPedido = maxId + 1;

        Pedido nuevoPedido = new Pedido(nuevoIdPedido, numMesa, total, "PENDIENTE", LocalDateTime.now(), null, productosSeleccionados);
        listaPedidos.add(nuevoPedido);
        System.out.println("\nPedido #" + nuevoIdPedido + " creado exitosamente para la Mesa " + numMesa + ". Total: $" + total);
    }

    public void marcarEntregado() {
        if (listaPedidos.isEmpty()) {
            System.out.println("\nNo hay pedidos registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE PEDIDOS ---");
        for (Pedido p : listaPedidos) {
            System.out.println("ID [" + p.getId() + "] - Mesa: " + p.getNumeroMesa() + " | Estado: " + p.getEstado() + " | Total: $" + p.getTotal());
        }

        int idBuscado = 0;
        boolean control = false;
        do {
            try {
                System.out.print("\nIngrese el ID del Pedido a marcar como ENTREGADO: ");
                idBuscado = Integer.parseInt(tecla.nextLine().trim());
                if (idBuscado <= 0) {
                    System.out.println("El ID debe ser mayor a 0.");
                } else {
                    control = true;
                }
            } catch (Exception e) {
                System.out.println("Solo se admiten números.");
            }
        } while (!control);

        Pedido encontrado = null;
        for (Pedido p : listaPedidos) {
            if (p.getId() == idBuscado) {
                encontrado = p;
                break;
            }
        }

        if (encontrado != null) {
            encontrado.setEstado("ENTREGADO");
            System.out.println("\nPedido #" + idBuscado + " actualizado a estado ENTREGADO.");
        } else {
            System.out.println("\nNo se encontró el pedido con ID #" + idBuscado);
        }
    }

    public void eliminarPedido(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("\nNo hay pedidos registrados para eliminar.");
            return;
        }

        System.out.println("\n--- LISTA DE PEDIDOS ---");
        for (Pedido p : pedidos) {
            System.out.println("ID [" + p.getId() + "] - Mesa: " + p.getNumeroMesa() + " | Estado: " + p.getEstado());
        }

        int idBuscado = 0;
        boolean control = false;
        do {
            try {
                System.out.print("\nIngrese el ID del Pedido a eliminar: ");
                idBuscado = Integer.parseInt(tecla.nextLine().trim());
                if (idBuscado <= 0) {
                    System.out.println("El ID debe ser mayor a 0.");
                } else {
                    control = true;
                }
            } catch (Exception e) {
                System.out.println("Solo se admiten números.");
            }
        } while (!control);

        Iterator<Pedido> it = pedidos.iterator();
        boolean eliminado = false;
        while (it.hasNext()) {
            Pedido p = it.next();
            if (p.getId() == idBuscado) {
                it.remove();
                eliminado = true;
                break;
            }
        }

        if (eliminado) {
            System.out.println("\nPedido #" + idBuscado + " eliminado exitosamente.");
        } else {
            System.out.println("\nNo se encontró ningún pedido con ID #" + idBuscado);
        }
    }

    public void gestionMesas() {
        System.out.println("\n--- GESTIÓN DE MESAS ---");
        if (listaPedidos.isEmpty()) {
            System.out.println("No hay pedidos asignados a mesas en este momento.");
            return;
        }
        for (Pedido p : listaPedidos) {
            System.out.println("Mesa " + p.getNumeroMesa() + " -> Pedido #" + p.getId() + " | Estado: " + p.getEstado());
        }
    }

    public void gestionUsuarios() {
        System.out.println("\n--- GESTIÓN DE USUARIOS ---");
        System.out.println("Función reservada para consulta de usuarios registrados.");
    }

    public void verTiempos() {
        System.out.println("\n--- TIEMPOS Y ESTADOS DE PEDIDOS ---");
        if (listaPedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        for (Pedido p : listaPedidos) {
            System.out.println("Pedido #" + p.getId() + " - Mesa " + p.getNumeroMesa() + " - Estado Actual: " + p.getEstado() + " - Fecha: " + p.getMarcadeTiempo());
        }
    }
}
