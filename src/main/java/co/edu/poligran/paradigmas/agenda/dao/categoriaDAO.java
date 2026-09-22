/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.agenda.dao;

import co.edu.poligran.paradigmas.agenda.modelo.Categoria;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CRISTIAN
 */
public class categoriaDAO implements ICrudDAO<Categoria> {

    @Override
    public Categoria crear(List<Categoria> lista, Categoria Categoria) {
    validar(lista, Categoria);
        if (Categoria.getId() <= 0) {
            Categoria.setId(siguienteId(lista));
        } else if (buscarPorId(lista, Categoria.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un teléfono con id " + Categoria.getId());
        }
        lista.add(Categoria);
        return Categoria;}
    /**
     * Reemplaza el teléfono que tenga el mismo id que el recibido.
     *
     * @param lista    lista en memoria de teléfonos
     * @param telefono teléfono con los datos actualizados
     * @return el teléfono actualizado, o {@code null} si no existe uno con ese id
     * @throws IllegalArgumentException si la lista o el teléfono son {@code null}
     */
    @Override
    public Categoria actualizar(List<Categoria> lista, Categoria Categoria) {
        validar(lista, Categoria);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == Categoria.getId()) {
                lista.set(i, Categoria);
                return Categoria;
            }
        }
        return null;
    }
    /**
     * Elimina el teléfono con el id indicado.
     *
     * @param lista lista en memoria de teléfonos
     * @param id    identificador del teléfono a eliminar
     * @return {@code true} si se eliminó; {@code false} si no se encontró
     */
    @Override
    public boolean eliminar(List<Categoria> lista, int id) {
    return lista.removeIf(t -> t.getId() == id);}
    /**
     * Busca un teléfono por su identificador.
     *
     * @param lista lista en memoria de teléfonos
     * @param id    identificador buscado
     * @return el teléfono encontrado, o {@code null} si no existe
     */
    @Override
    public Categoria buscarPorId(List<Categoria> lista, int id) {
    for (Categoria t : lista) {
            if (t.getId()== id) {
                return t;
            }
        }
        return null;
    }
    /**
     * Obtiene todos los teléfonos de la lista.
     *
     * @param lista lista en memoria de teléfonos
     * @return vista no modificable de la lista
     */
    @Override
    public List<Categoria> listar(List<Categoria> lista) {
        return Collections.unmodifiableList(lista);
    }
    /**
     * Calcula el siguiente identificador disponible (id máximo + 1).
     *
     * @param lista lista en memoria de teléfonos
     * @return el siguiente id; 1 si la lista está vacía
     */
    private int siguienteId(List<Categoria> lista) {
        int max = 0;
        for (Categoria t : lista) {
            max = Math.max(max, t.getId());
        }
        return max + 1;
    }
    /**
     * Verifica que la lista y el teléfono no sean nulos.
     *
     * @param lista    lista a validar
     * @param Categoria teléfono a validar
     * @throws IllegalArgumentException si alguno de los parámetros es {@code null}
     */
    private void validar(List<Categoria> lista, Categoria telefono) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de teléfonos no puede ser nula");
        }
        if (telefono == null) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo");
        }
    }
}