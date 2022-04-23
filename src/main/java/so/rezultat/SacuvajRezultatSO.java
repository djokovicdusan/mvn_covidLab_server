/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezultat;

import domen.Rezultat;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class SacuvajRezultatSO extends OpstaSistemskaOperacija{
    
     @Override
    protected void validate(Object entity) throws Exception {
        List<Rezultat> list = (List<Rezultat>) entity;
        for(Rezultat r: list){
        if (!(r instanceof Rezultat)) {
            throw new Exception("Invalid entity parameter!");
        }
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        List<Rezultat> list = (List<Rezultat>) entity;
        for(Rezultat rezultat: list){
        databaseBroker.sacuvajVoid(rezultat);
        }
        
    }

}
