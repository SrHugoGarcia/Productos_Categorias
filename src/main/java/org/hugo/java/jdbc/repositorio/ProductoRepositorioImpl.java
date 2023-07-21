package org.hugo.java.jdbc.repositorio;

import org.hugo.java.jdbc.models.Categoria;
import org.hugo.java.jdbc.models.Producto;
import org.hugo.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto>{
    private Connection conexion;
    public ProductoRepositorioImpl() {
    }
    public ProductoRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try(Statement stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                    "inner join categorias as c on (p.categoria_id=c.id)")){
            while(resultado.next()){
                productos.add(crearProducto(resultado));
            }
        }
        return productos;
    }

    @Override
    public Producto findById(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement stmt = conexion.
                prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                        "inner join categorias as c on (p.categoria_id=c.id) WHERE p.id=?")){
            stmt.setLong(1, id);
            try(ResultSet resultado = stmt.executeQuery()){
                if(resultado.next()){
                    producto = crearProducto(resultado);
                }
            }
        }
        return producto;
    }



    @Override
    public Producto createOne(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos(nombre,precio,fecha_registro, categoria_id, sku) VALUES(?,?,?,?,?)";
        try(PreparedStatement stmt = conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setDate(3, new Date(producto.getFechaRegistro().getTime()));
            stmt.setLong(4, producto.getCategoria().getId());
            stmt.setString(5,producto.getSku());
            stmt.executeUpdate();
            try(ResultSet resultado = stmt.getGeneratedKeys()){
                if(resultado.next()){
                    producto.setId(resultado.getLong(1));
                }
            }
        }
        return producto;
    }

    @Override
    public void updateOne(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=?, sku=? where id=?";
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());
            stmt.setString(4,producto.getSku());
            stmt.setLong(5, producto.getId());
            int resultado = stmt.executeUpdate();
            //System.out.println("resultado = " + resultado);
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    private static Producto crearProducto(ResultSet resultado) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultado.getLong("id"));
        producto.setNombre(resultado.getString("nombre"));
        producto.setPrecio(resultado.getInt("precio"));
        producto.setFechaRegistro(resultado.getDate("fecha_registro"));
        producto.setSku(resultado.getString("sku"));
        Categoria categoria = new Categoria();
        categoria.setId(resultado.getLong("categoria_id"));
        categoria.setNombre(resultado.getString("categoria"));
        producto.setCategoria(categoria);
        return producto;
    }
}
