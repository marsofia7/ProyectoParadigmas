/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import java.util.Collections;
import java.util.List;

/**
 * Implementación del CRUD en memoria para la entidad Persona2
 * @author asofi
 */
public class Persona2DAO implements ICrudDAO<Persona2>{
    
    @Override
    
    public Persona2 crear (List<Persona2> lista, Persona2 objeto){
       if(lista == null || objeto == null){
           throw new IllegalArgumentException ("La lista y el objeto no pueden ser nulos.");
       } 
       if (objeto.getIdPersona() <= 0){
           int siguienteId = lista.stream().mapToInt(Persona2::getIdPersona).max().orElse(0) + 1;
           objeto.setIdPersona(siguienteId);   
       } else if (buscarPorId (lista, objeto.getIdPersona()) != null){ 
           throw new IllegalArgumentException("Ya existe una persona con el Id: " +  objeto.getIdPersona());
        }
       lista.add(objeto);
       return objeto;
    }
    
    @Override 
    public Persona2 actualizar(List<Persona2> lista, Persona2 objeto) {
        if (lista == null || objeto == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser nulos.");
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
    public boolean eliminar(List<Persona2> lista, int id) {
        if (lista == null) {
            return false;
        }
        return lista.removeIf(p -> p.getIdPersona() == id);
    }
    
    @Override
    public Persona2 buscarPorId(List<Persona2> lista, int id) {
        if (lista == null) {
            return null;
        }
        return lista.stream()
                .filter(p -> p.getIdPersona() == id)
                .findFirst()
                .orElse(null);
    }
    
     @Override
    public List<Persona2> listar(List<Persona2> lista) {
        if (lista == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(lista);
    }
       /**
     * Busca una persona por su nombre de usuario (útil para el inicio de sesión).
     * @param lista lista en memoria donde se busca
     * @param nombreUsuario nombre de usuario a buscar
     * @return la persona encontrada, o {@code null} si no existe
     */
    public Persona2 buscarPorNombreUsuario(List<Persona2> lista, String nombreUsuario) {
        if (lista == null || nombreUsuario == null) {
            return null;
        }
        return lista.stream()
                .filter(p -> nombreUsuario.equals(p.getNombreUsuario()))
                .findFirst()
                .orElse(null);
    }
}
