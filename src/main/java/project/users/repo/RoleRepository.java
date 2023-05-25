package project.users.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.users.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
