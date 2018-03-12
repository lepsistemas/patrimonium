package br.com.lepsistemas.patrimonium.dto;

import java.io.Serializable;

import br.com.lepsistemas.patrimonium.domain.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@EqualsAndHashCode(exclude= {"name", "document"})
public class OwnerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	
	private String document;
	
	public OwnerDTO(Owner owner) {
		this.id = owner.getId();
		this.name = owner.getName();
		this.document = owner.getDocument();
	}

}
