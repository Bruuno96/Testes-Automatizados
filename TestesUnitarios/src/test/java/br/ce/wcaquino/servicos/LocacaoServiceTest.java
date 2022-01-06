package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	// Este objeto, diferente do Assert, varre todos os erros num mesmo teste e aponta mais que uma falha se houver
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	

	@Test
	public void testeLocacao() {
		
		// cenario 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",2,5.0);
		
		// acao
		Locacao x;
		try {
			x = service.alugarFilme(usuario, filme);
			// verificacao
			errorCollector.checkThat(x.getValor(), is(equalTo(5.0)));
			errorCollector.checkThat(DataUtils.isMesmaData(x.getDataLocacao(), new Date()), is(true));
			errorCollector.checkThat(DataUtils.isMesmaData(x.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
