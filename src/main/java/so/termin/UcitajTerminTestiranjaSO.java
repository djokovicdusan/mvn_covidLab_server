/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import domen.OpstiDomenskiObjekat;
import domen.TerminTestiranja;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class UcitajTerminTestiranjaSO extends OpstaSistemskaOperacija {
    private OpstiDomenskiObjekat generalEntity;
    
     @Override
     public void validate(Object entity) throws Exception {
        if (!(entity instanceof TerminTestiranja)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    public void execute(Object entity) throws Exception {
        TerminTestiranja terminTestiranja =(TerminTestiranja)entity;
        generalEntity = databaseBroker.nadji(terminTestiranja,terminTestiranja.getTerminTestiranjaId());
    }
    
    public OpstiDomenskiObjekat getResult() {
        return generalEntity;
    }
}
