package br.edu.ifal.leilao.modelo;

public class Produto {
	private String nome;
	private String descricao;
	private Double valorInicial;
	
	public Produto(String nome) {
		super();
		this.nome = nome;
	}
	
	public Produto(String nome, Double valorInicial) {
		super();
		this.nome = nome;
		this.valorInicial = valorInicial; 
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}
	
}
