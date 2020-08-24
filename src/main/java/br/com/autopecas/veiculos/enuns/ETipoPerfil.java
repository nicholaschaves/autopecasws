package br.com.autopecas.veiculos.enuns;

public enum ETipoPerfil {

	ADMIN(0, "ADMIN"),
	GESTOR(1, "GESTOR"),
	COLABORADOR(2, "COLABORADOR");
	
	private Integer id;
	private String name;
	
	private ETipoPerfil(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
