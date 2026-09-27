/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

import java.util.Date;

/**
 *
 * @author cdapalaciosa
 */
public class Pedido {
    private int id;
    private int numeroMesa;
    private long total;
    private String estado;
    private Date marcadeTiempo;
    private String formaDePago;

    public Pedido() {
    }

    public Pedido(int id, int numeroMesa, long total, String estado, Date marcadeTiempo, String formaDePago) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.total = total;
        this.estado = estado;
        this.marcadeTiempo = marcadeTiempo;
        this.formaDePago = formaDePago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getMarcadeTiempo() {
        return marcadeTiempo;
    }

    public void setMarcadeTiempo(Date marcadeTiempo) {
        this.marcadeTiempo = marcadeTiempo;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", numeroMesa=" + numeroMesa + ", total=" + total + ", estado=" + estado + ", marcadeTiempo=" + marcadeTiempo + ", formaDePago=" + formaDePago + '}';
    }

    
}
