/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.dao.AdministradorDAO;
import co.edu.poligran.paradigmas.restaurante.dao.MeseroDAO;
import co.edu.poligran.paradigmas.restaurante.modelo.Administrador;
import co.edu.poligran.paradigmas.restaurante.modelo.Mesero;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Danilo
 */
public class DaniloManager {
    
    Scanner tecla = new Scanner(System.in); 
    
    private final List<Mesero> meseros;
    private final List<Administrador> administradores;
    private MeseroDAO controlMesero;
    private AdministradorDAO controlAdministrador;

    public DaniloManager() {
        this.meseros=new ArrayList<>();
        this.administradores=new ArrayList<>();
    }
    
    //Metodos Administrador/Mesero
    
    //Crear 
    public void registrar(int rol){
        boolean control = false;
        
        //Solicitar nombre
        System.out.print("\nIngrese su nombre: ");
        String nombre = tecla.nextLine();
        
        //Solicitar apellido
        System.out.print("\nIngrese su apellido: ");
        String apellido = tecla.nextLine();
        
        //Solicitar tipo de documento
        int tipoDoc=0;
        do {           
            try {
                System.out.print("Escoger tipo de documento:\n1.tarjeta de identidad.\n2. Cedula.\n\nSeleccione una Opcion");
                tipoDoc = tecla.nextInt();
                if(tipoDoc!=1 || tipoDoc!=2) throw new IllegalArgumentException();
                control = true;
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admite otra opcion");
            }
        } while (!control);
        control=false;
        
        //Solicitar numero de documento
        int numDoc=0;
        do {           
            try {
                System.out.print("Ingrese su numero de documento ");
                numDoc= tecla.nextInt();
                if(numDoc<0) throw new IllegalArgumentException();
                control=true;
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admiten numeros negativos");
            }
        } while (!control);
        control=false;
        
        //Solcitar correo 
        System.out.print("\nIngrese su correo: ");
        String correo = tecla.nextLine();
        
        //Solicitar Contrasena
        System.out.print("\nIngrese su contrasena: ");
        String contrasena= tecla.nextLine();
        
        //Solicitar numero de celular(long)
        long numCelular=0;
        do {           
            try {
                System.out.print("Ingrese su numero de celular: ");
                numCelular = tecla.nextLong();
                if(numCelular <0) throw new IllegalArgumentException();
                control=true;
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admiten numeros negativos");
            }
        } while (!control);
        
        //Solicitar nombre de usuario
        System.out.print("\nIngrese su nombre de Usuario: ");
        String nombreUsuario= tecla.nextLine();
        
        if(rol==1){
            Mesero nuevoMesero = new Mesero(0, nombre, apellido, tipoDoc, numDoc, correo, contrasena, numCelular, nombreUsuario, "Mesero");
            controlMesero.crear(meseros, nuevoMesero);
        }
        else if(rol ==2){
            Administrador nuevoAdministrador = new Administrador(0, nombre, apellido, tipoDoc, numDoc, correo, contrasena, numCelular, nombreUsuario, "Mesero");
            controlAdministrador.crear(administradores, nuevoAdministrador);
        }

    }
    
