package project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.User;
import project.models.UserRoles;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findFirstByUsername(String username);
	List<User> findByRolesIn(Set<UserRoles> roles);
}
