package so.laborant;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Laborant;
import domen.OpstiDomenskiObjekat;
import so.laborant.NadjiLaboranteSO;
import so.laborant.VratiSveLaboranteSO;
import util.SettingsLoader;

class VratiSveLaboranteTest {

	private VratiSveLaboranteSO vratiSveLaboranteSO;
	private static Laborant laborant;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");

		laborant = new Laborant();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");

	}

	@BeforeEach
	void setUp() throws Exception {
		vratiSveLaboranteSO = new VratiSveLaboranteSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		vratiSveLaboranteSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> vratiSveLaboranteSO.validate(new domen.Pacijent()));
	}

	@Test
	void testExecute() {
		try {
			vratiSveLaboranteSO.execute(laborant);
			List<OpstiDomenskiObjekat> filterResult = vratiSveLaboranteSO.getList();
			assertNotNull(filterResult);
			assertTrue(filterResult.size() >= 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
