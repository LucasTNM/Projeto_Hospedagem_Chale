package br.com.pc2.rh.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		String driver = "org.postgresql.Driver";
		/* Coloque o usuário criado para acesso ao banco */
		String user = "PC2";
		/* Coloque a senha criada para o usuário */
		String senha = "pc2";
		/* Configure o URL para a conectar com o banco */
		String url = "jdbc:postgresql://127.0.0.1:5432/PC2";/*										 */
		Connection con = null;
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user, senha);
		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
		return con;
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
		}
	}

}

