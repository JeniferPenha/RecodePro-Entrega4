package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
	
	//Conexão padrão
	private static final String URL = "jdbc:mysql://localhost:3306/viagemprojeto_mvc";
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	
	public static Connection creConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver encontrado!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado: " + e.getMessage());
		}
		
		try {
			Connection conection = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Conectado com sucesso");
			return conection;
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar ao banco! Mensagem: " + e.getMessage());
			return null;
		}
	}


	}


