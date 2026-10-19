/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.dao.DomiciliarioDAO;
import co.edu.poligran.paradigmas.restaurante.dao.PedidoDAO;
import co.edu.poligran.paradigmas.restaurante.modelo.Domiciliario;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author cdapalaciosa
 */
public class CristianManager {

    // =====================================================================
    // GESTIÓN DE CLIENTE 
    // =====================================================================

    private final List<Pedido> pedidos;
    private final PedidoDAO pedidoDAO;

    
    public CristianManager() {
        this.pedidos = new ArrayList<>();
        this.pedidoDAO = new PedidoDAO();
    }

   
    public boolean actualizarEstadoPedido(int idPedido, String nuevoEstado) {
        Pedido pedido = pedidoDAO.buscarPorId(pedidos, idPedido);
        if (pedido == null) {
            return false;
        }
        pedido.setEstado(nuevoEstado);
        pedidoDAO.actualizar(pedidos, pedido);
        return true;
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoDAO.listar(pedidos);
    }
    // =====================================================================
    // GESTIÓN DE DOMICILIARIOS 
    // =====================================================================

    private final DomiciliarioDAO domiciliarioDAO = new DomiciliarioDAO();
    private final List<Domiciliario> domiciliarios = new ArrayList<>();

    public Domiciliario registrarDomiciliario(Domiciliario domiciliario) {
        if (domiciliario == null) {
            throw new IllegalArgumentException("El domiciliario no puede ser nulo");
        }
        return domiciliarioDAO.crear(domiciliarios, domiciliario);
    }

    public List<Domiciliario> listarDomiciliarios() {
        return domiciliarioDAO.listar(domiciliarios);
    }

    public Domiciliario buscarDomiciliarioPorId(int id) {
        return domiciliarioDAO.buscarPorId(domiciliarios, id);
    }

    public boolean actualizarDomiciliario(Domiciliario domiciliario) {
        return domiciliarioDAO.actualizar(domiciliarios, domiciliario) != null;
    }

    public boolean eliminarDomiciliario(int id) {
        return domiciliarioDAO.eliminar(domiciliarios, id);
    }

    /**
     * Coordina la acción del domiciliario para entregar un pedido específico.
     */
    public String gestionarEntregaPedido(int idDomiciliario, int idPedido) {
        Domiciliario domiciliario = domiciliarioDAO.buscarPorId(domiciliarios, idDomiciliario);
        if (domiciliario == null) {
            return "Domiciliario no encontrado.";
        }
        // Llama al método que definiste en la clase modelo Domiciliario
        return domiciliario.entregarPedido(idPedido);
    }
}
