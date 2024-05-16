/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

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
    public static boolean libro(JTextField nombre, JTextField autor, JTextField genero, JTextField editorial, JFormattedTextField isbn, JTextField anio, JTextField edicion, JTextField unidad, JTextField estante, JTextField palabras) {
        try {
            String consulta = "INSERT INTO libros (titulo, autor, genero, editorial, isbn, "
                    + "anio_publicacion, edicion, unidades, estante, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intEdicion = Integer.parseInt(edicion.getText());
            int intUnidad = Integer.parseInt(unidad.getText());
            
            ps.setString(1, nombre.getText());
            ps.setString(2, autor.getText());
            ps.setString(3, genero.getText());
            ps.setString(4, editorial.getText());
            ps.setString(5, isbn.getText());
            ps.setInt(6, intAnio);
            ps.setInt(7, intEdicion);
            ps.setInt(8, intUnidad);
            ps.setString(9, estante.getText());
            ps.setString(10, palabras.getText());
            
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
            String consulta = "INSERT INTO obras (titulo, autor, genero, editorial, isbn, "
                    + "anio_publicacion, edicion, unidades, estante, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intEdicion = Integer.parseInt(edicion.getText());
            int intUnidad = Integer.parseInt(unidad.getText());
            
            ps.setString(1, nombre.getText());
            ps.setString(2, autor.getText());
            ps.setString(3, genero.getText());
            ps.setString(4, editorial.getText());
            ps.setString(5, isbn.getText());
            ps.setInt(6, intAnio);
            ps.setInt(7, intEdicion);
            ps.setInt(8, intUnidad);
            ps.setString(9, estante.getText());
            ps.setString(10, palabras.getText());
            
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
            String consulta = "INSERT INTO revistas (titulo, editorial, frecuencia, issn, "
                    + "tematica, volumen, estante, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intVolumen = Integer.parseInt(volumen.getText());
            
            ps.setString(1, nombre.getText());
            ps.setString(2, editorial.getText());
            ps.setString(3, frecuencia.getSelectedItem().toString());
            ps.setString(4, issn.getText());
            ps.setString(5, tematica.getText());
            ps.setInt(6, intVolumen);
            ps.setString(7, estante.getText());
            ps.setString(8, palabras.getText());
            
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
                    + "(titulo, autor, genero, anio_publicacion, duracion, estante) "
                    + "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intDuracion = Integer.parseInt(duracion.getText());
            
            ps.setString(1, nombre.getText());
            ps.setString(2, autor.getText());
            ps.setString(3, genero.getText());
            ps.setInt(4, intAnio);
            ps.setInt(5, intDuracion);
            ps.setString(6, estante.getText());
            
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
                    + "(titulo, fecha_publicacion, institucion, facultad, paginas, estante) "
                    + "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intPaginas = Integer.parseInt(paginas.getText());
            
            ps.setString(1, titulo.getText());
            ps.setString(2, fecha.getText());
            ps.setString(3, institucion.getText());
            ps.setString(4, facultad.getText());
            ps.setInt(5, intPaginas);
            ps.setString(6, ubicacion.getText());
            
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
