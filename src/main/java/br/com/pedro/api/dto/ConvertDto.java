package br.com.pedro.api.dto;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pedro.api.model.Task;


public class ConvertDto {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TaskDto toTaskDto(Task task) {
		return modelMapper.map(task, TaskDto.class);
	}
	
	public Task toTaskModel(TaskDto taskDto) {
		return modelMapper.map(taskDto, Task.class);
	}
	
	public List<TaskDto> listAll(List<Task> tasks){
		return tasks.stream()
				       .map(cliente -> modelMapper.map(cliente, TaskDto.class))
				       .toList();
	}
	
	
	
	

}
