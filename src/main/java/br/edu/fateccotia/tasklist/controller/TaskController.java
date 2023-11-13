package br.edu.fateccotia.tasklist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.tasklist.model.Task;
import br.edu.fateccotia.tasklist.service.AuthService;
import br.edu.fateccotia.tasklist.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private AuthService authService;

	@GetMapping
	public ResponseEntity<?> find() {
		return ResponseEntity.ok("Hello World");
	}

	@GetMapping("/all")
	public ResponseEntity<List<Task>> findAll() {
		/*
		 * List<Task> list = new ArrayList<Task>(); list.add(new Task(1, "Atividade 1",
		 * TaskStatus.PENDING)); list.add(new Task(2, "Atividade 2",
		 * TaskStatus.PENDING)); list.add(new Task(3, "Atividade 3",
		 * TaskStatus.PENDING));
		 */
		List<Task> list = taskService.findAll();

		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task, @RequestHeader String token) {

		if (authService.validate(token)) {
			task.setUser(authService.toUser(token));
			Task taskCreated = taskService.save(task);
			return ResponseEntity.ok(taskCreated);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> edit(@PathVariable Integer id, @RequestBody Task task) {
		Optional<Task> taskActual = taskService.edit(id, task);
		if (taskActual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(taskActual.get());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		taskService.delete(id);
		return ResponseEntity.ok().build();

	}

}
