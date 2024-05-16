/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package usuario;

/**
 *
 * @author scrab
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import database.Conexion;

public class StatusUsuario {
    public final String userId;
    public final String nombre;
    public final int rolId;
    public final int maxPrestamos;
    public final int maxDias;
    public final boolean puedePrestar;
    public final float moraDiaria;
    public final int prestamosActivos;
    
    public StatusUsuario(String userId, String nombre, int rolId, int maxPrestamos, int maxDias, float moraDiaria, int prestamosActivos) {
        this.userId = userId;
        this.nombre = nombre;
        this.rolId = rolId;
        this.maxPrestamos = maxPrestamos;
        this.maxDias = maxDias;
        this.puedePrestar = prestamosActivos < maxPrestamos;
        this.moraDiaria = moraDiaria;
        this.prestamosActivos = prestamosActivos;
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
}
