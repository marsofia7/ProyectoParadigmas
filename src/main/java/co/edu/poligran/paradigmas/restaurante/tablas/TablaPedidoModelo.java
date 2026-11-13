/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.tablas;

import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TablaPedidoModelo extends AbstractTableModel {
    private final String[] columnas = {"ID Pedido", "Estado", "Total ($)", "Forma de Pago", "Fecha / Tiempo"};
    private List<Pedido> pedidos;

    public TablaPedidoModelo(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() { return pedidos.size(); }

    @Override
    public int getColumnCount() { return columnas.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pe = pedidos.get(rowIndex);
        switch (columnIndex) {
            case 0: return pe.getId();
            case 1: return pe.getEstado();
            case 2: return pe.getTotal();
            case 3: return pe.getFormaDePago();
            case 4: return pe.getMarcadeTiempo();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) { return columnas[column]; }
}