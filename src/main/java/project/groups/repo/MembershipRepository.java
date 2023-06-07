package project.groups.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.groups.models.Group;
import project.groups.models.Membership;
import project.groups.models.MembershipKey;
import project.users.models.User;

import java.util.List;


@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipKey> {
    List<Membership> findByGroup(Group group);
    boolean existsByGroupAndUser(Group group, User user);
}
