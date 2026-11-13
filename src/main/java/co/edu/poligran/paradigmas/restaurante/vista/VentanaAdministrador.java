/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.vista;

import co.edu.poligran.paradigmas.restaurante.RestauranteFormulario;
import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import co.edu.poligran.paradigmas.restaurante.tablas.TablaPersonaModelo;
import co.edu.poligran.paradigmas.restaurante.tablas.TablaProductoModelo;
import co.edu.poligran.paradigmas.restaurante.tablas.TablaPedidoModelo;
import javax.swing.JOptionPane;

public class VentanaAdministrador extends javax.swing.JFrame {
    private Persona2 adminActual;
    private javax.swing.JTabbedPane jTabbedPane1;

    // Componentes Usuarios
    private javax.swing.JPanel panelUsuarios;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JScrollPane scrollUsuarios;
    private TablaPersonaModelo modeloUsuarios;
    private javax.swing.JButton btnModificarUsuario, btnEliminarUsuario;

    // Componentes Productos
    private javax.swing.JPanel panelProductos;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JScrollPane scrollProductos;
    private TablaProductoModelo modeloProductos;
    private javax.swing.JButton btnAgregarProducto, btnModificarProducto, btnEliminarProducto;

    // Componentes Pedidos
    private javax.swing.JPanel panelPedidos;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JScrollPane scrollPedidos;
    private TablaPedidoModelo modeloPedidos;
    private javax.swing.JButton btnCambiarEstado;

    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel lblAdmin;

    public VentanaAdministrador(Persona2 usuario) {
        this.adminActual = usuario;
        initComponents();
        lblAdmin.setText("Administrador: " + adminActual.getNombre());
        actualizarTablas();
    }

