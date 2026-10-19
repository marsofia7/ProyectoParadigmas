/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

/**
 *
 * @author CRISTIAN
 */
public class Producto  {
    private int idProducto;
    private long precioProducto;
    private String nombresProducto;
    private String descripcionProducto;

    public Producto(int idProducto, long precioProducto, String nombresProducto, String descripcionProducto) {
        this.idProducto=idProducto;
        this.precioProducto=precioProducto;
        this.nombresProducto=nombresProducto;
        this.descripcionProducto=descripcionProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public long getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(long precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getNombresProducto() {
        return nombresProducto;
    }

    public void setNombresProducto(String nombresProducto) {
        this.nombresProducto = nombresProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    @Override
    public String toString() {
        // Corregido: decía "Pedido" y ahora dice "Producto"
        return "Producto{" + "idProducto=" + idProducto + ", precioProducto=" + precioProducto + ", nombresProducto=" + nombresProducto + ", descripcionProducto=" + descripcionProducto + '}';
    }
}