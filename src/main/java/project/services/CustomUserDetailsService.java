package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.repo.UserRepository;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserServiceI userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user with username " + username));
    }
}
