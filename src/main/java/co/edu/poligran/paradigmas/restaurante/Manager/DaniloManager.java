/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.Manager;

import co.edu.poligran.paradigmas.restaurante.dao.AdministradorDAO;
import co.edu.poligran.paradigmas.restaurante.dao.MeseroDAO;
import co.edu.poligran.paradigmas.restaurante.dao.PedidoDAO;
import co.edu.poligran.paradigmas.restaurante.dao.ProductoDAO;
import co.edu.poligran.paradigmas.restaurante.modelo.Administrador;
import co.edu.poligran.paradigmas.restaurante.modelo.Mesero;
import co.edu.poligran.paradigmas.restaurante.modelo.Pedido;
import co.edu.poligran.paradigmas.restaurante.modelo.Persona2;
import co.edu.poligran.paradigmas.restaurante.modelo.Producto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Danilo
 */
public class DaniloManager {
    
    Scanner tecla = new Scanner(System.in); 
    
    private final List<Mesero> meseros;
    private final List<Administrador> administradores;
    private final List<Producto> productosPedido;
    private final List<Pedido> pedidos;
    private MeseroDAO controlMesero;
    private AdministradorDAO controlAdministrador;
    private PedidoDAO controlPedido;
    private ProductoDAO controlProducto;

    public DaniloManager() {
        this.meseros=new ArrayList<>();
        this.administradores=new ArrayList<>();
        this.productosPedido=new ArrayList<>();
        this.pedidos=new ArrayList<>();
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
        String usuarioRol;
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
    
    public void asignarMesa(List<Pedido> lista, Pedido pedido ){
        if (pedido.getNumeroMesa()<= 20) {
            int maxId = 0;
            for (Pedido c : lista) {
                if (c.getNumeroMesa()> maxId) {
                    maxId = c.getNumeroMesa();
                }
            }
            pedido.setNumeroMesa(maxId + 1);
        } 
        else{
            System.out.print("\n\nYa todas las mesas fueron asignadas.");
        }
    }
    
    public void calcularTotal(List<Producto> lista, Pedido pedido){
        long total=0;
        for(Producto c: lista){
            total=total+c.getPrecioProducto();
        }
        pedido.setTotal(total);
    }
    
    public Producto buscarProducto(List<Producto> lista, int idProducto) {
        for (Producto c: lista) {
            if(c.getIdProducto()==idProducto){
                return c;
            }
        }
        return null;
    }
    
    public void agregarProducto(List<Producto> listaProductos, List<Producto> productosNuevoPedido){
        boolean control = false;
        
        do {           
            try {
                System.out.print("Ingres el id del producto: ");
                int idProducto = tecla.nextInt();
                if(idProducto <0) throw new IllegalArgumentException();
                control = true;
                Producto nuevo = buscarProducto(listaProductos, idProducto);
                if(nuevo!=null){
                    productosNuevoPedido.add(nuevo);
                    System.out.print("Producto agregado.");
                }
                else{
                    System.out.print("\n\nNo Existe un Producto Con el Id Ingresado.");
                }
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admiten numeros negativos");
            }
        } while (!control);      
    }
    
    public boolean eliminarProducto(List<Producto> productosNuevoPedido, int idProducto){
        if (productosNuevoPedido == null) {
            throw new IllegalArgumentException("La lista y el objeto no pueden ser null.");
        }
        return productosNuevoPedido.removeIf(producto -> producto.getIdProducto()== idProducto);    
    }
    
    public void crearPedido(List<Producto> productosDisponibles){
        int opcion =0;
        List<Producto> productosDelPedido=new ArrayList<>();
        do {            
            boolean control = false;
            do {            
                try {
                    System.out.print("\nPedido Nuevo."
                    + "\n1. Agregar Producto."
                    + "\n2. Eliminar Producto."
                    + "\n3. Completar Pedido."
                    + "\n4. Cancelar Pedido / Volver al Menu anterior."
                    + "\n\nSeleccione una opcion: ");
            
                    opcion= tecla.nextInt();
                    control =true;
                    
                } catch (NumberFormatException e) {
                    System.out.print("Solo se aceptan Numeros como opcion.");
                }
                switch (opcion) {
                    case 1:
                        agregarProducto(productosDisponibles, productosDelPedido);
                        break;
                    case 2:
                        do {           
                            try {
                                System.out.print("Ingres el id del producto: ");
                                int idProducto = tecla.nextInt();
                                if(idProducto <0) throw new IllegalArgumentException();
                                control = true;
                                boolean eliminado= eliminarProducto(productosDelPedido, idProducto);
                                if(eliminado)System.out.print("\nProducto Eliminado.");
                                else System.out.print("\nProducto No Encontrado.");
                               
                            } 
                            catch (NumberFormatException e) {
                                System.out.print("\nSolo se admiten Numeros");
                            }
                            catch (IllegalArgumentException a){
                                System.out.print("\nNo se admiten numeros negativos");
                            }
                        } while (!control); 
                        break;
                        
                    case 3:
                        Pedido nuevo =new Pedido(0, 0, 0, "En Proceso", LocalDateTime.now(), "tarjeta", productosDelPedido);
                        asignarMesa(pedidos, nuevo);
                        calcularTotal(productosDelPedido, nuevo);
                        controlPedido.crear(pedidos, nuevo);
                        System.out.print("Pedido creado correctamente");
                        break;
                                
                    case 4:
                        System.out.print("Regresando al Menu.");
                        break;
                        
                    default:
                        System.out.print("\n\nOpcion Invalida.");
                }
                
            } while (!control);
            
        } while (opcion!=3 && opcion!=4);
       
    }
    
    public void crearProducto(List<Producto> productosRestaurante){
        System.out.print("Ingrese el Nombre del Producto:");
        String nombre = tecla.nextLine();
        System.out.print("Ingrese la Descripcion del Producto:");
        String descripcion = tecla.nextLine();
        long precio =0;
        boolean control = false;
        do {            
            try {
                System.out.print("Ingrese el Precio Del Producto del Producto:");
                precio=tecla.nextLong();
                if(precio<0) throw new IllegalArgumentException();
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se aceptan Numeros.");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se Aceptan Valores Negativos..");
            }
            
        } while (!control);
        
        Producto nuevo = new Producto(0, precio, nombre, descripcion);
        if (nuevo.getIdProducto()<= 0) {
            int maxId = 0;
            for (Producto c : productosRestaurante) {
                if (c.getIdProducto()> maxId) {
                    maxId = c.getIdProducto();
                }
            }
            nuevo.setIdProducto(maxId + 1);
        }
        productosRestaurante.add(nuevo);
        
    }
    
