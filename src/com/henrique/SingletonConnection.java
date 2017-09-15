package com.henrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SingletonConnection {
	private Connection con; 
	private static SingletonConnection instance;
	
	
	private SingletonConnection() throws ClassNotFoundException, SQLException{	  
            String url = "jdbc:postgresql://localhost:5432/app1";  
            String usuario = "postgres";  
            String senha = "postgres";  
  
            Class.forName("org.postgresql.Driver");  
  
            con = DriverManager.getConnection(url, usuario, senha);  
  
            System.out.println("Conexão realizada com sucesso.");  
  

	}

	public static SingletonConnection getInstance() throws ClassNotFoundException, SQLException{
		if (instance == null)
			instance = new SingletonConnection();
		
		return instance;
	}
	
	public void closeConnection() throws SQLException {
		con.close();
	}
	
	public void adicionarUsuario(Usuario usuario) throws SQLException {
		String sql = "insert into USUARIO " +
		        "(id,nome,telefone)" +
		        " values ('" + usuario.getId() + "', '" + usuario.getNome() + "', '" + 
		        usuario.getTelefone() + "')";
		
		PreparedStatement stmt = con.prepareStatement(sql);	
		stmt.execute();
		
		System.out.println("usuario adicionado");	
	}
	
	public void listarUsuarios() throws SQLException {
		PreparedStatement stmt = con.prepareStatement("select * from USUARIO");

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
		  String nome = rs.getString("nome");
		  String telefone = rs.getString("telefone");

		  System.out.println(nome + " :: " + telefone);
		}

		stmt.close();
	}
	
	public void excluiUsuario(Usuario usuario) throws SQLException {
		String sql = "delete from USUARIO WHERE id = "+usuario.getId()+";";
		
		PreparedStatement stmt = con.prepareStatement(sql);	
		stmt.execute();
		
		System.out.println("usuario deletado");
	}
}
