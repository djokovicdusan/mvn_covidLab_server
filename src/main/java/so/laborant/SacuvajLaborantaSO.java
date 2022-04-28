/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.laborant;

import domen.Laborant;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class SacuvajLaborantaSO extends OpstaSistemskaOperacija{
    private Long id;
    
     @Override
    public void validate(Object entity) throws Exception {
        if (!(entity instanceof Laborant)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    public void execute(Object entity) throws Exception {
        Laborant laborant =(Laborant)entity;
        id = databaseBroker.sacuvaj(laborant);
    }
    
    public Long getId() {
        return id;
    }
}