package br.edu.ifal.leilao.controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.ifal.leilao.modelo.Lance;
import br.edu.ifal.leilao.modelo.Leilao;
import br.edu.ifal.leilao.modelo.Usuario;

public class Avaliador {
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> top3MarioresLances;
	
	public void avaliar(Leilao leilao) {
		for(Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos)
				maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos)
				menorDeTodos = lance.getValor();
				
		}
	}
	
	
	
	public void setTop3Lances(Leilao leilao) {
		top3MarioresLances = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(top3MarioresLances, new Comparator<Lance>() {
	
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() < o2.getValor()) return 1;
				if (o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		if(top3MarioresLances.size() > 3)
			top3MarioresLances = top3MarioresLances.subList(0, 3);
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}
	
	public List<Lance> getTop3Lances() {
		return top3MarioresLances;
	}

	public boolean validarLeilao(Leilao leilao) {
		for(int i = 0; i < leilao.getLances().size(); i++) {	
			Usuario usuario = leilao.getLances().get(i).getUsuario();
			int quantidadeLances = 0;
			for(Lance lance : leilao.getLances()) {
				if(lance.getUsuario().equals(usuario))
					quantidadeLances++;
			}
			if(quantidadeLances > 5)
				return false;
		}
		return true;
	}

	public boolean aceitaLance(Leilao leilao, Lance lance) {
		
		if(leilao.getLances().isEmpty())
			return true;
		
		int quantidadeDeLance = leilao.getLances().size();
		Lance ultimoLance = leilao.getLances().get(quantidadeDeLance - 1);
		
		if(ultimoLance.getUsuario().equals(lance.getUsuario()) ||
				ultimoLance.getValor() >= lance.getValor())
			return false;
		return true;
	}

	public boolean aceitarPrimeiroLance(Leilao leilao, Lance lance) {
		if(leilao.getProduto().getValorInicial() <= lance.getValor())
			return true;
		return false;
	}
	
}
