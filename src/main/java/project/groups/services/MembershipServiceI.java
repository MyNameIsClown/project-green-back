package project.groups.services;

import project.groups.models.Group;
import project.groups.models.Membership;
import project.users.models.User;

import java.util.List;

public interface MembershipServiceI {
    Membership suscribe(Membership membership);
    List<Membership> getMembersOf(Group group);
    boolean existMembershipOf(Group group, User user);
    User getOwner(Group group);
    Group getOwn(User user);
}
