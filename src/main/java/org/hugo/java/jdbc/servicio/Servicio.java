package org.hugo.java.jdbc.servicio;

import org.hugo.java.jdbc.models.Categoria;
import org.hugo.java.jdbc.models.Producto;

import java.sql.SQLException;
import java.util.List;

public interface Servicio {
    List<Producto> findAllProductos() throws SQLException;
    Producto findByIdProducto(Long id) throws SQLException;
    Producto createProducto(Producto producto) throws SQLException;
    void deleteProducto(Long id) throws SQLException;
    void updateProducto(Producto producto) throws  SQLException;
    Producto createProductoWithCategoria(Producto producto, Categoria categoria) throws SQLException;
    List<Categoria> findAllCategorias() throws SQLException;
    Categoria findByIdCategoria(Long id) throws SQLException;
    Categoria createCategoria(Categoria categoria) throws SQLException;
    void deleteCategoria(Long id) throws SQLException;
    void updateCategoria(Categoria categoria) throws  SQLException;
}
