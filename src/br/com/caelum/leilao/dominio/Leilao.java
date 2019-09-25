package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		int totalLancesUsuario = getTotalLancesUsuario(lance.getUsuario());
		
		if(lances.isEmpty() || !usuarioProposLanceAnterior(lance.getUsuario()) && totalLancesUsuario<5) {
			lances.add(lance);
		}
	}

	private int getTotalLancesUsuario(Usuario usuario) {
		int totalLancesUsuario = 0;
		for(Lance l : this.lances) {
			if(l.getUsuario().equals(usuario)) totalLancesUsuario++;
		}
		return totalLancesUsuario;
	}

	private boolean usuarioProposLanceAnterior(Usuario usuario) {
		return usuario.equals(this.lances.get(this.lances.size()-1).getUsuario());
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLanceUsuario(Usuario usuario) {
		Lance ultimoLance = ultimoLanceDo(usuario);
		
		if(ultimoLance != null) {
			this.propoe(new Lance(usuario, ultimoLance.getValor()*2));
		}
	}

	private Lance ultimoLanceDo(Usuario usuario) {
		Lance ultimoLance = null;
		for(Lance lance : this.lances) {
			if(lance.getUsuario().equals(usuario)) ultimoLance = lance;
		}
		return ultimoLance;
	}
	
}
