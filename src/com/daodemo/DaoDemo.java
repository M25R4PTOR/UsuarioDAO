package com.daodemo;

import java.sql.Connection;
import java.sql.SQLException;

import com.dao.UsuarioDaoImpl;

public class DaoDemo {

	public static void main(String[] args) throws SQLException {
		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

		Connection conn = usuarioDao.conectarBaseDatos();

		usuarioDao.insertarRegistro(conn);
		usuarioDao.selectRegistros(conn);
		usuarioDao.actualizaRegistro(conn);
		usuarioDao.selectRegistros(conn);
		usuarioDao.borraRegistro(conn);
		usuarioDao.selectRegistros(conn);

	}

}
