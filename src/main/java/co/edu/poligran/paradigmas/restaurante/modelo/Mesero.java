/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

import java.util.List;

/**
 *
 * @author Danilo
 */
public class Mesero extends Persona2 {

    public Mesero() {
        super();
    }
    
    public Mesero(int idPersona, String nombre, String apellido, int tipoDoc, int numDoc, String correo, String contraseña, long celular, String NombreUsuario) {
        super(idPersona, nombre, apellido, tipoDoc, numDoc, correo, contraseña, celular, NombreUsuario);
    }
    
    public void AsignarMesa(List<Cliente> lista){
        
    }
    
    public void AgregarProducto(){   
    }
    
    public void EliminarProducto(){
    }
    
    public void CrearPedido(){
    }
    
    public void CancelarPedido(){
    }
    
    public void MarcarEntregado(){
        System.out.println("Entregado");    
    }
    
}
