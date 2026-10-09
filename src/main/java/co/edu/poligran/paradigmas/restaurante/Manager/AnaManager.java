/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.dao.DetallePedidoDAO;
import co.edu.poligran.paradigmas.restaurante.dao.DomiciliarioDAO;
import co.edu.poligran.paradigmas.restaurante.dao.PedidoDAO;
import co.edu.poligran.paradigmas.restaurante.dao.Persona2DAO;
import co.edu.poligran.paradigmas.restaurante.modelo.DetallePedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Domiciliario;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 *Manager Ana. Integra funcionalidades de Persona, Domiciliario y Pedido/Detalle de Pedido.
 * IMPORTANTE: Pedido no tiene un campo IdDomiciliario, por lo que la asignación domiciliario pedido se manejara aqui mediante un hashmap.
 * Si se agrega este campo a Pedido este se puede reemplazar por pedido.setIdDomiciliario().
 * @author cdapalaciosa
 */
public class AnaManager {
    
    private final List<Persona2> personas = new ArrayList <>();
    private final List<Domiciliario> domiciliarios = new ArrayList <>();
    private final List<Pedido> pedidos = new ArrayList <>();
    private final List<DetallePedido> detalles = new ArrayList <>();
    
    private final Map<Integer, Integer> asignaciones = new HashMap<>();
    
    private final Persona2DAO personaDAO = new Persona2DAO();
    private final DomiciliarioDAO domiciliarioDAO = new DomiciliarioDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final DetallePedidoDAO detallepedidoDAO = new DetallePedidoDAO();
    
    //======================persona====================
    //Registrar usuario
    
    public Persona2 registrarPersona(Persona2 persona){
        return personaDAO.crear (personas, persona);
    }
    
   //iniciar sesión
    public Persona2 iniciarSesion(String usuario, String clave){
       Persona2 p =  personaDAO.buscarPorNombreUsuario(personas, usuario);
       if(p != null && p.iniciarSesion(usuario, clave)){
        return p;   
       }
       return null;
    }
    
    //Listar personas
    public List<Persona2> listarPersonas(){
        return personaDAO.listar(personas);
    }
    
    //Actualizar datos de una persona
    public Persona2 actualizar(Persona2 persona){
        return personaDAO.actualizar(personas, persona);
    }
}
/**
 * 
 */