package br.com.cotiinformatica.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoPutRequestDto {

	@NotNull(message = "Por favor, informe o id do aluno.")
	private UUID id;
	
	@Size(min = 8, max = 100, message = "Por favor, informe um nome de 8 a 100 caracteres.")
	@NotBlank(message = "Por favor, informe o nome do aluno.")
	private String nome;
	
	@Pattern(regexp = "^(?=.*[a-zA-Z0-9])[a-zA-Z0-9]{1,20}$", message = "Por favor, informe uma matrícula que contenha apenas números e letras de 5 a 20 caracteres.")
	private String matricula;
	
	@Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Por favor, informe um CPF válido.")
	private String cpf;
}
