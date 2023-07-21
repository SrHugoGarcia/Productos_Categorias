package org.hugo.java.jdbc.models;

import javax.swing.plaf.PanelUI;
import java.lang.annotation.Target;
import java.util.Date;

public class Producto {
    private Long id;
    private String nombre;
    private Integer precio;
    private Date fechaRegistro;
    private Categoria categoria;
    private String sku;
    public Producto(){

    }

    public Producto(Long id, String nombre, Integer precio, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(this.id).append(" | ");
        sb.append("Nombre: ").append(this.nombre).append(" | ");
        sb.append("Precio: ").append(this.precio).append(" | ");
        sb.append("Fecha registro: ").append(this.fechaRegistro).append(" | ");
        sb.append("Categoria ID: ").append(this.categoria.getId()).append(" | ");
        sb.append("Categoria: ").append(this.categoria.getNombre()).append(" | ");
        sb.append("SKU: ").append(this.sku);
        return sb.toString();
    }
}
