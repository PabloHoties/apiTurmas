package br.com.cotiinformatica.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Turma {

	private UUID id;
	private String nome, dataInicio, dataTermino;
	private Professor professor;
}
