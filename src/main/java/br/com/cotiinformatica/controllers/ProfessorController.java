package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ProfessorPostRequestDto;
import br.com.cotiinformatica.dtos.ProfessorPutRequestDto;
import br.com.cotiinformatica.entities.Professor;
import br.com.cotiinformatica.repositories.ProfessorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/professores")
public class ProfessorController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid ProfessorPostRequestDto dto) {

		try {

			Professor professor = new Professor();

			professor.setId(UUID.randomUUID());
			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());

			ProfessorRepository professorRepository = new ProfessorRepository();
			professorRepository.insert(professor);

			return ResponseEntity.status(201).body("Professor cadastrado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid ProfessorPutRequestDto dto) {

		try {

			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(dto.getId());

			if (professor == null)
				return ResponseEntity.status(400).body("Professor não encontrado. Verifique o ID informado.");

			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());

			professorRepository.update(professor);

			return ResponseEntity.status(200).body("Professor atualizado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {

		try {

			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(id);

			if (professor == null)
				return ResponseEntity.status(400).body("Professor não encontrado. Verifique o ID informado.");

			professorRepository.delete(professor);

			return ResponseEntity.status(200).body("Professor excluído com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Professor>> getAll() throws Exception {

		try {

			ProfessorRepository professorRepository = new ProfessorRepository();
			List<Professor> professores = professorRepository.findAll();

			if (professores.size() == 0)
				return ResponseEntity.status(204).body(null);

			return ResponseEntity.status(200).body(professores);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Professor> getById(@PathVariable("id") UUID id) throws Exception {

		try {

			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(id);

			if (professor == null)
				return ResponseEntity.status(204).body(null);

			return ResponseEntity.status(200).body(professor);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
