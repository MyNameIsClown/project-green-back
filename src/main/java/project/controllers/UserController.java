package project.controllers;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.User;
import project.models.dto.CreateUserRequest;
import project.services.UserServiceI;

@RestController
@RequestMapping("project-green/user")
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private final UserServiceI service;
	
	@GetMapping("{id}")
	@ResponseBody
	public User getUserById(@PathVariable UUID id) {
		System.out.println(service.isPresent(id));
		return service.findById(id).get();
	}
	
	@GetMapping("")
	@ResponseBody
	public List<User> getUsers() {
		return service.findAll();
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<User> createUserWithUserRole(@RequestBody CreateUserRequest createUserRequest) {
		User user = service.createUserWithUserRole(createUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	@PostMapping("/register/admin")
	@ResponseBody
	public ResponseEntity<User> createUserWithAdminRole(@RequestBody CreateUserRequest createUserRequest) {
		User user = service.createUserWithAdminRole(createUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	
}
