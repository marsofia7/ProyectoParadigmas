/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import java.util.ArrayList;
import java.util.List;

public class AnaManager {

    private final List<Persona2> listaPersonas = new ArrayList<>();

    /**
     * Registra una nueva persona validando que sus datos únicos no estén duplicados.
     */
    public Persona2 registrarPersona(Persona2 nuevaPersona) {
  
        if (!validarPersonaUnica(nuevaPersona)) {
            return null; 
        }
        
    
        if (nuevaPersona.getIdPersona() <= 0) {
            int maxId = 0;
            for (Persona2 p : listaPersonas) {
                if (p.getIdPersona() > maxId) {
                    maxId = p.getIdPersona();
                }
            }
            nuevaPersona.setIdPersona(maxId + 1);
        }

        listaPersonas.add(nuevaPersona);
        return nuevaPersona;
    }

    /**
     * Valida que documento, correo, usuario y celular no se repitan.
     * Permite repetir nombre y apellido.
     */
    public boolean validarPersonaUnica(Persona2 nuevaPersona) {
        for (Persona2 p : listaPersonas) {
            if (p.getNumDoc() == nuevaPersona.getNumDoc()) {
                System.out.println(" Error: Ya existe una persona registrada con el número de documento: " + nuevaPersona.getNumDoc());
                return false;
            }
            if (p.getCorreo() != null && p.getCorreo().equalsIgnoreCase(nuevaPersona.getCorreo())) {
                System.out.println(" Error: Ya existe una persona registrada con el correo: " + nuevaPersona.getCorreo());
                return false;
            }
            if (p.getNombreUsuario() != null && p.getNombreUsuario().equalsIgnoreCase(nuevaPersona.getNombreUsuario())) {
                System.out.println(" Error: El nombre de usuario '" + nuevaPersona.getNombreUsuario() + "' ya está en uso.");
                return false;
            }
            if (p.getCelular() == nuevaPersona.getCelular()) {
                System.out.println(" Error: Ya existe una persona registrada con el número de celular: " + nuevaPersona.getCelular());
                return false;
            }
        }
        return true;
    }

    public List<Persona2> listarPersonas() {
        return listaPersonas;
    }

    public Persona2 iniciarSesion(String usuario, String contraseña) {
        for (Persona2 p : listaPersonas) {
            if (p.iniciarSesion(usuario, contraseña)) {
                return p;
            }
        }
        return null;
    }
}
/**
 * 
 */