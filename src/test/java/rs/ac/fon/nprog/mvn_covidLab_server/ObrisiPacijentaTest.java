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
import so.laborant.SacuvajLaborantaSO;
import so.laborant.UcitajLaborantaSO;
import so.pacijent.ObrisiPacijentaSO;
import so.pacijent.SacuvajPacijentaSO;
import so.pacijent.UcitajPacijentaSO;
import util.SettingsLoader;

class ObrisiPacijentaTest {

	private ObrisiPacijentaSO obrisiPacijentaSO;
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
		obrisiPacijentaSO = new ObrisiPacijentaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		obrisiPacijentaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> obrisiPacijentaSO.validate(new domen.Test()));
	}

	@Test
	void testExecute() {
		Laborant l = new Laborant();
		l.setLaborantId((long) 1);
		pacijent.setIme("Zdravko");
		pacijent.setPrezime("Sotra");
		pacijent.setDatumRodjenja(new Date(78, 10, 10));

		pacijent.setLaborant(l);
		try {
			SacuvajPacijentaSO sacuvajPacijentaSO = new SacuvajPacijentaSO();
			sacuvajPacijentaSO.execute(pacijent);
			obrisiPacijentaSO.execute(pacijent);
			UcitajPacijentaSO ucitajPacijentaSO = new UcitajPacijentaSO();
			ucitajPacijentaSO.execute(pacijent);
			assertNull(ucitajPacijentaSO.getResult());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
