package org.hugo.java.jdbc.servicio;

import org.hugo.java.jdbc.models.Categoria;
import org.hugo.java.jdbc.models.Producto;
import org.hugo.java.jdbc.repositorio.CategoriaRepositorioImpl;
import org.hugo.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.hugo.java.jdbc.repositorio.Repositorio;
import org.hugo.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class CatalogoServicio implements Servicio{
    private Repositorio<Producto> productoRepositorio;
    private Repositorio<Categoria> categoriaRepositorio;
    public CatalogoServicio(){
        this.productoRepositorio = new ProductoRepositorioImpl();
        this.categoriaRepositorio = new CategoriaRepositorioImpl();
    }
    @Override
    public List<Producto> findAllProductos() throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConexion(conexion);
            return productoRepositorio.findAll();
        }
    }

    @Override
    public Producto findByIdProducto(Long id) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConexion(conexion);
            return productoRepositorio.findById(id);
        }
    }

    @Override
    public Producto createProducto(Producto producto) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConexion(conexion);
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            Producto p = null;
            try{
                p= productoRepositorio.createOne(producto);
                conexion.commit();
            }catch (SQLException e){
                conexion.rollback();
                e.printStackTrace();
            }
            return p;
        }
    }

    @Override
    public void deleteProducto(Long id) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConexion(conexion);
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            try{
                productoRepositorio.deleteById(id);
                conexion.commit();
            }catch (SQLException e){
                conexion.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateProducto(Producto producto) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConexion(conexion);
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            try{
                productoRepositorio.updateOne(producto);
                conexion.commit();;
            }catch (SQLException e){
                conexion.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public Producto createProductoWithCategoria(Producto producto, Categoria categoria) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            productoRepositorio.setConexion(conexion);
            categoriaRepositorio.setConexion(conexion);
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            try{
                if(categoria.getId() == null){
                    categoria = categoriaRepositorio.createOne(categoria);
                }
                producto.setCategoria(categoria);
                producto = productoRepositorio.createOne(producto);
                conexion.commit();
            }catch (SQLException e){
                e.printStackTrace();
                conexion.rollback();
            }
            return producto;
        }
    }

    @Override
    public List<Categoria> findAllCategorias() throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConexion(conexion);
            return categoriaRepositorio.findAll();
        }
    }

    @Override
    public Categoria findByIdCategoria(Long id) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConexion(conexion);
            return categoriaRepositorio.findById(id);
        }
    }

    @Override
    public Categoria createCategoria(Categoria categoria) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConexion(conexion);
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            Categoria c = null;
            try{
                c = categoriaRepositorio.createOne(categoria);
                conexion.commit();
            }catch (SQLException e){
                conexion.rollback();
                e.printStackTrace();
            }
            return c;
        }
    }

    @Override
    public void deleteCategoria(Long id) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConexion(conexion);
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            try{
                categoriaRepositorio.deleteById(id);
                conexion.commit();
            }catch (SQLException e){
                conexion.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateCategoria(Categoria categoria) throws SQLException {
        try(Connection conexion = ConexionBaseDatos.getConnection()){
            categoriaRepositorio.setConexion(conexion);
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            try{
                categoriaRepositorio.updateOne(categoria);
                conexion.commit();
            }catch (SQLException e){
                conexion.rollback();
                e.printStackTrace();
            }
        }
    }
}
