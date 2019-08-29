package br.edu.ifal.leilao.controle;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifal.leilao.construtor.CriadorDeLeilao;
import br.edu.ifal.leilao.construtor.CriadorDeProduto;
import br.edu.ifal.leilao.modelo.Lance;
import br.edu.ifal.leilao.modelo.Leilao;
import br.edu.ifal.leilao.modelo.Usuario;

public class LanceTest {
	
	private Leilao leilao;
	private Avaliador avaliador;
	private Usuario primeiroUsuario;
	private Usuario segundoUsuario;
	private Usuario teceiroUsuario;
	private CriadorDeLeilao criadorDeLeilao;
	private CriadorDeProduto criadorDeProduto;
	
	@Before
	public void inicializacao() {
		avaliador = new Avaliador();
		primeiroUsuario = new Usuario("Bruno");
		segundoUsuario = new Usuario("Clara");
		teceiroUsuario = new Usuario("José");
		criadorDeLeilao = new CriadorDeLeilao();
		criadorDeProduto = new CriadorDeProduto();
	}
	
	@Test
	public void naoDeveAceitarOPrimeioLanceComValorMenorQueOValorInicialDoRpoduto(){
		
		leilao = criadorDeLeilao.para(criadorDeProduto.chamado("Wacom Intuos Creative")
				.detalhe("Mesa digitalizadora da wacom").valendo(400).constroi()).constroi();
		
		Lance lance = new Lance(primeiroUsuario, 300);
		
		boolean validacaoObtida = avaliador.aceitarPrimeiroLance(leilao, lance);
		boolean validacaoEsperada = false;
		
		assertEquals(validacaoEsperada, validacaoObtida);
	}
	
	@Test
	public void deveAceitarOPrimeiroLanceComValorigualAoValorInicialDoProduto() {
		leilao = criadorDeLeilao.para(criadorDeProduto.chamado("Wacom Intuos Creative")
				.detalhe("Mesa digitalizadora da wacom").valendo(400).constroi()).constroi();
		
		Lance lance = new Lance(primeiroUsuario, 400);
		
		boolean validacaoObtida = avaliador.aceitarPrimeiroLance(leilao, lance);
		boolean validacaoEsperada = true;
		
		assertEquals(validacaoEsperada, validacaoObtida);
	}
	
	@Test
	public void deveAceitarOPrimeiroLanceMaiorQueOValorInicialDoProduto() {
		leilao = criadorDeLeilao.para(criadorDeProduto.chamado("Wacom Intuos Creative")
				.detalhe("Mesa digitalizadora da wacom").valendo(400).constroi()).constroi();
		
		Lance lance = new Lance(primeiroUsuario, 500);
		
		boolean validacaoObtida = avaliador.aceitarPrimeiroLance(leilao, lance);
		boolean validacaoEsperada = true;
		
		assertEquals(validacaoEsperada, validacaoObtida);
	}
}
