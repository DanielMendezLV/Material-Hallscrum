package org.halley.md.hallscrum.Model;

/**
 * Created by U on 14/07/2015.
 */
public class Meta {
    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    private int foto;

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private boolean estado;
    private String descripcion;
}



