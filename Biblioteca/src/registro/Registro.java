/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registro;

/**
 *
 * @author Mario
 */

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import database.Conexion;

public class Registro {
    public static boolean usuario(JTextField nombre, JTextField passwd, int tipo){
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
    
    public static boolean prestamo(String category, String userId, String returnDate, String itemId){
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
    
    public static boolean libro(JTextField nombre, JTextField autor, JTextField genero, JTextField editorial, JFormattedTextField isbn, JTextField anio, JTextField edicion, JTextField unidad, JTextField estante, JTextField palabras) {
        try {
            String consulta = "INSERT INTO libros (nombre, autor, genero, editorial, isbn, "
                    + "anio_publicacion, edicion, unidades, disponible, estante, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intEdicion = Integer.parseInt(edicion.getText());
            int intUnidad = Integer.parseInt(unidad.getText());
            int disponible = 1;
            
            ps.setString(1, nombre.getText());
            ps.setString(2, autor.getText());
            ps.setString(3, genero.getText());
            ps.setString(4, editorial.getText());
            ps.setString(5, isbn.getText());
            ps.setInt(6, intAnio);
            ps.setInt(7, intEdicion);
            ps.setInt(8, intUnidad);
            ps.setInt(9, disponible);
            ps.setString(10, estante.getText());
            ps.setString(11, palabras.getText());
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return false;
    }
    
    public static boolean obra(JTextField nombre, JTextField autor, JTextField genero, JTextField editorial, JFormattedTextField isbn, JTextField anio, JTextField edicion, JTextField unidad, JTextField estante, JTextField palabras) {
        try {
            String consulta = "INSERT INTO obras (nombre, autor, genero, editorial, isbn, "
                    + "anio_publicacion, edicion, unidades, disponible, estante, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intEdicion = Integer.parseInt(edicion.getText());
            int intUnidad = Integer.parseInt(unidad.getText());
            int disponible = 1;
            
            ps.setString(1, nombre.getText());
            ps.setString(2, autor.getText());
            ps.setString(3, genero.getText());
            ps.setString(4, editorial.getText());
            ps.setString(5, isbn.getText());
            ps.setInt(6, intAnio);
            ps.setInt(7, intEdicion);
            ps.setInt(8, intUnidad);
            ps.setInt(9, disponible);
            ps.setString(10, estante.getText());
            ps.setString(11, palabras.getText());
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return false;
    }
    
    public static boolean revista(JTextField nombre, JTextField editorial, JComboBox frecuencia, JFormattedTextField issn, JTextField tematica, JTextField volumen, JTextField estante, JTextField palabras) {
        try {
            String consulta = "INSERT INTO revistas (nombre, editorial, frecuencia, issn, "
                    + "tematica, volumen, estante, disponible, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intVolumen = Integer.parseInt(volumen.getText());
            int disponible = 1;
            
            ps.setString(1, nombre.getText());
            ps.setString(2, editorial.getText());
            ps.setString(3, frecuencia.getSelectedItem().toString());
            ps.setString(4, issn.getText());
            ps.setString(5, tematica.getText());
            ps.setInt(6, intVolumen);
            ps.setString(7, estante.getText());
            ps.setInt(8, disponible);
            ps.setString(9, palabras.getText());
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return false;
    }
    
    public static boolean cd(JTextField nombre, JTextField autor, JTextField genero, JTextField anio, JTextField duracion, JTextField estante) {
        try {
            String consulta = "INSERT INTO cds "
                    + "(nombre, autor, genero, anio_publicacion, duracion, disponible, estante) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intDuracion = Integer.parseInt(duracion.getText());
            int disponible = 1;
            
            ps.setString(1, nombre.getText());
            ps.setString(2, autor.getText());
            ps.setString(3, genero.getText());
            ps.setInt(4, intAnio);
            ps.setInt(5, intDuracion);
            ps.setInt(6, disponible);
            ps.setString(7, estante.getText());
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar los datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return false;
    }
    
    public static boolean tesis(JTextField titulo, JTextField fecha, JTextField institucion, JTextField facultad, JTextField paginas, JTextField ubicacion) {
        try {
            String consulta="INSERT INTO tesis "
                    + "(nombre, fecha_publicacion, institucion, facultad, paginas, estante, disponible) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intPaginas = Integer.parseInt(paginas.getText());
            int disponible = 1;
            
            ps.setString(1, titulo.getText());
            ps.setString(2, fecha.getText());
            ps.setString(3, institucion.getText());
            ps.setString(4, facultad.getText());
            ps.setInt(5, intPaginas);
            ps.setString(6, ubicacion.getText());
            ps.setInt(7, disponible);
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Registro guardado");
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
