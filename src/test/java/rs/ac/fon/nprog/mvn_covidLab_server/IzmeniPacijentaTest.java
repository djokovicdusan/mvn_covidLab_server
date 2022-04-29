package rs.ac.fon.nprog.mvn_covidLab_server;

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
import so.laborant.IzmeniLaborantaSO;
import so.laborant.UcitajLaborantaSO;
import so.pacijent.IzmeniPacijentaSO;
import so.pacijent.UcitajPacijentaSO;
import util.SettingsLoader;

class IzmeniPacijentaTest {

	private static IzmeniPacijentaSO izmeniPacijentaSO;
	private static Pacijent pacijent;
	private static Laborant laborant;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");

		laborant = new Laborant();
		pacijent = new Pacijent();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		izmeniPacijentaSO = new IzmeniPacijentaSO();
		laborant.setBrojOrdinacije(1);
		laborant.setIme("Micko");
		laborant.setPrezime("Ljubicic");
		laborant.setLaborantId((long) 1);
		pacijent.setDatumRodjenja(new Date(99, 5, 16));
		pacijent.setIme("Stevan");
		pacijent.setPrezime("Markovic");
		pacijent.setEmail("mail@mail.com");
		pacijent.setTelefon("44321");
		pacijent.setLaborant(laborant);
		pacijent.setPacijentId((long)1);
		try {
			izmeniPacijentaSO.execute(pacijent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		izmeniPacijentaSO = null;
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");

	}

	@BeforeEach
	void setUp() throws Exception {
		izmeniPacijentaSO = new IzmeniPacijentaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		izmeniPacijentaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> izmeniPacijentaSO.validate(new domen.Laborant()));
	}

	@Test
	void testExecute() {
//		laborant.setIme("Zdravko");
//		laborant.setPrezime("Colic");
		pacijent.setIme("Stevan");
		pacijent.setPrezime("Markovic");
		pacijent.setEmail("mail@mail.com");
		pacijent.setTelefon("+373737");
		pacijent.setDatumRodjenja(new Date(99, 5, 16));
		pacijent.setPacijentId((long)1);
		laborant.setLaborantId((long) 1);
		pacijent.setLaborant(laborant);
		

		try {
			izmeniPacijentaSO.execute(pacijent);
			UcitajPacijentaSO ucitajPacijentaSO = new UcitajPacijentaSO();
			ucitajPacijentaSO.execute(pacijent);
			boolean condition = false;
			Pacijent labDummy = (Pacijent) ucitajPacijentaSO.getResult();
			if (labDummy.getTelefon().equals(pacijent.getTelefon())) {
				System.out.println(labDummy);
				condition = true;
			}
			assertTrue(condition);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
