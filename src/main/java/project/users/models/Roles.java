package project.users.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "users")
public class Roles {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="role_id")
    private Integer roleId;
    @Column(name="role_name", unique = true, updatable = false, nullable = false)
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    Set<User> users;

    public Roles(String role){
        if(role.equals(this.ADMIN)){
            this.roleId = 0;
        }else{
            this.roleId = 1;
        }
        this.roleName = role;
    }
}
