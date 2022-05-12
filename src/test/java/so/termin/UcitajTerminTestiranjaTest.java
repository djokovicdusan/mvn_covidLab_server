package so.termin;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.TerminTestiranja;
import so.termin.UcitajTerminTestiranjaSO;
import so.test.UcitajTestSO;
import util.SettingsLoader;

class UcitajTerminTestiranjaTest {
	private UcitajTerminTestiranjaSO ucitajTerminTestiranjaSO;
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
		ucitajTerminTestiranjaSO = new UcitajTerminTestiranjaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		ucitajTerminTestiranjaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> ucitajTerminTestiranjaSO.validate(new domen.Test()));
	}
	@Test
	void testExecute() {
		terminTestiranja.setTerminTestiranjaId((long) 1);

		try {
//			SacuvajPacijentaSO sacuvajPacijentaSO = new SacuvajPacijentaSO();
//			sacuvajPacijentaSO.execute(pacijent);
			ucitajTerminTestiranjaSO.execute(terminTestiranja);

			boolean condition = false;

			TerminTestiranja labDummy = (TerminTestiranja) ucitajTerminTestiranjaSO.getResult();
			if (labDummy.getTerminTestiranjaId().equals(terminTestiranja.getTerminTestiranjaId())) {
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
