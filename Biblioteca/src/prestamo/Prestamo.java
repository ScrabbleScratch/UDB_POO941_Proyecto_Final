/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prestamo;

/**
 *
 * @author scrab
 */

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import database.Conexion;

public class Prestamo {
    public static boolean registrar(String category, String userId, String returnDate, String itemId){
        try {
            String catStr = category.toLowerCase();
            String tableStr = catStr;
            
            if (!catStr.equals("tesis"))
                tableStr = catStr + "s";
            
            String consulta = "INSERT INTO prestamos_" + tableStr + " "
                    + "(usuario, fecha_devolucion, " + catStr + ") VALUES "
                    + "(?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
                     
            ps.setInt(1, Integer.parseInt(userId));
            ps.setString(2, returnDate);
            ps.setInt(3, Integer.parseInt(itemId));
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Préstamo guardado");
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
