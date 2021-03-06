package br.com.lepsistemas.patrimonium.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class WakeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean woke;

}
