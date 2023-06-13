package project.groups.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.groups.models.Group;
import project.groups.models.Membership;
import project.groups.models.MembershipKey;
import project.users.models.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipKey> {
    List<Membership> findByGroup(Group group);
    List<Membership> findByUser(User user);
    Optional<Membership> findByGroupAndUser(Group group, User user);
    boolean existsByGroupAndUser(Group group, User user);
}
