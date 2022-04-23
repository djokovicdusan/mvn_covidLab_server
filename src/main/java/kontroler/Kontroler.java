/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Laborant;
import domen.TerminTestiranja;
import domen.OpstiDomenskiObjekat;
import domen.Test;
import domen.Rezultat;
import domen.Pacijent;
import domen.Administrator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ServerNit;
import so.OpstaSistemskaOperacija;
import so.administrator.UlogujAdministratoraSO;
import so.laborant.IzmeniLaborantaSO;
import so.laborant.NadjiLaboranteSO;
import so.laborant.UcitajLaborantaSO;
import so.laborant.ObrisiLaborantaSO;
import so.laborant.SacuvajLaborantaSO;
import so.laborant.VratiSveLaboranteSO;
import so.pacijent.IzmeniPacijentaSO;
import so.pacijent.NadjiPacijenteSO;
import so.pacijent.UcitajPacijentaSO;
import so.pacijent.ObrisiPacijentaSO;
import so.pacijent.SacuvajPacijentaSO;
import so.pacijent.VratiSvePacijenteSO;
import so.rezultat.SacuvajRezultatSO;
import so.rezultat.VratiSveRezultateSO;
import so.termin.VratiSveTermineTestiranjaSO;
import so.termin.UcitajTerminTestiranjaSO;
import so.termin.SacuvajTerminTestiranjaSO;
import so.test.VratiSveTestoveSO;
import so.test.UcitajTestSO;

import storage.IDBBroker;
import storage.database.DatabaseBroker;

/**
 *
 * @author Dule Djo
 */
public class Kontroler {

    private static Kontroler instance;
    private final IDBBroker databaseBroker;
    private ServerNit server;

