package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

// Executando testes na ordem alfabética com essa anotação.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {

	public static int c = 0;
	@Test
	public void inicio() {
		c = 1;
	}
	
	@Test
	public void verifica() {
		Assert.assertEquals(1,c);
	}
}