    //Modificar
    public void Modificar(int rol){
        boolean control = false;
        int idPersona = 0;
        Persona2 usuario= new Persona2();
        String usuarioRol="";
        if(rol==1) usuarioRol = "Mesero";
        else if(rol==2) usuarioRol = "Adminitrador";
        
        do {           
            try {
                System.out.print("Ingrese el id del "+usuarioRol+" que desea Modificar: ");
                idPersona = tecla.nextInt();
                if(idPersona <0) throw new IllegalArgumentException();
                control = true;
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admiten numeros negativos");
            }
        } while (!control);
        control =false;
        if(rol==1) usuario = controlMesero.buscarPorId(meseros, idPersona);
        else if(rol==2)usuario = controlAdministrador.buscarPorId(administradores, idPersona);
        if(usuario!=null){
            int opcion =0;
            do {
                do {                
                    try {
                        System.out.print("Que desea Modificar?"
                        + "\n1. Nombre."
                        + "\n2. Apellido."
                        + "\n3. Tipo de Documento."
                        + "\n4. Numero de Documento."
                        + "\n5. Correo."
                        + "\n6. contrasena."
                        + "\n7. Numero de Celular."
                        + "\n8. Nombre de Usuario."
                        + "\n9. Regresar al Menu principal."
                        + "\n\nSeleccione una Opcion: ");
                        opcion = tecla.nextInt();
                    } 
                    catch (NumberFormatException e) {
                        System.out.print("\nSolo se admiten Numeros");
                    }
                } while (!control);
                control = false;
                switch (opcion) {
                    case 1:
                        System.out.print("\nIngrese el nuevo nombre: ");
                        String nombre = tecla.nextLine();
                        usuario.setNombre(nombre);
                        break;
                    case 2: 
                        System.out.print("\nIngrese el nuevo apellido: ");
                        String apellido = tecla.nextLine();
                        usuario.setApellido(apellido);
                        break;
                    case 3:
                        do {           
                            try {
                                System.out.print("Escoger tipo de documento:\n1.tarjeta de identidad.\n2. Cedula.\n\nSeleccione una Opcion");
                                int tipoDoc = tecla.nextInt();
                                if(tipoDoc!=1 || tipoDoc!=2) throw new IllegalArgumentException();
                                control = true;
                                usuario.setTipoDoc(tipoDoc);
                            } 
                            catch (NumberFormatException e) {
                                System.out.print("\nSolo se admiten Numeros");
                            }
                            catch (IllegalArgumentException a){
                                System.out.print("\nNo se admite otra opcion");
                            }
                        } while (!control);
                        control=false;
                        break;
                    case 4:
                        do {           
                            try {
                                System.out.print("Ingrese el nuevo Numero de documento: ");
                                int numeroDoc = tecla.nextInt();
                                if(numeroDoc!=1 || numeroDoc!=2) throw new IllegalArgumentException();
                                control = true;
                                usuario.setNumDoc(numeroDoc);
                            } 
                            catch (NumberFormatException e) {
                                System.out.print("\nSolo se admiten Numeros");
                            }
                            catch (IllegalArgumentException a){
                                System.out.print("\nNo se admite otra opcion");
                            }
                        } while (!control);
                        control=false;
                        break;
                    case 5:
                        System.out.print("\nIngrese el nuevo correo: ");
                        String correo = tecla.nextLine();
                        usuario.setCorreo(correo);
                        break;
                    case 6:
                        System.out.print("Ingrese su nombre de usuario: ");
                        String nombreUsuario = tecla.nextLine();
                        System.out.print("\nIngrese su contrasena: ");
                        String contrasena = tecla.nextLine();
                        if(usuario.iniciarSesion(nombreUsuario, contrasena)){
                            System.out.print("\nIngrese la nueva contrasena: ");
                            contrasena = tecla.nextLine();
                            usuario.setContraseña(contrasena);
                        }
                        else{
                            System.out.print("\n\nUsuario o Contrasena Incorrecta.");
                        }
                        break;
                    case 7:
                        do {           
                            try {
                                System.out.print("Ingrese el nuevo Numero de Celular: ");
                                long numCelular= tecla.nextLong();
                                if(numCelular<0) throw new IllegalArgumentException();
                                control = true;
                                usuario.setCelular(numCelular);
                            } 
                            catch (NumberFormatException e) {
                                System.out.print("\nSolo se admiten Numeros");
                            }
                            catch (IllegalArgumentException a){
                                System.out.print("\nNo se admiten numeros negativos");
                            }
                        } while (!control);
                        control=false;
                        break;
                    case 8: 
                        System.out.print("Ingrese su nombre de usuario: ");
                        nombreUsuario = tecla.nextLine();
                        System.out.print("\nIngrese su contrasena: ");
                        contrasena = tecla.nextLine();
                        if(usuario.iniciarSesion(nombreUsuario, contrasena)){
                            System.out.print("\nIngrese su nuevo nombre de Usuario: ");
                            nombreUsuario= tecla.nextLine();
                            usuario .setNombreUsuario(nombreUsuario);
                        }
                        else{
                            System.out.print("\n\nUsuario o Contrasena Incorrecta.");
                        }
                        break;
                    default:
                        System.out.print("\\nOpcion Invalida.");
                        break;
                }
            } while (opcion != 9);
        }
    }
    
    //Eliminar 
    public void eliminar(int rol){
        boolean control = false;
        int idPersona = 0;
        Persona2 usuario= new Persona2();
        String usuarioRol="";
        if(rol==1) usuarioRol = "Mesero";
        else if(rol==2) usuarioRol = "Adminitrador";
        
        do {           
            try {
                System.out.print("Ingres el id del "+usuarioRol+" a Eliminar: ");
                idPersona = tecla.nextInt();
                if(idPersona <0) throw new IllegalArgumentException();
                control = true;
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admiten numeros negativos");
            }
        } while (!control);
        control =false;
        if(rol==1) usuario = controlMesero.buscarPorId(meseros, idPersona);
        else if(rol==2)usuario = controlAdministrador.buscarPorId(administradores, idPersona);
        
        if(usuario!=null){
            System.out.print("\n\n"+usuarioRol+":\n"+usuario.toString());
            System.out.print("\nEsta Seguro de Eliminar:\n1. si.\n2. no.\n\nSeleccione una Opcion: ");
            do {           
                try {
                    System.out.print("Escoger tipo de documento:\n1.tarjeta de identidad.\n2. Cedula.\n\nSeleccione una Opcion");
                    int seguro= tecla.nextInt();
                    if(seguro!=1 || seguro!=2) throw new IllegalArgumentException();
                        control = true;
                        if(seguro==1){
                            if(rol==1) controlMesero.eliminar(meseros, idPersona);
                            else if(rol==2) controlAdministrador.eliminar(administradores, idPersona);
                        }
                        else{
                            System.out.print("\nOperacion Cancelada.\nRegresando al Menu");
                        }
                    } 
                catch (NumberFormatException e) {
                     System.out.print("\nSolo se admiten Numeros");
                }
                catch (IllegalArgumentException a){
                    System.out.print("\nNo se admite otra opcion");
                }
            } while (!control);
        }
        else{
            System.out.print("\n\nNo se encontro el "+usuarioRol+".\nVerifica la Informacion.");
        }
    }
    
    //Mostrar lista
    public void mostrar(int rol){
        boolean control = false;
        Persona2 usuario= new Persona2();
        String usuarioRol="";
        if(rol==1){
            usuarioRol = "Mesero";
            System.out.print("Lista de "+usuarioRol+":\n");
            for(Mesero c: controlMesero.listar(meseros)){
                System.out.println(c.toString());
            }
            
        }
        else if(rol==2){
            usuarioRol = "Adminitrador";
            System.out.print("Lista de "+usuarioRol+":\n");
            for(Administrador c: controlAdministrador.listar(administradores)){
                System.out.println(c.toString());
            }
        }
    }
    
    //Funciones de mesero
    
    public int asginarMesa(int mesa){
        if (mesa<= 20) {
            return mesa++;
        } 

        return-1;
    }
    
    
    
    
}
