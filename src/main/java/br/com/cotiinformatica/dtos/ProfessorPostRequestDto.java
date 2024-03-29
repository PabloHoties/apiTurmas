package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfessorPostRequestDto {

	@Size(min = 8, max = 100, message = "Por favor, informe um nome de 8 a 100 caracteres.")
	@NotBlank(message = "Por favor, informe o nome do professor.")
	private String nome;

	@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}", message = "Por favor, informe o telefone no formato: '(99) 99999-9999'.")
	private String telefone;
}
