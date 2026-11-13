/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante;

import co.edu.poligran.paradigmas.restaurante.Manager.AnaManager;
import co.edu.poligran.paradigmas.restaurante.Manager.CristianManager;
import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import javax.swing.SwingUtilities;

public class RestauranteFormulario {

    // 1. Declaramos y creamos los managers de forma pública y estática
    public static CristianManager cristianManager = new CristianManager();
    
    // 2. Le pasamos la lista de pedidos de Cristian a Ana para que estén sincronizados
    public static AnaManager anaManager = new AnaManager(cristianManager.getPedidos());

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
