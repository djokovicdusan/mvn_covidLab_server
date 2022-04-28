/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import domen.TerminTestiranja;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class VratiSveTermineTestiranjaSO extends OpstaSistemskaOperacija{
    private List<OpstiDomenskiObjekat> list;
    
    @Override
    public void validate(Object entity) throws Exception {
        if (!(entity instanceof TerminTestiranja)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    public void execute(Object entity) throws Exception {
        list = databaseBroker.dajSve((TerminTestiranja) entity);
        
    }
    
    public List<OpstiDomenskiObjekat> getList() {
        return list;
    }
}
