/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Cocinero;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CRISTIAN
 */
public class CocineroDAO implements ICrudDAO<Cocinero> {

    @Override
    public Cocinero crear(List<Cocinero> lista, Cocinero Cocinero) {
        if (lista == null || Cocinero == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        // Lógica de asignación o validación de ID
        if (Cocinero.getIdPersona() <= 0) {
            int maxId = 0;
            for (Cocinero c : lista) {
                if (c.getIdPersona() > maxId) {
                    maxId = c.getIdPersona();
                }
            }
            Cocinero.setIdPersona(maxId + 1);
        } else {
            for (Cocinero c : lista) {
                if (c.getIdPersona() == Cocinero.getIdPersona()) {
                    throw new IllegalArgumentException("Ya existe un cocinero con el ID: " + Cocinero.getIdPersona());
                }
            }
        }

        lista.add(Cocinero);
        return Cocinero;
    }

    @Override
    public Cocinero actualizar(List<Cocinero> lista, Cocinero Cocinero) {
        if (lista == null || Cocinero == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersona() == Cocinero.getIdPersona()) {
                lista.set(i, Cocinero);
                return Cocinero;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Cocinero> lista, int idPersona) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser null.");
        }
        return lista.removeIf(cocinero -> cocinero.getIdPersona() == idPersona);
    }

    @Override
    public Cocinero buscarPorId(List<Cocinero> lista, int id) {
        if (lista == null) {
            return null;
        }

        for (Cocinero cocinero : lista) {
            if (cocinero.getIdPersona() == id) {
                return cocinero;
            }
        }
        return null;
    }

    @Override
    public List<Cocinero> listar(List<Cocinero> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(lista);
    }

    
}
