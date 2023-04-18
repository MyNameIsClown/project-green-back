package project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	@Column(name="id_bin")
	private UUID id;
	@Column(name="username", unique = true, updatable = false, nullable = false)
	private String username;
	@Column(name="email", unique = true, nullable = false)
	private String email;
	@Column(name="password", nullable = false)
	private String password;
	@Column(name="full_name")
	private String fullName;
	@Column(name="eco_reputation")
	private String ecoReputation;
	@Column(name="green_points")
	@Builder.Default
	private Integer greenPoints = 0;
	@Column(name="avatar")
	private String avatar;
	@Builder.Default
	@Column(name="account_non_expired")
	private boolean accountNonExpired = true;
	@Builder.Default
	@Column(name="account_non_locked")
	private boolean accountNonLocked = true;
	@Builder.Default
	@Column(name="credentials_non_expired")
	private boolean credentialsNonExpired = true;
	@Builder.Default
	@Column(name="enabled")
	private boolean enabled = true;
	@Column(name="roles")
	private Set<UserRoles> roles;
	@CreatedDate
	@Column(name="created_at")
	private LocalDateTime createdAt;
	@Builder.Default
	@Column(name="last_password_change_at")
	private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> "ROLE_" + role)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public static Set<UserRoles>getUserRoleFromString(String role){
		if(role.equalsIgnoreCase("admin")){
			return Set.of(UserRoles.ADMIN);
		}else{
			return Set.of(UserRoles.USER);
		}
	}
}
