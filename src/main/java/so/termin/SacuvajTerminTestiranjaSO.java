/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import domen.TerminTestiranja;
import so.OpstaSistemskaOperacija;

/**
 * System operation that saves a TerminTestiranja in database.
 * 
 * @author Dule Djo
 */
public class SacuvajTerminTestiranjaSO extends OpstaSistemskaOperacija {
	/**
	 * ID of a saved TerminTestiranja
	 */
	private Long id;

	/**
	 * Validates an object before the system operation is executed.
	 * 
	 * @param entity Object that is sent as request argument.
	 * @throws Exception If there were any type errors.
	 */
	@Override
	public void validate(Object entity) throws Exception {
		if (!(entity instanceof TerminTestiranja)) {
			throw new Exception("Invalid entity parameter!");
		}
	}

	/**
	 * Executes the operation after the transaction has started.
	 * 
	 * @param entity Object that is sent as request argument.
	 * @throws Exception If there were any type errors.
	 */
	@Override
	public void execute(Object entity) throws Exception {
		TerminTestiranja i = (TerminTestiranja) entity;
		id = databaseBroker.sacuvaj(i);
	}

	/**
	 * Returns the operation result as a primary key.
	 * 
	 * @return id ID which represents the primary key of the saved TerminTestiranja.
	 */
	public Long getId() {
		return id;
	}
}
