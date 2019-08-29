package br.edu.ifal.leilao.controle;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifal.leilao.construtor.CriadorDeLeilao;
import br.edu.ifal.leilao.construtor.CriadorDeUsuario;
import br.edu.ifal.leilao.modelo.Lance;
import br.edu.ifal.leilao.modelo.Leilao;
import br.edu.ifal.leilao.modelo.Produto;
import br.edu.ifal.leilao.modelo.Usuario;

public class TDDTest {
	
	private CriadorDeLeilao criadorDeLeilao;
	private CriadorDeUsuario criadorDeUsuario;
	private Usuario primeiroUsuario;
	private Usuario segundoUsuario;
	private Usuario terceiroUsuario;
	private Usuario quartoUsuario;
	private Usuario quintoUsuario;
	private Leilao leilao;
	private Avaliador avaliador;
	
	@Before
	public void inicializador() {
		criadorDeLeilao = new CriadorDeLeilao();
		avaliador = new Avaliador();
		criadorDeUsuario = new CriadorDeUsuario();
		primeiroUsuario = criadorDeUsuario.com("Bruno", "bruno@gmail.com").constroi();
		segundoUsuario = criadorDeUsuario.com("Mario", "maria@gmail.com").constroi();
		terceiroUsuario = criadorDeUsuario.com("Joao", "joao@gmail.com").constroi();
		quartoUsuario = criadorDeUsuario.com("Jose", "jose@gmail.com").constroi();
		quintoUsuario = criadorDeUsuario.com("Breno", "breno@gmail.com").constroi();
	}
	
	@Test
	public void deveFuncionarComUsuariosELancesDiferentes() {
		
		double valorPrimeiroLance = 250;
		double valorSegundoLance = 300;
		double valorTerceiroLance = 400;
		double valorQuartoLance = 500;
		double valorQuintoLance = 550;
		
		leilao = criadorDeLeilao.para(new Produto("Tv"))
				.lance(primeiroUsuario, valorPrimeiroLance)
				.lance(segundoUsuario, valorSegundoLance)
				.lance(terceiroUsuario, valorTerceiroLance)
				.lance(quartoUsuario, valorQuartoLance)
				.lance(quintoUsuario, valorQuintoLance).constroi();
		
		boolean validadeObtida = avaliador.validarLeilao(leilao);
		boolean validadeEsperada = true;
		
		assertEquals(validadeEsperada, validadeObtida);
	}
	
	@Test
	public void naoDeveFuncionarComMaisDeCincoLanceDoMesmoUsuario() {
		
		double valorPrimeiroLance = 250;
		double valorSegundoLance = 300;
		double valorTerceiroLance = 400;
		double valorQuartoLance = 500;
		double valorQuintoLance = 550;
		double valorSextoLance = 600;
		
		leilao = criadorDeLeilao.para(new Produto("Tv"))
				.lance(primeiroUsuario, valorPrimeiroLance)
				.lance(primeiroUsuario, valorSegundoLance)
				.lance(primeiroUsuario, valorTerceiroLance)
				.lance(primeiroUsuario, valorQuartoLance)
				.lance(primeiroUsuario, valorQuintoLance)
				.lance(primeiroUsuario, valorSextoLance).constroi();
		
		boolean validadeObtida = avaliador.validarLeilao(leilao);
		boolean validadeEsperada = false;
		
		assertEquals(validadeEsperada, validadeObtida);
	}
	
	@Test
	public void deveFuncionarCom3LancePorUsuario() {
		
		double valorPrimeiroLance = 250;
		double valorSegundoLance = 300;
		double valorTerceiroLance = 400;
		double valorQuartoLance = 500;
		double valorQuintoLance = 550;
		double valorSextoLance = 600;
		
		leilao = criadorDeLeilao.para(new Produto("Tv"))
				.lance(primeiroUsuario, valorPrimeiroLance)
				.lance(primeiroUsuario, valorSegundoLance)
				.lance(primeiroUsuario, valorTerceiroLance)
				.lance(segundoUsuario, valorQuartoLance)
				.lance(segundoUsuario, valorQuintoLance)
				.lance(segundoUsuario, valorSextoLance).constroi();
		
		boolean validadeObtida = avaliador.validarLeilao(leilao);
		boolean validadeEsperada = true;
		
		assertEquals(validadeEsperada, validadeObtida);
	}
	
	@Test
	public void deveAceitaOPrimeiroLanceDoLeilao() {
		
		leilao = criadorDeLeilao.para(new Produto("Wacom Intuos")).constroi();
		
		Lance lance = new Lance(primeiroUsuario, 450);
		
		boolean aceitacaoObtida = avaliador.aceitaLance(leilao, lance);
		boolean aceitacaoEsperada = true;
		
		assertEquals(aceitacaoEsperada, aceitacaoObtida);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuarios(){
		leilao = criadorDeLeilao.para(new Produto("Café"))
				.lance(primeiroUsuario, 10).constroi();
		
		Lance lance = new Lance(primeiroUsuario, 10.5);
		
		boolean aceitacaoObtida = avaliador.aceitaLance(leilao, lance);
		boolean aceitacaoEsperada = false;
		
		assertEquals(aceitacaoEsperada, aceitacaoObtida);
	}
	
	@Test
	public void deveAceitarDoisLancesSeguidosDeDoisUsuariosDiferentes() {
		leilao = criadorDeLeilao.para(new Produto("Tv"))
				.lance(primeiroUsuario, 600).constroi();
		
		Lance lance = new Lance(segundoUsuario, 610);
		
		boolean aceitacaoObtida = avaliador.aceitaLance(leilao, lance);
		boolean aceitacaoEsperada = true;
		
		assertEquals(aceitacaoEsperada, aceitacaoObtida);
	}
	
	@Test
	public void naoDeveFuncionarComLancesValidosSeguidosDeLancesDoMesmoUsuario() {
		leilao = criadorDeLeilao.para(new Produto("Tv"))
				.lance(primeiroUsuario, 600)
				.lance(segundoUsuario, 610)
				.lance(terceiroUsuario, 620)
				.constroi();
		
		Lance lance = new Lance(terceiroUsuario, 630);
		
		boolean aceitacaoObtida = avaliador.aceitaLance(leilao, lance);
		boolean aceitacaoEsperada = false;
		
		assertEquals(aceitacaoEsperada, aceitacaoObtida);
	}
	
	@Test
	public void naoDeveAceitarUmLanceComValorMenorQueOUltimoLance() {
		leilao = criadorDeLeilao.para(new Produto("Tv"))
				.lance(primeiroUsuario, 600)
				.constroi();
		
		Lance lance = new Lance(terceiroUsuario, 500);
		
		boolean aceitacaoObtida = avaliador.aceitaLance(leilao, lance);
		boolean aceitacaoEsperada = false;
		
		assertEquals(aceitacaoEsperada, aceitacaoObtida);
	}
	
	@Test
	public void naoDeveAceitarUmNovoLanceComUmValorIgualAoUltimoLance() {
		leilao = criadorDeLeilao.para(new Produto("Tv"))
				.lance(primeiroUsuario, 600)
				.constroi();
		
		Lance lance = new Lance(terceiroUsuario, 600);
		
		boolean aceitacaoObtida = avaliador.aceitaLance(leilao, lance);
		boolean aceitacaoEsperada = false;
		
		assertEquals(aceitacaoEsperada, aceitacaoObtida);
	}
}
