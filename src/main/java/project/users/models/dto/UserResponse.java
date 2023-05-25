package project.users.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.users.models.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    protected String username, email, fullname;

    public static UserResponse convertTo(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .fullname(user.getFullName())
                .email(user.getEmail())
                .build();
    }
}
