package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.idao.IUsuarioDao;
import com.model.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao {

	List<Usuario> usuarios;

	public UsuarioDaoImpl() {
		usuarios = new ArrayList<>();
		Usuario usuario1 = new Usuario("Usuario1", "Pass1", "Nombre1", "Correo1");
		Usuario usuario2 = new Usuario("Usuario1Actualizado", "Pass1Actualizado", "Nombre1", "Correo1Actualizado");
		usuarios.add(usuario1);
		usuarios.add(usuario2);
	}

	@Override
	public Connection conectarBaseDatos() {
		try {
			try {
				// Se registra el Driver de MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error al registrar el driver de MySQL: " + ex);
			}
			Connection connection = null;

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");

			boolean valid = connection.isValid(5000);
			System.out.println(valid ? "TEST OK" : "TEST FAIL");

			return connection;

		} catch (java.sql.SQLException sqle) {
			System.out.println("Error: " + sqle);
		}
		return null;// Estara bien¿?
	}

	@Override
	public void insertarRegistro(Connection conn) throws SQLException {
		String sql = "INSERT INTO usuario (user, pass, nombre, correo) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, usuarios.get(0).getUser());
		statement.setString(2, usuarios.get(0).getPass());
		statement.setString(3, usuarios.get(0).getNombre());
		statement.setString(4, usuarios.get(0).getCorreo());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Usuario insertado!");
		}

	}

	@Override
	public void actualizaRegistro(Connection conn) throws SQLException {
		String sql = "UPDATE usuario SET user=?, pass=?, correo=? WHERE nombre=?";

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setString(1, usuarios.get(1).getUser());
		statement.setString(2, usuarios.get(1).getPass());
		statement.setString(3, usuarios.get(1).getCorreo());
		statement.setString(4, usuarios.get(1).getNombre());

		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			System.out.println("Actualizado!");
		}
	}

	@Override
	public void borraRegistro(Connection conn) throws SQLException {
		String sql = "DELETE FROM usuario WHERE user=?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, usuarios.get(1).getUser());

		int rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
			System.out.println("Registro eliminado!");
		}
	}

	@Override
	public void selectRegistros(Connection conn) throws SQLException {
		String sql = "Select * FROM usuario";

		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int count = 0;

		while (result.next()) {
			String user = result.getString("user");
			String pass = result.getString("pass");
			String nombre = result.getString("nombre");
			String email = result.getString("correo");
			count++;
			System.out.println(count + " " + user + " " + pass + " " + nombre + " " + email);
		}
	}

}
