/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roles;

/**
 *
 * @author Migue, Mario
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import database.Conexion;

public class Roles {
    public static Number[] roleData(int roleId) {
        try {
            String consulta = "SELECT * FROM rolparams WHERE rol = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Number[] {
                    rs.getInt("max_prestamos"),
                    rs.getInt("max_dias"),
                    rs.getDouble("mora_diaria")
                };
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    public static boolean configurar(int roleId, int maxRentals, int maxDays, double dailyFee) {
        try {
            String consulta = "UPDATE rolparams " +
                    "SET max_prestamos = ?, max_dias = ?, mora_diaria = ? " +
                    "WHERE rol = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            ps.setInt(1, maxRentals);
            ps.setInt(2, maxDays);
            ps.setDouble(3, dailyFee);
            ps.setInt(4, roleId);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cambio guardado");
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
