package br.com.lepsistemas.patrimonium.domain;

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
