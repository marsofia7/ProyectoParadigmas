/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.dao;

import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CRISTIAN
 */
public class ProductoDAO implements ICrudDAO<Producto>{

    @Override
    public Producto crear(List<Producto> lista, Producto Producto) {
        validar(lista, Producto);
        if (Producto.getIdProducto() <= 0) {
            Producto.setIdProducto(siguienteId(lista));
        } else if (buscarPorId(lista, Producto.getIdProducto()) != null) {
            throw new IllegalArgumentException("Ya existe un producto con id " + Producto.getIdProducto());
        }
        lista.add(Producto);
        return Producto;
    }

    @Override
    public Producto actualizar(List<Producto> lista, Producto producto) {
        validar(lista, producto);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProducto() == producto.getIdProducto()) {
                lista.set(i, producto);
                return producto;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(List<Producto> lista, int idProducto) {
        return lista.removeIf(t -> t.getIdProducto() == idProducto);
    }

    @Override
    public Producto buscarPorId(List<Producto> lista, int idProducto) {
        for (Producto t : lista) {
            if (t.getIdProducto() == idProducto) {
                return t;
            }
        }
        return null;
    }
    @Override
    public List<Producto> listar(List<Producto> lista) {
        return Collections.unmodifiableList(lista);
    }

    private int siguienteId(List<Producto> lista) {
        int max = 0;
        for (Producto t : lista) {
            max = Math.max(max, t.getIdProducto());
        }
        return max + 1;
    }

    private void validar(List<Producto> lista, Producto Producto) {
        if (lista == null) {
            throw new IllegalArgumentException("La lista de Productos no puede ser nula");
        }
        if (Producto == null) {
            throw new IllegalArgumentException("El Producto no puede ser nulo");
        }
    }
}
