package project.activities.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.activities.model.Activity;
import project.activities.model.Invitation;
import project.activities.model.InvitationKey;
import project.users.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, InvitationKey> {
    List<Invitation> findByActivity(Activity activity);
    boolean existsByActivityAndUser(Activity activity, User user);
    Optional<Invitation> findByActivityAndUser(Activity activity, User user);
    List<Invitation> findByUser(User user);
}
