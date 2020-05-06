package br.com.gridsoftware.testes.core;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.Component;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.gridsoftware.persistencia.dao.ContratoAverbacaoDAO;
import br.com.gridsoftware.persistencia.entidades.ContratoAverbacao;

/**
 * 0. Nestas configura��es o servidor j� deve estar em execu��o!
 * 1. Executar pelo CMD ou "Run as -> JUnit Test".
 * 2. O deploy ser� gerado pelo m�todo createDeployment()
 * 3. O Arquillian injetar� suas libs no deploy e subir� junto com a aplica��o no servidor.
 * 4. O m�todo com @Before begin() � invocado aqui e simula um contexto para o Seam (Copiado de JUnitSeamTest e modificado com a orienta��o do Ivan).
 * 5. O m�todo com @Test ser� invocado. new ComponentTest(){}.run() � invocado e o teste � executado, sem o .run n�o funciona.
 * 6. O teste finaliza e o deploy � automaticamente removido do servidor.
 */
@RunWith(Arquillian.class)
public class ClasseExemploTeste extends JUnitSeamArquillianTest {
	
	@Test
	public void TesteSimplesParaVerificarFuncionamento() throws Exception {
		new ComponentTest() {
			@Override
			protected void testComponents() throws Exception {
				ContratoAverbacaoDAO contratoAverbacaoDAO = (ContratoAverbacaoDAO) Component.getInstance("contratoAverbacaoDAO");
				
				ContratoAverbacao ca = contratoAverbacaoDAO.loadById(1L);
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				System.out.println(ca.getDataAverbacao());
				System.out.println(ca.getNumeroContrato());
				System.out.println(ca.getColaborador().getPessoa().getNome());
				Assert.assertNotNull(ca.getDataAverbacao());
			}
		}.run();
	}

}