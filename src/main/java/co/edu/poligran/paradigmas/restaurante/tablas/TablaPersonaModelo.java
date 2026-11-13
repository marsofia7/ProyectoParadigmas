/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.tablas;

import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TablaPersonaModelo extends AbstractTableModel {
    private final String[] columnas = {"ID", "Nombre", "Apellido", "Tipo Doc", "N° Doc", "Correo", "Usuario", "Rol"};
    private List<Persona2> personas;

    public TablaPersonaModelo(List<Persona2> personas) {
        this.personas = personas;
    }

    public void setPersonas(List<Persona2> personas) {
        this.personas = personas;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() { return personas.size(); }

    @Override
    public int getColumnCount() { return columnas.length; }

    @Override
    public String getColumnName(int columnIndex) { return columnas[columnIndex]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona2 p = personas.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getIdPersona();
            case 1: return p.getNombre();
            case 2: return p.getApellido();
            case 3: return p.getTipoDoc();
            case 4: return p.getNumDoc();
            case 5: return p.getCorreo();
            case 6: return p.getNombreUsuario();
            case 7: return p.getRol();
            default: return null;
        }
    }
}
