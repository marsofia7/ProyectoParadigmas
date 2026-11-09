/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.tablas;

import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ModeloTablaPedidos {

    public static DefaultTableModel obtenerModelo(List<Pedido> listaPedidos) {
        String[] columnas = {"ID Pedido", "Mesa", "Total", "Estado", "Forma de Pago", "Fecha"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Pedido p : listaPedidos) {
            Object[] fila = {
                p.getId(),
                p.getNumeroMesa(),
                p.getTotal(),
                p.getEstado(),
                p.getFormaDePago(),
                p.getMarcadeTiempo()
            };
            modelo.addRow(fila);
        }

        return modelo;
    }
}
