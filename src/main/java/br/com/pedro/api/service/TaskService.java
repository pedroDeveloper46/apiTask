package br.com.pedro.api.service;

import org.aspectj.apache.bcel.generic.IINC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedro.api.dto.ConvertDto;
import br.com.pedro.api.dto.TaskDto;
import br.com.pedro.api.model.Task;
import br.com.pedro.api.repository.TaskRepository;
import jakarta.validation.ValidationException;

import java.util.List;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ConvertDto convertDto;
	
	public TaskDto saveTask(TaskDto taskDto) {
		
		Task taskDb = taskRepository.findByDescription(taskDto.getDescription());
		
		if (taskDb != null) {
			throw new ValidationException("Essa tarefa já existe");
		}
		
		Task task = convertDto.toTaskModel(taskDto);
		
		task.setDone(false);
		
		task = taskRepository.save(task);
		
		return convertDto.toTaskDto(task);
		
	}
	
	public TaskDto atualizarTask(TaskDto taskDto) {
		
		if(!validateTask(taskDto)) {
			throw new ValidationException("Essa tarefa já existe!");
		}
		
		Task task = convertDto.toTaskModel(taskDto);
		
		task = taskRepository.save(task);
		
		return convertDto.toTaskDto(task);
				
	}
	
	public List<TaskDto> listTask(){
		List<Task> tasks = (List<Task>) taskRepository.findByDone();
		
		return convertDto.listAll(tasks);
	}
	
	public void deletarTarefa(Long id) {
		
		if (id == null) {
			throw new ValidationException("ID de Tarefa nulo");
		}
		
		Task task = taskRepository.findById(id).orElseThrow();
		
		if(task == null) {
			throw new ValidationException("Essa tarefa não existe!");
		}
		
		taskRepository.delete(task);
		
	
		
	}
	
	public TaskDto fazerTarefa(Long id) {
		
		Task task = taskRepository.findById(id).orElseThrow();
		
		if(task == null) {
			throw new ValidationException("Essa tarefa não existe");
		}
		
		task.setDone(true);
		
		task = taskRepository.save(task);
		
		return convertDto.toTaskDto(task);
		
	}
	
	private boolean validateTask(TaskDto taskDto) {
		
		Task taskDb = taskRepository.findByDescription(taskDto.getDescription());
		
		if(taskDb != null) {
			if(taskDb.getId() != taskDto.getId()) {
				return false;
			}
			
			return true;
		}
		
		return true;
		
	}
	
	
	
	
}
