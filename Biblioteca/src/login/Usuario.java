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
    public final int tipo;
    public final boolean esAdmin;
    
    public Usuario(int id, String nombre, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.esAdmin = tipo == 0;
    }
}
