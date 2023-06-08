package project.activities.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.activities.model.Activity;
import project.activities.model.dto.ActivityCreationRequest;
import project.activities.model.dto.ActivityShortResponse;
import project.activities.services.ActivityServiceI;
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

    @GetMapping("/activity/listPublic")
    @ResponseBody
    public ResponseEntity<Object> getPublicActivities (){
        List<ActivityShortResponse> response = activityServiceI.getAllPublic().stream().map(
                (activity -> ActivityShortResponse.of(activity))
        ).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
