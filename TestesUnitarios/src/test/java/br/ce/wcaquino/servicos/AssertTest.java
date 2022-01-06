package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	@Test
	public void test() {
		
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		Assert.assertEquals(1, 1);
		
		// Ultimo valor, chamado de DELTA para deixar como margem de erro, neste caso considera-se até a terceira casa depois da virgula
		Assert.assertEquals(0.5123, 0.512, 0.001);
		
		int i = 5;
		Integer i2 = 5;
		
		// Comparação entre o objeto e o tipo primitivo, os dois exigem conversão para que os tipos sejam iguais. 
		// Conversão para Integer
		Assert.assertEquals(Integer.valueOf(i), i2);
		// Conversão para int
		Assert.assertEquals(i, i2.intValue());
		
		
		// Primeiro argumento esta recebendo apenas uma frase pra ser exibida caso este teste de erro
		Assert.assertEquals("Erro de comparação","bola", "bola");
		Assert.assertNotEquals("bola", "casa");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = null;
		
		// Veririca se os objetos são iguais
		Assert.assertEquals(u1,u2);
		
		// Garantir que os objetos sejam da mesma instancias.
		Assert.assertSame(u2, u2);
		Assert.assertNotSame(u1, u2);

		// Verificar se é nulo
		Assert.assertNull(u3);
		Assert.assertNotNull(u2);
		

		

		
		
	}
}
