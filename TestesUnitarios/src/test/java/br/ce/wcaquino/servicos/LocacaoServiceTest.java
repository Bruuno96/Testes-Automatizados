package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.entidades.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.entidades.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	LocacaoService service = new LocacaoService();

	
	// Este objeto, diferente do Assert, varre todos os erros
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() {

		service = new LocacaoService();
	}

	@Test
	public void deveAlugarFilme() throws Exception {
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		// cenario 
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",2,5.0);
		List<Filme> filmes = new ArrayList<Filme>();	
		filmes.add(filme);
		// acao
		Locacao x = service.alugarFilme(usuario, filmes);
		
		// verificacao
		errorCollector.checkThat(DataUtils.isMesmaData(x.getDataLocacao(), new Date()), is(true));
		errorCollector.checkThat(DataUtils.isMesmaData(x.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
		
	}
	
	@Test(expected = Exception.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		
		// cenario 
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",0,5.0);
		List<Filme> filmes = new ArrayList<Filme>();	
		filmes.add(filme);
		// acao
		service.alugarFilme(usuario, filmes);
		
	}
	
	@Test
	public void testLocacao_filmeSemEstoque2(){
		
		// cenario 
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",0,5.0);
		List<Filme> filmes = new ArrayList<Filme>();	
		filmes.add(filme);
		
		// acao
		try {
			service.alugarFilme(usuario, filmes);
			Assert.fail("Deveria ter lançado uma exceção");
			
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
		}
		
	}
	
	@Test
	public void testLocacao_filmeSemEstoque3() throws Exception {
		
	
		// cenario 
		Usuario usuario = new Usuario();
		Filme filme = new Filme("filme1",0,5.0);
		List<Filme> filmes = new ArrayList<Filme>();	
		filmes.add(filme);
		
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		// acao
		service.alugarFilme(usuario, filmes);	
	}
	
	// Forma robusta, guarda o erro e segue com o fluxo do codigo no método
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException{
		
		// cenario 
		Filme filme = new Filme("filme1",1,5.0);
		List<Filme> filmes = new ArrayList<Filme>();	
		filmes.add(filme);
		// acao
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}			
		
		
	}
	
	// Forma nova nao imprimi/executa o que vem depois do erro
	@Test
	public void testLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException{
		
		// cenario 
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		service.alugarFilme(usuario, null);		
	}
	
	
	@Ignore
	@Test
	public void naoDeveDevolverFilmeNoDomingo() throws FilmeSemEstoqueException, LocadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(
						new Filme("Filme 1", 2, 4.0),
						new Filme("Filme 2", 2, 4.0),
						new Filme("Filme 3", 2, 4.0));
		
		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificacao
		boolean ehSegunda = DataUtils.verificarDiaSemana(resultado.getDataRetorno(),Calendar.MONDAY);
		Assert.assertTrue(ehSegunda);
		
	}
	
	
	
	
	
	
	
	
	
	

}
