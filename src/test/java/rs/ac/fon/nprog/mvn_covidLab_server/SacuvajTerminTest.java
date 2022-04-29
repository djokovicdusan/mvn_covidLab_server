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
import domen.TerminTestiranja;
import so.pacijent.SacuvajPacijentaSO;
import so.pacijent.VratiSvePacijenteSO;
import so.termin.SacuvajTerminTestiranjaSO;
import so.termin.VratiSveTermineTestiranjaSO;
import util.SettingsLoader;

class SacuvajTerminTest {
	private SacuvajTerminTestiranjaSO sacuvajTerminTestiranja;
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

	}

	@BeforeEach
	void setUp() throws Exception {
		sacuvajTerminTestiranja = new SacuvajTerminTestiranjaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		sacuvajTerminTestiranja = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> sacuvajTerminTestiranja.validate(new domen.Test()));
	}

	@Test
	void testExecute() {
		terminTestiranja.setDatum(new Date(1999, 11, 1));
		terminTestiranja.setNapomena("test");
		Pacijent p = new Pacijent();
		p.setPacijentId((long)1);
		Laborant l = new Laborant();
		l.setLaborantId((long)1);
		terminTestiranja.setPacijent(p);
		terminTestiranja.setLaborant(l);

		try {
			sacuvajTerminTestiranja.execute(terminTestiranja);
			VratiSveTermineTestiranjaSO vratiSveTermineTestiranjaSO = new VratiSveTermineTestiranjaSO();
			vratiSveTermineTestiranjaSO.execute(terminTestiranja);
			List<OpstiDomenskiObjekat> laborantList = vratiSveTermineTestiranjaSO.getList();
			boolean condition = false;
			for (OpstiDomenskiObjekat opstiDomenskiObjekat : laborantList) {
				TerminTestiranja labDummy = (TerminTestiranja) opstiDomenskiObjekat;
				if (labDummy.getNapomena().equals(terminTestiranja.getNapomena())) {
					assertNotNull(labDummy.getLaborant());
					assertNotNull(labDummy.getPacijent());
					condition = true;
				}

			}
			assertTrue(condition);
//			
//			assertEquals(laborant.getIme(),((Laborant)ucitajLaborantaSO.getResult()).getIme());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