    private Kontroler() {
        databaseBroker = new DatabaseBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void startServer() {
        if (server == null || !server.isAlive()) {
            server = new ServerNit();
            server.start();
        }
    }

    public void stopServer() {
        server.stopServer();
    }

    public OpstiDomenskiObjekat ulogujAdministratora(String username, String password) throws Exception {

        Administrator administrator = new Administrator();
        administrator.setKorisnickoIme(username);
        administrator.setLozinka(password);
        OpstaSistemskaOperacija so = new UlogujAdministratoraSO();
        // promeniti !!!
        so.templateExecute(administrator);
        return ((UlogujAdministratoraSO) so).getResult();
    }



    public List<OpstiDomenskiObjekat> vratiSveLaborante() throws Exception {
        OpstaSistemskaOperacija so = new VratiSveLaboranteSO();
        so.templateExecute(new Laborant());
        List<OpstiDomenskiObjekat> laboranti = ((VratiSveLaboranteSO) so).getList();

        return laboranti;
    }

    public OpstiDomenskiObjekat dajTest(Long id) throws Exception {

        Test test = new Test();
        test.setTestId(id);
        OpstaSistemskaOperacija so = new UcitajTestSO();
        so.templateExecute(test);
        return ((UcitajTestSO) so).getResult();
    }

    public OpstiDomenskiObjekat dajTerminTestiranja(Long id) throws Exception {
        System.out.println(id +"  test id");
        TerminTestiranja terminTestiranja = new TerminTestiranja();
        terminTestiranja.setTerminTestiranjaId(id);
        OpstaSistemskaOperacija so = new UcitajTerminTestiranjaSO();
        so.templateExecute(terminTestiranja);
        OpstiDomenskiObjekat odoTermin =((UcitajTerminTestiranjaSO) so).getResult();
        TerminTestiranja t = (TerminTestiranja) odoTermin;
        t.setPacijent((Pacijent) dajPacijenta(t.getPacijent().getPacijentId()));
        return t;
    }

    public Long sacuvajPacijenta(Pacijent pacijent) throws Exception {
        OpstaSistemskaOperacija so = new SacuvajPacijentaSO();
        so.templateExecute(pacijent);
        return ((SacuvajPacijentaSO) so).getId();
    }

    public OpstiDomenskiObjekat dajPacijenta(long id) throws Exception {

        Pacijent pacijent = new Pacijent();
        pacijent.setPacijentId(id);
        OpstaSistemskaOperacija so = new UcitajPacijentaSO();
        so.templateExecute(pacijent);
        return ((UcitajPacijentaSO) so).getResult();
    }

    public void azurirajPacijenta(Pacijent azuriranPacijent) throws Exception {

        OpstaSistemskaOperacija so = new IzmeniPacijentaSO();
        so.templateExecute(azuriranPacijent);

    }

    public void obrisiPacijenta(Pacijent pacijent) throws Exception {
        OpstaSistemskaOperacija so = new ObrisiPacijentaSO();
        so.templateExecute(pacijent);
    }

    public List<OpstiDomenskiObjekat> filtrirajPacijente(String ime, String prezime) throws Exception {

        Pacijent pacijent = new Pacijent();
        pacijent.setIme(ime);
        pacijent.setPrezime(prezime);
        OpstaSistemskaOperacija so = new NadjiPacijenteSO();
        so.templateExecute(pacijent);
        List<OpstiDomenskiObjekat> pacijenti = ((NadjiPacijenteSO) so).getList();
        for (OpstiDomenskiObjekat p : pacijenti) {
            Pacijent pac = (Pacijent) p;
            pac.setLaborant((Laborant) dajLaboranta(pac.getLaborant().getLaborantId()));
        }
        return pacijenti;
    }

    public OpstiDomenskiObjekat dajLaboranta(long id) throws Exception {

        Laborant laborant = new Laborant();
        laborant.setLaborantId(id);
        OpstaSistemskaOperacija so = new UcitajLaborantaSO();
        so.templateExecute(laborant);
        return ((UcitajLaborantaSO) so).getResult();

    }

    public List<OpstiDomenskiObjekat> vratiSvePacijente() throws Exception {
        OpstaSistemskaOperacija so = new VratiSvePacijenteSO();
        so.templateExecute(new Pacijent());
        List<OpstiDomenskiObjekat> pacijenti = ((VratiSvePacijenteSO) so).getList();
        for (OpstiDomenskiObjekat p : pacijenti) {
            Pacijent pacijent = (Pacijent) p;
            pacijent.setLaborant((Laborant) dajLaboranta(pacijent.getLaborant().getLaborantId()));
        }
        return pacijenti;
    }

    public Long sacuvajLaboranta(Laborant laborant) throws Exception {
        OpstaSistemskaOperacija so = new SacuvajLaborantaSO();
        so.templateExecute(laborant);
        return ((SacuvajLaborantaSO) so).getId();
    }

    public void azurirajLaboranta(Laborant laborant) throws Exception {
        OpstaSistemskaOperacija so = new IzmeniLaborantaSO();
        so.templateExecute(laborant);
    }

    public void obrisiLaboranta(Laborant laborant) throws Exception {
        OpstaSistemskaOperacija so = new ObrisiLaborantaSO();
        so.templateExecute(laborant);
    }

    public List<OpstiDomenskiObjekat> filtrirajLaborante(String prezimeLaboranta) throws Exception {
        Laborant laborant = new Laborant();
        laborant.setPrezime(prezimeLaboranta);
        OpstaSistemskaOperacija so = new NadjiLaboranteSO();
        so.templateExecute(laborant);
        List<OpstiDomenskiObjekat> laboranti = ((NadjiLaboranteSO) so).getList();

        return laboranti;
    }

    public List<OpstiDomenskiObjekat> vratiSveTestove() throws Exception {
        OpstaSistemskaOperacija so = new VratiSveTestoveSO();
        so.templateExecute(new Test());
        return ((VratiSveTestoveSO) so).getList();
    }

    public Long sacuvajTerminTestiranja(TerminTestiranja termin) throws Exception {
        OpstaSistemskaOperacija so = new SacuvajTerminTestiranjaSO();
        so.templateExecute(termin);
        return ((SacuvajTerminTestiranjaSO) so).getId();
    }

    public List<OpstiDomenskiObjekat> vratiSveRezultate() throws Exception {
        OpstaSistemskaOperacija so = new VratiSveRezultateSO();
        so.templateExecute(new Rezultat());
        List<OpstiDomenskiObjekat> rezultati = ((VratiSveRezultateSO) so).getList();
        List<OpstiDomenskiObjekat> termini = vratiSveTermineTestiranja();

        for (OpstiDomenskiObjekat r : rezultati) {
            Rezultat rezultat = (Rezultat) r;
            rezultat.setTest((Test) dajTest(rezultat.getTest().getTestId()));
            rezultat.setTerminTestiranja((TerminTestiranja) dajTerminTestiranja(rezultat.getTerminTestiranja().getTerminTestiranjaId()));
        }
        return rezultati;
    }

    public List<OpstiDomenskiObjekat> vratiSveTermineTestiranja() throws Exception {
        OpstaSistemskaOperacija so = new VratiSveTermineTestiranjaSO();
        so.templateExecute(new TerminTestiranja());
        List<OpstiDomenskiObjekat> termini = ((VratiSveTermineTestiranjaSO) so).getList();
        
        for (OpstiDomenskiObjekat t : termini) {
            TerminTestiranja termin = (TerminTestiranja) t;
            termin.setLaborant((Laborant) dajLaboranta(termin.getLaborant().getLaborantId()));
            termin.setPacijent((Pacijent) dajPacijenta(termin.getPacijent().getPacijentId()));
        }
        return termini;
    }
    
    public void sacuvajSveRezultate(List<Rezultat> rezultati) throws Exception {
        OpstaSistemskaOperacija so = new SacuvajRezultatSO();
        so.templateExecute(rezultati);
    }


}
