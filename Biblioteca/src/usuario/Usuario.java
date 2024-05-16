/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario;

/**
 *
 * @author Mario
 */

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.Conexion;

public class Usuario {
    public final int id;
    public final String nombre;
    public final int rol;
    public final boolean esAdmin;
    
    public Usuario(int id, String nombre, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.esAdmin = rol == 1;
    }
    
    public static boolean registrar(JTextField nombre, JTextField passwd, int tipo){
        try {
            String consulta = "INSERT INTO usuarios (nombre, passwd, rol) VALUES (?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
                     
            ps.setString(1, nombre.getText());
            ps.setString(2, passwd.getText());
            ps.setInt(3, tipo);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Usuario guardado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return false;
    }
}
