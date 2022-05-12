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
import so.laborant.SacuvajLaborantaSO;
import so.laborant.UcitajLaborantaSO;
import so.laborant.VratiSveLaboranteSO;
import util.SettingsLoader;

class UcitajLaborantaTest {

	private UcitajLaborantaSO ucitajLaborantaSO;
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
		ucitajLaborantaSO = new UcitajLaborantaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		ucitajLaborantaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> ucitajLaborantaSO.validate(new domen.Pacijent()));
	}

	@Test
	void testExecute() {
//		laborant.setIme("Zdravko");
//		laborant.setPrezime("Colic");
//		laborant.setBrojOrdinacije(13);
		laborant.setLaborantId((long) 1);

		try {

			ucitajLaborantaSO.execute(laborant);

			boolean condition = false;

			Laborant labDummy = (Laborant) ucitajLaborantaSO.getResult();
			if (labDummy.getLaborantId() == (laborant.getLaborantId())) {
				System.out.println(labDummy);
				condition = true;

			}
			assertTrue(condition);
//			assertEquals(laborant.getIme(),((Laborant)ucitajLaborantaSO.getResult()).getIme());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
