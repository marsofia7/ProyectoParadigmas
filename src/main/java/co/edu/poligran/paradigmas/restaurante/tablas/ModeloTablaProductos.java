/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.tablas;

import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ModeloTablaProductos {

    public static DefaultTableModel obtenerModelo(List<Producto> listaProductos) {
        String[] columnas = {"ID", "Nombre", "Descripcion", "Precio"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita que las celdas se editen directamente en la tabla
            }
        };

        for (Producto p : listaProductos) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombresProducto(),
                p.getDescripcionProducto(),
                p.getPrecioProducto()
            };
            modelo.addRow(fila);
        }

        return modelo;
    }
}
