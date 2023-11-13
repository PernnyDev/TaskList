package br.edu.fateccotia.tasklist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.tasklist.model.Task;
import br.edu.fateccotia.tasklist.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task save(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Optional<Task> edit(Integer id, Task task) {
	   Optional<Task> t = taskRepository.findById(id);
	   if(t.isPresent()) {
		   t.get().setDescription(task.getDescription());
		   t.get().setStatus(task.getStatus());
		   Task saved = taskRepository.save(t.get());
		   return Optional.of(saved);	   
		   
	   }
		return Optional.empty();
	}

	public void delete(Integer id) {
		taskRepository.deleteById(id);
		
	}
	

	
	
	
	
	
	
	
}
