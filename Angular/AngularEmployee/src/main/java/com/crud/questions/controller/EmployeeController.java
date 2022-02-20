package com.crud.questions.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.questions.models.Employee;
import com.crud.questions.service.EmployeeServiceInterface;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "cont", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	@Autowired
	private EmployeeServiceInterface service;



	@GetMapping
	public ResponseEntity<?> listar(){
		Iterable<Employee> lista = new Iterable<Employee>() {
			@Override
			public Iterator<Employee> iterator() {
				return (Iterator<Employee>) service.findAll();
			}
		};
		lista = new ArrayList<>();
		lista=service.findAll();
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<Employee> a = service.findById(id);
		if(a.isEmpty()) {
			ResponseEntity.notFound().build(); 
		}
		return ResponseEntity.ok(a);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Employee student){
		Employee studentBD = service.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(studentBD);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Employee student , @PathVariable Long id){
		Optional<Employee> o = service.findById(id);
		
		if(o.isPresent() == false) {
			ResponseEntity.notFound().build();
		}
		Employee studentBD = o.get();
		studentBD.setFirstName(student.getFirstName());
		studentBD.setLastName(student.getLastName());
		studentBD.setId(student.getId());
		studentBD.setEmailId(student.getEmailId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentBD));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
