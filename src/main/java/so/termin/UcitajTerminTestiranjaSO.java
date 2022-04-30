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
 * System operation that returns single TerminTestiranja, given the key.
 * 
 * @author Dule Djo
 */
public class UcitajTerminTestiranjaSO extends OpstaSistemskaOperacija {
	/**
	 * OpstiDomenskiObjekat object which will store the operation result.
	 */
	private OpstiDomenskiObjekat generalEntity;

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
		TerminTestiranja terminTestiranja = (TerminTestiranja) entity;
		generalEntity = databaseBroker.nadji(terminTestiranja, terminTestiranja.getTerminTestiranjaId());
	}

	/**
	 * Returns the operation result.
	 * 
	 * @return generalEntity Objects which represents the operation result
	 */
	public OpstiDomenskiObjekat getResult() {
		return generalEntity;
	}
}
