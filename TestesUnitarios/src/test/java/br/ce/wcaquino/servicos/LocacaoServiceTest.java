package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	// Este objeto, diferente do Assert, varre todos os erros
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	

	@Test
	public void testeLocacao() throws Exception {
		
		// cenario 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",2,5.0);
		
		// acao
		Locacao x = service.alugarFilme(usuario, filme);
		
		// verificacao
		errorCollector.checkThat(x.getValor(), is(equalTo(5.0)));
		errorCollector.checkThat(DataUtils.isMesmaData(x.getDataLocacao(), new Date()), is(true));
		errorCollector.checkThat(DataUtils.isMesmaData(x.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
		
	}
	
	@Test(expected = Exception.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		
		// cenario 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",0,5.0);
		
		// acao
		Locacao x = service.alugarFilme(usuario, filme);
		
	}
	
	@Test
	public void testLocacao_filmeSemEstoque2(){
		
		// cenario 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",0,5.0);
		
		// acao
		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lançado uma exceção");
			
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
		}
		
	}
	
	@Test
	public void testLocacao_filmeSemEstoque3() throws Exception {
		
	
		// cenario 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",0,5.0);
		
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		// acao
		Locacao x = service.alugarFilme(usuario, filme);
		
		
		
		
	}

}
