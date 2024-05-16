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
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.Conexion;
import utilidades.Tablas;

public class Prestamo {
    public enum Categoria {
        LIBROS,
        OBRAS,
        REVISTAS,
        CDS,
        TESIS
    };
    
    public static boolean registrar(String category, String userId, String returnDate, String itemId){
        try {
            String tableStr = category.toLowerCase();
            
            if (!category.equals("Tesis"))
                tableStr = tableStr.toLowerCase() + "s";
            
            String consulta = "INSERT INTO prestamos_" + tableStr + " "
                    + "(usuario, fecha_devolucion, item_id) VALUES "
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
    
    public static DefaultTableModel prestamosCategoria(Categoria category) {
        try {
            String consulta = "SELECT "
                    + "P.id, U.nombre, I.titulo, P.fecha_prestamo, P.fecha_devolucion, "
                    + "IF(P.fecha_devuelto IS NOT NULL, 'SI', 'NO') AS devuelto, "
                    + "IF(DATEDIFF(CURDATE(), P.fecha_devolucion) > 0, 'SI', 'NO') AS limite_excedido "
                    + "FROM prestamos_" + category.toString() + " AS P "
                    + "JOIN usuarios AS U ON U.id = P.usuario "
                    + "JOIN rolparams AS R ON R.rol = U.rol "
                    + "JOIN " + category.toString() + " AS I ON I.id = P.item_id "
                    + "WHERE P.fecha_devuelto IS NULL;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return Tablas.buildTableModelFromResultSet(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
}
