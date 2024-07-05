package br.com.pedro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import br.com.pedro.api.dto.TaskDto;
import br.com.pedro.api.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping(path = "task")
public class TaskController {


	@Autowired
	private TaskService taskService;

	@GetMapping("/")
	public List<TaskDto> listAll() {
		return taskService.listTask();
	}
	
	
	@PostMapping(path="/cadastrar")
	public TaskDto cadastrarTask(@RequestBody @Valid TaskDto task, Errors erros) {
		
		if (erros.hasErrors()) {
			throw new ValidationException("Erro de validação:" +erros.getFieldError().getDefaultMessage());
		}
		
		return taskService.saveTask(task);
	}
	
	@PutMapping(path="/atualizar")
	public TaskDto atualizarTask(@RequestBody @Valid TaskDto task, Errors erros) {
		
		if (erros.hasErrors()) {
			throw new ValidationException("Erro de validação:" +erros.getFieldError().getDefaultMessage());
		}
		
		return taskService.atualizarTask(task);
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletarTarefa(@PathVariable Long id) {
			taskService.deletarTarefa(id);	
	}
	
	@PutMapping("/fazerTarefa/{id}")
	public TaskDto fazerTarefa(@PathVariable Long id) {
		return taskService.fazerTarefa(id);
	}
	
	
}
