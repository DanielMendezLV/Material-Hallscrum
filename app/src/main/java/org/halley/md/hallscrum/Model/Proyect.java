package org.halley.md.hallscrum.Model;

/**
 * Created by Mendez Diaz on 11/06/2015.
 */
public class Proyect {
    private int idProyecto;
    private String nombre;
    private String fechaCreacion;
    private int foto;

    public Proyect() {
    }

    public Proyect(String nombre, String fechaCreacion, int foto) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.foto = foto;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }







}
