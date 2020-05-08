
import org.jboss.seam.Component;
import org.junit.Assert;
import org.junit.Test;

public class ClasseExemploJUnitTest extends MyJUnitSeamTest {

	/**
	 * Em caso de problemas:
	 * 1. Verifique se o Eclipse > Project > Clean est� limpando o diret�rio target/test-classes e recriando com os resources.
	 * 2. Ap�s o clean deve existir em target/test-classes a pasta WEB-INF/lib com o Beans, Commons e o Hibernate-Core.
	 * 
	 * Dica: Desabilite o CronApp e Fabrica para @Startup para agilizar a execu��o do teste.
	 */
	@Test
	public void test() throws Exception {

		try {
			new ComponentTest() {
				protected void testComponents() throws Exception {
					System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					
					Assert.assertNotNull(new Object());
				}
			}.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
