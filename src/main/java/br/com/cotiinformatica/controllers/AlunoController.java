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

import br.com.cotiinformatica.dtos.AlunoPostRequestDto;
import br.com.cotiinformatica.dtos.AlunoPutRequestDto;
import br.com.cotiinformatica.entities.Aluno;
import br.com.cotiinformatica.repositories.AlunoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/alunos")
public class AlunoController {

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid AlunoPostRequestDto dto) {
		
		try {
			
			Aluno aluno = new Aluno();
			
			aluno.setId(UUID.randomUUID());
			aluno.setNome(dto.getNome());
			aluno.setMatricula(dto.getMatricula());
			aluno.setCpf(dto.getCpf());
			
			AlunoRepository alunoRepository = new AlunoRepository();
			alunoRepository.insert(aluno);
			
			return ResponseEntity.status(201).body("Aluno cadastrado com sucesso.");
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid AlunoPutRequestDto dto) {
		
		try {
			
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(dto.getId());
			
			if (aluno == null)
				return ResponseEntity.status(400).body("Aluno não encontrado. Verifique o ID informado.");
			
			aluno.setNome(dto.getNome());
			aluno.setMatricula(dto.getMatricula());
			aluno.setCpf(dto.getCpf());
			
			alunoRepository.update(aluno);
			
			return ResponseEntity.status(200).body("Aluno atualizado com sucesso.");
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
		
		try {
			
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(id);
			
			if (aluno == null)
				return ResponseEntity.status(400).body("Aluno não encontrado. Verifique o ID informado.");
			
			alunoRepository.delete(aluno);
			
			return ResponseEntity.status(200).body("Aluno excluído com sucesso.");
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> getAll() throws Exception {
		
		try {
			
			AlunoRepository alunoRepository = new AlunoRepository();
			List<Aluno> alunos = alunoRepository.findAll();
			
			if (alunos.size() == 0)
				return ResponseEntity.status(204).body(null);
			
			return ResponseEntity.status(200).body(alunos);
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Aluno> getById(@PathVariable("id") UUID id) throws Exception {
		
		try {
			
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(id);
			
			if (aluno == null)
				return ResponseEntity.status(204).body(null);
			
			return ResponseEntity.status(200).body(aluno);
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
	}
}
