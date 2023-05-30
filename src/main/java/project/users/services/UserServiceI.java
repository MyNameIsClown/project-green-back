package project.users.services;

import project.users.models.User;
import project.users.models.Roles;
import project.users.models.dto.CreateUserRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserServiceI {
	public Optional<User> findById(UUID id);
	public List<User> findAll();
	public User createUser(CreateUserRequest user, Set<Roles> roles);
	public User updateUser(User user);

	public Boolean isPresent(UUID id);
	public User createUserWithUserRole(CreateUserRequest createUserRequest);
	public User createUserWithAdminRole(CreateUserRequest createUserRequest);
	public Optional<User> editInfo(User user);
	public Optional<User> editPassword(UUID userId, String newPassword);
	public void deleteById(UUID id);
	public void delete(User user);
	public Optional<User> findByUsername(String username);
	public List<User> findByRol(String role);
	public Boolean matchesPassword(User user, String clearPassword);

}
