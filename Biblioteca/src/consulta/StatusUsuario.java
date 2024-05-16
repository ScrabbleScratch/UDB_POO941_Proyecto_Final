/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consulta;

/**
 *
 * @author scrab
 */
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
}
