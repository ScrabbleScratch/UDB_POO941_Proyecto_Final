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
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import database.Conexion;
import utilidades.Tablas;
import utilidades.Fechas;

public class Prestamo {
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
    
    public static boolean devolver(String category, String prestamoId){
        try {
            String tableStr = category.toLowerCase();
            
            String consulta = "UPDATE prestamos_" + tableStr + " "
                    + "SET fecha_devuelto = ?"
                    + "WHERE id = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
                     
            ps.setString(1, Fechas.format(LocalDateTime.now()));
            ps.setString(2, prestamoId);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Préstamo devuelto");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return false;
    }
    
    public static DefaultTableModel prestamosCategoria(String category) {
        try {
            String consulta = "SELECT "
                    + "P.id, U.nombre, I.titulo, P.fecha_prestamo, P.fecha_devolucion, "
                    + "IF(P.fecha_devuelto IS NOT NULL, 'SI', 'NO') AS devuelto, "
                    + "IF(DATEDIFF(CURDATE(), P.fecha_devolucion) > 0, 'SI', 'NO') AS prestamo_excedido, "
                    + "CONCAT('$', R.mora_diaria) AS mora_diaria, "
                    + "CONCAT('$', IF(DATEDIFF(CURDATE(), P.fecha_devolucion) > 0, DATEDIFF(CURDATE(), P.fecha_devolucion) * R.mora_diaria, 0)) AS mora_total "
                    + "FROM prestamos_" + category + " AS P "
                    + "JOIN usuarios AS U ON U.id = P.usuario "
                    + "JOIN rolparams AS R ON R.rol = U.rol "
                    + "JOIN " + category + " AS I ON I.id = P.item_id "
                    + "WHERE P.fecha_devuelto IS NULL;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return Tablas.buildTableModelFromResultSet(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static List<String[]> prestamosUsuarioCategoria(String username, String category) {
        try {
            String consulta = "SELECT "
                    + "P.id, I.titulo, P.fecha_prestamo, P.fecha_devolucion, "
                    + "CONCAT('$', IF(DATEDIFF(CURDATE(), P.fecha_devolucion) > 0, DATEDIFF(CURDATE(), P.fecha_devolucion) * R.mora_diaria, 0)) AS mora_total "
                    + "FROM prestamos_" + category + " AS P "
                    + "JOIN usuarios AS U ON U.id = P.usuario "
                    + "JOIN rolparams AS R ON R.rol = U.rol "
                    + "JOIN " + category + " AS I ON I.id = P.item_id "
                    + "WHERE U.nombre = ? AND P.fecha_devuelto IS NULL;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            List<String[]> results = new ArrayList<>();
            while(rs.next()) {
                String[] rental = new String[] {
                    rs.getString("id"),
                    rs.getString("titulo"),
                    Fechas.format(rs.getDate("fecha_prestamo").toLocalDate()),
                    Fechas.format(rs.getDate("fecha_devolucion").toLocalDate()),
                    rs.getString("mora_total")
                };
                results.add(rental);
            }
            
            return results;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
}
