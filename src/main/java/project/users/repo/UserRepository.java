package project.users.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.users.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findFirstByUsername(String username);

	@Query(value = "SELECT u.* FROM users u JOIN `user_roles` r ON r.user_id = u.user_id WHERE r.role_id=?1", nativeQuery = true)
	List<User> findByRol(int roleId);
}
