package project.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findFirstByUsername(String username);
}
