package so.pacijent;

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
import domen.Pacijent;
import kontroler.Kontroler;
import so.pacijent.NadjiPacijenteSO;
import so.pacijent.UcitajPacijentaSO;
import util.SettingsLoader;

class NadjiPacijenteTest {

	private NadjiPacijenteSO nadjiPacijenteSO;
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
		nadjiPacijenteSO = new NadjiPacijenteSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		nadjiPacijenteSO = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> nadjiPacijenteSO.validate(new domen.Test()));
	}
	@Test
	void testExecute() {
		try {
			List<OpstiDomenskiObjekat> filterResult = Kontroler.getInstance().filtrirajPacijente("Stevan","Markovic");
			ArrayList<Pacijent> filterResultCasted = new ArrayList<>();
			assertEquals(1,filterResult.size());
			for (OpstiDomenskiObjekat opstiDomenskiObjekat : filterResult) {
				Pacijent l = (Pacijent) opstiDomenskiObjekat;
				filterResultCasted.add(l);
			}
			assertEquals("Markovic",filterResultCasted.get(0).getPrezime());
			assertEquals("Stevan",filterResultCasted.get(0).getIme());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
