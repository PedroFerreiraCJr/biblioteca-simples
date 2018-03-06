package br.com.dotofcodex.biblioteca.model;

public class Autor {

	private Long id;
	private String nome;
	private Long livroID;

	public Autor(String nome) {
		this.nome = nome;
	}

	public Autor() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getLivroID() {
		return livroID;
	}

	public void setLivroID(Long livroID) {
		this.livroID = livroID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Autor [id=").append(id).append(", name=").append(nome).append("]");
		return builder.toString();
	}

}
