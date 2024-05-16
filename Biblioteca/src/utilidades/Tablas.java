/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

/**
 *
 * @author Mario
 */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Tablas {
    public static DefaultTableModel buildTableModelFromResultSet(ResultSet rs) throws SQLException {
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
}
