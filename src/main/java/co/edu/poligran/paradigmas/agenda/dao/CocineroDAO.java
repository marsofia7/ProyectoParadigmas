/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.agenda.dao;

import co.edu.poligran.paradigmas.agenda.modelo.Cocinero;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CRISTIAN
 */
public class CocineroDAO implements ICrudDAO<Cocinero> {

    @Override
    public Cocinero crear(List<Cocinero> lista, Cocinero objeto) {
        if (lista == null || objeto == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        // Lógica de asignación o validación de ID
        if (objeto.getIdPersona() <= 0) {
            int maxId = 0;
            for (Cocinero c : lista) {
                if (c.getIdPersona() > maxId) {
                    maxId = c.getIdPersona();
                }
            }
            objeto.setIdPersona(maxId + 1);
        } else {
            for (Cocinero c : lista) {
                if (c.getIdPersona() == objeto.getIdPersona()) {
                    throw new IllegalArgumentException("Ya existe un cocinero con el ID: " + objeto.getIdPersona());
                }
            }
        }

        lista.add(objeto);
        return objeto;
    }

    @Override
    public Cocinero actualizar(List<Cocinero> lista, Cocinero objeto) {
        if (lista == null || objeto == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersona() == objeto.getIdPersona()) {
                lista.set(i, objeto);
                return objeto;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Cocinero> lista, int id) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista no puede ser null.");
        }
        return lista.removeIf(cocinero -> cocinero.getIdPersona() == id);
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
