package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Professor;
import br.com.cotiinformatica.entities.Turma;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class TurmaRepository {

	public void insert(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"insert into turma(id, nome, data_inicio, data_termino, id_professor) values(?,?,?,?,?)");

		statement.setObject(1, turma.getId());
		statement.setString(2, turma.getNome());
		statement.setString(3, turma.getDataInicio());
		statement.setString(4, turma.getDataTermino());
		statement.setObject(5, turma.getProfessor().getId());
		statement.execute();

		connection.close();
	}

	public void update(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update turma set nome=?, data_inicio=?, data_termino=?, id_professor=? where id=?");

		statement.setString(1, turma.getNome());
		statement.setString(2, turma.getDataInicio());
		statement.setString(3, turma.getDataTermino());
		statement.setObject(4, turma.getProfessor().getId());
		statement.setObject(5, turma.getId());
		statement.execute();

		connection.close();
	}

	public void delete(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from turma where id=?");

		statement.setObject(1, turma.getId());
		statement.execute();

		connection.close();
	}

	public List<Turma> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("select tu.id, tu.nome, tu.data_inicio, tu.data_termino, pr.id as idprofessor, "
						+ "pr.nome as nomeprofessor, pr.telefone as telefoneprofessor "
						+ "from turma tu inner join professor pr on pr.id = tu.id_professor " + "order by tu.nome");

		ResultSet resultSet = statement.executeQuery();

		List<Turma> lista = new ArrayList<Turma>();

		while (resultSet.next()) {
			Turma turma = new Turma();
			turma.setProfessor(new Professor());

			turma.setId(UUID.fromString(resultSet.getString("id")));
			turma.setNome(resultSet.getString("nome"));
			turma.setDataInicio(resultSet.getString("data_inicio"));
			turma.setDataTermino(resultSet.getString("data_termino"));
			turma.getProfessor().setId(UUID.fromString(resultSet.getString("idprofessor")));
			turma.getProfessor().setNome(resultSet.getString("nomeprofessor"));
			turma.getProfessor().setTelefone(resultSet.getString("telefoneprofessor"));

			lista.add(turma);
		}
		connection.close();
		return lista;
	}

	public Turma findById(UUID id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("select tu.id, tu.nome, tu.data_inicio, tu.data_termino, pr.id as idprofessor, "
						+ "pr.nome as nomeprofessor, pr.telefone as telefoneprofessor "
						+ "from turma tu inner join professor pr on pr.id = tu.id_professor " + "where tu.id=?");

		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();

		Turma turma = null;

		if (resultSet.next()) {
			turma = new Turma();
			turma.setProfessor(new Professor());
			turma.setId(UUID.fromString(resultSet.getString("id")));
			turma.setNome(resultSet.getString("nome"));
			turma.setDataInicio(resultSet.getString("data_inicio"));
			turma.setDataTermino(resultSet.getString("data_termino"));
			turma.getProfessor().setId(UUID.fromString(resultSet.getString("idprofessor")));
			turma.getProfessor().setNome(resultSet.getString("nomeprofessor"));
			turma.getProfessor().setTelefone(resultSet.getString("telefoneprofessor"));
		}
		connection.close();
		return turma;
	}
}
