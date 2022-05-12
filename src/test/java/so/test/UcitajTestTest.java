package so.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Pacijent;
import so.pacijent.UcitajPacijentaSO;
import so.test.UcitajTestSO;
import util.SettingsLoader;

class UcitajTestTest {
	private UcitajTestSO ucitajTestSO;
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
		ucitajTestSO = new UcitajTestSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		ucitajTestSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> ucitajTestSO.validate(new domen.TerminTestiranja()));
	}

	@Test
	void testExecute() {
		test.setTestId((long) 1);

		try {
//			SacuvajPacijentaSO sacuvajPacijentaSO = new SacuvajPacijentaSO();
//			sacuvajPacijentaSO.execute(pacijent);
			ucitajTestSO.execute(test);

			boolean condition = false;

			domen.Test labDummy = (domen.Test) ucitajTestSO.getResult();
			if (labDummy.getTestId().equals(test.getTestId())) {
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