    public void eliminarPedido(List<Pedido> lista){
        boolean control = false;
        do {           
            try {
                System.out.print("Ingres el id del pedidido: ");
                int idPedido = tecla.nextInt();
                if(idPedido <0) throw new IllegalArgumentException();
                control = true;
                boolean eliminado= controlPedido.eliminar(lista, idPedido);
                if(eliminado)System.out.print("\nPedido Eliminado.");
                else System.out.print("\nPedido No Encontrado.");   
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admiten numeros negativos");
            }
        } while (!control);
    }
    
    public void marcarEntregado(){
        boolean control = false;
        do {           
            try {
                System.out.print("Ingrese el id del pedidido: ");
                int idPedido = tecla.nextInt();
                if(idPedido <0) throw new IllegalArgumentException();
                control = true;
                controlPedido.buscarPorId(pedidos, idPedido).setEstado("entregado");
                controlPedido.buscarPorId(pedidos, idPedido).setNumeroMesa(0);
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se admiten Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se admiten numeros negativos");
            }
        } while (!control);
    }
    
    public void meseroManager(int rol, List<Producto> productosDisponibles){
        int opcion =0;
        boolean control =false;
        do {
            do {
                try {
                    System.out.print("\nOpciones De Mesero:"
                        + "\n1. Registrar Mesero."
                        + "\n2. Actualizar Mesero."
                        + "\n3. Eliminar Mesero."
                        + "\n4. Crear Pedido"
                        + "\n5. Eliminar Pedido."
                        + "\n6. Marcar Pedido Entregado"
                        + "\n7. volver al menu anterior."
                        + "\n\nSeleccione una Opcion: ");
                    opcion = tecla.nextInt();
                    control= true;
                } catch (NumberFormatException e) {
                    System.out.print("\nSolo se aceptan Numeros.");
                } 
            } while (!control);
            
            switch (opcion) {
                case 1:
                    registrar(rol);
                    break;
                    
                case 2:
                    Modificar(rol);
                    break;
                case 3:
                    eliminar(rol);
                    break;
                case 4: 
                    crearPedido(productosDisponibles);
                    break;
                case 5:
                    eliminarPedido(pedidos);
                    break;
                case 6:
                    marcarEntregado();
                    break;
                case 7:
                    System.out.print("Regresando al menu anterior.");
                    break;
                default:
                    System.out.print("Opcion Invalida.");
                    break;
            }
        } while (opcion!=7);
    }
    
