package br.com.dotofcodex.biblioteca.model;

public class Revisor {

	private Long id;
	private String nome;
	private Long livroID;

	public Revisor(String nome) {
		this.nome = nome;
	}

	public Revisor() {
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
		builder.append("Revisor [id=").append(id).append(", nome=").append(nome).append(", livroID=").append(livroID)
				.append("]");
		return builder.toString();
	}
}
