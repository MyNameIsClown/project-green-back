package project.users.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.security.jwt.service.JwtService;
import project.users.models.Roles;
import project.users.models.User;
import project.users.models.dto.*;
import project.users.services.RoleServiceI;
import project.users.services.UserServiceI;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log
public class UserController {
	@Autowired
	private final UserServiceI service;
	@Autowired
	private final RoleServiceI serviceRole;
	@Autowired
	private final AuthenticationManager authenticationManager;
	@Autowired
	private final JwtService jwtService;
	@GetMapping("/users/user/{username}")
	public Optional<UserResponse> getUserByUsername(@PathVariable("username")String username){
		Optional<User> user = service.findByUsername(username);
		if(user.isPresent()){
			return Optional.of(UserResponse.convertTo(user.get()));
		}
		return Optional.empty();
	}
	@GetMapping("/admin/user/type/{rol}")
	public List<UserResponse> getUserByRole(@PathVariable("rol")String rol){
		return service.findByRol(rol).stream().map(user -> UserResponse.convertTo(user)).toList();
	}
	
	@GetMapping("/admin/users")
	@ResponseBody
	public List<UserResponse> getUsers() {
		return service.findAll().stream()
				.map(u->UserResponse.convertTo(u))
				.toList();
	}
	
	@PostMapping("/users/register")
	@ResponseBody
	public ResponseEntity<UserResponse> createUserWithUserRole(@RequestBody CreateUserRequest createUserRequest) {
		User user = service.createUserWithUserRole(createUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.convertTo(user));
	}

	@PostMapping("/users/createRoles")
	@ResponseBody
	public ResponseEntity<Roles> createRoles(@RequestBody List<Roles> roles) {
		serviceRole.createRoles(roles);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	@PostMapping("/admin/register")
	@ResponseBody
	public ResponseEntity<UserResponse> createUserWithAdminRole(@RequestBody CreateUserRequest createUserRequest) {
		User user = service.createUserWithAdminRole(createUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.convertTo(user));
	}

	@PostMapping("/users/login")
	@ResponseBody
	public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest){
		// Realizamos la autenticacion
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()
				)
		);
		// Lo añadimos al contexto de seguridad
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Creamos el token
		String token = jwtService.generateToken(authentication);

		// obtenemos el usuario autenticado
		User user = (User) authentication.getPrincipal();

		return ResponseEntity.status(HttpStatus.CREATED).body(JwtUserResponse.of(user, token));

	}

	@PutMapping("/users/changePassword")
	@ResponseBody
	public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, @AuthenticationPrincipal User userLogged){
		try{
			SecurityContextHolder.getContext().getAuthentication();
			if(service.matchesPassword(userLogged, changePasswordRequest.getOldPassword())
			&& changePasswordRequest.getNewPassword().equals(changePasswordRequest.getVerifyNewPassword())){
				Optional<User> modified = service.editPassword(userLogged.getId(), changePasswordRequest.getNewPassword());
		 		if(modified.isPresent()){
					return ResponseEntity.ok(UserResponse.convertTo(modified.get()));
				}else{
					throw new RuntimeException("Password Data Error");
				}
			}else{
				throw new RuntimeException("The password does not match");
			}
		}catch(RuntimeException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Data error");
		}
	}

	@GetMapping("/users/currentUser")
	@ResponseBody
	public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal User userLogged){
		return ResponseEntity.ok(UserResponse.convertTo(userLogged));
	}
	
}
