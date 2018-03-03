package br.com.lepsistemas.patrimonium.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@EqualsAndHashCode(exclude= {"username", "password"})
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

}
