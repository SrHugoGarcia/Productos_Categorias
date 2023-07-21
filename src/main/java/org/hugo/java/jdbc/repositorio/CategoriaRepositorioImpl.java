package org.hugo.java.jdbc.repositorio;

import org.hugo.java.jdbc.models.Categoria;
import org.hugo.java.jdbc.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImpl implements Repositorio<Categoria> {
    private Connection conexion;
    public CategoriaRepositorioImpl(){
    }
    public CategoriaRepositorioImpl(Connection conexion){
        this.conexion = conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Categoria> findAll() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try(PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()){
            while (resultado.next()){
                categorias.add(crearCategoria(resultado));
            }
        }
        return categorias;
    }

    @Override
    public Categoria findById(Long id) throws SQLException {
        Categoria categoria = new Categoria();
        String sql = "SELECT * FROM categorias WHERE id=?";
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setLong(1, id);
            try(ResultSet resultado = stmt.executeQuery()){
                if(resultado.next()){
                    categoria = crearCategoria(resultado);
                }
            }
        }
        return categoria;
    }

    @Override
    public Categoria createOne(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categorias(nombre) VALUES(?)";
        try(PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, categoria.getNombre());
            stmt.executeUpdate();
            try(ResultSet resultado = stmt.getGeneratedKeys()){
                if(resultado.next()){
                    categoria.setId(resultado.getLong(1));
                }
            }
        }
        return categoria;
    }

    @Override
    public void updateOne(Categoria categoria) throws SQLException {
        String sql = "UPDATE categorias SET nombre=? where id=?";
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setString(1, categoria.getNombre());
            stmt.setLong(2, categoria.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id=?";
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public static Categoria crearCategoria(ResultSet resultado) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultado.getLong("id"));
        categoria.setNombre(resultado.getString("nombre"));
        return categoria;
    }
}
