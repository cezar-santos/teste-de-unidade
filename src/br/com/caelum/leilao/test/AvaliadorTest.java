package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import br.com.caelum.leilao.test.build.CriadorDeLeilao;

/**
 * @author vntcesa
 *
 */
/**
 * @author vntcesa
 *
 */
public class AvaliadorTest {

	private Avaliador leiloeiro;

	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		joao = new Usuario("Joao");
		jose = new Usuario("José");
		maria = new Usuario("Maria");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// cenario: 3 lances em ordem crescente

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(maria, 250.0)
				.lance(joao, 300.0)
				.lance(jose, 400.0)
				.constroi();

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;
		double totalEsperado = 950;

		assertThat(leiloeiro.getMaiorLance(), equalTo(maiorEsperado));
		assertThat(leiloeiro.getMenorLance(), equalTo(menorEsperado));
		assertThat(leiloeiro.getTotalLance(), equalTo(totalEsperado));
	}

	@Test
	public void deveCalcularAMedia() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(maria, 300.0)
				.lance(joao,400.0)
				.lance(jose, 500.0)
				.constroi();

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		assertEquals(400, leiloeiro.getMediaLance(leilao), 0.0001);
	}

	/*@Test
	public void deveCalcularMediadeZeroLance() {
		Leilao leilao = new Leilao("Playstation 4 Novo");

		// executando a acao
		leiloeiro.avalia(leilao);

		double mediaEsperada = 0;

		assertEquals(mediaEsperada, leiloeiro.getMediaLance(leilao), 0.0001);

	}*/

	@Test
	public void deveEntenderApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 200.0)
				.constroi();
		leiloeiro.avalia(leilao);

		assertEquals(200, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLancesEmOrdemRandomica() {
		// cenario: 3 lances em ordem crescente
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 120.0)
				.lance(jose, 400.0)
				.lance(jose, 1000.0)
				.lance(maria, 900.0)
				.lance(jose, 950.0)
				.lance(joao, 320.0)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(950.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(maria, 400.0)
				.lance(joao, 300.0)
				.lance(jose, 200.0)
				.lance(jose, 100.0)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(200.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
			.lance(joao, 100.0)
			.lance(maria, 200.0)
			.lance(joao, 300.0)
			.lance(maria, 400.0)
			.constroi();
		

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		
		assertThat(maiores, hasItems(
					new Lance(maria, 400.0),
					new Lance(joao, 300.0),
					new Lance(maria, 200.0)
				));
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		
		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
		
	}

	/*@Test
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(0, maiores.size());
	}*/

	@After
	public void finaliza() {
		System.out.println("fim");
	}
	
	
	@Test(expected = RuntimeException.class)
	public void naoDeveCalcularLeilaoSemLances() {
			Leilao leilao = new CriadorDeLeilao().para("Mesa de jantar").constroi();
			
			leiloeiro.avalia(leilao);
	}
}
