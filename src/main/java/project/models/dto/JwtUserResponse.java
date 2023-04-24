package project.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.models.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserResponse extends UserResponse{
    private String token;
    public JwtUserResponse (UserResponse userResponse){
        username = userResponse.getUsername();
        fullname = userResponse.getFullname();
        email = userResponse.getEmail();
    }
    public static JwtUserResponse of (User user, String token){
        JwtUserResponse result =  new JwtUserResponse(UserResponse.convertTo(user));
        result.setToken(token);
        return result;
    }
}
