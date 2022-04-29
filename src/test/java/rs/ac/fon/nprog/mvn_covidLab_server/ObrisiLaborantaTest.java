package rs.ac.fon.nprog.mvn_covidLab_server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Laborant;
import so.laborant.NadjiLaboranteSO;
import so.laborant.ObrisiLaborantaSO;
import so.laborant.SacuvajLaborantaSO;
import so.laborant.UcitajLaborantaSO;
import util.SettingsLoader;

class ObrisiLaborantaTest {

	private ObrisiLaborantaSO obrisiLaborantaSO;
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
		obrisiLaborantaSO = new ObrisiLaborantaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		obrisiLaborantaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> obrisiLaborantaSO.validate(new domen.Pacijent()));
	}

	@Test
	void testExecute() {
		laborant.setIme("Zdravko");
		laborant.setPrezime("Colic");
		laborant.setBrojOrdinacije(13);
		SacuvajLaborantaSO sacuvajLaborantaSO = new SacuvajLaborantaSO();

		try {
			sacuvajLaborantaSO.execute(laborant);
			laborant.setLaborantId(sacuvajLaborantaSO.getId());

			obrisiLaborantaSO.execute(laborant);
			UcitajLaborantaSO ucitajLaborantaSO = new UcitajLaborantaSO();
			ucitajLaborantaSO.execute(laborant);
			assertNull(ucitajLaborantaSO.getResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
