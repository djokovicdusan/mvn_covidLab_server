/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.laborant;

import domen.Laborant;
import so.OpstaSistemskaOperacija;

/**
 * System operation that changes any of the Laborant parameters.
 * 
 * @author Dule Djo
 */
public class IzmeniLaborantaSO extends OpstaSistemskaOperacija {
	/**
	 * Validates an object before the system operation is executed.
	 * 
	 * @param entity Object that is sent as request argument.
	 * @throws Exception If there were any type errors.
	 */
	@Override
	public void validate(Object entity) throws Exception {
		if (!(entity instanceof Laborant)) {
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
		Laborant laborant = (Laborant) entity;
		databaseBroker.azuriraj(laborant);
	}
}
