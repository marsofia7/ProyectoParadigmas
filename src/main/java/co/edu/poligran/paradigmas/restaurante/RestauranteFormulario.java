/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante;

import co.edu.poligran.paradigmas.restaurante.Manager.AnaManager;
import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import javax.swing.SwingUtilities;

/**
 *
 * @author CRISTIAN
 */
public class RestauranteFormulario {

    public static AnaManager anaManager = new AnaManager(); // Instancia global única
    // ...
public static void main(String[] args) {
        // Garantiza que la interfaz gráfica se ejecute de forma segura en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instancia y muestra tu ventana de Login (InicioSecion)
                InicioSecion login = new InicioSecion();
                login.setVisible(true);
            }
        });
    }
}
