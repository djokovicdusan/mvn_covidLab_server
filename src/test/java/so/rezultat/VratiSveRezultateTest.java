package so.rezultat;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.OpstiDomenskiObjekat;
import domen.Rezultat;
import so.rezultat.SacuvajRezultatSO;
import so.rezultat.VratiSveRezultateSO;
import util.SettingsLoader;

class VratiSveRezultateTest {

	private VratiSveRezultateSO vratiSveRezultateSO;
	private static Rezultat rezultat;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");

		rezultat = new Rezultat();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");

	}

	@BeforeEach
	void setUp() throws Exception {
		vratiSveRezultateSO = new VratiSveRezultateSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		vratiSveRezultateSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> vratiSveRezultateSO.validate(new domen.Test()));
	}

	@Test
	void testExecute() {
		try {
			vratiSveRezultateSO.execute(rezultat);
			List<OpstiDomenskiObjekat> filterResult = vratiSveRezultateSO.getList();
			assertNotNull(filterResult);
			assertTrue(filterResult.size() >= 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
