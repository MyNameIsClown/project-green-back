package project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.models.User;
import project.models.UserRoles;
import project.models.dto.CreateUserRequest;
import project.repo.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceI{
	@Autowired
    private final UserRepository repository;
	@Autowired
	private final PasswordEncoder passwordEncoder;

	public Optional<User> findById(UUID id) {
		return repository.findById(id);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User createUser(CreateUserRequest createUserRequest, Set<UserRoles> roles){
		User user = User.builder()
				.username(createUserRequest.getUsername())
				.password(passwordEncoder.encode(createUserRequest.getPassword()))
				.avatar(createUserRequest.getAvatar())
				.fullName(createUserRequest.getFullName())
				.roles(roles)
				.email(createUserRequest.getEmail())
				.build();
		return repository.save(user);
	}

	public User createUserWithUserRole(CreateUserRequest createUserRequest){
		return createUser(createUserRequest, Set.of(new UserRoles(UserRoles.USER)));
	}
	public User createUserWithAdminRole(CreateUserRequest createUserRequest){
		return createUser(createUserRequest, Set.of(new UserRoles(UserRoles.ADMIN)));
	}

	@Override
	public Optional<User> editInfo(User user) {
		return repository.findById(user.getId()).map(u -> {
			u.setAvatar(user.getAvatar());
			u.setFullName(user.getFullName());
			u.setEmail(user.getEmail());
			return repository.save(u);
		});
	}

	@Override
	public Optional<User> editPassword(UUID userId, String newPassword) {
		return repository.findById(userId).map(u -> {
			u.setPassword(passwordEncoder.encode(newPassword));
			return repository.save(u);
		});
	}

	@Override
	public void deleteById(UUID id) {
		if(repository.existsById(id)){
			repository.deleteById(id);
		}

	}

	@Override
	public void delete(User user) {
		repository.delete(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repository.findFirstByUsername(username);
	}

	@Override
	public List<User> findByRole(String role) {
		return repository.findByRoles(role);
	}

	@Override
	public Boolean matchesPassword(User user, String clearPassword) {
		return passwordEncoder.matches(clearPassword, user.getPassword());
	}


	public Boolean isPresent(UUID id) {
		return repository.existsById(id);
	}
}
