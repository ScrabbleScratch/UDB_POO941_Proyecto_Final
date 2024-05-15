/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login;

/**
 *
 * @author Mario
 */

public class Usuario {
    public final int id;
    public final String nombre;
    public final int rol;
    public final boolean esAdmin;
    
    public Usuario(int id, String nombre, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.esAdmin = rol == 1;
    }
}
