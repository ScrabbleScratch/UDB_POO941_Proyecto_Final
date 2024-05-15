/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Migue, Mario
 */

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    private static Connection con;
    
    private static final String USUARIO = "biblioteca";
    private static final String PASSWD = "biblioteca";
    private static final String DB = "biblioteca";
    private static final String IP = "localhost";
    private static final String PUERTO = "3306";
    
    private static final String CON_STR = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + DB;
    
    public static Connection establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CON_STR, USUARIO, PASSWD);
            //JOptionPane.showMessageDialog(null,"Conexión exitosa con la base de datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No es posible conectar con la base de datos" + e.toString());
        }
        return con;
    }
}
