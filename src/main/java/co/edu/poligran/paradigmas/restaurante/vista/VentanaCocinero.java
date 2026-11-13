/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.vista;

import co.edu.poligran.paradigmas.restaurante.RestauranteFormulario;
import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.tablas.TablaPedidoModelo;
import javax.swing.JOptionPane;

public class VentanaCocinero extends javax.swing.JFrame {
    private Persona2 cocineroActual;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaPedidosCocinero;
    private javax.swing.JScrollPane scrollPedidos;
    private javax.swing.JButton btnModificarPedido;
    private javax.swing.JButton btnCerrarSesion;
    private TablaPedidoModelo modeloTabla;

    public VentanaCocinero(Persona2 usuario) {
        this.cocineroActual = usuario;
        initComponents();
        lblTitulo.setText("Panel de Cocina - Cocinero: " + cocineroActual.getNombre());
        cargarTabla();
    }

    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        scrollPedidos = new javax.swing.JScrollPane();
        
        modeloTabla = new TablaPedidoModelo(RestauranteFormulario.anaManager.listarPedidosListosParaEntrega());
        tablaPedidosCocinero = new javax.swing.JTable(modeloTabla);
        scrollPedidos.setViewportView(tablaPedidosCocinero);

        btnModificarPedido = new javax.swing.JButton("Quitar Producto de Pedido");
        btnCerrarSesion = new javax.swing.JButton("Cerrar Sesión");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de Cocinero");

        btnModificarPedido.addActionListener(evt -> modificarPedidoAccion());
        btnCerrarSesion.addActionListener(evt -> { new InicioSecion().setVisible(true); this.dispose(); });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo)
                    .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnModificarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarPedido)
                    .addComponent(btnCerrarSesion))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void cargarTabla() {
        modeloTabla.setPedidos(RestauranteFormulario.anaManager.listarPedidosListosParaEntrega());
    }

    private void modificarPedidoAccion() {
        try {
            String idPedidoStr = JOptionPane.showInputDialog(this, "Ingrese el ID del pedido pendiente:");
            if (idPedidoStr != null && !idPedidoStr.trim().isEmpty()) {
                int idPedido = Integer.parseInt(idPedidoStr.trim());
                String idProdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del producto a quitar:");
                if (idProdStr != null && !idProdStr.trim().isEmpty()) {
                    int idProducto = Integer.parseInt(idProdStr.trim());
                    RestauranteFormulario.anaManager.quitarProductoDePedido(idPedido, idProducto);
                    JOptionPane.showMessageDialog(this, "¡Producto removido con éxito!");
                    cargarTabla();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
