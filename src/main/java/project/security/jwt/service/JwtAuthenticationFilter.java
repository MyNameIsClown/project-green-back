package project.security.jwt.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import project.users.models.User;
import project.users.services.UserServiceI;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final UserServiceI userService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        try{
            if(StringUtils.hasText(token) && jwtService.validateToken(token)){
                UUID userId = jwtService.getUserIdFromJwtToken(token);
                Optional<User> result = userService.findById(userId);
                if(result.isPresent()){
                    User user = result.get();

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

            }
        }catch (Exception e){
            log.info("Authentication failed using token JWT: " + token);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtService.TOKEN_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtService.TOKEN_PREFIX)) {
            return bearerToken.substring(JwtService.TOKEN_PREFIX.length());
        }
        return null;
    }

}
