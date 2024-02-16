package br.com.cotiinformatica.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Matricula {

	private UUID id;
	private Turma turma;
	private Aluno aluno;
	private String data;
}
