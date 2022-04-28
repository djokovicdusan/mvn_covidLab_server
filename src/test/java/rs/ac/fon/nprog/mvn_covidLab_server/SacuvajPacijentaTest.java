package rs.ac.fon.nprog.mvn_covidLab_server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Laborant;
import domen.OpstiDomenskiObjekat;
import domen.Pacijent;
import so.laborant.SacuvajLaborantaSO;
import so.laborant.VratiSveLaboranteSO;
import so.pacijent.SacuvajPacijentaSO;
import so.pacijent.VratiSvePacijenteSO;
import util.SettingsLoader;

class SacuvajPacijentaTest {
	private SacuvajPacijentaSO sacuvajPacijentaSO;
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
		sacuvajPacijentaSO = new SacuvajPacijentaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		sacuvajPacijentaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> sacuvajPacijentaSO.validate(new domen.Test()));
	}

	@Test
	void testExecute() {
		pacijent.setIme("Zdravko");
		pacijent.setPrezime("Colic");
		pacijent.setDatumRodjenja(new Date(2000, 10, 10));
		pacijent.setEmail("test");
		Laborant lale = new Laborant();
		lale.setLaborantId((long)1);
		pacijent.setLaborant(lale);
		pacijent.setTelefon("123");

		try {
			sacuvajPacijentaSO.execute(pacijent);
			VratiSvePacijenteSO vratiSvePacijenteSO = new VratiSvePacijenteSO();
			vratiSvePacijenteSO.execute(pacijent);
			List<OpstiDomenskiObjekat> laborantList = vratiSvePacijenteSO.getList();
			boolean condition = false;
			for (OpstiDomenskiObjekat opstiDomenskiObjekat : laborantList) {
				Pacijent labDummy = (Pacijent) opstiDomenskiObjekat;
				if (labDummy.getIme().equals(pacijent.getIme()) && labDummy.getLaborant().getLaborantId() ==
						pacijent.getLaborant().getLaborantId()) {
					assertNotNull(labDummy.getLaborant());
					condition = true;
				}

			}
			assertTrue(condition);
//			assertEquals(laborant.getIme(),((Laborant)ucitajLaborantaSO.getResult()).getIme());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
