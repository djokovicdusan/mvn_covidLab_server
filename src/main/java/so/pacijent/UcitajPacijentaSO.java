/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.pacijent;

import domen.OpstiDomenskiObjekat;
import domen.Pacijent;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class UcitajPacijentaSO extends OpstaSistemskaOperacija{
    private OpstiDomenskiObjekat generalEntity;
    
     @Override
     public void validate(Object entity) throws Exception {
        if (!(entity instanceof Pacijent)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    public void execute(Object entity) throws Exception {
        Pacijent pacijent=(Pacijent)entity;
        generalEntity = databaseBroker.nadji(pacijent,pacijent.getPacijentId());
    }
    
    public OpstiDomenskiObjekat getResult() {
        return generalEntity;
    }
}
