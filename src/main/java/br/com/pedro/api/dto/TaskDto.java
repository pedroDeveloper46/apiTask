package br.com.pedro.api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDto {
	
	private Long id;
	
	@NotNull(message = "A descrição não pode ser nula")
	private String description;
	
	@NotNull(message = "A data é obrigatória")
	@FutureOrPresent(message = " A data da tarefa não pode estar no passado")
	private LocalDate whenToDo;
	
	private boolean done;

}
