/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Administrador;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class AdministradorDAO implements ICrudDAO<Administrador> {
    
    @Override
    public Administrador crear(List<Administrador> lista, Administrador administrador){
        if (lista == null || administrador == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }
        
        // Lógica de asignación o validación de ID
        if (administrador.getIdPersona() <= 0) {
            int maxId = 0;
            for (Administrador c : lista) {
                if (c.getIdPersona() > maxId) {
                    maxId = c.getIdPersona();
                }
            }
            administrador.setIdPersona(maxId + 1);
        } else {
            for (Administrador c : lista) {
                if (c.getIdPersona() == administrador.getIdPersona()) {
                    throw new IllegalArgumentException("Ya existe un administrador con el ID: " + administrador.getIdPersona());
                }
            }
        }

        lista.add(administrador);
        return administrador;
    }

    @Override
    public Administrador actualizar(List<Administrador> lista, Administrador administrador) {
        if (lista == null || administrador == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdPersona() == administrador.getIdPersona()) {
                lista.set(i, administrador);
                return administrador;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Administrador> lista, int idPersona){
        if (lista == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }
        return lista.removeIf(administrador -> administrador.getIdPersona()== idPersona);    
    }

    @Override
    public Administrador buscarPorId(List<Administrador> lista, int idPersona) {
        for (Administrador c: lista) {
            if(c.getIdPersona()==idPersona){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Administrador> listar(List<Administrador> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
            return Collections.unmodifiableList(lista);    
    }
    
}

