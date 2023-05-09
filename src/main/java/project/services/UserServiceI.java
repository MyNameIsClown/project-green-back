package project.services;

import project.models.User;
import project.models.UserRoles;
import project.models.dto.CreateUserRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserServiceI {
	public Optional<User> findById(UUID id);
	public List<User> findAll();
	public User createUser(CreateUserRequest user, Set<UserRoles> roles);

	public Boolean isPresent(UUID id);
	public User createUserWithUserRole(CreateUserRequest createUserRequest);
	public User createUserWithAdminRole(CreateUserRequest createUserRequest);
	public Optional<User> editInfo(User user);
	public Optional<User> editPassword(UUID userId, String newPassword);
	public void deleteById(UUID id);
	public void delete(User user);
	public Optional<User> findByUsername(String username);
	public List<User> findByRole(String role);
	public Boolean matchesPassword(User user, String clearPassword);

}
