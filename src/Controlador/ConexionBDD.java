/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConexionBDD {
    java.sql.Connection conexion;
     public java.sql.Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://localhost/BusquedaDeLibros?autoReconnect=true&useSSL=false","root","0984315441k");
        } catch (ClassNotFoundException | SQLException e)
        {
             System.out.println("ERROR DE CONEXION A LA BASE DE DATOS");
        }
        return conexion;
    }
    
}
