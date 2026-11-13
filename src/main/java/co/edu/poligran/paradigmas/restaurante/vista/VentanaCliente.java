/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.vista;

import co.edu.poligran.paradigmas.restaurante.RestauranteFormulario;
import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import javax.swing.JOptionPane;

public class VentanaCliente extends javax.swing.JFrame {
    private Persona2 clienteActual;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JButton btnEfectivo;
    private javax.swing.JButton btnTarjeta;
    private javax.swing.JButton btnCerrarSesion;

    public VentanaCliente(Persona2 usuario) {
        this.clienteActual = usuario;
        initComponents();
        lblBienvenida.setText("Bienvenido, Cliente: " + clienteActual.getNombre());
    }

    private void initComponents() {
        lblBienvenida = new javax.swing.JLabel();
        btnEfectivo = new javax.swing.JButton("Pagar en Efectivo");
        btnTarjeta = new javax.swing.JButton("Pagar con Tarjeta");
        btnCerrarSesion = new javax.swing.JButton("Cerrar Sesión");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de Cliente");

        btnEfectivo.addActionListener(evt -> cambiarMetodoPago("Efectivo"));
        btnTarjeta.addActionListener(evt -> cambiarMetodoPago("Tarjeta"));
        btnCerrarSesion.addActionListener(evt -> { new InicioSecion().setVisible(true); this.dispose(); });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBienvenida)
                    .addComponent(btnEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(btnTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblBienvenida)
                .addGap(35, 35, 35)
                .addComponent(btnEfectivo)
                .addGap(18, 18, 18)
                .addComponent(btnTarjeta)
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSesion)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void cambiarMetodoPago(String formaPago) {
        try {
            String idPedidoStr = JOptionPane.showInputDialog(this, "Ingrese el ID de su pedido activo:");
            if (idPedidoStr != null && !idPedidoStr.trim().isEmpty()) {
                int idPedido = Integer.parseInt(idPedidoStr.trim());
                RestauranteFormulario.anaManager.cambiarFormaDePago(idPedido, formaPago);
                JOptionPane.showMessageDialog(this, "¡Método de pago establecido a: " + formaPago + "!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}