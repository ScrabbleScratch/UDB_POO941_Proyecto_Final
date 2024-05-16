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
import java.util.HashMap;
import java.util.Map;

import database.Conexion;

public class StatusUsuario {
    public final String userId;
    public final String nombre;
    public final int rolId;
    public final int maxPrestamos;
    public final int maxDias;
    public final float moraDiaria;
    public final int prestamosActivos;
    public final Map<String, String[]> moraAcumulada;
    public final boolean limiteExcedido;
    public final boolean tieneMora;
    public final boolean puedePrestar;
    
    public StatusUsuario(String userId, String nombre, int rolId, int maxPrestamos, int maxDias, float moraDiaria, int prestamosActivos) {
        this.userId = userId;
        this.nombre = nombre;
        this.rolId = rolId;
        this.maxPrestamos = maxPrestamos;
        this.maxDias = maxDias;
        this.moraDiaria = moraDiaria;
        this.prestamosActivos = prestamosActivos;
        this.moraAcumulada = moraAcumulada(nombre);
        this.limiteExcedido = prestamosActivos >= maxPrestamos;
        this.tieneMora = !this.moraAcumulada.get("total")[0].equals("0");
        this.puedePrestar = this.limiteExcedido || this.tieneMora;
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
    
    private static Map<String, String[]> moraAcumulada(String username) {
        try {
            Map<String, String[]> results = new HashMap<>();
            String[] total = new String[] { "0", "0" };
            for (String category : new String[] { "libros", "obras", "revistas", "cds", "tesis" }) {
                String consulta = "SELECT "
                            + "COUNT(id) AS prestamos_pendientes, "
                            + "IF(SUM(mora_acumulada) > 0, SUM(mora_acumulada), 0) AS mora_total "
                        + "FROM ( "
                            + "SELECT P.id, P.fecha_devolucion, R.mora_diaria, "
                                + "IF(DATEDIFF(CURDATE(), fecha_devolucion) > 0, DATEDIFF(CURDATE(), fecha_devolucion) * mora_diaria, 0) AS mora_acumulada "
                            + "FROM prestamos_" + category + " AS P "
                            + "JOIN usuarios AS U ON U.id = P.usuario "
                            + "JOIN rolparams AS R ON R.rol = U.rol "
                            + "JOIN libros AS I ON I.id = P.item_id "
                            + "WHERE U.nombre = ? AND P.fecha_devuelto IS NULL "
                        + ") AS P "
                        + "WHERE P.mora_acumulada > 0;";
                PreparedStatement ps = Conexion.establecerConexion().prepareStatement(consulta);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                
                String[] catFee = new String[2];
                if (rs.next()) {
                    catFee[0] = rs.getString("prestamos_pendientes");
                    catFee[1] = "$" + rs.getString("mora_total");
                    
                    total[0] = Integer.toString(Integer.parseInt(total[0]) + rs.getInt("prestamos_pendientes"));
                    total[1] = Double.toString(Double.parseDouble(total[1]) + rs.getDouble("mora_total"));
                }
                
                results.put(category, catFee);
            }
            total[1] = "$" + total[1];
            results.put("total", total);
            
            return results;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
        
        return null;
    }
}
