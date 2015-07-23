package org.halley.md.hallscrum.Model;

/**
 * Created by Mendez Diaz on 16/07/2015.
 */
public class Team {
    private int idEquipo;
    private String nombre;
    private String key;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    private int foto;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public String toString() {
        return this.nombre;            // What to display in the Spinner list.
    }


    public Team() {
    }

    public Team(int idEquipo, String nombre, String key) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
