package so.termin;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.OpstiDomenskiObjekat;
import domen.TerminTestiranja;
import so.termin.UcitajTerminTestiranjaSO;
import so.termin.VratiSveTermineTestiranjaSO;
import util.SettingsLoader;

class VratiSveTermineTestiranjaTest {

	private VratiSveTermineTestiranjaSO vratiSveTermineTestiranjaSO;
	private static TerminTestiranja terminTestiranja;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");

		terminTestiranja = new TerminTestiranja();
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
		vratiSveTermineTestiranjaSO = new VratiSveTermineTestiranjaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		vratiSveTermineTestiranjaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> vratiSveTermineTestiranjaSO.validate(new domen.Test()));
	}

	@Test
	void testExecute() {
		try {
			vratiSveTermineTestiranjaSO.execute(terminTestiranja);
			List<OpstiDomenskiObjekat> filterResult = vratiSveTermineTestiranjaSO.getList();
			assertNotNull(filterResult);
			assertTrue(filterResult.size() >= 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
