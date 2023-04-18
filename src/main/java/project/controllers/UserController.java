package project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.User;
import project.models.UserRoles;
import project.models.dto.CreateUserRequest;
import project.models.dto.UserResponse;
import project.repo.UserRepository;
import project.services.UserServiceI;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/project-green")
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private final UserServiceI service;
	@Autowired
	private final UserRepository repository;
	@GetMapping("/user/{username}")
	public Optional<UserResponse> getUserByUsername(@PathVariable("username")String username){
		Optional<User> user = service.findByUsername(username);
		if(user.isPresent()){
			return Optional.of(UserResponse.convertTo(user.get()));
		}
		return Optional.empty();
	}
	@GetMapping("/user/type/{rol}")
	public List<UserResponse> getUserByRole(@PathVariable("rol")String rol){
		Set<UserRoles> roles = Set.of(UserRoles.fromString(rol));
		List<User> users = repository.findByRolesIn(roles);
		return users.stream().map(user -> UserResponse.convertTo(user)).toList();
	}
	
	@GetMapping("/users")
	@ResponseBody
	public List<UserResponse> getUsers() {
		return service.findAll().stream()
				.map(u->UserResponse.convertTo(u))
				.toList();
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
