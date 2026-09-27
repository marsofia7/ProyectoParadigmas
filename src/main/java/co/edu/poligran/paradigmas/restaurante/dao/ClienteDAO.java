/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Cliente;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author salaG201
 */
public class ClienteDAO implements ICrudDAO<Cliente> {

    @Override
    public Cliente crear(List<Cliente> lista, Cliente Cliente) {
        if (lista == null || Cliente == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        // Asignación autoincremental si el ID es <= 0
        if (Cliente.getIdPersona() <= 0) {
            int maxId = 0;
            for (Cliente c : lista) {
                if (c.getIdPersona() > maxId) {
                    maxId = c.getIdPersona();
                }
            }
            Cliente.setIdPersona(maxId + 1);
        } else {
            // Verificar que el ID no esté duplicado
            for (Cliente c : lista) {
                if (c.getIdPersona() == Cliente.getIdPersona()) {
                    throw new IllegalArgumentException("Ya existe un cliente con el ID: " + Cliente.getIdPersona());
                }
            }
        }

        lista.add(Cliente);
        return Cliente;
    }

    @Override
    public Cliente actualizar(List<Cliente> lista, Cliente Cliente) {
        if (lista == null || Cliente == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersona() == Cliente.getIdPersona()) {
                lista.set(i, Cliente);
                return Cliente;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Cliente> lista, int idPersona) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser null.");
        }
        return lista.removeIf(cliente -> cliente.getIdPersona() == idPersona);
    }

    @Override
    public Cliente buscarPorId(List<Cliente> lista, int idPersona) {
        if (lista == null) {
            return null;
        }

        for (Cliente cliente : lista) {
            if (cliente.getIdPersona() == idPersona) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> listar(List<Cliente> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(lista);
    }
}