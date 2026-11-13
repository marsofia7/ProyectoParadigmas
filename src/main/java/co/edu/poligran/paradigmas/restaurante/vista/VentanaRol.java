/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.vista;

import co.edu.poligran.paradigmas.restaurante.Manager.AnaManager;
import co.edu.poligran.paradigmas.restaurante.Manager.CristianManager;
import co.edu.poligran.paradigmas.restaurante.RestauranteFormulario;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/** Ventana principal de trabajo para los usuarios autenticados. */
public class VentanaRol extends JFrame {
    private static final Color FONDO = new Color(248, 245, 239);
    private static final Color CAFE = new Color(82, 63, 44);
    private final Persona2 usuario;
    private final CristianManager restaurante = RestauranteFormulario.cristianManager;
    private final AnaManager operaciones = RestauranteFormulario.anaManager;
    private final DefaultTableModel productosModel = modelo("ID", "Producto", "Descripción", "Precio");
    private final DefaultTableModel pedidosModel = modelo("ID", "Mesa", "Estado", "Total", "Hora");
    private final JTable tablaProductos = new JTable(productosModel);
    private final JTable tablaPedidos = new JTable(pedidosModel);

    public VentanaRol(Persona2 usuario) {
        this.usuario = usuario;
        setTitle("PoliRestaurante | " + usuario.getRol());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(850, 560));
        setLocationRelativeTo(null);
        getContentPane().setBackground(FONDO);
        construirInterfaz();
        actualizarTablas();
    }

    private void construirInterfaz() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(CAFE);
        encabezado.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));
        JLabel titulo = new JLabel("PoliRestaurante");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        JLabel saludo = new JLabel("Hola, " + usuario.getNombre() + "  ·  " + usuario.getRol());
        saludo.setForeground(Color.WHITE);
        encabezado.add(titulo, BorderLayout.WEST);
        encabezado.add(saludo, BorderLayout.EAST);
        add(encabezado, BorderLayout.NORTH);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Productos", panelTabla(tablaProductos));
        pestanas.addTab("Pedidos", panelTabla(tablaPedidos));
        add(pestanas, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new GridLayout(1, 0, 10, 0));
        acciones.setBackground(FONDO);
        acciones.setBorder(BorderFactory.createEmptyBorder(12, 18, 16, 18));
        String rol = usuario.getRol() == null ? "" : usuario.getRol().toLowerCase();
        if (rol.equals("cliente") || rol.equals("mesero")) {
            JButton nuevoPedido = boton("Crear pedido");
            nuevoPedido.addActionListener(e -> crearPedido());
            acciones.add(nuevoPedido);
        }
        if (rol.equals("cocinero")) {
            JButton preparar = boton("Marcar en preparación");
            preparar.addActionListener(e -> cambiarEstado("EN PREPARACION"));
            JButton listo = boton("Marcar listo");
            listo.addActionListener(e -> cambiarEstado("LISTO"));
            acciones.add(preparar);
            acciones.add(listo);
        }
        if (rol.equals("administrador")) {
            JButton agregarProducto = boton("Registrar producto");
            agregarProducto.addActionListener(e -> registrarProducto());
            acciones.add(agregarProducto);
        }
        JButton actualizar = boton("Actualizar listas");
        actualizar.addActionListener(e -> actualizarTablas());
        acciones.add(actualizar);
        JButton salir = boton("Cerrar sesión");
        salir.addActionListener(e -> {
            dispose();
            new co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion().setVisible(true);
        });
        acciones.add(salir);
        add(acciones, BorderLayout.SOUTH);
    }

    private JPanel panelTabla(JTable tabla) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        tabla.setRowHeight(26);
        tabla.setFillsViewportHeight(true);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    private JButton boton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(CAFE);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        return boton;
    }

    private void actualizarTablas() {
        productosModel.setRowCount(0);
        for (Producto producto : restaurante.listarProductos()) {
            productosModel.addRow(new Object[]{producto.getIdProducto(), producto.getNombresProducto(),
                producto.getDescripcionProducto(), "$" + producto.getPrecioProducto()});
        }
        pedidosModel.setRowCount(0);
        for (Pedido pedido : restaurante.listarPedidos()) {
            pedidosModel.addRow(new Object[]{pedido.getId(), pedido.getNumeroMesa(), pedido.getEstado(),
                "$" + pedido.getTotal(), pedido.getMarcadeTiempo()});
        }
    }

    private void crearPedido() {
        List<Producto> disponibles = restaurante.listarProductos();
        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todavía no hay productos disponibles.", "Catálogo vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JComboBox<Producto> selector = new JComboBox<>(disponibles.toArray(new Producto[0]));
        JTextField mesa = new JTextField();
        JPanel formulario = new JPanel(new GridLayout(0, 1, 4, 4));
        formulario.add(new JLabel("Número de mesa:"));
        formulario.add(mesa);
        formulario.add(new JLabel("Producto:"));
        formulario.add(selector);
        if (JOptionPane.showConfirmDialog(this, formulario, "Nuevo pedido", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
        try {
            int numeroMesa = Integer.parseInt(mesa.getText().trim());
            if (numeroMesa <= 0) throw new NumberFormatException();
            Pedido pedido = restaurante.crearPedido(numeroMesa, 0);
            operaciones.agregarProductoAPedido(pedido.getId(), (Producto) selector.getSelectedItem());
            actualizarTablas();
            JOptionPane.showMessageDialog(this, "Pedido #" + pedido.getId() + " creado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un número de mesa positivo.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "No se pudo crear el pedido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cambiarEstado(String estado) {
        int fila = tablaPedidos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido de la tabla.", "Pedido requerido", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = ((Number) pedidosModel.getValueAt(tablaPedidos.convertRowIndexToModel(fila), 0)).intValue();
        if (restaurante.actualizarEstadoPedido(id, estado)) {
            actualizarTablas();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el pedido seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarProducto() {
        JTextField nombre = new JTextField();
        JTextField precio = new JTextField();
        JTextField descripcion = new JTextField();
        JPanel formulario = new JPanel(new GridLayout(0, 1, 4, 4));
        formulario.add(new JLabel("Nombre:")); formulario.add(nombre);
        formulario.add(new JLabel("Precio (pesos):")); formulario.add(precio);
        formulario.add(new JLabel("Descripción:")); formulario.add(descripcion);
        if (JOptionPane.showConfirmDialog(this, formulario, "Registrar producto", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;
        try {
            if (nombre.getText().trim().isEmpty() || descripcion.getText().trim().isEmpty()) throw new IllegalArgumentException("Completa todos los campos.");
            long valor = Long.parseLong(precio.getText().trim());
            if (valor <= 0) throw new IllegalArgumentException("El precio debe ser mayor que cero.");
            restaurante.registrarProducto(nombre.getText().trim(), valor, descripcion.getText().trim());
            actualizarTablas();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número entero.", "Precio inválido", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static DefaultTableModel modelo(String... columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int fila, int columna) { return false; }
        };
    }
}