    public void administradorManager(int rol, List<Producto> productosDisponibles){
        int opcion =0;
        boolean control=false;
        do {            
            do {
                try {
                    System.out.print("Opciones de Administrador:"
                        + "\n1. Gestion De Productos."
                        + "\n2. Gestion De Mesas"
                        + "\n3. Gestion De Usuarios."
                        + "\n4. Ver Reportes."
                        + "\n5. Ver Tiempos y estados"
                        + "\n7. Regresar al Menu anterior."
                        + "\n\nSelecciones una Opcion: ");
                } catch (NumberFormatException e) {
                }
                
            } while (!control);
            switch (opcion) {
                case 1:
                    gestionProductos();
                    break;
                case 2:
                    gestionMesas();
                    break;
                case 3:
                    gestionUsuarios();
                    break;
                case 4:
                    
                    break;
                case 5:
                    verTiempos();
                    break;
                case 6:
                    
                    break;
                default:
                    break;
            }
        } while (opcion!=7);
    }
    
    public void verTiempos(){
        for(Pedido c: pedidos){
            System.out.print("Id del Pedido: "+c.getId()
                    +"\nMesa Asginada: "+c.getNumeroMesa()
                    +"\nTiempo de inicio del pedido: "+c.getMarcadeTiempo()
                    +"\nEstado: "+c.getEstado()+"\n\n");
        }
    }
    
    public void gestionProductos(){
        int opcion=0;
        boolean control = false;
        do{
            do {                
                try {
                    System.out.print("Opciones de Gestion de Producto:"
                            + "\n1. Crear Producto."
                            + "\n2. Eliminar Producto."
                            + "\n3. Modificar Producto"
                            + "\n4. Volver al Menu Anterior."
                            + "\n\nSeleccione una Opcion: ");
                    opcion = tecla.nextInt();
                    control =true;
                } catch (NumberFormatException e) {
                    System.out.print("\nSolo se aceptan Numeros.");
                }
            } while (!control);
            switch (opcion) {
                case 1:
                    crearProducto(productosPedido); 
                    break;
                case 2:
                    do {           
                        try {
                            System.out.print("Ingres el id del producto: ");
                            int idProducto = tecla.nextInt();
                            if(idProducto <0) throw new IllegalArgumentException();
                            control = true;
                            boolean eliminado= eliminarProducto(productosPedido, idProducto);
                            if(eliminado)System.out.print("\nProducto Eliminado.");
                            else System.out.print("\nProducto No Encontrado.");
                               
                        } 
                        catch (NumberFormatException e) {
                            System.out.print("\nSolo se admiten Numeros");
                        }
                        catch (IllegalArgumentException a){
                            System.out.print("\nNo se admiten numeros negativos");
                        }
                    } while (!control); 
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                        System.out.print("\nRegresando al Menu anterior.");
                    break;
                default:
                    break;
                }
        } while (opcion!=4);
    }
    
