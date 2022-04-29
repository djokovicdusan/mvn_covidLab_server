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
import domen.Pacijent;
import so.pacijent.NadjiPacijenteSO;
import so.pacijent.VratiSvePacijenteSO;
import util.SettingsLoader;

class VratiSvePacijenteTest {

	private VratiSvePacijenteSO vratiSvePacijenteSO;
	private static Pacijent pacijent;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");

		pacijent = new Pacijent();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");
//		properties.setProperty("url", "jdbc:mysql://localhost:3306/lab");
//		properties.setProperty("username", "root");
//		properties.setProperty("password", "root");
//		properties.store(out, null);
//		out.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		vratiSvePacijenteSO = new VratiSvePacijenteSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		vratiSvePacijenteSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> vratiSvePacijenteSO.validate(new domen.Test()));
	}

	@Test
	void testExecute() {
		try {
			vratiSvePacijenteSO.execute(pacijent);
			List<OpstiDomenskiObjekat> filterResult = vratiSvePacijenteSO.getList();
			assertNotNull(filterResult);
			assertTrue(filterResult.size() >= 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
