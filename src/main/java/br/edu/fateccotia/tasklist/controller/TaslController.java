package br.edu.fateccotia.tasklist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")


public class TaslController {

	@GetMapping
	public ResponseEntity<?> find(){
		
		return ResponseEntity.ok( "Hello Vagabundo");	
		
		
	}
	
	
}
