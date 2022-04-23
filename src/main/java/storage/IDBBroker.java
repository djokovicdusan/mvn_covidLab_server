/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domen.OpstiDomenskiObjekat;
import domen.Rezultat;
import java.util.List;

/**
 *
 * @author Dule Djo
 */
public interface IDBBroker {
    List<OpstiDomenskiObjekat> dajSve(OpstiDomenskiObjekat odo) throws Exception;
    OpstiDomenskiObjekat nadji(OpstiDomenskiObjekat odo,Long id) throws Exception;
    Long sacuvaj(OpstiDomenskiObjekat odo) throws Exception;
    void azuriraj(OpstiDomenskiObjekat odo) throws Exception;
    void obrisi(OpstiDomenskiObjekat odo) throws Exception;
    OpstiDomenskiObjekat uloguj(OpstiDomenskiObjekat odo,String username,String password) throws Exception;
    List<OpstiDomenskiObjekat> filtriraj(OpstiDomenskiObjekat odo) throws Exception;
    void sacuvajVoid(OpstiDomenskiObjekat odo) throws Exception;
    
}
