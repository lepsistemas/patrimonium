package br.com.lepsistemas.patrimonium.dto;

import java.util.Date;
import java.util.Set;

import br.com.lepsistemas.patrimonium.domain.Role;
import br.com.lepsistemas.patrimonium.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@EqualsAndHashCode(exclude= {"username", "name", "enabled", "expire", "roles"})
public class UserDTO {
	
	private String id;
	private String username;
	private String name;
	private boolean enabled;
	private Date expire;
	private Set<Role> roles;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.name = user.getName();
		this.enabled = user.isEnabled();
		this.expire = user.getExpire();
		this.roles = user.getRoles();
	}

}
