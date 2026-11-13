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

public class VentanaDomiciliario extends javax.swing.JFrame {
    private Persona2 domiciliarioActual;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JTable tablaPedidosDom;
    private javax.swing.JScrollPane scrollDom;
    private javax.swing.JButton btnAsignarPedido;
    private javax.swing.JButton btnEntregarPedido;
    private javax.swing.JButton btnCerrarSesion;
    private TablaPedidoModelo modeloTabla;

    public VentanaDomiciliario(Persona2 usuario) {
        this.domiciliarioActual = usuario;
        initComponents();
        lblInfo.setText("Domiciliario: " + domiciliarioActual.getNombre());
        cargarTabla();
    }

    private void initComponents() {
        lblInfo = new javax.swing.JLabel();
        scrollDom = new javax.swing.JScrollPane();
        
        modeloTabla = new TablaPedidoModelo(RestauranteFormulario.anaManager.listarPedidosListosParaEntrega());
        tablaPedidosDom = new javax.swing.JTable(modeloTabla);
        scrollDom.setViewportView(tablaPedidosDom);

        btnAsignarPedido = new javax.swing.JButton("Asignarme Pedido");
        btnEntregarPedido = new javax.swing.JButton("Marcar Entregado");
        btnCerrarSesion = new javax.swing.JButton("Cerrar Sesión");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de Domiciliario");

        btnAsignarPedido.addActionListener(evt -> asignarPedidoAccion());
        btnEntregarPedido.addActionListener(evt -> entregarPedidoAccion());
        btnCerrarSesion.addActionListener(evt -> { new InicioSecion().setVisible(true); this.dispose(); });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblInfo)
                    .addComponent(scrollDom, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAsignarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEntregarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblInfo)
                .addGap(18, 18, 18)
                .addComponent(scrollDom, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsignarPedido)
                    .addComponent(btnEntregarPedido)
                    .addComponent(btnCerrarSesion))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void cargarTabla() {
        modeloTabla.setPedidos(RestauranteFormulario.anaManager.listarPedidosListosParaEntrega());
    }

    private void asignarPedidoAccion() {
        try {
            String idPedidoStr = JOptionPane.showInputDialog(this, "Ingrese el ID del pedido a asignar:");
            if (idPedidoStr != null && !idPedidoStr.trim().isEmpty()) {
                int idPedido = Integer.parseInt(idPedidoStr.trim());
                RestauranteFormulario.anaManager.asignarDomiciliarioAPedido(domiciliarioActual.getIdPersona(), idPedido);
                JOptionPane.showMessageDialog(this, "¡Pedido asignado!");
                cargarTabla();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void entregarPedidoAccion() {
        try {
            String idPedidoStr = JOptionPane.showInputDialog(this, "Ingrese el ID del pedido a entregar:");
            if (idPedidoStr != null && !idPedidoStr.trim().isEmpty()) {
                int idPedido = Integer.parseInt(idPedidoStr.trim());
                String res = RestauranteFormulario.anaManager.entregarPedido(domiciliarioActual.getIdPersona(), idPedido);
                JOptionPane.showMessageDialog(this, res);
                cargarTabla();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
