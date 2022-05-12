package so.pacijent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Laborant;
import domen.Pacijent;
import so.laborant.SacuvajLaborantaSO;
import so.pacijent.SacuvajPacijentaSO;
import so.pacijent.UcitajPacijentaSO;
import util.SettingsLoader;

class UcitajPacijentaTest {
	private UcitajPacijentaSO ucitajPacijentaSO;
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
		ucitajPacijentaSO = new UcitajPacijentaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		ucitajPacijentaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> ucitajPacijentaSO.validate(new domen.Test()));
	}
	@Test
	void testExecute() {
		pacijent.setIme("Stevan");
//		pacijent.setPrezime("Colic");
//		pacijent.setDatumRodjenja(new Date(2000, 10, 10));
//		pacijent.setEmail("test");
		pacijent.setPacijentId((long)1);
//		Laborant lale = new Laborant();
//		lale.setLaborantId((long)1);
//		pacijent.setLaborant(lale);
//		pacijent.setTelefon("123");

		try {
//			SacuvajPacijentaSO sacuvajPacijentaSO = new SacuvajPacijentaSO();
//			sacuvajPacijentaSO.execute(pacijent);
			ucitajPacijentaSO.execute(pacijent);

			boolean condition = false;

			Pacijent labDummy = (Pacijent) ucitajPacijentaSO.getResult();
			if (labDummy.getIme().equals(pacijent.getIme())) {
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
