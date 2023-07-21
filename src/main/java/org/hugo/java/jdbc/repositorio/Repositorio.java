package org.hugo.java.jdbc.repositorio;

import org.hugo.java.jdbc.models.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio <T>{
    void setConexion(Connection conexion);
    List<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    T createOne(T t) throws SQLException;
    void updateOne(T t) throws SQLException;
    void deleteById(Long id) throws SQLException;

}
