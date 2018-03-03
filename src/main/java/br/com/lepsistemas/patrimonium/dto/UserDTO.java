package br.com.lepsistemas.patrimonium.dto;

import java.util.Date;
import java.util.Set;

import br.com.lepsistemas.patrimonium.domain.Role;
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

}
