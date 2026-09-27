/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.DetallePedido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CRISTIAN
 */
public class DetallePedidoDAO implements ICrudDAO<DetallePedido> {

    @Override
    public DetallePedido crear(List<DetallePedido> lista, DetallePedido DetallePedido) {
        if (lista == null || DetallePedido == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        // Asignación autoincremental de ID si es <= 0
        if (DetallePedido.getId() <= 0) {
            int maxId = 0;
            for (DetallePedido dp : lista) {
                if (dp.getId() > maxId) {
                    maxId = dp.getId();
                }
            }
            DetallePedido.setId(maxId + 1);
        } else {
            // Validar que el ID no esté repetido
            for (DetallePedido dp : lista) {
                if (dp.getId() == DetallePedido.getId()) {
                    throw new IllegalArgumentException("Ya existe un detalle de pedido con el ID: " + DetallePedido.getId());
                }
            }
        }

        lista.add(DetallePedido);
        return DetallePedido;
    }

    @Override
    public DetallePedido actualizar(List<DetallePedido> lista, DetallePedido DetallePedido) {
        if (lista == null || DetallePedido == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == DetallePedido.getId()) {
                lista.set(i, DetallePedido);
                return DetallePedido;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<DetallePedido> lista, int id) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser null.");
        }
        return lista.removeIf(detalle -> detalle.getId() == id);
    }

    @Override
    public DetallePedido buscarPorId(List<DetallePedido> lista, int id) {
        if (lista == null) {
            return null;
        }

        for (DetallePedido detalle : lista) {
            if (detalle.getId() == id) {
                return detalle;
            }
        }
        return null;
    }

    @Override
    public List<DetallePedido> listar(List<DetallePedido> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(lista);
    }
    
}
