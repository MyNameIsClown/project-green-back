package project.activities.services;

import project.activities.model.Activity;
import project.activities.model.Invitation;
import project.users.models.User;

import java.util.List;
import java.util.Optional;

public interface InvitationServiceI {
    List<Invitation> getAllByActivity(Activity activity);
    boolean existInvitationOf(Activity activity, User user);
    Invitation save (Invitation invitation);
    Optional<Invitation> getOne(Activity activity, User user);
    void deleteJoin(Invitation invitation);
    List<Invitation> getAllByUser(User user);
    void deleteAll(List<Invitation> invitations);
}
