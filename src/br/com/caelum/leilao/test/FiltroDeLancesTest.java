package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.FiltroDeLances;

public class FiltroDeLancesTest {

    @Test
    public void deveSelecionarLancesEntre1000E3000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,2000), 
                new Lance(joao,1000), 
                new Lance(joao,3000), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,600), 
                new Lance(joao,500), 
                new Lance(joao,700), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
    }
    
    @Test
    public void deveSelecionarLancesMaioresQue5000() {
    	Usuario cezar = new Usuario("Cézar");
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(cezar, 6000),
    			new Lance(cezar, 200),
    			new Lance(cezar, 4000)));
    	
    	assertEquals(1, resultado.size());
    	assertEquals(6000, resultado.get(0).getValor(), 0.00001);
    }
    
    @Test
    public void deveEliminarLancesMenoresde500() {
    	Usuario cezar = new Usuario("Cézar");
    	
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(cezar, 300),
    			new Lance(cezar, 200),
    			new Lance(cezar, 100)));
    	
    	assertEquals(0, resultado.size());
    }
    
    @Test
    public void deveEliminarLancesEntre3000e5000() {
    	Usuario cezar = new Usuario("Cézar");
    	
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(cezar, 3500),
    			new Lance(cezar, 4000),
    			new Lance(cezar, 4500)));
    	
    	assertEquals(0, resultado.size());
    }
    
    
    @Test
    public void deveEliminarLancesEntre700e1000() {
Usuario cezar = new Usuario("Cézar");
    	
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(cezar, 850),
    			new Lance(cezar, 730),
    			new Lance(cezar, 900)));
    	
    	assertEquals(0, resultado.size());
    }
    
    
    
}