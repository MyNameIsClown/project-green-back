package project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.User;
import project.models.dto.CreateUserRequest;
import project.models.dto.UserResponse;
import project.services.UserServiceI;

import java.util.List;
import java.util.UUID;

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
	public ResponseEntity<UserResponse> createUserWithUserRole(@RequestBody CreateUserRequest createUserRequest) {
		User user = service.createUserWithUserRole(createUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.convertTo(user));
	}
	@PostMapping("/register/admin")
	@ResponseBody
	public ResponseEntity<UserResponse> createUserWithAdminRole(@RequestBody CreateUserRequest createUserRequest) {
		User user = service.createUserWithAdminRole(createUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.convertTo(user));
	}
	
	
}
