package project.InitializeData;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.users.services.UserServiceImpl;

@Data
@NoArgsConstructor
@Component
public class UserBean implements InitializingBean, DisposableBean {
    @Autowired
    private UserServiceImpl service;

    @Override
    public void afterPropertiesSet() throws Exception {
        /*CreateUserRequest admin = CreateUserRequest.builder()
                .email("admin@user_project_green.com")
                .fullName("Admin")
                .password("admin")
                .verifyPassword("admin")
                .username("admin")
                .build();
        service.createUserWithAdminRole(admin);*/
    }

    @Override
    public void destroy() throws Exception {
        // List<User> users = service.findAll();
    }
}
