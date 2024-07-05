package br.com.pedro.api.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "task")
@Getter
@Setter
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "A descrição não pode ser nula")
	private String description;
	
	@NotNull(message = "A data é obrigatória")
	@FutureOrPresent(message = " A data da tarefa não pode estar no passado")
	private LocalDate whenToDo;
	
	private boolean done;
	
}
