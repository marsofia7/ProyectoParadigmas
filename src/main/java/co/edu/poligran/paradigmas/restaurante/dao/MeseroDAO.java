/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Mesero;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class MeseroDAO implements ICrudDAO<Mesero> {
    
    @Override
    public Mesero crear(List<Mesero> lista, Mesero mesero){
        if (lista == null || mesero == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }
        
        // Lógica de asignación o validación de ID
        if (mesero.getIdPersona() <= 0) {
            int maxId = 0;
            for (Mesero c : lista) {
                if (c.getIdPersona() > maxId) {
                    maxId = c.getIdPersona();
                }
            }
            mesero.setIdPersona(maxId + 1);
        } else {
            for (Mesero c : lista) {
                if (c.getIdPersona() == mesero.getIdPersona()) {
                    throw new IllegalArgumentException("Ya existe un mesero con el ID: " + mesero.getIdPersona());
                }
            }
        }

        lista.add(mesero);
        return mesero;
    }

    @Override
    public Mesero actualizar(List<Mesero> lista, Mesero mesero) {
        if (lista == null || mesero == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersona() == mesero.getIdPersona()) {
                lista.set(i, mesero);
                return mesero;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Mesero> lista, int idPersona){
        if (lista == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }
        return lista.removeIf(mesero -> mesero.getIdPersona()== idPersona);    
    }

    @Override
    public Mesero buscarPorId(List<Mesero> lista, int idPersona) {
        for (Mesero c: lista) {
            if(c.getIdPersona()==idPersona){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Mesero> listar(List<Mesero> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
            return Collections.unmodifiableList(lista);    
    }
    
}
