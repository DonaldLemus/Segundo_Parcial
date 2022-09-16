package com.progra2.tarea2.segundo_parcial;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Cls_Parcial {
    
    public static void consulta(int niv){
        //Contiene la direccion de nuestra base de datos
        String url = "jdbc:mysql://localhost:3306/dbvendedores?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {
            //Creamos el objeto conexion con el string anterior, usuario root y su contraseña
            Connection conexion = DriverManager.getConnection(url,"root","donald225");
            //paso 3 creamos el objeto statement
            Statement sentencia = conexion.createStatement();
            //Este string contiene la instruccion para Mysql
            String sql = "select *, sum(enero + febrero + marzo + abril + mayo + junio) from tb_vendedores where niv = "+niv;
            //Ejecutamos el querito
            ResultSet resultado = sentencia.executeQuery(sql);
            //precesamos el resultado, haciendo una iteracion
            while(resultado.next()){
                System.out.println("Correlativo: "+resultado.getInt(1));
                System.out.println("Niv: "+resultado.getString(2));
                System.out.println("Nombre: "+resultado.getString(3));
                System.out.println("Enero: "+resultado.getInt(4));
                System.out.println("Febrero: "+resultado.getInt(5));
                System.out.println("Marzo: "+resultado.getInt(6));
                System.out.println("Abril: "+resultado.getInt(7));
                System.out.println("Mayo: "+resultado.getInt(8));
                System.out.println("Junio: "+resultado.getInt(9));
                System.out.println("Total: "+resultado.getInt(10));
            }          
        } catch (SQLException ex) {
            System.out.println("Hubo un error:"+ex.getMessage());
        }
    }
    
    public static void listar(){
        String url = "jdbc:mysql://localhost:3306/dbvendedores?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {

            Connection conexion = DriverManager.getConnection(url,"root","donald225");
            Statement sentencia = conexion.createStatement();
            String sql = "select nombre from tb_vendedores";
            String sql2 = "select sum(enero), sum(febrero), sum(marzo), sum(abril), sum(mayo), sum(junio) from tb_vendedores";
            ResultSet resultado = sentencia.executeQuery(sql);
            
            while(resultado.next()){
                System.out.print("Nombre: "+resultado.getString(1)+" || ");
            }
            ResultSet resultado2 = sentencia.executeQuery(sql2);
            System.out.println("");
            System.out.println("");
            while(resultado2.next()){
                System.out.print("\nTotal Enero: "+resultado2.getString(1)+"  ||");
                System.out.print("\tTotal Febrero: "+resultado2.getString(2)+"  ||");
                System.out.print("\tTotal Marzo: "+resultado2.getString(3)+"  ||");
                System.out.print("\tTotal Abril: "+resultado2.getString(4)+"  ||");
                System.out.print("\tTotal Mayo: "+resultado2.getString(5)+"  ||");
                System.out.print("\tTotal Junio: "+resultado2.getString(6)+"  ||");
            }
           
        } catch (SQLException ex) {
            System.out.println("Hubo un error:"+ex.getMessage());
        }
    }
    
    public static void eliminar(int niv){
        String url = "jdbc:mysql://localhost:3306/dbvendedores?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {
            Connection conexion = DriverManager.getConnection(url,"root","donald225");
            Statement sentencia = conexion.createStatement();
            String sql = "delete from tb_vendedores where niv = "+niv;
            sentencia.execute(sql);
            System.out.println("Vendedor eliminado");
           
        } catch (SQLException ex) {
            System.out.println("Hubo un error:"+ex.getMessage());
        }
    }
    
    
    public static void actualizar(int niv, String nombre){
        String url = "jdbc:mysql://localhost:3306/dbvendedores?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
        try {
            Connection conexion = DriverManager.getConnection(url,"root","donald225");
            Statement sentencia = conexion.createStatement();
            String sql = "update tb_vendedores set nombre = '"+nombre+"' where niv = "+niv;
            sentencia.execute(sql);
            System.out.println("Vendedor actualizado");
           
        } catch (SQLException ex) {
            System.out.println("Hubo error:"+ex.getMessage());
        }
    }

    
    
     public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String nombre;
        int opcion, pregunta, niv;
        do{
            System.out.println("Menu");
            System.out.println("Seleccione una opcion: ");   
            System.out.println("1. Consulta de vendedor");
            System.out.println("2. Listar todos los vendedores y el total de cada mes");         
            System.out.println("3. Eliminar un vendedor"); 
            System.out.println("4. Actualizar informacion de un vendedor");
            System.out.println("5 Salir");    
            opcion = Integer.parseInt(scanner.nextLine());

            switch(opcion){
                case 1:
                    System.out.println("Ingrese el niv del vendedor:");
                    niv = Integer.parseInt(scanner.nextLine());
                    consulta(niv);
                    System.out.println("");
                    System.out.println("");
                    break;
                case 2:
                    listar();
                    System.out.println("");
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Ingese el niv del vendedor a eliminar: ");
                    niv = Integer.parseInt(scanner.nextLine());
                    System.out.println("Seguro de querer eliminar al vendedor?: ");
                    System.out.println("1 para Si y 0 para No");
                    pregunta = Integer.parseInt(scanner.nextLine());
                    if(pregunta == 1){
                        eliminar(niv);
                    }else {
                        System.out.println("Vendedor no eliminado");
                    }
                    System.out.println("");
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Ingrese el niv del vendedor: ");
                    niv = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese el nuevo nombre: ");
                    nombre = scanner.nextLine();
                    actualizar(niv, nombre);
                    System.out.println("");
                    System.out.println("");
                    break;
                case 5: 
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    System.out.println("");
                    System.out.println("");
                    break;
            }
            
        }while(opcion != 5);    
     }
}
