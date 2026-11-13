/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.tablas;

import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TablaProductoModelo extends AbstractTableModel {
    private final String[] columnas = {"ID Producto", "Nombre", "Precio ($)", "Descripción"};
    private List<Producto> productos;

    public TablaProductoModelo(List<Producto> productos) {
        this.productos = productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() { 
        return productos != null ? productos.size() : 0; 
    }

    @Override
    public int getColumnCount() { 
        return columnas.length; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto pr = productos.get(rowIndex);
        switch (columnIndex) {
            case 0: return pr.getIdProducto();
            case 1: return pr.getNombresProducto();
            case 2: return pr.getPrecioProducto();
            case 3: return pr.getDescripcionProducto();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) { 
        return columnas[column]; 
    }
}
