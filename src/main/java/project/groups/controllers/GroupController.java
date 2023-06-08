package project.groups.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.groups.models.Group;
import project.groups.models.GroupRoleTypes;
import project.groups.models.Membership;
import project.groups.models.MembershipKey;
import project.groups.models.dto.CreateGroupRequest;
import project.groups.models.dto.GroupDetailResponse;
import project.groups.services.GroupServiceI;
import project.groups.services.MembershipServiceI;
import project.users.models.User;
import project.users.models.dto.UserResponse;
import project.users.services.UserServiceI;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log
public class GroupController {
    @Autowired
    private GroupServiceI groupServiceI;
    @Autowired
    private MembershipServiceI membershipServiceI;
    @Autowired
    private UserServiceI userServiceI;

    @PostMapping("/admin/createGroup")
    @ResponseBody
    public ResponseEntity<Object> createGroup (@RequestBody CreateGroupRequest createGroupRequest, @AuthenticationPrincipal User userLogged){
        if(userLogged.getHaveAGroup()){
            return ResponseEntity.badRequest().body("The current user already has a group created");
        }
        userLogged.setHaveAGroup(true);
        userServiceI.updateUser(userLogged);
        Group response = groupServiceI.createGroup(Group.of(createGroupRequest));
        try {
            membershipServiceI.suscribe(Membership.builder()
                    .id(MembershipKey.builder().build())
                    .group(response)
                    .user(userLogged)
                    .groupRole(GroupRoleTypes.OWNER.getValue())
                    .creationDate(LocalDateTime.now())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(CreateGroupRequest.of(response));
    }

    @PostMapping("/groups/suscribe/{id}")
    @ResponseBody
    public ResponseEntity<Object> suscribe (@PathVariable("id") Long groupId, @AuthenticationPrincipal User userLogged){
        Optional<Group> response = groupServiceI.getById(groupId);

        Membership membership;
        try{
            if(membershipServiceI.existMembershipOf(response.get(), userLogged)){
                return ResponseEntity.badRequest().body("The user is subscribe");
            }

             membership = membershipServiceI.suscribe(Membership.builder()
                    .id(MembershipKey.builder().build())
                    .group(response.get())
                    .user(userLogged)
                    .groupRole(GroupRoleTypes.MEMBER.getValue())
                    .creationDate(LocalDateTime.now())
                    .build());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(membership.getId());
    }

    @GetMapping("/groups")
    @ResponseBody
    public List<CreateGroupRequest> getAll() {
        return groupServiceI.getAll().stream()
                .map(group -> CreateGroupRequest.of(group))
                .toList();
    }

    @GetMapping("/groups/{id}")
    @ResponseBody
    public ResponseEntity<GroupDetailResponse> getOne(@PathVariable("id") Long id) {
        Optional<Group> groupResponse = groupServiceI.getById(id);
        GroupDetailResponse response = null;
            List<Membership> members = membershipServiceI.getMembersOf(groupResponse.get());
            response = GroupDetailResponse.of(groupResponse.get(), members);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/groups/getOwner/{id}")
    @ResponseBody
    public ResponseEntity<Object> getOwnership(@PathVariable("id") Long id) {
        Group group = groupServiceI.getById(id).orElse(null);
        User owner = membershipServiceI.getOwner(group);
        if(group==null || owner == null || owner.getUsername() == null){
            return ResponseEntity.badRequest().body("The id group does not exists");
        }
        return ResponseEntity.ok(UserResponse.convertTo(owner));
    }
    @GetMapping("/groups/isMine/{id}")
    @ResponseBody
    public ResponseEntity<Object> isMine(@PathVariable("id") Long id, @AuthenticationPrincipal User userLogged) {
        Group group = groupServiceI.getById(id).orElse(null);
        if(group==null){
            return ResponseEntity.badRequest().body("The id group does not exists");
        }
        return ResponseEntity.ok(membershipServiceI.getOwner(group).equals(userLogged));
    }
    @GetMapping("/groups/getOwn")
    @ResponseBody
    public ResponseEntity<Object> isMine(@AuthenticationPrincipal User userLogged) {
        Group group = membershipServiceI.getOwn(userLogged);
        if(group == null){
            return ResponseEntity.badRequest().body("The user does not own any group");
        }
        return ResponseEntity.ok(GroupDetailResponse.of(group));
    }


}
