package com.henrique;

import java.sql.SQLException;

public class teste {
 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	SingletonConnection singletonconnection = null;
	
	Usuario usuario = new Usuario();
	usuario.setId(3);
	usuario.setNome("Henrique");
	usuario.setTelefone("24433-223");
	
	singletonconnection = singletonconnection.getInstance();
	//singletonconnection.adicionarUsuario(usuario);
	singletonconnection.listarUsuarios();
	
	singletonconnection.excluiUsuario(usuario);
	singletonconnection.listarUsuarios();
}
}
