package br.com.lepsistemas.patrimonium.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lepsistemas.patrimonium.dto.RegisterDTO;
import br.com.lepsistemas.patrimonium.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@EqualsAndHashCode(exclude= {"username", "password", "name", "enabled", "expire", "roles"})
@Document
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Indexed(unique = true)
	private String username;

	@JsonIgnore
	private String password;
	
	private String name;
	
	private boolean enabled;
	
	private Date expire;
	
	private Set<Role> roles;

	public User(RegisterDTO dto) {
		this.username = dto.getUsername();
		this.name = dto.getName();
	}
	
	public User(UserDTO dto) {
		this.id = dto.getId();
		this.username = dto.getUsername();
		this.name = dto.getName();
		this.enabled = dto.isEnabled();
		this.expire = dto.getExpire();
	}
	
	@Transient
	public boolean isSuper() {
		if (roles == null) {
			return false;
		}
		return roles.contains(Role.SUPER);
	}
	
	@Transient
	public boolean isAdmin() {
		if (roles == null) {
			return false;
		}
		return roles.contains(Role.ADMIN);
	}
	
	public enum Role {
		
		SUPER("SUPER"), ADMIN("ADMIN"), USER("USER");
		
		private String name;
		
		Role(String name) {
			this.setName(name);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}

	}

}
