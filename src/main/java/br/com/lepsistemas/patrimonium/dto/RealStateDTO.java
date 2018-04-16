package br.com.lepsistemas.patrimonium.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@EqualsAndHashCode(exclude= {"name", "registration", "ownerId"})
public class RealStateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String registration;
	private String ownerId;

}
