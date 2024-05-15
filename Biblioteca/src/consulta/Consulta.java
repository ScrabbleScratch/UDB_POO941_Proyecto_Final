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
    
    public static DefaultTableModel libro() {
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
    
    public static DefaultTableModel revista() {
        try {
            String consulta = "SELECT * FROM revista;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return buildTableModel(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static DefaultTableModel cd() {
        try {
            String consulta = "SELECT * FROM cd;";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            return buildTableModel(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
    
    public static DefaultTableModel tesis() {
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
