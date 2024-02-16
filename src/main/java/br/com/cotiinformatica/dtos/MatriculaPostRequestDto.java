package br.com.cotiinformatica.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MatriculaPostRequestDto {

	@NotNull
	private UUID idTurma;

	@NotNull
	private UUID idAluno;

	@Pattern(regexp = "^(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}$")
	private String data;
}
