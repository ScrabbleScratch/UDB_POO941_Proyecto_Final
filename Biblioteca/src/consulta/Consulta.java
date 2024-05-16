/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package consulta;

/**
 *
 * @author Mario
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.Conexion;

public class Consulta {
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            modeloTabla.addColumn(metaData.getColumnName(column));
        }

        // data of the table
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowData[columnIndex - 1] = rs.getObject(columnIndex);
            }
            modeloTabla.addRow(rowData);
        }

        return modeloTabla;
    }
    
    public static StatusUsuario usuarioStatus(String username) {
        try {
            String consulta = "SELECT U.id, U.nombre, U.rol, R.max_prestamos, R.max_dias, R.mora_diaria "
                    + "FROM usuarios AS U "
                    + "JOIN rolparams AS R ON R.rol = U.rol "
                    + "WHERE U.nombre = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setString(1, username);
            ResultSet usrRs = ps.executeQuery();
            if (usrRs.next()) {
                int activeRents = 0;
                for (String table : new String[] { "libros", "obras", "revistas", "cds", "tesis" }) {
                    consulta = "SELECT COUNT(*) AS prestamos_activos "
                        + "FROM usuarios AS U "
                        + "JOIN prestamos_" + table + " AS P ON P.usuario = U.id "
                        + "WHERE U.nombre = ? AND P.fecha_devuelto IS NULL;";
                    ps = Conexion.establecerConexion().prepareStatement(consulta);
                    ps.setString(1, username);
                    ResultSet rentsRs = ps.executeQuery();
                    if (rentsRs.next())
                        activeRents += rentsRs.getInt("prestamos_activos");
                }
                
                return new StatusUsuario( 
                    usrRs.getString("id"),
                    usrRs.getString("nombre"),
                    usrRs.getInt("rol"),
                    usrRs.getInt("max_prestamos"),
                    usrRs.getInt("max_dias"),
                    usrRs.getInt("mora_diaria"),
                    activeRents
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] librosIds() {
        try {
            String consulta = "SELECT id FROM libros;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] ids = new String[columnCount];
            int counter = 0;
            while (rs.next()) {
                ids[counter] = rs.getString("id");
                counter++;
            }
            return ids;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] librosDescriptiveStr(String id) {
        try {
            String consulta = "SELECT "
                    + "CONCAT(A.nombre, ' | ', A.autor, ' | ', A.editorial) AS 'descripcion', "
                    + "IF(A.unidades > P.activos, 'Si', 'No') AS 'disponibilidad' "
                    + "FROM libros AS A "
                    + "LEFT JOIN ( "
                        + "SELECT libro, COUNT(*) AS activos "
                        + "FROM prestamos_libros "
                        + "WHERE fecha_devuelto IS NULL "
                        + "GROUP BY libro "
                    + ") AS P ON P.libro = A.id "
                    + "WHERE A.id = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[] { rs.getString("descripcion"), rs.getString("disponibilidad") };
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static DefaultTableModel librosData() {
        try {
            String consulta = "SELECT * FROM libros;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return buildTableModel(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] obrasIds() {
        try {
            String consulta = "SELECT id FROM obras;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] ids = new String[columnCount];
            int counter = 0;
            while (rs.next()) {
                ids[counter] = rs.getString("id");
                counter++;
            }
            return ids;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] obrasDescriptiveStr(String id) {
        try {
            String consulta = "SELECT "
                    + "CONCAT(A.nombre, ' | ', A.autor, ' | ', A.editorial) AS 'descripcion', "
                    + "IF(A.unidades > P.activos, 'Si', 'No') AS 'disponibilidad' "
                    + "FROM obras AS A "
                    + "LEFT JOIN ( "
                        + "SELECT obra, COUNT(*) AS activos "
                        + "FROM prestamos_obras "
                        + "WHERE fecha_devuelto IS NULL "
                        + "GROUP BY obra "
                    + ") AS P ON P.obra = A.id "
                    + "WHERE A.id = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[] { rs.getString("descripcion"), rs.getString("disponibilidad") };
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static DefaultTableModel obrasData() {
        try {
            String consulta = "SELECT * FROM obras;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return buildTableModel(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] revistasIds() {
        try {
            String consulta = "SELECT id FROM revistas;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] ids = new String[columnCount];
            int counter = 0;
            while (rs.next()) {
                ids[counter] = rs.getString("id");
                counter++;
            }
            return ids;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] revistasDescriptiveStr(String id) {
        try {
            String consulta = "SELECT "
                    + "CONCAT(A.nombre, ' | ', A.editorial) AS 'descripcion', "
                    + "IF(A.unidades > P.activos, 'Si', 'No') AS 'disponibilidad' "
                    + "FROM revistas AS A "
                    + "LEFT JOIN ( "
                        + "SELECT revista, COUNT(*) AS activos "
                        + "FROM prestamos_revistas "
                        + "WHERE fecha_devuelto IS NULL "
                        + "GROUP BY revista "
                    + ") AS P ON P.revista = A.id "
                    + "WHERE A.id = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[] { rs.getString("descripcion"), rs.getString("disponibilidad") };
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static DefaultTableModel revistasData() {
        try {
            String consulta = "SELECT * FROM revistas;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return buildTableModel(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] cdsIds() {
        try {
            String consulta = "SELECT id FROM cds;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] ids = new String[columnCount];
            int counter = 0;
            while (rs.next()) {
                ids[counter] = rs.getString("id");
                counter++;
            }
            return ids;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] cdsDescriptiveStr(String id) {
        try {
            String consulta = "SELECT "
                    + "CONCAT(A.nombre, ' | ', A.autor, ' | ', A.genero) AS 'descripcion', "
                    + "IF(A.unidades > P.activos, 'Si', 'No') AS 'disponibilidad' "
                    + "FROM cds AS A "
                    + "LEFT JOIN ( "
                        + "SELECT cd, COUNT(*) AS activos "
                        + "FROM prestamos_cds "
                        + "WHERE fecha_devuelto IS NULL "
                        + "GROUP BY cd "
                    + ") AS P ON P.cd = A.id "
                    + "WHERE A.id = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[] { rs.getString("descripcion"), rs.getString("disponibilidad") };
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static DefaultTableModel cdsData() {
        try {
            String consulta = "SELECT * FROM cds;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return buildTableModel(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] tesisIds() {
        try {
            String consulta = "SELECT id FROM tesis;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] ids = new String[columnCount];
            int counter = 0;
            while (rs.next()) {
                ids[counter] = rs.getString("id");
                counter++;
            }
            return ids;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static String[] tesisDescriptiveStr(String id) {
        try {
            String consulta = "SELECT "
                    + "CONCAT(A.nombre, ' | ', A.fecha_publicacion, ' | ', A.institucion, ' | ', A.facultad) AS 'descripcion', "
                    + "IF(A.unidades > P.activos, 'Si', 'No') AS 'disponibilidad' "
                    + "FROM tesis AS A "
                    + "LEFT JOIN ( "
                        + "SELECT tesis, COUNT(*) AS activos "
                        + "FROM prestamos_tesis "
                        + "WHERE fecha_devuelto IS NULL "
                        + "GROUP BY tesis "
                    + ") AS P ON P.tesis = A.id "
                    + "WHERE A.id = ?;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new String[] { rs.getString("descripcion"), rs.getString("disponibilidad") };
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static DefaultTableModel tesisData() {
        try {
            String consulta="SELECT * FROM tesis;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return buildTableModel(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
}