    private void initComponents() {
        lblAdmin = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        // 1. Pestaña Usuarios
        panelUsuarios = new javax.swing.JPanel();
        modeloUsuarios = new TablaPersonaModelo(RestauranteFormulario.anaManager.listarPersonas());
        tablaUsuarios = new javax.swing.JTable(modeloUsuarios);
        scrollUsuarios = new javax.swing.JScrollPane(tablaUsuarios);
        btnModificarUsuario = new javax.swing.JButton("Modificar Usuario");
        btnEliminarUsuario = new javax.swing.JButton("Eliminar Usuario");

        btnModificarUsuario.addActionListener(evt -> modificarUsuario());
        btnEliminarUsuario.addActionListener(evt -> eliminarUsuario());

        javax.swing.GroupLayout gl_u = new javax.swing.GroupLayout(panelUsuarios);
        panelUsuarios.setLayout(gl_u);
        gl_u.setHorizontalGroup(gl_u.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_u.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(gl_u.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gl_u.createSequentialGroup()
                        .addComponent(btnModificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        gl_u.setVerticalGroup(gl_u.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_u.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(gl_u.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarUsuario)
                    .addComponent(btnEliminarUsuario))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Usuarios", panelUsuarios);

        // 2. Pestaña Productos
        panelProductos = new javax.swing.JPanel();
        modeloProductos = new TablaProductoModelo(RestauranteFormulario.cristianManager.getProductos());
        tablaProductos = new javax.swing.JTable(modeloProductos);
        scrollProductos = new javax.swing.JScrollPane(tablaProductos);
        btnAgregarProducto = new javax.swing.JButton("Agregar Producto");
        btnModificarProducto = new javax.swing.JButton("Modificar Producto");
        btnEliminarProducto = new javax.swing.JButton("Eliminar Producto");

        btnAgregarProducto.addActionListener(evt -> agregarProducto());
        btnModificarProducto.addActionListener(evt -> modificarProducto());
        btnEliminarProducto.addActionListener(evt -> eliminarProducto());

        javax.swing.GroupLayout gl_p = new javax.swing.GroupLayout(panelProductos);
        panelProductos.setLayout(gl_p);
        gl_p.setHorizontalGroup(gl_p.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_p.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(gl_p.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gl_p.createSequentialGroup()
                        .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        gl_p.setVerticalGroup(gl_p.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_p.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(gl_p.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarProducto)
                    .addComponent(btnModificarProducto)
                    .addComponent(btnEliminarProducto))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Productos", panelProductos);

        // 3. Pestaña Pedidos
        panelPedidos = new javax.swing.JPanel();
        modeloPedidos = new TablaPedidoModelo(RestauranteFormulario.cristianManager.getPedidos());
        tablaPedidos = new javax.swing.JTable(modeloPedidos);
        scrollPedidos = new javax.swing.JScrollPane(tablaPedidos);
        btnCambiarEstado = new javax.swing.JButton("Cambiar Estado Pedido");

        btnCambiarEstado.addActionListener(evt -> cambiarEstadoPedido());

        javax.swing.GroupLayout gl_ped = new javax.swing.GroupLayout(panelPedidos);
        panelPedidos.setLayout(gl_ped);
        gl_ped.setHorizontalGroup(gl_ped.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_ped.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(gl_ped.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        gl_ped.setVerticalGroup(gl_ped.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gl_ped.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCambiarEstado)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Pedidos", panelPedidos);

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
                        .addComponent(lblAdmin)
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
                    .addComponent(lblAdmin)
                    .addComponent(btnCerrarSesion))
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void actualizarTablas() {
        modeloUsuarios.setPersonas(RestauranteFormulario.anaManager.listarPersonas());
        modeloProductos.setProductos(RestauranteFormulario.cristianManager.getProductos());
        modeloPedidos.setPedidos(RestauranteFormulario.cristianManager.getPedidos());
    }

    private void modificarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione un usuario."); return; }
        int id = (int) tablaUsuarios.getValueAt(fila, 0);
        Persona2 p = RestauranteFormulario.anaManager.buscarPorId(id);
        if (p != null) {
            String[] campos = {"Nombre", "Apellido", "Correo", "Contraseña"};
            String elegido = (String) JOptionPane.showInputDialog(this, "¿Qué desea cambiar?", "Modificar", JOptionPane.QUESTION_MESSAGE, null, campos, campos[0]);
            if (elegido != null) {
                String val = JOptionPane.showInputDialog(this, "Nuevo valor para " + elegido + ":");
                if (val != null && !val.trim().isEmpty()) {
                    if (elegido.equals("Nombre")) p.setNombre(val);
                    else if (elegido.equals("Apellido")) p.setApellido(val);
                    else if (elegido.equals("Correo")) p.setCorreo(val);
                    else if (elegido.equals("Contraseña")) p.setContraseña(val);
                    RestauranteFormulario.anaManager.actualizarPersona(p);
                    actualizarTablas();
                    JOptionPane.showMessageDialog(this, "¡Actualizado con éxito!");
                }
            }
        }
    }

    private void eliminarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione un usuario."); return; }
        int id = (int) tablaUsuarios.getValueAt(fila, 0);
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar usuario?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            RestauranteFormulario.anaManager.eliminarPersona(id);
            actualizarTablas();
        }
    }

    private void agregarProducto() {
        try {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
            if (nombre == null || nombre.trim().isEmpty()) return;

            String precioStr = JOptionPane.showInputDialog(this, "Precio ($):");
            if (precioStr == null || precioStr.trim().isEmpty()) return;
            long precio = Long.parseLong(precioStr.trim());

            String desc = JOptionPane.showInputDialog(this, "Descripción:");
            if (desc == null) desc = "";

            RestauranteFormulario.cristianManager.registrarProducto(nombre.trim(), precio, desc.trim());
            actualizarTablas();
            JOptionPane.showMessageDialog(this, "¡Producto agregado con éxito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void modificarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla."); return; }
        int idProducto = (int) tablaProductos.getValueAt(fila, 0);
        
        Producto prod = null;
        for (Producto p : RestauranteFormulario.cristianManager.getProductos()) {
            if (p.getIdProducto() == idProducto) { prod = p; break; }
        }
        
        if (prod != null) {
            String nuevoNom = JOptionPane.showInputDialog(this, "Nuevo Nombre:", prod.getNombresProducto());
            if (nuevoNom != null && !nuevoNom.trim().isEmpty()) prod.setNombresProducto(nuevoNom.trim());

            String nuevoPrec = JOptionPane.showInputDialog(this, "Nuevo Precio:", prod.getPrecioProducto());
            if (nuevoPrec != null && !nuevoPrec.trim().isEmpty()) {
                try { prod.setPrecioProducto(Long.parseLong(nuevoPrec.trim())); } catch (Exception ignored) {}
            }

            String nuevaDesc = JOptionPane.showInputDialog(this, "Nueva Descripción:", prod.getDescripcionProducto());
            if (nuevaDesc != null) prod.setDescripcionProducto(nuevaDesc.trim());

            actualizarTablas();
            JOptionPane.showMessageDialog(this, "¡Producto modificado con éxito!");
        }
    }

    private void eliminarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla."); return; }
        int idProducto = (int) tablaProductos.getValueAt(fila, 0);
        
        if (JOptionPane.showConfirmDialog(this, "¿Desea eliminar el producto ID #" + idProducto + "?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            var lista = RestauranteFormulario.cristianManager.getProductos();
            lista.removeIf(p -> p.getIdProducto() == idProducto);
            actualizarTablas();
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
        }
    }

    private void cambiarEstadoPedido() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila == -1) { JOptionPane.showMessageDialog(this, "Seleccione un pedido."); return; }
        int idPedido = (int) tablaPedidos.getValueAt(fila, 0);
        
        String[] estados = {"PENDIENTE", "EN_PREPARACION", "LISTO", "ENTREGADO"};
        String est = (String) JOptionPane.showInputDialog(this, "Nuevo estado:", "Estado", JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);
        if (est != null) {
            RestauranteFormulario.cristianManager.actualizarEstadoPedido(idPedido, est);
            actualizarTablas();
            JOptionPane.showMessageDialog(this, "¡Estado de pedido actualizado a " + est + "!");
        }
    }
}