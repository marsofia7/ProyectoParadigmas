/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        this.pedidoDAO = new PedidoDAO();
        this.productoDAO = new ProductoDAO();
        this.cocineroDAO = new CocineroDAO();
        this.domiciliarioDAO = new DomiciliarioDAO();
    }

    // ==========================================
    // GESTIÓN DE PRODUCTOS
    // ==========================================
    public Producto registrarProducto(String nombre, long precio, String descripcion) {
        // Usamos el constructor parametrizado que exige la clase Producto
        Producto p = new Producto(0, precio, nombre, descripcion);
        return productoDAO.crear(productos, p);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listar(productos);
    }

    // ==========================================
    // GESTIÓN DE PEDIDOS
    // ==========================================
    public Pedido crearPedido(int numeroMesa, long total) {
        Pedido p = new Pedido();
        p.setNumeroMesa(numeroMesa);
        p.setTotal(total);
        p.setEstado("PENDIENTE");
        p.setMarcadeTiempo(LocalDateTime.now()); // Corregido a LocalDateTime
        return pedidoDAO.crear(pedidos, p);
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.listar(pedidos);
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoDAO.buscarPorId(pedidos, id);
    }

    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        Pedido p = pedidoDAO.buscarPorId(pedidos, idPedido);
        if (p != null) {
            p.setEstado(nuevoEstado);
            p.setMarcadeTiempo(LocalDateTime.now()); // Corregido a LocalDateTime
            pedidoDAO.actualizar(pedidos, p);
            return true;
        }
        return false;
    }

    // ==========================================
    // GESTIÓN DE COCINEROS Y DOMICILIARIOS
    // ==========================================
    public Cocinero registrarCocinero(Cocinero cocinero) {
        return cocineroDAO.crear(cocineros, cocinero);
    }

    public List<Cocinero> listarCocineros() {
        return cocineroDAO.listar(cocineros);
    }
}