package org.hugo.java.jdbc;

import org.hugo.java.jdbc.models.Categoria;
import org.hugo.java.jdbc.models.Producto;
import org.hugo.java.jdbc.repositorio.CategoriaRepositorioImpl;
import org.hugo.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.hugo.java.jdbc.repositorio.Repositorio;
import org.hugo.java.jdbc.servicio.CatalogoServicio;
import org.hugo.java.jdbc.servicio.Servicio;
import org.hugo.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class EjemploJdbc {
    public static void main(String[] args) throws SQLException {
        Servicio catalogo = new CatalogoServicio();
        /*Categoria c = catalogo.findByIdCategoria(4l);
        Categoria c2 = new Categoria();
        c2.setNombre("Nadar");*/
        /*Producto p = new Producto(null,"Cama",5210,new Date());
        p.setSku("9379373");
        Producto producto = catalogo.createProductoWithCategoria(p,c);*/
        catalogo.findAllProductos().forEach(System.out::println);
        catalogo.findAllCategorias().forEach(System.out::println);
        Categoria categoria = catalogo.findByIdCategoria(3l);
        categoria.setNombre("Hogar");
        catalogo.updateCategoria(categoria);
        //catalogo.deleteCategoria(10l);
    }
}
