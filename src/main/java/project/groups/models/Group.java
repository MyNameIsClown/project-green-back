package project.groups.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.activities.model.Activity;
import project.groups.models.dto.CreateGroupRequest;

import java.util.Set;

@Entity
@Table(name="GROUP_TABLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String locationName;
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Membership> memberships;
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Activity> activities;

    public static Group of(CreateGroupRequest createGroupRequest){
        return Group.builder()
                .name(createGroupRequest.getName())
                .description(createGroupRequest.getDescription())
                .locationName(createGroupRequest.getLocationName())
                .build();
    }
}
