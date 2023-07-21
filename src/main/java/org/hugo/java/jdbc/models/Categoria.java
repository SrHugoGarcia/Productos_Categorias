package org.hugo.java.jdbc.models;

public class Categoria {
    private Long id;
    private String nombre;

    public Categoria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString(){
        return "ID: " + this.id + " | " + "Nombre: " + this.nombre;
    }
}
