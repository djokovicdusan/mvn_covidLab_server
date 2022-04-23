/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import storage.IDBBroker;
import storage.database.DatabaseBroker;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author Dule Djo
 */
public abstract class OpstaSistemskaOperacija {
    
    protected IDBBroker databaseBroker;

    public OpstaSistemskaOperacija() {
        this.databaseBroker = new DatabaseBroker();
    }

    public final void templateExecute(Object entity) throws Exception {
        try {
            validate(entity);
            startTransaction();
            execute(entity);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        }
    }

    protected abstract void validate(Object entity) throws Exception;

    protected abstract void execute(Object entity) throws Exception;

    private void startTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().setAutoCommit(false);
    }

    private void commitTransaction() throws Exception {
        DatabaseConnection.getInstance().commit();
    }

    private void rollbackTransaction() throws Exception {
        DatabaseConnection.getInstance().rollback();
    }
}
