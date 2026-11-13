/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.vista;

import co.edu.poligran.paradigmas.restaurante.RestauranteFormulario;
import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.tablas.TablaProductoModelo;
import co.edu.poligran.paradigmas.restaurante.tablas.TablaPedidoModelo;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentanaMesero extends javax.swing.JFrame {
    private Persona2 meseroActual;
    private javax.swing.JTabbedPane jTabbedPane1;

    // Pestaña Productos
    private javax.swing.JPanel panelProductos;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JScrollPane scrollProductos;
    private TablaProductoModelo modeloProductos;

    // Pestaña Pedidos
    private javax.swing.JPanel panelPedidos;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JScrollPane scrollPedidos;
    private TablaPedidoModelo modeloPedidos;
    private javax.swing.JButton btnCrearPedido;
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnRegistrarTiempo;

    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel lblMesero;

    public VentanaMesero(Persona2 usuario) {
        this.meseroActual = usuario;
        initComponents();
        lblMesero.setText("Panel de Mesero: " + meseroActual.getNombre());
        actualizarTablas();
    }

    private void initComponents() {
        lblMesero = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        // 1. Pestaña de Productos (Para que el mesero los consulte)
        panelProductos = new javax.swing.JPanel();
        modeloProductos = new TablaProductoModelo(RestauranteFormulario.cristianManager.getProductos());
        tablaProductos = new javax.swing.JTable(modeloProductos);
        scrollProductos = new javax.swing.JScrollPane(tablaProductos);

        javax.swing.GroupLayout gl_p = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(gl_p);
        gl_p.setHorizontalGroup(gl_p.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_p.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        gl_p.setVerticalGroup(gl_p.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_p.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Menú de Productos", panelProductos);

        // 2. Pestaña de Pedidos (Crear y cambiar estados)
        panelPedidos = new javax.swing.JPanel();
        modeloPedidos = new TablaPedidoModelo(RestauranteFormulario.cristianManager.getPedidos());
        tablaPedidos = new javax.swing.JTable(modeloPedidos);
        scrollPedidos = new javax.swing.JScrollPane(tablaPedidos);
        
        btnCrearPedido = new javax.swing.JButton("Crear Nuevo Pedido");
        btnCambiarEstado = new javax.swing.JButton("Cambiar Estado");
        btnRegistrarTiempo = new javax.swing.JButton("Registrar Marca de Tiempo");

        btnCrearPedido.addActionListener(evt -> crearPedidoAccion());
        btnCambiarEstado.addActionListener(evt -> cambiarEstadoPedidoAccion());
        btnRegistrarTiempo.addActionListener(evt -> registrarTiempoAccion());

        javax.swing.GroupLayout gl_ped = new javax.swing.GroupLayout(panelPedidos);
        panelPedidos.setLayout(gl_ped);
        gl_ped.setHorizontalGroup(gl_ped.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_ped.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(gl_ped.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gl_ped.createSequentialGroup()
                        .addComponent(btnCrearPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        gl_ped.setVerticalGroup(gl_ped.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_ped.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(gl_ped.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearPedido)
                    .addComponent(btnCambiarEstado)
                    .addComponent(btnRegistrarTiempo))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Gestión de Pedidos", panelPedidos);

        btnCerrarSesion = new javax.swing.JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(evt -> { new InicioSecion().setVisible(true); this.dispose(); });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMesero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMesero)
                    .addComponent(btnCerrarSesion))
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void actualizarTablas() {
        modeloProductos.setProductos(RestauranteFormulario.cristianManager.getProductos());
        modeloPedidos.setPedidos(RestauranteFormulario.cristianManager.getPedidos());
    }

    private void crearPedidoAccion() {
        try {
            List<Producto> productosDisponibles = RestauranteFormulario.cristianManager.getProductos();
            if (productosDisponibles.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay productos registrados en el restaurante.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String mesaStr = JOptionPane.showInputDialog(this, "Ingrese el Número de Mesa:");
            if (mesaStr == null || mesaStr.trim().isEmpty()) return;
            int numMesa = Integer.parseInt(mesaStr.trim());
            if (numMesa <= 0) {
                JOptionPane.showMessageDialog(this, "El número de mesa debe ser mayor a 0.");
                return;
            }

            List<Producto> productosSeleccionados = new ArrayList<>();
            boolean agregarMas = true;

            while (agregarMas) {
                String idProdStr = JOptionPane.showInputDialog(this, "Ingrese el ID del producto que desea agregar al pedido:");
                if (idProdStr == null || idProdStr.trim().isEmpty()) break;
                int idProd = Integer.parseInt(idProdStr.trim());

                Producto seleccionado = null;
                for (Producto p : productosDisponibles) {
                    if (p.getIdProducto() == idProd) {
                        seleccionado = p;
                        break;
                    }
                }

                if (seleccionado != null) {
                    productosSeleccionados.add(seleccionado);
                    JOptionPane.showMessageDialog(this, "Producto '" + seleccionado.getNombresProducto() + "' agregado.");
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un producto con el ID #" + idProd, "Error", JOptionPane.ERROR_MESSAGE);
                }

                int opcion = JOptionPane.showConfirmDialog(this, "¿Desea agregar otro producto?", "Continuar", JOptionPane.YES_NO_OPTION);
                if (opcion != JOptionPane.YES_OPTION) {
                    agregarMas = false;
                }
            }

            if (productosSeleccionados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se agregaron productos. Pedido cancelado.");
                return;
            }

            long total = 0;
            for (Producto p : productosSeleccionados) {
                total += p.getPrecioProducto();
            }

            List<Pedido> listaPedidos = RestauranteFormulario.cristianManager.getPedidos();
            int maxId = 0;
            for (Pedido p : listaPedidos) {
                if (p.getId() > maxId) {
                    maxId = p.getId();
                }
            }
            int nuevoIdPedido = maxId + 1;

            Pedido nuevoPedido = new Pedido(nuevoIdPedido, numMesa, total, "PENDIENTE", LocalDateTime.now(), null, productosSeleccionados);
            listaPedidos.add(nuevoPedido);
            actualizarTablas();
            JOptionPane.showMessageDialog(this, "¡Pedido #" + nuevoIdPedido + " creado con éxito para la Mesa " + numMesa + "!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cambiarEstadoPedidoAccion() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un pedido de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int idPedido = (int) tablaPedidos.getValueAt(fila, 0);

        // Los estados oficiales del sistema
        String[] estados = {"PENDIENTE", "EN_PREPARACION", "LISTO", "ENTREGADO"};
        String estSeleccionado = (String) JOptionPane.showInputDialog(
            this, 
            "Seleccione el nuevo estado para el pedido #" + idPedido + ":", 
            "Cambiar Estado", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            estados, 
            estados[0]
        );

        if (estSeleccionado != null) {
            RestauranteFormulario.cristianManager.actualizarEstadoPedido(idPedido, estSeleccionado);
            actualizarTablas();
            JOptionPane.showMessageDialog(this, "¡Estado actualizado exitosamente a: " + estSeleccionado + "!");
        }
    }

    private void registrarTiempoAccion() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un pedido de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int idPedido = (int) tablaPedidos.getValueAt(fila, 0);
            LocalDateTime tiempo = RestauranteFormulario.anaManager.registrarMarcaDeTiempo(idPedido);
            actualizarTablas();
            JOptionPane.showMessageDialog(this, "¡Marca de tiempo registrada con éxito!\nHora: " + tiempo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar tiempo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}