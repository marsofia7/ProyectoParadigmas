/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

/**
 *
 * @author CRISTIAN
 */
public class Cocinero extends Persona2{

    public Cocinero() {
        super();
    }

    public Cocinero(int idPersona, String nombre, String apellido, int tipoDoc, int numDoc, String correo, String contraseña, long celular, String NombreUsuario) {
        super(idPersona, nombre, apellido, tipoDoc, numDoc, correo, contraseña, celular, NombreUsuario);
    }
    
    public void verPedido(){
        System.out.println("pedido");
    }
    public void estadoPedido(){
        System.out.println("manipulacion del pedido desde el cocinero o cocina");
    }
}
