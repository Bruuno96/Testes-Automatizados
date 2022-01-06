package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.entidades.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.entidades.exceptions.LocadoraException;

public class LocacaoService {
	
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
		
		
		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if (filmes == null) {
			throw new LocadoraException("Filme vazio");
		}
		
		for(Filme x : filmes) {
			if(x.getEstoque() == 0) {
				throw new FilmeSemEstoqueException("Filme sem estoque");
			}
		}
		Locacao locacao = new Locacao();
		locacao.setFilme(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		double valorLocacao = extracted(filmes);
		locacao.setValor(valorLocacao);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	private double extracted(List<Filme> filmes) {
		double valorLocacao = 0;
		for(Filme y : filmes) {
			valorLocacao = valorLocacao + y.getPrecoLocacao();
		}
		return valorLocacao;
	}

}