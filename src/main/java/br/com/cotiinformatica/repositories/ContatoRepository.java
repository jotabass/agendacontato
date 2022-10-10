package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.dtos.QuantidadeContatoDto;
import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ContatoRepository {

	// método para cadastrar contato
	public void create(Contato contato) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.openConnection();

		// executando o comando SQL no banco de dados
		PreparedStatement preparedStatement = connection
				.prepareStatement("insert into contato(nome, telefone, email, datanasc, idusuario) values(?,?,?,?,?)");

		preparedStatement.setString(1, contato.getNome());
		preparedStatement.setString(2, contato.getTelefone());
		preparedStatement.setString(3, contato.getEmail());
		preparedStatement.setDate(4,
				java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(contato.getDataNasc())));
		preparedStatement.setInt(5, contato.getUsuario().getIdUsuario());
		preparedStatement.execute();

		// fechando a conexão
		connection.close();
	}

	// método para atualizar um contato
	public void update(Contato contato) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.openConnection();

		// executando o comando SQL no banco de dados
		PreparedStatement preparedStatement = connection
				.prepareStatement("update contato set nome=?, telefone=?, email=?, datanasc=? where idcontato=?");

		preparedStatement.setString(1, contato.getNome());
		preparedStatement.setString(2, contato.getTelefone());
		preparedStatement.setString(3, contato.getEmail());
		preparedStatement.setDate(4,
				java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(contato.getDataNasc())));
		preparedStatement.setInt(5, contato.getIdContato());
		preparedStatement.execute();

		connection.close();
	}

	// método para excluir um contato
	public void delete(Contato contato) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.openConnection();

		// executando o comando SQL no banco de dados
		PreparedStatement preparedStatement = connection.prepareStatement("delete from contato where idcontato=?");

		preparedStatement.setInt(1, contato.getIdContato());
		preparedStatement.execute();

		connection.close();
	}

	// método para consultar contatos de um usuário
	public List<Contato> findByUsuario(Integer idUsuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.openConnection();

		// executando o comando SQL no banco de dados
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from contato where idusuario = ? order by nome");

		preparedStatement.setInt(1, idUsuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<Contato> lista = new ArrayList<Contato>();

		// enquanto houver registros
		while (resultSet.next()) {

			Contato contato = new Contato();

			contato.setIdContato(resultSet.getInt("idcontato"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEmail(resultSet.getString("email"));
			contato.setTelefone(resultSet.getString("telefone"));
			contato.setDataNasc(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("datanasc")));

			lista.add(contato); // adicionar na lista
		}

		connection.close();
		return lista;
	}

	// método para cosultar 1 contato no banco de dados
	public Contato findById(Integer idContato, Integer idUsuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.openConnection();

		// executando comando SQL no banco de dados
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from contato where idcontato = ? and idusuario = ?");

		preparedStatement.setInt(1, idContato);
		preparedStatement.setInt(2, idUsuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		Contato contato = null; // vazio

		// se algum registro foi encontrado
		if (resultSet.next()) {

			contato = new Contato(); // inicializando

			contato.setIdContato(resultSet.getInt("idcontato"));
			contato.setNome(resultSet.getString("nome"));
			contato.setEmail(resultSet.getString("email"));
			contato.setTelefone(resultSet.getString("telefone"));
			contato.setDataNasc(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("datanasc")));
		}

		connection.close();
		return contato;
	}

	// método para consultar a quantidade de contatos cadastrados por data de um
	// usuário
	public List<QuantidadeContatoDto> findQuantidade(Integer idUsuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.openConnection();

		// escrevendo a consulta no banco de dados
		PreparedStatement preparedStatement = connection.prepareStatement(
				"select datacadastro, count(idcontato) as quantidade from contato where idusuario = ? group by datacadastro");

		preparedStatement.setInt(1, idUsuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<QuantidadeContatoDto> lista = new ArrayList<QuantidadeContatoDto>();

		while (resultSet.next()) {

			QuantidadeContatoDto dto = new QuantidadeContatoDto();
			dto.setDataCadastro(resultSet.getString("datacadastro"));
			dto.setQuantidade(resultSet.getInt("quantidade"));

			lista.add(dto);
		}

		connection.close();
		return lista;
	}
}
