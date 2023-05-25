package project.users.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoles {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    private int id;
    @Column(name="role", unique = true, updatable = false, nullable = false)
    private String role;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public UserRoles(String role){
        if(role.equals(this.ADMIN)){
            this.id = 0;
        }else{
            this.id = 1;
        }
        this.role = role;
    }
}
