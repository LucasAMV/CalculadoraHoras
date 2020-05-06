package br.com.gridsoftware.testes.core;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.seam.mock.AbstractSeamTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;

import br.com.gridsoftware.testes.core.util.JarsUtil;

/**
 * 0. Nestas configura��es o servidor j� deve estar em execu��o!
 * 1. Executar pelo CMD ou "Run as -> JUnit Test".
 * 2. O deploy ser� gerado pelo m�todo createDeployment()
 * 3. O Arquillian injetar� suas libs no deploy e subir� junto com a aplica��o no servidor.
 * 4. O m�todo com @Before begin() � invocado aqui e simula um contexto para o Seam (Copiado de JUnitSeamTest e modificado com a orienta��o do Ivan).
 * 5. O m�todo com @Test ser� invocado. new ComponentTest(){}.run() � invocado e o teste � executado, sem o .run n�o funciona.
 * 6. O teste finaliza e o deploy � automaticamente removido do servidor.
 */
public class JUnitSeamArquillianTest extends AbstractSeamTest {
	
	/** M�todo padr�o do Arquillian, criado a partir da documenta��o. **/
	@Deployment
	public static WebArchive createDeployment() throws Exception {
		
		WebArchive war = ShrinkWrap.create(WebArchive.class, "greenTest.war")
				.addClass(JUnitSeamArquillianTest.class)
				//Arquivos em src/test/resources
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "seam.properties")
				.addAsWebInfResource("components.xml")
				.addAsWebInfResource("test-persistence.xml", "classes/META-INF/persistence.xml")
				.addAsWebInfResource("ejb-jar.xml")
				.addAsWebInfResource("jboss-deployment-structure.xml")
				.addAsWebInfResource("log4j.xml", "classes/log4j.xml")
				.setWebXML("web.xml")
				
				//Tem como usar uma API chamada MavenResolver do Arquillian e ler do pom.xml para n�o ter que fazer na m�o.
				.addAsLibraries(JarsUtil.carregaArquivosLib())
				;
		
		return war;
	}
	
	@Before
	@Override
	public void begin() {
		try {
			super.setupClass();
			super.begin();
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}
	
	@After
	@Override
	public void end() {
		try {
			super.end();
			super.cleanupClass();
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}
	
	/**
	 * Call this method within a test method to end the previous mock session and
	 * start another one.
	 */
	public void reset() {
		end();
		begin();
	}

}
