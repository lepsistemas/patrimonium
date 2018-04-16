package br.com.lepsistemas.patrimonium.dto;

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
@EqualsAndHashCode(exclude= {"name"})
public class RealStateOwnerDTO {
	
	private String id;
	private String name;
	
	public RealStateOwnerDTO(Owner owner) {
		this.id = owner.getId();
		this.name = owner.getName();
	}

}
