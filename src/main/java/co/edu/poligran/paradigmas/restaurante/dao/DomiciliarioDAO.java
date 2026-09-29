/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Domiciliario;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author cdapalaciosa
 */
public class DomiciliarioDAO implements ICrudDAO<Domiciliario> {

    @Override
    public Domiciliario crear(List<Domiciliario> lista, Domiciliario Domiciliario){
        if (lista == null || Domiciliario == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }
        
        // Lógica de asignación o validación de ID
        if (Domiciliario.getIdPersona() <= 0) {
            int maxId = 0;
            for (Domiciliario c : lista) {
                if (c.getIdPersona() > maxId) {
                    maxId = c.getIdPersona();
                }
            }
            Domiciliario.setIdPersona(maxId + 1);
        } else {
            for (Domiciliario c : lista) {
                if (c.getIdPersona() == Domiciliario.getIdPersona()) {
                    throw new IllegalArgumentException("Ya existe un mesero con el ID: " + Domiciliario.getIdPersona());
                }
            }
        }

        lista.add(Domiciliario);
        return Domiciliario;
    }

    @Override
    public Domiciliario actualizar(List<Domiciliario> lista, Domiciliario Domiciliario) {
        if (lista == null || Domiciliario == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersona() == Domiciliario.getIdPersona()) {
                lista.set(i, Domiciliario);
                return Domiciliario;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Domiciliario> lista, int idPersona){
        if (lista == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }
        return lista.removeIf(mesero -> mesero.getIdPersona()== idPersona);    
    }

    @Override
    public Domiciliario buscarPorId(List<Domiciliario> lista, int idPersona) {
        for (Domiciliario c: lista) {
            if(c.getIdPersona()==idPersona){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Domiciliario> listar(List<Domiciliario> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
            return Collections.unmodifiableList(lista);    
    }
}
