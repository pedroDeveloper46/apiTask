package br.com.pedro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pedro.api.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	
	public Task findByDescription(String description);
	
	@Query("SELECT t from Task t WHERE done = false")
	public List<Task> findByDone();

}
