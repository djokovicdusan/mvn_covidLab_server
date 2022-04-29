/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.test;

import domen.OpstiDomenskiObjekat;
import domen.Test;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class UcitajTestSO extends OpstaSistemskaOperacija{
    private OpstiDomenskiObjekat generalEntity;
    
     @Override
     public void validate(Object entity) throws Exception {
        if (!(entity instanceof Test)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    public void execute(Object entity) throws Exception {
        Test test =(Test)entity;
        generalEntity = databaseBroker.nadji(test,test.getTestId());
    }
    
    public OpstiDomenskiObjekat getResult() {
        return generalEntity;
    }
}
