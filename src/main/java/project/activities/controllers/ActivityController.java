package project.activities.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.activities.model.Activity;
import project.activities.model.Invitation;
import project.activities.model.InvitationKey;
import project.activities.model.dto.*;
import project.activities.services.ActivityServiceI;
import project.activities.services.InvitationServiceI;
import project.groups.models.Group;
import project.groups.services.GroupServiceI;
import project.groups.services.MembershipServiceI;
import project.users.models.User;
import project.users.services.UserServiceI;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log
public class ActivityController {
    @Autowired
    private GroupServiceI groupServiceI;
    @Autowired
    private ActivityServiceI activityServiceI;
    @Autowired
    private MembershipServiceI membershipServiceI;
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private InvitationServiceI invitationServiceI;

    @PostMapping("/activity/create")
    @ResponseBody
    public ResponseEntity<Object> createActivity (@RequestBody ActivityCreationRequest activityCreationRequest, @AuthenticationPrincipal User userLogged){
        Group groupOwner = groupServiceI.getById(activityCreationRequest.getGroupId()).get();
        if(!membershipServiceI.getOwner(groupOwner).equals(userLogged)) {
            return ResponseEntity.badRequest().body("You can't create activities for groups you don't own");
        }
        Activity response = activityServiceI.createActivity(activityCreationRequest, groupOwner);
        return ResponseEntity.ok(ActivityShortResponse.of(response));
    }

    @GetMapping("/activity/listPublic/{id}")
    @ResponseBody
    public ResponseEntity<Object> getPublicActivities (@PathVariable("id") Long id){
        Group group = groupServiceI.getById(id).get();
        List<ActivityShortResponse> response = activityServiceI.getAllPublic(group).stream().filter(activity -> activity.isCanceled()==false).map(
                (activity -> ActivityShortResponse.of(activity))
        )
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activity/{id}")
    @ResponseBody
    public ResponseEntity<Object> getOne (@PathVariable("id") Long id){
        Activity activity = activityServiceI.getOne(id).get();
        List<Invitation> invitations = invitationServiceI.getAllByActivity(activity);
        ActivityDetailResponse response = ActivityDetailResponse.of(activity);
        response.setInvitations(invitations.stream().map((invitation -> InvitationShortResponse.of(invitation))).collect(Collectors.toList()));
        return ResponseEntity.ok(response);
    }
    @GetMapping("/activity/getInvitations")
    @ResponseBody
    public ResponseEntity<Object> getOne (@AuthenticationPrincipal User userLogged){
        List<Invitation> invitations = invitationServiceI.getAllByUser(userLogged);
        List<ActivityShortResponse> response = invitations.stream().filter(invitation -> invitation.getActivity().isCanceled()==false)
                .map(invitation -> ActivityShortResponse.of(invitation.getActivity())).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/activity/basic/{id}")
    @ResponseBody
    public ResponseEntity<Object> getOneBasic (@PathVariable("id") Long id, @AuthenticationPrincipal User userLogged){
        Activity activity = activityServiceI.getOne(id).get();
        List<Invitation> invitations = invitationServiceI.getAllByActivity(activity);
        ActivityDetailBasicResponse response = ActivityDetailBasicResponse.of(activity);
        response.setHasJoined(invitationServiceI.existInvitationOf(activity, userLogged));
        response.setHasSuscribeToGroup(membershipServiceI.existMembershipOf(activity.getGroup(), userLogged));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/activity/join/{id}")
    @ResponseBody
    public ResponseEntity<InvitationShortResponse> joinActivity (@PathVariable("id") Long activityId, @AuthenticationPrincipal User userLogged){
        Activity activity = activityServiceI.getOne(activityId).get();
        Invitation invitation = Invitation.builder()
                .id(InvitationKey.builder().build())
                .activity(activity)
                .user(userLogged)
                .isUserPresent(false)
                .build();
        invitationServiceI.save(invitation);
        return ResponseEntity.ok(InvitationShortResponse.of(invitation));

    }

    @DeleteMapping("/activity/join/{id}")
    @ResponseBody
    public ResponseEntity<Object> unsubscribe (@PathVariable("id") Long activityId, @AuthenticationPrincipal User userLogged){
        Activity activity = activityServiceI.getOne(activityId).get();
        Invitation invitation = invitationServiceI.getOne(activity, userLogged).get();
        invitationServiceI.deleteJoin(invitation);
        return ResponseEntity.accepted().body("Delete Join");
    }
    @DeleteMapping("/activity/join/{id}/{username}")
    @ResponseBody
    public ResponseEntity<Object> unsubscribe (@PathVariable("id") Long activityId, @PathVariable("username") String username){
        Activity activity = activityServiceI.getOne(activityId).get();
        User user = userServiceI.findByUsername(username).get();
        Invitation invitation = invitationServiceI.getOne(activity, user).get();
        invitationServiceI.deleteJoin(invitation);
        return ResponseEntity.accepted().body("Delete Join");
    }

    @PostMapping("/activity/start/{id}")
    @ResponseBody
    public ResponseEntity<Object> startActivity (@PathVariable("id") Long activityId){
        Activity activity = activityServiceI.getOne(activityId).get();
        activity.setStarted(true);
        activity.setCanceled(false);
        activity.setFinished(false);
        activityServiceI.save(activity);
        return ResponseEntity.ok(ActivityDetailResponse.of(activity));
    }
    @PostMapping("/activity/cancel/{id}")
    @ResponseBody
    public ResponseEntity<Object> cancelActivity (@PathVariable("id") Long activityId){
        Activity activity = activityServiceI.getOne(activityId).get();
        activity.setStarted(false);
        activity.setCanceled(true);
        activity.setFinished(false);
        activityServiceI.save(activity);
        return ResponseEntity.ok(ActivityDetailResponse.of(activity));
    }
    @PostMapping("/activity/finish/{id}")
    @ResponseBody
    public ResponseEntity<Object> finishActivity (@PathVariable("id") Long activityId){
        Activity activity = activityServiceI.getOne(activityId).get();
        activity.setStarted(false);
        activity.setCanceled(false);
        activity.setFinished(true);
        activityServiceI.save(activity);
        return ResponseEntity.ok(ActivityDetailResponse.of(activity));
    }

    @PostMapping("/activity/updatePresence/{id}/{username}")
    @ResponseBody
    public ResponseEntity<Object> finishActivity (@PathVariable("id") Long activityId, @PathVariable("username") String username){
        Activity activity = activityServiceI.getOne(activityId).get();
        User user = userServiceI.findByUsername(username).get();
        Invitation invitation = invitationServiceI.getOne(activity, user).get();
        invitation.setUserPresent(!invitation.isUserPresent());
        invitationServiceI.save(invitation);
        return ResponseEntity.ok("the presence has change");
    }
}
