/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.dao.PedidoDAO;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;

import java.util.ArrayList;
import java.util.List;
public class CristianManager {


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

    // Listar todos los pedidos
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoDAO.listar(pedidos);
    }
}
