/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

/**
 *
 * @author CRISTIAN
 */
public class DetallePedido {
    private int id;
    private long cantidad;
    private Pedido Pedido;
    private Producto producto;

    public DetallePedido() {
    }

    public DetallePedido(int id, long cantidad, Pedido Pedido, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.Pedido = Pedido;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return Pedido;
    }

    public void setPedido(Pedido Pedido) {
        this.Pedido = Pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "id=" + id + ", cantidad=" + cantidad + ", Pedido=" + Pedido + ", producto=" + producto + '}';
    }

    
}
