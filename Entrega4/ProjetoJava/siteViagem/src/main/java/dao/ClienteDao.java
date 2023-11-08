package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Conection;
import model.Cliente;

public class ClienteDao implements CRUD{
	
	private static Connection connection = Conection.creConnection();
	private static String sql;
	
	
	public static void create(Cliente cliente) {
		sql = "INSERT INTO cliente (nomeCliente, cpfCliente, emailCliente, usuarioCliente, senhaCliente) VALUES (?, ?, ?, ?, ?)";	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNomeCliente());
			stmt.setString(2, cliente.getCpfCliente());
			stmt.setString(3, cliente.getEmailCliente());
			stmt.setString(4, cliente.getUsuarioCliente());
			stmt.setString(5, cliente.getUsuarioCliente());
			
			stmt.executeUpdate();
			System.out.println("Dados inseridos com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao inseriri dados");			
		}
		
		
	};
	public static void delete(int clienteId) {
		
	sql = "DELETE FROM cliente WHERE idCliente = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, clienteId);
			stmt.executeUpdate();
			
			System.out.println("Cliente deletado");
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar cliente. " + e.getMessage());
		}		
	};
	
	public static List<Cliente> find(String pesquisa) { 
		
		sql = String.format("SELECT * FROM cliente WHERE nomeCliente like '%s%%' OR cpfCliente LIKE '%s%%' ", pesquisa, pesquisa);
		List<Cliente> clients = new ArrayList<Cliente>();

		try {Statement statement = connection.createStatement();
		 ResultSet r = statement.executeQuery(sql);

			while (r.next()) {
				Cliente cliente = new Cliente();
				
				cliente.setIdCliente(r.getInt("idCliente"));
				cliente.setNomeCliente(r.getString("nomeCliente"));
				cliente.setCpfCliente(r.getString("cpfCliente"));
				cliente.setEmailCliente(r.getString("emailCliente"));
				cliente.setUsuarioCliente(r.getString("usuarioCliente"));
				cliente.setSenhaCliente(r.getString("senhaCliente"));

				clients.add(cliente);
			}
			System.out.println("Lista encontrada");
			return clients;
		} catch (SQLException e) {
		System.out.println("Erro ao encontra lista " + e.getMessage());
		return null;}
		}
		
	public static Cliente findBypk(int clienteID) {
		
		sql = String.format("SELECT * FROM cliente WHERE idCliente = '%d' ", clienteID);
		
		try {Statement statement = connection.createStatement();
		ResultSet r = statement.executeQuery(sql);
		Cliente cliente = new Cliente();

			while (r.next()) {							
				cliente.setIdCliente(r.getInt("idCliente"));
				cliente.setNomeCliente(r.getString("nomeCliente"));
				cliente.setCpfCliente(r.getString("cpfCliente"));
				cliente.setEmailCliente(r.getString("emailCliente"));
				cliente.setUsuarioCliente(r.getString("usuarioCliente"));
				cliente.setSenhaCliente(r.getString("senhaCliente"));
				
			}
			System.out.println("Cliente atualizado");
			return cliente;
			
		} catch (SQLException e) {
		System.out.println("Erro ao atualizar cliente" + e.getMessage());		
		return null;
		}		
		
	}
	public static void update(Cliente cliente) {
		
		sql = "UPDATE cliente SET nomeCliente=?, cpfCliente=?, emailCliente=?, usuarioCliente=?, senhaCliente=? WHERE idCliente=?";
		 
		 try {
			 PreparedStatement stmt = connection.prepareStatement(sql);
			 
			 	stmt.setString(1, cliente.getNomeCliente());
				stmt.setString(2, cliente.getCpfCliente());
				stmt.setString(3, cliente.getEmailCliente());
				stmt.setString(4, cliente.getUsuarioCliente());
				stmt.setString(5, cliente.getSenhaCliente());
				stmt.setInt(6, cliente.getIdCliente());
			 
				stmt.executeUpdate();
			 
			 System.out.println("--Atualizado na base e dadose");
			 
		 } catch(SQLException e) {
			 System.out.println("--Erro ao atualizar no banco de dados " + e.getMessage());
		 }
	}				
		
	}


