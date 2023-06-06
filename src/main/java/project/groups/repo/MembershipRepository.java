package project.groups.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.groups.models.Membership;
import project.groups.models.MembershipKey;


@Repository
public interface MembershipRepository extends JpaRepository<Membership, MembershipKey> {
}
