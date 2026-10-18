/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una persona del sistema (clase base para Cliente, Administrador,
 * Mesero, Cocinero y Domiciliario).
 * @author asofi
 */
public class Persona2 {

    private int idPersona;
    private String nombre;
    private String apellido;
    private int tipoDoc;
    private int numDoc;
    private String correo;
    private String contraseña;
    private long celular;
    private String NombreUsuario;
    String rol;

    public Persona2() {
        
    }
    public Persona2(int idPersona, String nombre, String apellido, int tipoDoc, int numDoc, String correo, String contraseña, long celular, String NombreUsuario, String rol) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.correo = correo;
        this.contraseña = contraseña;
        this.celular = celular;
        this.NombreUsuario = NombreUsuario;
        this.rol = rol;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    } 
    
    public String getRol(){
        return rol;
    }
    
    public void setRol (String rol) {
        this.rol = rol;
    }

    /**
     * Valida las credenciales recibidas contra las almacenadas en esta persona.
     * @param usuario nombre del usuario ingresado
     * @param clave contraseña ingresada
     * @return {@code true} si coincide con el usuario y contraseña
     */
    public boolean iniciarSesion (String usuario, String clave) { 
        if (usuario == null || clave == null) {
            return false;
        }
        return usuario.equals(this.NombreUsuario) && clave.equals(this.contraseña);
    }  
    
    @Override
    public String toString(){
        return "Persona (" + idPersona + ", nombre" + nombre + " " + apellido + ", usuario: " + NombreUsuario + ", rol: " + rol + ").";
    }
}
