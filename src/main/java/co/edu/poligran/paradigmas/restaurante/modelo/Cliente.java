/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

/**
 *
 * @author salaG201
 */
public class Cliente extends Persona2 {

    public Cliente() {
        super();
    }

    public Cliente(int idPersona, String nombre, String apellido, int tipoDoc, int numDoc, String correo, String contraseña, long celular, String NombreUsuario, String rol) {
        super(idPersona, nombre, apellido, tipoDoc, numDoc, correo, contraseña, celular, NombreUsuario, rol);
    }
    
    public void pagar(Pedido pedido, String formaPago) {
        if (pedido != null) {
            // Validamos que sea una forma de pago permitida
            String tipoPagoLower = formaPago.toLowerCase();
            if (tipoPagoLower.equals("efectivo") || tipoPagoLower.equals("tarjeta")) {
                pedido.setFormaDePago(formaPago);
                pedido.setEstado("Pagado");
                System.out.println("El cliente " + getNombre() + " ha pagado el pedido de la mesa " + pedido.getNumeroMesa() + " mediante pago en " + formaPago + ".");
            } else {
                System.out.println("Forma de pago no válida. Debe ser 'efectivo' o 'tarjeta'.");
            }
        }
    }

    public void recibirPedido(Pedido pedido) {
        if (pedido != null) {
            pedido.setEstado("Recibido");
            System.out.println("El cliente " + getNombre() + " ha marcado el pedido de la mesa " + pedido.getNumeroMesa() + " como RECIBIDO.");
        }
    }
}
