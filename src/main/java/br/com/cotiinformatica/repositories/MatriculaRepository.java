package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import br.com.cotiinformatica.entities.Aluno;
import br.com.cotiinformatica.entities.Matricula;
import br.com.cotiinformatica.entities.Turma;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class MatriculaRepository {

	public void insert(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into matricula(id, id_turma, id_aluno, data_matricula) values (?,?,?,?)");

		statement.setObject(1, matricula.getId());
		statement.setObject(2, matricula.getTurma().getId());
		statement.setObject(3, matricula.getAluno().getId());
		statement.setString(4, matricula.getData());
		statement.execute();

		connection.close();
	}

	public void delete(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from matricula where id=?");

		statement.setObject(1, matricula.getId());
		statement.execute();

		connection.close();
	}
	
	public Matricula findById(UUID id) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection
				.prepareStatement("select ma.id, ma.data_matricula, tu.id as idturma, al.id as idaluno "
						+ "from matricula ma inner join turma tu on tu.id = ma.id_turma "
						+ "inner join aluno al on al.id = ma.id_aluno "
						+ "where ma.id=?");
		
		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		Matricula matricula = null;
		
		if (resultSet.next()) {
			matricula = new Matricula();
			matricula.setTurma(new Turma());
			matricula.setAluno(new Aluno());
			matricula.setId(UUID.fromString(resultSet.getString("id")));
			matricula.getTurma().setId(UUID.fromString(resultSet.getString("idturma")));
			matricula.getAluno().setId(UUID.fromString(resultSet.getString("idaluno")));
			matricula.setData(resultSet.getString("data_matricula"));
		}
		connection.close();
		return matricula;
	}
}
