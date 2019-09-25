package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;



public class LeilaoTest {
	
	@Test
	public void deveProporUmLance() {
		Leilao leilao = new Leilao("Cadeira de Salão");
		
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Clea"), 2000.00));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.00, leilao.getLances().get(0).getValor(), 0.00001);
	}
	@Test
	public void deveProporVariosLances() {
		Leilao leilao = new Leilao("Secador de Cabelo");
		
		leilao.propoe(new Lance(new Usuario("Clea"), 250.00));
		leilao.propoe(new Lance(new Usuario("Alessandra"), 300.00));
		leilao.propoe(new Lance(new Usuario("Sione"), 350.00));
		
		
		assertEquals(3, leilao.getLances().size(), 0.00001);
		assertEquals(250.00, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(350.00, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Cadeira de Massagem");
		Usuario usuario = new Usuario("Sildeles");
		
		leilao.propoe(new Lance(usuario, 800.00));
		leilao.propoe(new Lance(usuario, 850.00));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(800.00, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDe5LancesPorUsuario() {
		Leilao leilao = new Leilao("Cadeira de Massagem");
		Usuario sildeles = new Usuario("Sildeles");
		Usuario cleide = new Usuario("Cleide");
		
		leilao.propoe(new Lance(sildeles, 800.00));
		leilao.propoe(new Lance(cleide, 860.00));
		
		leilao.propoe(new Lance(sildeles, 870.00));
		leilao.propoe(new Lance(cleide, 900.00));

		leilao.propoe(new Lance(sildeles, 910.00));
		leilao.propoe(new Lance(cleide, 920.00));
		
		leilao.propoe(new Lance(sildeles, 950.00));
		leilao.propoe(new Lance(cleide, 955.00));
		
		leilao.propoe(new Lance(sildeles, 956.00));
		leilao.propoe(new Lance(cleide, 957.00));
		
		leilao.propoe(new Lance(sildeles, 1000.00));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(957.00, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void dobraUltimoLanceDoUsuarioQueDeuLance(){
		Leilao leilao = new Leilao("Tenis Olimpicus");
		
		Usuario sildeles = new Usuario("Sildeles");
		Usuario pedro = new Usuario("Pedro");
		
		leilao.propoe(new Lance(sildeles, 150.00));
		leilao.propoe(new Lance(pedro, 160.00));
		
		leilao.dobraLanceUsuario(sildeles);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(300.00, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	@Test
	public void dobraUltimoLanceDoUsuarioQueNaoDeuLance(){
		Leilao leilao = new Leilao("Tenis Olimpicus");
		
		Usuario sildeles = new Usuario("Sildeles");
		Usuario pedro = new Usuario("Pedro");
		
		leilao.propoe(new Lance(pedro, 160.00));
		
		leilao.dobraLanceUsuario(sildeles);
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(160.00, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
	
	

}
