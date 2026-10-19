/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

/**
 * Representa a un cocinero, encargado de ver los pedidos y gestionar su estado.
 * @author CRISTIAN
 */
public class Cocinero extends Persona2 {

    public Cocinero() {
        super();
    }

    public Cocinero(int idPersona, String nombre, String apellido, int tipoDoc, int numDoc, String correo, String contraseña, long celular, String NombreUsuario, String rol) {
        super(idPersona, nombre, apellido, tipoDoc, numDoc, correo, contraseña, celular, NombreUsuario, rol);
    }
    
    /**
     * Muestra la información y los detalles del pedido a cocinar.
     * @param pedido el pedido que el cocinero desea revisar
     */
    public void verPedido(Pedido pedido) {
        if (pedido != null) {
            System.out.println("\n--- COCINA: DETALLE DEL PEDIDO ---");
            System.out.println("ID Pedido: " + pedido.getId());
            System.out.println("Número de Mesa: " + pedido.getNumeroMesa());
            System.out.println("Estado Actual: " + pedido.getEstado());
            System.out.println("Hora del Pedido: " + pedido.getMarcadeTiempo());
            System.out.println("Total: $" + pedido.getTotal());
        } else {
            System.out.println("El pedido seleccionado no existe o es nulo.");
        }
    }
    
    /**
     * Permite al cocinero cambiar el estado del pedido (Ej. En preparación, Cancelado, Listo).
     * @param pedido el pedido a modificar
     * @param nuevoEstado el nuevo estado asignado por la cocina
     */
    public void estadoPedido(Pedido pedido, String nuevoEstado) {
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            System.out.println("El cocinero " + getNombre() + " actualizó el pedido #" + pedido.getId() + " al estado: [" + nuevoEstado + "]");
        } else {
            System.out.println("No se pudo actualizar el estado, el pedido es nulo.");
        }
    }
}
