package br.com.autopecas.veiculos.enuns;

public enum ECargoTipo {
	
	ADMINISTRADOR(0, "ADMINISTRADOR"),
	OPERADOR(1, "OPERADOR"),
	CONSULTOR(2, "CONSULTOR"),
	MECANICO(3, "MECÂNICO");
	
	private Integer id;
	private String name;
	private String descricao;

	private ECargoTipo(Integer id, String name){
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
