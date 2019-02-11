package com.idao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IUsuarioDao {

	public Connection conectarBaseDatos();

	public void insertarRegistro(Connection conn) throws SQLException;

	public void actualizaRegistro(Connection conn) throws SQLException;

	public void borraRegistro(Connection conn) throws SQLException;

	public void selectRegistros(Connection conn) throws SQLException;
}
