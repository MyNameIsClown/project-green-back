package project.groups.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.groups.models.Group;
@Repository
public interface GroupsRepository extends JpaRepository<Group, Long> {
}
