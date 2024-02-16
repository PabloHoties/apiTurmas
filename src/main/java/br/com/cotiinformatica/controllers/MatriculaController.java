package br.com.cotiinformatica.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.MatriculaPostRequestDto;
import br.com.cotiinformatica.entities.Aluno;
import br.com.cotiinformatica.entities.Matricula;
import br.com.cotiinformatica.entities.Turma;
import br.com.cotiinformatica.repositories.AlunoRepository;
import br.com.cotiinformatica.repositories.MatriculaRepository;
import br.com.cotiinformatica.repositories.TurmaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/matriculas")
public class MatriculaController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid MatriculaPostRequestDto dto) {

		try {

			Matricula matricula = new Matricula();
			matricula.setTurma(new Turma());
			matricula.setAluno(new Aluno());

			matricula.setId(UUID.randomUUID());
			matricula.getTurma().setId(dto.getIdTurma());
			matricula.getAluno().setId(dto.getIdAluno());
			matricula.setData(dto.getData());

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(dto.getIdTurma());

			if (turma == null)
				return ResponseEntity.status(400).body("Turma não encontrada. Verifique o ID informado.");

			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(dto.getIdAluno());

			if (aluno == null)
				return ResponseEntity.status(400).body("Aluno não encontrado. Verifique o ID informado.");

			MatriculaRepository matriculaRepository = new MatriculaRepository();
			matriculaRepository.insert(matricula);

			return ResponseEntity.status(201).body("Matrícula cadastrada com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {

		try {

			MatriculaRepository matriculaRepository = new MatriculaRepository();
			Matricula matricula = matriculaRepository.findById(id);

			if (matricula == null)
				return ResponseEntity.status(400).body("Matricula não encontrada. Verifique o ID informado.");

			matriculaRepository.delete(matricula);

			return ResponseEntity.status(200).body("Matrícula excluída com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
}
