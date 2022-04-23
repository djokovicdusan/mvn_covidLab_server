/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import domen.TerminTestiranja;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class SacuvajTerminTestiranjaSO extends OpstaSistemskaOperacija{
    private Long id;
    
     @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof TerminTestiranja)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        TerminTestiranja i=(TerminTestiranja)entity;
        id = databaseBroker.sacuvaj(i);
    }
    
    public Long getId() {
        return id;
    }
}
