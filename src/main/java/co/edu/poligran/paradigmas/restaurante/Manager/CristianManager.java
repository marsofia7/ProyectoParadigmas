package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.dao.*;
import co.edu.poligran.paradigmas.restaurante.modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CristianManager {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<Producto> productos = new ArrayList<>();
    private final List<Cocinero> cocineros = new ArrayList<>();
    private final List<Domiciliario> domiciliarios = new ArrayList<>();

    private final PedidoDAO pedidoDAO;
    private final ProductoDAO productoDAO;
    private final CocineroDAO cocineroDAO;
    private final DomiciliarioDAO domiciliarioDAO;

    public CristianManager() {
        pedidoDAO = new PedidoDAO();
        productoDAO = new ProductoDAO();
        cocineroDAO = new CocineroDAO();
        domiciliarioDAO = new DomiciliarioDAO();
    }

    public Producto registrarProducto(String nombre, long precio, String descripcion) {
        Producto producto = new Producto(0, precio, nombre, descripcion);
        return productoDAO.crear(productos, producto);
    }

    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarPorId(productos, id);
    }

    public Producto actualizarProducto(Producto producto) {
        return productoDAO.actualizar(productos, producto);
    }

    public boolean eliminarProducto(int id) {
        return productoDAO.eliminar(productos, id);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listar(productos);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido crearPedido(int numeroMesa, long total) {
        return crearPedido(numeroMesa, total, -1);
    }

    public Pedido crearPedido(int numeroMesa, long total, int idCliente) {
        Pedido pedido = new Pedido();
        pedido.setNumeroMesa(numeroMesa);
        pedido.setIdCliente(idCliente);
        pedido.setTotal(total);
        pedido.setEstado("PENDIENTE");
        pedido.setProductos(new ArrayList<>());
        pedido.setMarcadeTiempo(LocalDateTime.now());
        return pedidoDAO.crear(pedidos, pedido);
    }

    public boolean eliminarPedido(int id) {
        return pedidos.removeIf(pedido -> pedido.getId() == id);
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.listar(pedidos);
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoDAO.buscarPorId(pedidos, id);
    }

    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        Pedido pedido = pedidoDAO.buscarPorId(pedidos, idPedido);

        if (pedido == null) {
            return false;
        }

        pedido.setEstado(nuevoEstado);
        pedido.setMarcadeTiempo(LocalDateTime.now());
        pedidoDAO.actualizar(pedidos, pedido);
        return true;
    }

    public Cocinero registrarCocinero(Cocinero cocinero) {
        return cocineroDAO.crear(cocineros, cocinero);
    }

    public List<Cocinero> listarCocineros() {
        return cocineroDAO.listar(cocineros);
    }
}