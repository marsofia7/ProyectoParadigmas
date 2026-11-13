package co.edu.poligran.paradigmas.restaurante.vista;

import co.edu.poligran.paradigmas.restaurante.Manager.AnaManager;
import co.edu.poligran.paradigmas.restaurante.Manager.CristianManager;
import co.edu.poligran.paradigmas.restaurante.RestauranteFormulario;
import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

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

public class FormularioAdministrador extends JFrame {

    private final Persona2 administrador;
    private final CristianManager restaurante =
            RestauranteFormulario.cristianManager;
    private final AnaManager usuarios =
            RestauranteFormulario.anaManager;

    private final DefaultTableModel productos =
            modelo("ID", "Nombre", "Descripción", "Precio");
    private final DefaultTableModel pedidos =
            modelo("ID", "Mesa", "Estado", "Total", "Pago", "Hora");
    private final DefaultTableModel personas =
            modelo("ID", "Nombre", "Documento", "Correo", "Usuario", "Rol");

    private final JTable tablaProductos = new JTable(productos);
    private final JTable tablaPedidos = new JTable(pedidos);
    private final JTable tablaPersonas = new JTable(personas);

    private final JTextField nombre = new JTextField(12);
    private final JTextField precio = new JTextField(8);
    private final JTextField descripcion = new JTextField(18);

    private final Color acento = new Color(92, 63, 42);
    private Integer idProducto;

