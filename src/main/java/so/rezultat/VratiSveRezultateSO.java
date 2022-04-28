/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezultat;

import domen.OpstiDomenskiObjekat;
import domen.Rezultat;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class VratiSveRezultateSO extends OpstaSistemskaOperacija {

    private List<OpstiDomenskiObjekat> list;

    @Override
    public void validate(Object entity) throws Exception {
        if (!(entity instanceof Rezultat)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    public void execute(Object entity) throws Exception {
        list = databaseBroker.dajSve((Rezultat) entity);

    }

    public List<OpstiDomenskiObjekat> getList() {
        return list;
    }
}
