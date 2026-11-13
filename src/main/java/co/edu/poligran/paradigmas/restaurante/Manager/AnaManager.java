/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.dao.DomiciliarioDAO;
import co.edu.poligran.paradigmas.restaurante.dao.PedidoDAO;
import co.edu.poligran.paradigmas.restaurante.dao.Persona2DAO;
import co.edu.poligran.paradigmas.restaurante.modelo.Domiciliario;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnaManager {

    private final List<Persona2> listaPersonas = new ArrayList<>();
    private final List<Domiciliario> domiciliarios = new ArrayList<>();
    private final List<Pedido> pedidos; 


    private final Map<Integer, Integer> asignaciones = new HashMap<>();

    private final Persona2DAO personaDAO = new Persona2DAO();
    private final DomiciliarioDAO domiciliarioDAO = new DomiciliarioDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

   
    public AnaManager() {
        this(new ArrayList<>());
    }

    /**Recibe la lista de pedidos compartida, p. ej. cristianManager.getPedidos(). */
    public AnaManager(List<Pedido> pedidosCompartidos) {
        this.pedidos = pedidosCompartidos;
    }

    // =====================================================================
    // PERSONAS (funcionalidades)
    // =====================================================================

    /**
     * Registra una nueva persona validando que sus datos únicos no estén duplicados.
     */
    public Persona2 registrarPersona(Persona2 nuevaPersona) {
        if (!validarPersonaUnica(nuevaPersona)) {
            return null;
        }
        Persona2 creada = personaDAO.crear(listaPersonas, nuevaPersona);
        if (esDomiciliario(creada)) {
            domiciliarioDAO.crear(domiciliarios, aDomiciliario(creada));
        }
        return creada;
    }

    /**
     * Valida que documento, correo, usuario y celular no se repitan.
     * Permite repetir nombre y apellido.
     */
    public boolean validarPersonaUnica(Persona2 nuevaPersona) {
        for (Persona2 p : listaPersonas) {
            if (nuevaPersona.getIdPersona() > 0
                    && p.getIdPersona() == nuevaPersona.getIdPersona()) {
                continue;
            }
            if (p.getNumDoc() == nuevaPersona.getNumDoc()) {
                System.out.println(" Error: Ya existe una persona registrada con el número de documento: " + nuevaPersona.getNumDoc());
                return false;
            }
            if (p.getCorreo() != null && p.getCorreo().equalsIgnoreCase(nuevaPersona.getCorreo())) {
                System.out.println(" Error: Ya existe una persona registrada con el correo: " + nuevaPersona.getCorreo());
                return false;
            }
            if (p.getNombreUsuario() != null && p.getNombreUsuario().equalsIgnoreCase(nuevaPersona.getNombreUsuario())) {
                System.out.println(" Error: El nombre de usuario '" + nuevaPersona.getNombreUsuario() + "' ya está en uso.");
                return false;
            }
            if (p.getCelular() == nuevaPersona.getCelular()) {
                System.out.println(" Error: Ya existe una persona registrada con el número de celular: " + nuevaPersona.getCelular());
                return false;
            }
        }
        return true;
    }

    public List<Persona2> listarPersonas() {
        return personaDAO.listar(listaPersonas);
    }

    public Persona2 iniciarSesion(String usuario, String contraseña) {
        for (Persona2 p : listaPersonas) {
            if (p.iniciarSesion(usuario, contraseña)) {
                return p;
            }
        }
        return null;
    }

    /*Buscar persona por id. */
    public Persona2 buscarPorId(int id) {
        return personaDAO.buscarPorId(listaPersonas, id);
    }

    /**Buscar persona por nombre de usuario. */
    public Persona2 buscarPorUsuario(String nombreUsuario) {
        return personaDAO.buscarPorNombreUsuario(listaPersonas, nombreUsuario);
    }

    /**Actualizar los datos de una persona. Devuelve null si hay duplicados o no existe. */
    public Persona2 actualizarPersona(Persona2 persona) {
        if (!validarPersonaUnica(persona)) {
            return null;
        }
        Persona2 actualizada = personaDAO.actualizar(listaPersonas, persona);
        if (actualizada != null) {
            domiciliarioDAO.eliminar(domiciliarios, actualizada.getIdPersona());
            if (esDomiciliario(actualizada)) {
                domiciliarioDAO.crear(domiciliarios, aDomiciliario(actualizada));
            }
        }
        return actualizada;
    }

    /**Eliminar una persona. */
    public boolean eliminarPersona(int id) {
        boolean eliminada = personaDAO.eliminar(listaPersonas, id);
        if (eliminada) {
            domiciliarioDAO.eliminar(domiciliarios, id);
            asignaciones.values().removeIf(idDom -> idDom == id);
        }
        return eliminada;
    }

    private boolean esDomiciliario(Persona2 p) {
        return p != null && "Domiciliario".equalsIgnoreCase(p.getRol());
    }

    private Domiciliario aDomiciliario(Persona2 p) {
        return new Domiciliario(p.getIdPersona(), p.getNombre(), p.getApellido(),
                p.getTipoDoc(), p.getNumDoc(), p.getCorreo(), p.getContraseña(),
                p.getCelular(), p.getNombreUsuario(), p.getRol());
    }

    // =====================================================================
    // DOMICILIARIO (funcionalidades)
    // Los métodos lanzan IllegalArgumentException / IllegalStateException con
    // un mensaje claro, para mostrarlo luego con JOptionPane en la ventana.
    // =====================================================================

    public List<Domiciliario> listarDomiciliarios() {
        return domiciliarioDAO.listar(domiciliarios);
    }

    /**Domiciliarios sin pedido en curso. */
    public List<Domiciliario> listarDomiciliariosDisponibles() {
        return domiciliarios.stream()
                .filter(d -> !tienePedidoActivo(d.getIdPersona()))
                .collect(Collectors.toList());
    }

    /**Pedidos en estado LISTO que aún no tienen domiciliario. */
    public List<Pedido> listarPedidosListosParaEntrega() {
        return pedidos.stream()
                .filter(p -> "LISTO".equals(p.getEstado()) && !asignaciones.containsKey(p.getId()))
                .collect(Collectors.toList());
    }

    /**Pedidos que ya están asignados al domiciliario indicado. */
    public List<Pedido> listarPedidosAsignados(int idDomiciliario) {
        return pedidos.stream()
                .filter(p -> asignaciones.get(p.getId()) != null
                        && asignaciones.get(p.getId()) == idDomiciliario
                        && !"ENTREGADO".equals(p.getEstado()))
                .collect(Collectors.toList());
    }
    /**Asignar un domiciliario a un pedido LISTO. */
    public void asignarDomiciliarioAPedido(int idDomiciliario, int idPedido) {
        obtenerDomiciliario(idDomiciliario);
        Pedido p = obtenerPedido(idPedido);
        if (!"LISTO".equals(p.getEstado())) {
            throw new IllegalStateException("Solo se pueden asignar pedidos en estado LISTO.");
        }
        if (asignaciones.containsKey(idPedido)) {
            throw new IllegalStateException("El pedido ya tiene un domiciliario asignado.");
        }
        if (tienePedidoActivo(idDomiciliario)) {
            throw new IllegalStateException("El domiciliario ya tiene un pedido en curso.");
        }
        asignaciones.put(idPedido, idDomiciliario);
    }

    /**El domiciliario entrega el pedido que tiene asignado. */
    public String entregarPedido(int idDomiciliario, int idPedido) {
        Domiciliario d = obtenerDomiciliario(idDomiciliario);
        Pedido p = obtenerPedido(idPedido);
        Integer asignado = asignaciones.get(idPedido);
        if (asignado == null || asignado != idDomiciliario) {
            throw new IllegalStateException("El pedido no está asignado a este domiciliario.");
        }
        p.setEstado("ENTREGADO");
        p.setMarcadeTiempo(LocalDateTime.now());
        pedidoDAO.actualizar(pedidos, p);
        return d.entregarPedido(idPedido);
    }

    /**Historial de pedidos entregados por un domiciliario. */
    public List<Pedido> historialEntregas(int idDomiciliario) {
        return pedidos.stream()
                .filter(p -> asignaciones.containsKey(p.getId())
                        && asignaciones.get(p.getId()) == idDomiciliario)
                .filter(p -> "ENTREGADO".equals(p.getEstado()))
                .collect(Collectors.toList());
    }

    private boolean tienePedidoActivo(int idDomiciliario) {
        for (Map.Entry<Integer, Integer> e : asignaciones.entrySet()) {
            if (e.getValue() == idDomiciliario) {
                Pedido p = pedidoDAO.buscarPorId(pedidos, e.getKey());
                if (p != null && !"ENTREGADO".equals(p.getEstado())
                        && !"CANCELADO".equals(p.getEstado())) {
                    return true;
                }
            }
        }
        return false;
    }

    private Domiciliario obtenerDomiciliario(int idDomiciliario) {
        Domiciliario d = domiciliarioDAO.buscarPorId(domiciliarios, idDomiciliario);
        if (d == null) {
            throw new IllegalArgumentException("Domiciliario no encontrado.");
        }
        return d;
    }

    // =====================================================================
    // PEDIDO (funcionalidades)
    // Los productos del pedido se manejan con Pedido.getProductos(); la cantidad
    // de un producto es el número de veces que aparece en la lista.
    // =====================================================================

    /**Agregar un producto a un pedido PENDIENTE y recalcular el total. */
    public Pedido agregarProductoAPedido(int idPedido, Producto producto) {
        Pedido p = obtenerPedidoModificable(idPedido);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        p.getProductos().add(producto);
        calcularTotalPedido(idPedido);
        return p;
    }

    /**Quitar una unidad de un producto de un pedido PENDIENTE y recalcular el total. */
    public boolean quitarProductoDePedido(int idPedido, int idProducto) {
        Pedido p = obtenerPedidoModificable(idPedido);
        List<Producto> productos = p.getProductos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto() == idProducto) {
                productos.remove(i);
                calcularTotalPedido(idPedido);
                return true;
            }
        }
        return false;
    }

    /**Calcula el total sumando el precio de cada producto del pedido. */
    public long calcularTotalPedido(int idPedido) {
        Pedido p = obtenerPedido(idPedido);
        if (p.getProductos() == null) {
            return p.getTotal(); 
        }
        long total = 0;
        for (Producto producto : p.getProductos()) {
            total += producto.getPrecioProducto();
        }
        p.setTotal(total);
        pedidoDAO.actualizar(pedidos, p);
        return total;
    }

    /**Cambia la forma de pago (Efectivo o Tarjeta). */
    public void cambiarFormaDePago(int idPedido, String formaDePago) {
        Pedido p = obtenerPedido(idPedido);
        if (!"Efectivo".equalsIgnoreCase(formaDePago) && !"Tarjeta".equalsIgnoreCase(formaDePago)) {
            throw new IllegalArgumentException("Forma de pago no válida (Efectivo o Tarjeta).");
        }
        p.setFormaDePago(formaDePago);
        pedidoDAO.actualizar(pedidos, p);
    }

    /**Registra el pago y actualiza el estado del pedido. */
    public void registrarPago(int idPedido, String formaDePago) {
        Pedido pedido = obtenerPedido(idPedido);
        if (pedido.getFormaDePago() != null && !pedido.getFormaDePago().trim().isEmpty()) {
            throw new IllegalStateException("Este pedido ya tiene un pago registrado.");
        }
        cambiarFormaDePago(idPedido, formaDePago);
        pedido.setMarcadeTiempo(LocalDateTime.now());
        pedidoDAO.actualizar(pedidos, pedido);
    }
    /**Valida que el cliente autenticado sea dueño del pedido antes de cobrarlo. */
    public void registrarPago(int idPedido, String formaDePago, int idCliente) {
        Pedido pedido = obtenerPedido(idPedido);
        if (pedido.getIdCliente() != idCliente) {
            throw new IllegalStateException("No puedes pagar un pedido que pertenece a otro cliente.");
        }
        if (!"PENDIENTE".equalsIgnoreCase(pedido.getEstado())) {
            throw new IllegalStateException("Solo se puede pagar un pedido pendiente.");
        }
        registrarPago(idPedido, formaDePago);
    }
    /**Registra la marca de tiempo (momento actual) del pedido. */
    public LocalDateTime registrarMarcaDeTiempo(int idPedido) {
        Pedido p = obtenerPedido(idPedido);
        LocalDateTime ahora = LocalDateTime.now();
        p.setMarcadeTiempo(ahora);
        pedidoDAO.actualizar(pedidos, p);
        return ahora;
    }

    private Pedido obtenerPedido(int idPedido) {
        Pedido p = pedidoDAO.buscarPorId(pedidos, idPedido);
        if (p == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }
        return p;
    }

    private Pedido obtenerPedidoModificable(int idPedido) {
        Pedido p = obtenerPedido(idPedido);
        if (!"PENDIENTE".equals(p.getEstado())) {
            throw new IllegalStateException("Solo se pueden modificar pedidos en estado PENDIENTE.");
        }
        if (p.getProductos() == null) {
            throw new IllegalStateException("El pedido no tiene lista de productos.");
        }
        return p;
    }
}