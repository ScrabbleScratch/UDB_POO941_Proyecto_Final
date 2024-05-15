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
            String consulta = "INSERT INTO usuarios (nombre, passwd, tipo) VALUES (?, ?, ?);";
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
    
    public static boolean libro(JTextField codigo, JTextField nombre, JTextField autor, JTextField genero, JTextField editorial, JFormattedTextField isbn, JTextField anio, JTextField edicion, JTextField unidad, JTextField estante, JTextField palabras) {
        try {
            String consulta = "INSERT INTO libros (codigo, nombre, autor, genero, editorial, isbn, "
                    + "anio_publicacion, edicion, unidades, disponible, estante, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intEdicion = Integer.parseInt(edicion.getText());
            int intUnidad = Integer.parseInt(unidad.getText());
            int disponible = 1;
            
            ps.setString(1, codigo.getText());
            ps.setString(2, nombre.getText());
            ps.setString(3, autor.getText());
            ps.setString(4, genero.getText());
            ps.setString(5, editorial.getText());
            ps.setString(6, isbn.getText());
            ps.setInt(7, intAnio);
            ps.setInt(8, intEdicion);
            ps.setInt(9, intUnidad);
            ps.setInt(10, disponible);
            ps.setString(11, estante.getText());
            ps.setString(12, palabras.getText());
            
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
    
    public static boolean obra(JTextField codigo, JTextField nombre, JTextField autor, JTextField genero, JTextField editorial, JFormattedTextField isbn, JTextField anio, JTextField edicion, JTextField unidad, JTextField estante, JTextField palabras) {
        try {
            String consulta = "INSERT INTO obras (codigo, nombre, autor, genero, editorial, isbn, "
                    + "anio_publicacion, edicion, unidades, disponible, estante, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intEdicion = Integer.parseInt(edicion.getText());
            int intUnidad = Integer.parseInt(unidad.getText());
            int disponible = 1;
            
            ps.setString(1, codigo.getText());
            ps.setString(2, nombre.getText());
            ps.setString(3, autor.getText());
            ps.setString(4, genero.getText());
            ps.setString(5, editorial.getText());
            ps.setString(6, isbn.getText());
            ps.setInt(7, intAnio);
            ps.setInt(8, intEdicion);
            ps.setInt(9, intUnidad);
            ps.setInt(10, disponible);
            ps.setString(11, estante.getText());
            ps.setString(12, palabras.getText());
            
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
    
    public static boolean revista(JTextField codigo, JTextField nombre, JTextField editorial, JComboBox frecuencia, JFormattedTextField issn, JTextField tematica, JTextField volumen, JTextField estante, JTextField palabras) {
        try {
            String consulta = "INSERT INTO revistas (codigo, nombre, editorial, frecuencia, issn, "
                    + "tematica, volumen, estante, disponible, palabras_clave) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intVolumen = Integer.parseInt(volumen.getText());
            int disponible = 1;
            
            
            ps.setString(1, codigo.getText());
            ps.setString(2, nombre.getText());
            ps.setString(3, editorial.getText());
            ps.setString(4, frecuencia.getSelectedItem().toString());
            ps.setString(5, issn.getText());
            ps.setString(6, tematica.getText());
            ps.setInt(7, intVolumen);
            ps.setString(8, estante.getText());
            ps.setInt(9, disponible);
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
    
    public static boolean cd(JTextField codigo, JTextField nombre, JTextField autor, JTextField genero, JTextField anio, JTextField duracion, JTextField estante) {
        try {
            String consulta = "INSERT INTO cds "
                    + "(codigo, nombre, autor, genero, anio_publicacion, duracion, disponible, estante) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intAnio = Integer.parseInt(anio.getText());
            int intDuracion = Integer.parseInt(duracion.getText());
            int disponible = 1;
            
            
            ps.setString(1,codigo.getText());
            ps.setString(2, nombre.getText());
            ps.setString(3, autor.getText());
            ps.setString(4, genero.getText());
            ps.setInt(5, intAnio);
            ps.setInt(6, intDuracion);
            ps.setInt(7, disponible);
            ps.setString(8, estante.getText());
            
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
    
    public static boolean tesis(JTextField codigo, JTextField titulo, JTextField fecha, JTextField institucion, JTextField facultad, JTextField paginas, JTextField ubicacion) {
        try {
            String consulta="INSERT INTO tesis "
                    + "(codigo, nombre, fecha_publicacion, institucion, facultad, paginas, estante, disponible) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
            
            int intPaginas = Integer.parseInt(paginas.getText());
            int disponible = 1;
            
            
            ps.setString(1, codigo.getText());
            ps.setString(2, titulo.getText());
            ps.setString(3, fecha.getText());
            ps.setString(4, institucion.getText());
            ps.setString(5, facultad.getText());
            ps.setInt(6, intPaginas);
            ps.setString(7, ubicacion.getText());
            ps.setInt(8, disponible);
            
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
