package rs.ac.fon.nprog.mvn_covidLab_server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Administrator;
import domen.Laborant;
import domen.OpstiDomenskiObjekat;
import kontroler.Kontroler;
import so.OpstaSistemskaOperacija;
import so.laborant.SacuvajLaborantaSO;
import so.laborant.UcitajLaborantaSO;
import so.laborant.VratiSveLaboranteSO;
import util.SettingsLoader;

class SacuvajLaborantaTest {
	private SacuvajLaborantaSO sacuvajLaborantaSO;
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
//		properties.setProperty("url", "jdbc:mysql://localhost:3306/lab");
//		properties.setProperty("username", "root");
//		properties.setProperty("password", "root");
//		properties.store(out, null);
//		out.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		sacuvajLaborantaSO = new SacuvajLaborantaSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		sacuvajLaborantaSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> sacuvajLaborantaSO.validate(new domen.Pacijent()));
	}

	@Test
	void testExecute() {
		laborant.setIme("Zdravko");
		laborant.setPrezime("Colic");
		laborant.setBrojOrdinacije(13);


		try {
			sacuvajLaborantaSO.execute(laborant);
			VratiSveLaboranteSO vratiSveLaboranteSO = new VratiSveLaboranteSO();
			vratiSveLaboranteSO.execute(laborant);
			List<OpstiDomenskiObjekat> laborantList = vratiSveLaboranteSO.getList();
			boolean condition = false;
			for (OpstiDomenskiObjekat opstiDomenskiObjekat : laborantList) {
				Laborant labDummy = (Laborant) opstiDomenskiObjekat;
				if (labDummy.getIme().equals(laborant.getIme())) {
					System.out.println(labDummy);
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
