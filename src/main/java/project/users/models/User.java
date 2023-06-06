package project.users.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.carbonFootprint.models.CarbonFootprintData;
import project.groups.models.Membership;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	@Column(name="user_id")
	private UUID id;

	@Column(name="username", unique = true, updatable = false, nullable = false)
	private String username;

	@Column(name="email", unique = true, nullable = false)
	private String email;

	@Column(name="password", nullable = false)
	private String password;

	@Column(name="full_name")
	private String fullName;

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

	@Builder.Default
	@Column(name="hasCO2Footprint")
	private boolean carbonFootprintIsCalculated = false;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CarbonFootprintData> carbonFootprintData;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Membership> memberships;

	@CreatedDate
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@Builder.Default
	@Column(name="last_password_change_at")
	private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> "ROLE_" + role.getRoleName())
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
}
