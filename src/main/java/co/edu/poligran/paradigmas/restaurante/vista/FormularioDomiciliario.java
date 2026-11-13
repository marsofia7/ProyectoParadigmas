package co.edu.poligran.paradigmas.restaurante.vista;

import co.edu.poligran.paradigmas.restaurante.formularios.InicioSecion;
import co.edu.poligran.paradigmas.restaurante.Manager.AnaManager;
import co.edu.poligran.paradigmas.restaurante.RestauranteFormulario;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Ventana JFrame exclusiva para domiciliarios. */
public class FormularioDomiciliario extends JFrame {
    private final Persona2 usuario;private final AnaManager operaciones=RestauranteFormulario.anaManager;private final DefaultTableModel pedidos=modelo("ID","Mesa","Estado","Total","Hora");private final JTable tabla=new JTable(pedidos);private final JComboBox<Pedido> selector=new JComboBox<>();private final Color acento=new Color(26,126,132);
    public FormularioDomiciliario(Persona2 usuario){this.usuario=usuario;setTitle("PoliRestaurante | Domiciliario");setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);setMinimumSize(new Dimension(850,560));setLocationRelativeTo(null);add(header(),BorderLayout.NORTH);add(new JScrollPane(estilizar(tabla)),BorderLayout.CENTER);add(acciones(),BorderLayout.SOUTH);actualizar();}
    private JPanel acciones(){JPanel p=new JPanel(new FlowLayout());selector.setPreferredSize(new Dimension(360,28));p.add(new JLabel("Pedidos listos/asignados:"));p.add(selector);p.add(btn("Asignarme pedido",this::asignar));p.add(btn("Marcar entregado",this::entregar));p.add(btn("Actualizar",this::actualizar));p.add(btn("Cerrar sesión",()->{dispose();new InicioSecion().setVisible(true);}));return p;}
    private List<Pedido> visibles(){List<Pedido> lista=new ArrayList<>(operaciones.listarPedidosListosParaEntrega());for(Pedido p:operaciones.listarPedidosAsignados(usuario.getIdPersona()))if(!lista.contains(p))lista.add(p);return lista;}
    private void asignar(){Pedido p=(Pedido)selector.getSelectedItem();if(p==null){mensaje("No hay pedidos listos para asignar.");return;}try{operaciones.asignarDomiciliarioAPedido(usuario.getIdPersona(),p.getId());actualizar();mensaje("Pedido asignado.");}catch(RuntimeException ex){mensaje(ex.getMessage());}}
    private void entregar(){Pedido p=(Pedido)selector.getSelectedItem();if(p==null){mensaje("No tienes pedidos asignados.");return;}try{mensaje(operaciones.entregarPedido(usuario.getIdPersona(),p.getId()));actualizar();}catch(RuntimeException ex){mensaje(ex.getMessage());}}
    private void actualizar(){pedidos.setRowCount(0);selector.removeAllItems();for(Pedido p:visibles()){pedidos.addRow(new Object[]{p.getId(),p.getNumeroMesa(),p.getEstado(),p.getTotal(),p.getMarcadeTiempo()});selector.addItem(p);}}
    private JPanel header(){JPanel p=new JPanel(new GridLayout(0,1));p.setBackground(acento);p.setBorder(BorderFactory.createEmptyBorder(18,24,18,24));JLabel a=new JLabel("Panel de domicilios");a.setForeground(Color.WHITE);a.setFont(new Font("Segoe UI",Font.BOLD,24));p.add(a);JLabel b=new JLabel("Toma pedidos listos y registra la entrega al cliente");b.setForeground(Color.WHITE);p.add(b);return p;}
    private JButton btn(String s,Runnable r){JButton b=new JButton(s);b.setBackground(acento);b.setForeground(Color.WHITE);b.addActionListener(e->r.run());return b;}private JTable estilizar(JTable t){t.setRowHeight(30);t.getTableHeader().setBackground(acento);t.getTableHeader().setForeground(Color.WHITE);return t;}private void mensaje(String s){JOptionPane.showMessageDialog(this,s);}private static DefaultTableModel modelo(String... c){return new DefaultTableModel(c,0){public boolean isCellEditable(int r,int col){return false;}};}
}