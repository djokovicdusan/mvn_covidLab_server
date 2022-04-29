package rs.ac.fon.nprog.mvn_covidLab_server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.OpstiDomenskiObjekat;
import so.test.UcitajTestSO;
import so.test.VratiSveTestoveSO;
import util.SettingsLoader;

class VratiSveTestoveTest {

	private VratiSveTestoveSO vratiSveTestoveSO;
	private static domen.Test test;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");

		test = new domen.Test();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");

	}

	@BeforeEach
	void setUp() throws Exception {
		vratiSveTestoveSO = new VratiSveTestoveSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		vratiSveTestoveSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> vratiSveTestoveSO.validate(new domen.TerminTestiranja()));
	}

	@Test
	void testExecute() {
		try {
			vratiSveTestoveSO.execute(test);
			List<OpstiDomenskiObjekat> filterResult = vratiSveTestoveSO.getList();
			assertNotNull(filterResult);
			assertTrue(filterResult.size() >= 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