    public FormularioAdministrador(Persona2 usuario) {
        administrador = usuario;

        setTitle("PoliRestaurante | Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(950, 650));
        setLocationRelativeTo(null);

        add(encabezado(
                "Administración",
                "Gestiona productos, pedidos y usuarios"
        ), BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Productos", panelProductos());
        tabs.addTab("Pedidos y mesas", panelPedidos());
        tabs.addTab("Usuarios", panelUsuarios());

        add(tabs, BorderLayout.CENTER);
        add(barraInferior(), BorderLayout.SOUTH);

        refrescar();
    }

    private JPanel panelProductos() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JPanel formulario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formulario.setBorder(
                BorderFactory.createTitledBorder("Datos del producto")
        );

        formulario.add(new JLabel("Nombre:"));
        formulario.add(nombre);
        formulario.add(new JLabel("Precio:"));
        formulario.add(precio);
        formulario.add(new JLabel("Descripción:"));
        formulario.add(descripcion);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(boton("Guardar", this::guardarProducto));
        botones.add(boton("Nuevo", this::limpiarProducto));
        botones.add(boton("Eliminar seleccionado", this::eliminarProducto));

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(estilizar(tablaProductos)), BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        tablaProductos.getSelectionModel().addListSelectionListener(evento -> {
            if (!evento.getValueIsAdjusting()) {
                cargarProducto();
            }
        });

        return panel;
    }

    private JPanel panelPedidos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(estilizar(tablaPedidos)), BorderLayout.CENTER);
        panel.add(
                boton("Cambiar mesa del seleccionado", this::cambiarMesa),
                BorderLayout.SOUTH
        );
        return panel;
    }

    private JPanel panelUsuarios() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(estilizar(tablaPersonas)), BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.add(boton("Agregar usuario", () -> editarUsuario(null)));
        botones.add(boton("Modificar seleccionado", this::modificarUsuario));
        botones.add(boton("Eliminar seleccionado", this::eliminarUsuario));

        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel barraInferior() {
        JPanel panel = new JPanel();
        panel.add(boton("Actualizar", this::refrescar));
        panel.add(boton("Cerrar sesión", () -> {
            dispose();
            new InicioSecion().setVisible(true);
        }));
        return panel;
    }

    private void guardarProducto() {
        String nombreProducto = nombre.getText().trim();
        String textoPrecio = precio.getText().trim();
        String descripcionProducto = descripcion.getText().trim();

        if (nombreProducto.isEmpty() || descripcionProducto.isEmpty()) {
            mensaje("Completa el nombre y la descripción.");
            return;
        }

        try {
            long valor = Long.parseLong(textoPrecio);

            if (valor <= 0) {
                mensaje("El precio debe ser positivo.");
                return;
            }

            if (idProducto == null) {
                restaurante.registrarProducto(
                        nombreProducto,
                        valor,
                        descripcionProducto
                );
            } else {
                Producto producto = restaurante.buscarProductoPorId(idProducto);
                if (producto == null) {
                    mensaje("No se encontró el producto seleccionado.");
                    return;
                }

                producto.setNombresProducto(nombreProducto);
                producto.setPrecioProducto(valor);
                producto.setDescripcionProducto(descripcionProducto);
                restaurante.actualizarProducto(producto);
            }

            limpiarProducto();
            refrescar();
        } catch (NumberFormatException ex) {
            mensaje("El precio debe ser un número entero.");
        }
    }

    private void cargarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila < 0) {
            return;
        }

        idProducto = ((Number) productos.getValueAt(fila, 0)).intValue();
        Producto producto = restaurante.buscarProductoPorId(idProducto);

        if (producto != null) {
            nombre.setText(producto.getNombresProducto());
            precio.setText(String.valueOf(producto.getPrecioProducto()));
            descripcion.setText(producto.getDescripcionProducto());
        }
    }

    private void limpiarProducto() {
        idProducto = null;
        nombre.setText("");
        precio.setText("");
        descripcion.setText("");
        tablaProductos.clearSelection();
    }

    private void eliminarProducto() {
        int id = idProducto != null ? idProducto : idSeleccionado(tablaProductos);

        if (id < 0) {
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el producto seleccionado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            restaurante.eliminarProducto(id);
            limpiarProducto();
            refrescar();
        }
    }

    private void cambiarMesa() {
        int id = idSeleccionado(tablaPedidos);
        if (id < 0) {
            return;
        }

        Pedido pedido = restaurante.buscarPedidoPorId(id);
        if (pedido == null) {
            return;
        }

        String textoMesa = JOptionPane.showInputDialog(
                this,
                "Nueva mesa:",
                pedido.getNumeroMesa()
        );

        if (textoMesa == null) {
            return;
        }

        try {
            int mesa = Integer.parseInt(textoMesa.trim());

            if (mesa <= 0) {
                throw new NumberFormatException();
            }

            pedido.setNumeroMesa(mesa);
            refrescar();
        } catch (NumberFormatException ex) {
            mensaje("Ingresa un número de mesa positivo.");
        }
    }

    private void modificarUsuario() {
        int id = idSeleccionado(tablaPersonas);
        if (id >= 0) {
            editarUsuario(usuarios.buscarPorId(id));
        }
    }

    private void eliminarUsuario() {
        int id = idSeleccionado(tablaPersonas);
        if (id < 0) {
            return;
        }

        if (id == administrador.getIdPersona()) {
            mensaje("No puedes eliminar tu usuario activo.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el usuario seleccionado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            usuarios.eliminarPersona(id);
            refrescar();
        }
    }

    private void editarUsuario(Persona2 anterior) {
        JTextField campoNombre = new JTextField(
                anterior == null ? "" : anterior.getNombre()
        );
        JTextField apellido = new JTextField(
                anterior == null ? "" : anterior.getApellido()
        );
        JTextField documento = new JTextField(
                anterior == null ? "" : String.valueOf(anterior.getNumDoc())
        );
        JTextField correo = new JTextField(
                anterior == null ? "" : anterior.getCorreo()
        );
        JTextField celular = new JTextField(
                anterior == null ? "" : String.valueOf(anterior.getCelular())
        );
        JTextField usuario = new JTextField(
                anterior == null ? "" : anterior.getNombreUsuario()
        );
        JTextField contrasena = new JTextField(
                anterior == null ? "" : anterior.getContraseña()
        );

        JComboBox<String> rol = new JComboBox<>(new String[]{
            "Cliente", "Mesero", "Cocinero", "Domiciliario", "Administrador"
        });

        if (anterior != null) {
            rol.setSelectedItem(anterior.getRol());
        }

        JPanel formulario = new JPanel(new GridLayout(0, 2, 5, 5));
        agregarCampo(formulario, "Nombre:", campoNombre);
        agregarCampo(formulario, "Apellido:", apellido);
        agregarCampo(formulario, "Documento:", documento);
        agregarCampo(formulario, "Correo:", correo);
        agregarCampo(formulario, "Celular:", celular);
        agregarCampo(formulario, "Usuario:", usuario);
        agregarCampo(formulario, "Contraseña:", contrasena);
        agregarCampo(formulario, "Rol:", rol);

        String titulo = anterior == null ? "Agregar usuario" : "Modificar usuario";
        int resultado = JOptionPane.showConfirmDialog(
                this,
                formulario,
                titulo,
                JOptionPane.OK_CANCEL_OPTION
        );

        if (resultado != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            Persona2 persona = anterior == null
                    ? new Persona2()
                    : new Persona2(
                            anterior.getIdPersona(),
                            anterior.getNombre(),
                            anterior.getApellido(),
                            anterior.getTipoDoc(),
                            anterior.getNumDoc(),
                            anterior.getCorreo(),
                            anterior.getContraseña(),
                            anterior.getCelular(),
                            anterior.getNombreUsuario(),
                            anterior.getRol()
                    );

            persona.setNombre(campoNombre.getText().trim());
            persona.setApellido(apellido.getText().trim());
            persona.setNumDoc(Integer.parseInt(documento.getText().trim()));
            persona.setCorreo(correo.getText().trim());
            persona.setCelular(Long.parseLong(celular.getText().trim()));
            persona.setNombreUsuario(usuario.getText().trim());
            persona.setContraseña(contrasena.getText());
            persona.setRol((String) rol.getSelectedItem());

            Persona2 guardada = anterior == null
                    ? usuarios.registrarPersona(persona)
                    : usuarios.actualizarPersona(persona);

            if (guardada == null) {
                mensaje("No se guardó: revisa los datos duplicados.");
            } else {
                refrescar();
            }
        } catch (NumberFormatException ex) {
            mensaje("Documento y celular deben ser números.");
        }
    }

    private void agregarCampo(JPanel panel, String etiqueta, Component campo) {
        panel.add(new JLabel(etiqueta));
        panel.add(campo);
    }

    private void refrescar() {
        productos.setRowCount(0);
        for (Producto producto : restaurante.listarProductos()) {
            productos.addRow(new Object[]{
                producto.getIdProducto(),
                producto.getNombresProducto(),
                producto.getDescripcionProducto(),
                producto.getPrecioProducto()
            });
        }

        pedidos.setRowCount(0);
        for (Pedido pedido : restaurante.listarPedidos()) {
            pedidos.addRow(new Object[]{
                pedido.getId(),
                pedido.getNumeroMesa(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getFormaDePago(),
                pedido.getMarcadeTiempo()
            });
        }

        personas.setRowCount(0);
        for (Persona2 persona : usuarios.listarPersonas()) {
            personas.addRow(new Object[]{
                persona.getIdPersona(),
                persona.getNombre() + " " + persona.getApellido(),
                persona.getNumDoc(),
                persona.getCorreo(),
                persona.getNombreUsuario(),
                persona.getRol()
            });
        }
    }

    private int idSeleccionado(JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            mensaje("Selecciona un registro de la tabla.");
            return -1;
        }
        return ((Number) tabla.getValueAt(fila, 0)).intValue();
    }

    private JTable estilizar(JTable tabla) {
        tabla.setRowHeight(28);
        tabla.getTableHeader().setBackground(acento);
        tabla.getTableHeader().setForeground(Color.WHITE);
        return tabla;
    }

    private JButton boton(String texto, Runnable accion) {
        JButton boton = new JButton(texto);
        boton.setBackground(acento);
        boton.setForeground(Color.WHITE);
        boton.addActionListener(evento -> accion.run());
        return boton;
    }

    private JPanel encabezado(String titulo, String subtitulo) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(acento);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 22, 16, 22));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subtituloLabel = new JLabel(subtitulo);
        subtituloLabel.setForeground(Color.WHITE);

        panel.add(tituloLabel);
        panel.add(subtituloLabel);
        return panel;
    }

    private void mensaje(String texto) {
        JOptionPane.showMessageDialog(this, texto);
    }

    private static DefaultTableModel modelo(String... columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
    }
}