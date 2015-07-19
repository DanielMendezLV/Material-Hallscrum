package org.halley.md.hallscrum.Model;

/**
 * Created by retana on 22/05/2015.
 */
public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String nickname;
    private String contrasena;



    public Usuario(Integer idUsuario, String nombre, String apellido, String nickname, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.nickname=nickname;
        this.apellido=apellido;
        this.contrasena = contrasena;
    }

    public Usuario(String nombre, String apellido, String nickname, String contrasena) {
        this.nombre = nombre;
        this.nickname=nickname;
        this.apellido=apellido;
        this.contrasena = contrasena;
    }


    public Usuario() {

    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nick) {
        this.nickname = nick;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido){this.apellido=apellido;}
}
