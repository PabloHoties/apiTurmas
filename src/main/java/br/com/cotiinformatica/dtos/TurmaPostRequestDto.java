package br.com.cotiinformatica.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TurmaPostRequestDto {

	@Size(min = 3, max = 25, message = "Por favor, informe um nome de 3 a 25 caracteres.")
	@NotBlank
	private String nome;
	
	@Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}$")
	private String dataInicio;
	
	@Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}$")
	private String dataTermino;
	
	@NotNull
	private UUID idProfessor;
}
