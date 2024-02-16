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

import br.com.cotiinformatica.dtos.TurmaPostRequestDto;
import br.com.cotiinformatica.dtos.TurmaPutRequestDto;
import br.com.cotiinformatica.entities.Professor;
import br.com.cotiinformatica.entities.Turma;
import br.com.cotiinformatica.repositories.ProfessorRepository;
import br.com.cotiinformatica.repositories.TurmaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/turmas")
public class TurmaController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid TurmaPostRequestDto dto) {

		try {

			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(dto.getIdProfessor());

			if (professor == null)
				return ResponseEntity.status(400).body("Professor não encontrado. Verifique o ID informado.");

			Turma turma = new Turma();
			turma.setProfessor(new Professor());

			turma.setId(UUID.randomUUID());
			turma.setNome(dto.getNome());
			turma.setDataInicio(dto.getDataInicio());
			turma.setDataTermino(dto.getDataTermino());
			turma.getProfessor().setId(dto.getIdProfessor());

			TurmaRepository turmaRepository = new TurmaRepository();
			turmaRepository.insert(turma);

			return ResponseEntity.status(201).body("Turma cadastrada com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid TurmaPutRequestDto dto) {

		try {

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(dto.getId());

			if (turma == null)
				return ResponseEntity.status(400).body("Turma não encontrada. Verifique o ID informado.");

			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(dto.getIdProfessor());

			if (professor == null)
				return ResponseEntity.status(400).body("Professor não encontrado. Verifique o ID informado.");

			turma.setProfessor(new Professor());

			turma.setId(dto.getId());
			turma.setNome(dto.getNome());
			turma.setDataInicio(dto.getDataInicio());
			turma.setDataTermino(dto.getDataTermino());
			turma.getProfessor().setId(dto.getIdProfessor());

			turmaRepository.update(turma);

			return ResponseEntity.status(200).body("Turma atualizada com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {

		try {

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(id);

			if (turma == null)
				return ResponseEntity.status(400).body("Turma não encontrada. Verifique o ID informado.");

			turmaRepository.delete(turma);

			return ResponseEntity.status(200).body("Turma excluída com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Turma>> getAll() throws Exception {

		try {

			TurmaRepository turmaRepository = new TurmaRepository();
			List<Turma> turmas = turmaRepository.findAll();

			if (turmas.size() == 0)
				return ResponseEntity.status(204).body(null);

			return ResponseEntity.status(200).body(turmas);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Turma> getById(@PathVariable("id") UUID id) throws Exception {
		
		try {
			
			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(id);
			
			if (turma == null)
				return ResponseEntity.status(204).body(null);
			
			return ResponseEntity.status(200).body(turma);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
