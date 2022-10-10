package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// método para retornar conexões com o banco de dados
	public static Connection openConnection() throws Exception {

		// Nome do DRIVER JDBC para conexão com o banco de dados
		Class.forName("org.postgresql.Driver");

		// abrindo conexão com o banco de dados
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/bd_agendacontatos", "postgres", "coti");
	}

}
