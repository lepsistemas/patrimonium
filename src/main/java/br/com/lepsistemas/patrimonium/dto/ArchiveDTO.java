package br.com.lepsistemas.patrimonium.dto;

import br.com.lepsistemas.patrimonium.domain.Archive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@EqualsAndHashCode(exclude= {"name", "contentType", "file"})
public class ArchiveDTO {
	
	private String id;
	private String name;
	private String contentType;
	private byte[] file;
	
	public ArchiveDTO(Archive archive) {
		this.id = archive.getId();
		this.name = archive.getName();
		this.contentType = archive.getContentType();
		this.file = archive.getFile().getData();
	}

}
