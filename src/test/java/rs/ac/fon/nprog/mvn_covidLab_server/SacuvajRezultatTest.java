package rs.ac.fon.nprog.mvn_covidLab_server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
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
import domen.Rezultat;
import domen.TerminTestiranja;
import so.rezultat.SacuvajRezultatSO;
import so.rezultat.VratiSveRezultateSO;
import so.termin.SacuvajTerminTestiranjaSO;
import so.termin.VratiSveTermineTestiranjaSO;
import util.SettingsLoader;

class SacuvajRezultatTest {

	private SacuvajRezultatSO sacuvajRezultatSO;
	private static Rezultat rezultat;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");

		rezultat = new Rezultat();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");

	}

	@BeforeEach
	void setUp() throws Exception {
		sacuvajRezultatSO = new SacuvajRezultatSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		sacuvajRezultatSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> sacuvajRezultatSO.validate(new domen.Test()));
	}
	@Test
	void testExecute() {
		rezultat.setVrednost(false);
		domen.Test fakeTest = new domen.Test();
		fakeTest.setTestId((long)1);
//		TerminTestiranja fakeTerminTestiranja = new TerminTestiranja();
//		fakeTerminTestiranja.setTerminTestiranjaId((long)1);
		SacuvajTerminTestiranjaSO sacuvajTerminTestiranjaSO = new SacuvajTerminTestiranjaSO();
		TerminTestiranja terminTestiranja = new TerminTestiranja();
		TerminTestiranja terminTestiranjaFromDatabase = new TerminTestiranja();
		terminTestiranja.setDatum(new Date(1999, 11, 1));
		terminTestiranja.setNapomena("test");
		Pacijent p = new Pacijent();
		p.setPacijentId((long)1);
		Laborant l = new Laborant();
		l.setLaborantId((long)1);
		terminTestiranja.setPacijent(p);
		terminTestiranja.setLaborant(l);
		rezultat.setTest(fakeTest);
//		rezultat.setTerminTestiranja();


		try {
			sacuvajTerminTestiranjaSO.execute(terminTestiranja);
			terminTestiranjaFromDatabase.setTerminTestiranjaId(sacuvajTerminTestiranjaSO.getId());
			rezultat.setTerminTestiranja(terminTestiranjaFromDatabase);
			List<Rezultat> fakeListWithOneElement = new ArrayList<>();
			fakeListWithOneElement.add(rezultat);
			sacuvajRezultatSO.execute(fakeListWithOneElement);
			VratiSveRezultateSO vratiSveRezultateSO = new VratiSveRezultateSO();
			vratiSveRezultateSO.execute(rezultat);
			List<OpstiDomenskiObjekat> laborantList = vratiSveRezultateSO.getList();
			
			boolean condition = false;
			for (OpstiDomenskiObjekat opstiDomenskiObjekat : laborantList) {
				Rezultat labDummy = (Rezultat) opstiDomenskiObjekat;
				if (labDummy.isVrednost() == (rezultat.isVrednost() &&
						labDummy.getTerminTestiranja().getTerminTestiranjaId()
						== rezultat.getTerminTestiranja().getTerminTestiranjaId() && 
						labDummy.getTest().getTestId()
						== rezultat.getTest().getTestId())) {
					assertNotNull(labDummy.getTest());
					assertNotNull(labDummy.getTerminTestiranja());
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
