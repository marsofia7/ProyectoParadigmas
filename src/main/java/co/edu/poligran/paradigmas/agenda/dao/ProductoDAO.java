/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.agenda.dao;

import co.edu.poligran.paradigmas.agenda.modelo.Producto;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CRISTIAN
 */
public class ProductoDAO implements ICrudDAO<Producto>{
     /**
     * Agrega un teléfono a la lista.
     * <p>
     * Si su id es menor o igual a cero, se le asigna el siguiente id disponible.
     * El {@code idPersona} debe asignarse antes de invocar este método.
     * </p>
     *
     * @param lista    lista en memoria de teléfonos
     * @param Producto teléfono que se desea registrar
     * @return el teléfono registrado, con su id asignado
     * @throws IllegalArgumentException si la lista o el teléfono son {@code null},
     *                                  o si ya existe un teléfono con el mismo id
     */
    @Override
    public Producto crear(List<Producto> lista, Producto Producto) {
        validar(lista, Producto);
        if (Producto.getIdProducto() <= 0) {
            Producto.setIdProducto(siguienteId(lista));
        } else if (buscarPorId(lista, Producto.getIdProducto()) != null) {
            throw new IllegalArgumentException("Ya existe una categoria con id " + Producto.getIdProducto());
        }
        lista.add(Producto);
        return Producto;
    }

    /**
     * Reemplaza el teléfono que tenga el mismo id que el recibido.
     *
     * @param lista    lista en memoria de teléfonos
     * @param telefono teléfono con los datos actualizados
     * @return el teléfono actualizado, o {@code null} si no existe uno con ese id
     * @throws IllegalArgumentException si la lista o el teléfono son {@code null}
     */
    @Override
    public Producto actualizar(List<Producto> lista, Producto telefono) {
        validar(lista, telefono);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProducto() == telefono.getIdProducto()) {
                lista.set(i, telefono);
                return telefono;
            }
        }
        return null;
    }

    /**
     * Elimina el teléfono con el id indicado.
     *
     * @param lista lista en memoria de teléfonos
     * @param idProducto    identificador del teléfono a eliminar
     * @return {@code true} si se eliminó; {@code false} si no se encontró
     */
    @Override
    public boolean eliminar(List<Producto> lista, int idProducto) {
        return lista.removeIf(t -> t.getIdProducto() == idProducto);
    }

    /**
     * Busca un teléfono por su identificador.
     *
     * @param lista lista en memoria de teléfonos
     * @param idProducto    identificador buscado
     * @return el teléfono encontrado, o {@code null} si no existe
     */
    @Override
    public Producto buscarPorId(List<Producto> lista, int idProducto) {
        for (Producto t : lista) {
            if (t.getIdProducto() == idProducto) {
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
    public List<Producto> listar(List<Producto> lista) {
        return Collections.unmodifiableList(lista);
    }

    /**
     * Calcula el siguiente identificador disponible (id máximo + 1).
     *
     * @param lista lista en memoria de teléfonos
     * @return el siguiente id; 1 si la lista está vacía
     */
    private int siguienteId(List<Producto> lista) {
        int max = 0;
        for (Producto t : lista) {
            max = Math.max(max, t.getIdProducto());
        }
        return max + 1;
    }

    /**
     * Verifica que la lista y el teléfono no sean nulos.
     *
     * @param lista    lista a validar
     * @param telefono teléfono a validar
     * @throws IllegalArgumentException si alguno de los parámetros es {@code null}
     */
    private void validar(List<Producto> lista, Producto Producto) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de Productos no puede ser nula");
        }
        if (Producto == null) {
            throw new IllegalArgumentException("El Producto no puede ser nulo");
        }
    }
}
