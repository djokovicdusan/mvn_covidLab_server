package so.laborant;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Laborant;
import domen.OpstiDomenskiObjekat;
import kontroler.Kontroler;
import so.laborant.NadjiLaboranteSO;
import so.laborant.UcitajLaborantaSO;
import util.SettingsLoader;

class NadjiLaboranteTest {
	private NadjiLaboranteSO nadjiLaboranteSO;
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

	}

	@BeforeEach
	void setUp() throws Exception {
		nadjiLaboranteSO = new NadjiLaboranteSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		nadjiLaboranteSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> nadjiLaboranteSO.validate(new domen.Pacijent()));
	}
	@Test
	void testExecute() {
		try {
			List<OpstiDomenskiObjekat> filterResult = Kontroler.getInstance().filtrirajLaborante("Ljubicic");
			ArrayList<Laborant> filterResultCasted = new ArrayList<>();
			assertEquals(1,filterResult.size());
			for (OpstiDomenskiObjekat opstiDomenskiObjekat : filterResult) {
				Laborant l = (Laborant) opstiDomenskiObjekat;
				filterResultCasted.add(l);
			}
			assertEquals("Ljubicic",filterResultCasted.get(0).getPrezime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
