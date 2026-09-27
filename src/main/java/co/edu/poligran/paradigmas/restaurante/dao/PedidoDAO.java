/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cdapalaciosa
 */
public class PedidoDAO implements ICrudDAO<Pedido> {

    @Override
    public Pedido crear(List<Pedido> lista, Pedido Pedido) {
        if (lista == null || Pedido == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        // Asignación autoincremental si el ID es <= 0
        if (Pedido.getId() <= 0) {
            int maxId = 0;
            for (Pedido p : lista) {
                if (p.getId() > maxId) {
                    maxId = p.getId();
                }
            }
            Pedido.setId(maxId + 1);
        } else {
            // Verificar duplicidad de ID
            for (Pedido p : lista) {
                if (p.getId() == Pedido.getId()) {
                    throw new IllegalArgumentException("Ya existe un pedido con el ID: " + Pedido.getId());
                }
            }
        }

        lista.add(Pedido);
        return Pedido;
    }

    @Override
    public Pedido actualizar(List<Pedido> lista, Pedido Pedido) {
        if (lista == null || Pedido == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == Pedido.getId()) {
                lista.set(i, Pedido);
                return Pedido;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Pedido> lista, int id) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser null.");
        }
        return lista.removeIf(pedido -> pedido.getId() == id);
    }

    @Override
    public Pedido buscarPorId(List<Pedido> lista, int id) {
        if (lista == null) {
            return null;
        }

        for (Pedido pedido : lista) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    @Override
    public List<Pedido> listar(List<Pedido> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(lista);
    }

    
}
