/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

/**
 *Representa a un domiciliario, encargado e entregar pedidos.
 * Hereda los atributos de la superclase Persona2
 * @author asofi
 */
public class Domiciliario extends Persona2 {

    public Domiciliario() {
        super();
    }

    public Domiciliario(int idPersona, String nombre, String apellido, int tipoDoc, int numDoc, String correo, String contraseña, long celular, String NombreUsuario, String rol) {
        super(idPersona, nombre, apellido, tipoDoc, numDoc, correo, contraseña, celular, NombreUsuario, rol);
    }
   
    /**
     * Marca como entregado el pedido indicado.
     * @param idPedido identificador del pedido a entregar
     * @return mensaje de confirmacion de la entrega
     */
    
   public String entregarPedido (int idPedido) {
       return "Pedido " + idPedido + " entregado por " + getNombre() + " " + getApellido();
   } 
   @Override
   public String toString(){
       return "Domiciliario (" + "ID: " + getIdPersona() + ", nombre: " + getNombre() + " " + getApellido() + ").";
   }
}