    public void modificarProducto(){
        boolean control = false;
        int idProducto = 0;
        Producto modificar;
        
        do {           
            try {
                System.out.print("Ingrese el id del producto que desea Modificar: ");
                idProducto = tecla.nextInt();
                if(idProducto <0) throw new IllegalArgumentException();
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
        modificar= controlProducto.buscarPorId(productosPedido, idProducto);
        if(modificar!=null){
            int opcion =0;
            do {
                do {                
                    try {
                        System.out.print("Que desea Modificar?"
                        + "\n1. Nombre."
                        + "\n2. Descripcion."
                        + "\n3. Precio."
                        + "\n4. Regresar al Menu principal."
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
                        modificar.setNombresProducto(nombre);
                        break;
                    case 2: 
                        System.out.print("\nIngrese la nueva descripcion: ");
                        String descripcion = tecla.nextLine();
                        modificar.setDescripcionProducto(descripcion);
                        break;
                    case 3:
                        do {           
                            try {
                                System.out.print("Ingrese el nuevo Precio: ");
                                long precio = tecla.nextLong();
                                if(precio<0) throw new IllegalArgumentException();
                                control = true;
                               modificar.setPrecioProducto(precio);
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
                    case 4:
                        System.out.print("\nRegresando al Menu Anterior.");
                        break;
                    default:
                        System.out.print("\\nOpcion Invalida.");
                        break;
                }
            } while (opcion != 4);
        }
    }
    
    public void gestionMesas(){
        boolean control=false;
        do {            
            try {
                System.out.print("Ingrese el id del pedido al que le quiere Modificar su Mesa: ");
                int idPedido = tecla.nextInt();
                if(idPedido<0) throw new IllegalArgumentException();
                control=true;
                Pedido modificar = controlPedido.buscarPorId(pedidos, idPedido);
                if(modificar!=null){
                    control =false;
                    do {                        
                        try {
                            System.out.print("Ingrese el nuevo numero de mesa: ");
                            int numMesa = tecla.nextInt();
                            if(numMesa<0) throw new IllegalArgumentException();
                            control = true;
                            int repetir=0;
                            for(Pedido c: pedidos){
                                if(c.getNumeroMesa()==numMesa){
                                    repetir++;
                                }
                            }
                            if(repetir==0){
                                modificar.setNumeroMesa(numMesa);
                                System.out.print("\nMesa Modificada Correctamente.");
                            }
                            else System.out.print("\nEsta Mesa ya esta asignada.");
                            
                        } 
                        catch (NumberFormatException e) {
                            System.out.print("\nSolo se Aceptan Numeros");
                        }
                        catch (IllegalArgumentException a){
                            System.out.print("\nNo se aceptan numeros Negativos");
                        }
                    } while (!control);
                    
                    
                }
            } 
            catch (NumberFormatException e) {
                System.out.print("\nSolo se Aceptan Numeros");
            }
            catch (IllegalArgumentException a){
                System.out.print("\nNo se aceptan numeros Negativos");
            }
        } while (!control);
    }
    
    public void gestionUsuarios(){
        int opcion=0;
        boolean control=false;
        do {            
            do {                
                try {
                    System.out.print("Que usuario Desea Gestionar?"
                    + "\n1. Administradores."
                    + "\n2. Meseros."
                    + "\n3. Cocineros."
                    + "\n4. Domiciliarios."
                    + "\n5. Clientes"
                    + "\n6. Regresar al menu anterior."
                    + "\n\nSeleccione uns Opcion: ");
                    opcion = tecla.nextInt();
                    control =true;
                } 
                catch (NumberFormatException e) {
                    System.out.print("\nSolo se Aceptan numeros.");
                }
            } while (!control);
            switch (opcion) {
                case 1:
                    metodosUsuarios(2);
                    break;
                case 2:
                    metodosUsuarios(1);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.print("\nOpcion Invalida.");
            }
        } while (opcion!=6);
    }
    
    public void metodosUsuarios(int rol){
        int opcion =0;
        boolean control = false;
        String usuario="";
        if(rol==1)usuario="Mesero";
        if(rol==2)usuario="Administrador";
        
        do {            
            do {                
                try {
                    System.out.print("Opciones de "+usuario+":"
                            + "\n1. Crear."
                            + "\n2. Eliminar."
                            + "\n3. Modificar."
                            + "\n4. Regresar al Menu anterior."
                            + "\nSeleccione una Opcion: ");
                    opcion = tecla.nextInt();
                    control =true;
                } catch (NumberFormatException e) {
                    System.out.print("Solo se Aceptan Numeros");
                }
            } while (!control);
            switch (opcion) {
                case 1:
                    registrar(rol);
                    break;
                case 2: 
                    eliminar(rol);
                    break;
                case 3: 
                    Modificar(rol);
                    break;
                case 4:
                    System.out.print("\nRegresando al menu anterior.");
                    break;
                default:
                    System.out.print("\nOpcion Invalida");
                    break;
            }
        } while (opcion!=4);
    }
}
