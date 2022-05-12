package so.laborant;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Laborant;
import so.laborant.IzmeniLaborantaSO;
import so.laborant.UcitajLaborantaSO;
import util.SettingsLoader;

class IzmeniLaborantaTest {

	private  static IzmeniLaborantaSO izmeniLaborantaSO;
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
		izmeniLaborantaSO = new IzmeniLaborantaSO();
		laborant.setBrojOrdinacije(1);
		laborant.setIme("Micko");
		laborant.setPrezime("Ljubicic");
		laborant.setLaborantId((long) 1);
		try {
			izmeniLaborantaSO.execute(laborant);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		izmeniLaborantaSO = null;
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");

	}

	@BeforeEach
	void setUp() throws Exception {
		izmeniLaborantaSO = new IzmeniLaborantaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		izmeniLaborantaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> izmeniLaborantaSO.validate(new domen.Pacijent()));
	}

	@Test
	void testExecute() {
//		laborant.setIme("Zdravko");
//		laborant.setPrezime("Colic");
		laborant.setBrojOrdinacije(15);
		laborant.setLaborantId((long) 1);

		try {
			izmeniLaborantaSO.execute(laborant);
			UcitajLaborantaSO ucitajLaborantaSO = new UcitajLaborantaSO();
			ucitajLaborantaSO.execute(laborant);

			boolean condition = false;

			Laborant labDummy = (Laborant) ucitajLaborantaSO.getResult();
			if (labDummy.getBrojOrdinacije() == (laborant.getBrojOrdinacije())) {
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
