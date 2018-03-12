package br.com.lepsistemas.patrimonium.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.lepsistemas.patrimonium.dto.OwnerDTO;
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
@Document
public class Owner implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String name;
	
	private String document;
	
	public Owner(OwnerDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.document = dto.getDocument();
	}

}
