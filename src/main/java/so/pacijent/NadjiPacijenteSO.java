/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.pacijent;
import domen.*;
import domen.OpstiDomenskiObjekat;
import domen.Pacijent;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Dule Djo
 */
public class NadjiPacijenteSO extends OpstaSistemskaOperacija{
    private List<OpstiDomenskiObjekat> list;
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Pacijent)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = databaseBroker.filtriraj((Pacijent) entity);
    }
    
    public List<OpstiDomenskiObjekat> getList() {
        return list;
    }
}
