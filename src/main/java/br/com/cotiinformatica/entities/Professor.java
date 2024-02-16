package br.com.cotiinformatica.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Professor {

	private UUID id;
	private String nome, telefone;
}
