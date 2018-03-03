package br.com.lepsistemas.patrimonium.security;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.lepsistemas.patrimonium.domain.Role;
import br.com.lepsistemas.patrimonium.domain.User;

public class UserSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	
	UserSecurity(User user) {
		this.user = user;
		this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
	}
	
	public static UserSecurityBuilder builder() {
		return new UserSecurityBuilder();
	}
	
	public static class UserSecurityBuilder {
		
		private User user;
		
		UserSecurityBuilder() {}
		
		public UserSecurityBuilder user(User user) {
			this.user = user;
			return this;
		}
		
		public UserSecurity build() {
			return new UserSecurity(user);
		}
		
	}

	public String getId() {
		return user.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		if (user.isSuper() || user.isAdmin()) {
			return true;
		}
		Date today = new Date(System.currentTimeMillis());
		return today.before(user.getExpire());
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public boolean hasRole(Role role) {
		if (user.getRoles() == null) {
			return false;
		}
		return user.getRoles().contains(role);
	}

}
