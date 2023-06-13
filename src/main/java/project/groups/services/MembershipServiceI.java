package project.groups.services;

import project.groups.models.Group;
import project.groups.models.Membership;
import project.users.models.User;

import java.util.List;
import java.util.Optional;

public interface MembershipServiceI {
    Membership suscribe(Membership membership);
    void unsubscribe(Membership membership);
    void unsubscribe(Group group, User user);
    Optional<Membership> getOne(Group group, User user);
    List<Membership> getMembersOf(Group group);
    boolean existMembershipOf(Group group, User user);
    User getOwner(Group group);
    Group getOwn(User user);
}
