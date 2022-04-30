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
 * Generic system operation abstract class that all system operations extend.
 * 
 * @author Dule Djo
 */
public abstract class OpstaSistemskaOperacija {
	/**
	 * Database broker interface that is used for data manipulation.
	 */
	protected IDBBroker databaseBroker;

	/**
	 * Non-parameterized constructor used for initialization of
	 * OpstaSistemskaOperacija type objects. Initializes DB broker attribute.
	 */
	public OpstaSistemskaOperacija() {
		this.databaseBroker = new DatabaseBroker();
	}

	/**
	 * Executes the system operation in a specific order, after validating object
	 * type, then starting a transaction etc.
	 * 
	 * @param param Object that is sent as request argument.
	 * @throws Exception When the preconditions are not pleased or when there is an
	 *                   Exception thrown by the DBBroker.
	 */
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

	/**
	 * Validates an object before the system operation is executed.
	 * 
	 * @param param Object that is sent as request argument.
	 * @throws Exception If there were any type errors.
	 */
	protected abstract void validate(Object entity) throws Exception;

	/**
	 * Executes the operation after the transaction has started.
	 * 
	 * @param param Object that is sent as request argument.
	 * @throws Exception If there were any type errors.
	 */
	protected abstract void execute(Object entity) throws Exception;

	/**
	 * Starts the transaction.
	 * 
	 * @throws Exception If there were any errors.
	 */
	private void startTransaction() throws Exception {
		DatabaseConnection.getInstance().getConnection().setAutoCommit(false);
	}

	/**
	 * Commits the transaction.
	 * 
	 * @throws Exception If there were any errors.
	 */
	private void commitTransaction() throws Exception {
		DatabaseConnection.getInstance().commit();
	}

	/**
	 * Rollbacks the transaction.
	 * 
	 * @throws Exception If there were any errors.
	 */
	private void rollbackTransaction() throws Exception {
		DatabaseConnection.getInstance().rollback();
	}
}
