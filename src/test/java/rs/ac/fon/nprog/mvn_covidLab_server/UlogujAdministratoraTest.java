package rs.ac.fon.nprog.mvn_covidLab_server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Administrator;
import domen.OpstiDomenskiObjekat;
import kontroler.Kontroler;
import so.OpstaSistemskaOperacija;
import so.administrator.UlogujAdministratoraSO;
import util.SettingsLoader;

class UlogujAdministratoraTest {
	private UlogujAdministratoraSO ulogujAdministratoraSo;
	private static Administrator administrator;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab-test");
		SettingsLoader.getInstance().setProperty("username", "root");
		SettingsLoader.getInstance().setProperty("password", "root");
		administrator = new Administrator();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File propertyGetter = new File("settings.properties");
		SettingsLoader.getInstance().setProperty("url", "jdbc:mysql://localhost:3306/lab");

	}

	@BeforeEach
	void setUp() throws Exception {
		ulogujAdministratoraSo = new UlogujAdministratoraSO();
	}

	@AfterEach
	void tearDown() throws Exception {
		ulogujAdministratoraSo = null;
	}

	@Test
	void testValidate() {
		assertThrows(java.lang.Exception.class, () -> ulogujAdministratoraSo.validate(new domen.Pacijent()));
	}
	@Test
	void testGetResult() {
		OpstiDomenskiObjekat generalEntity = null;
		assertEquals(generalEntity, ulogujAdministratoraSo.getResult());
	}
	@Test
	void testGetResultFromController() {
		administrator.setIme("test");
		administrator.setPrezime("test");
		administrator.setKorisnickoIme("admin");
		administrator.setLozinka("admin");
//		ulogujAdministratoraSo.execute(administrator);
		
		try {
			assertEquals(administrator, Kontroler.getInstance().ulogujAdministratora(administrator.getKorisnickoIme(), administrator.getLozinka()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
