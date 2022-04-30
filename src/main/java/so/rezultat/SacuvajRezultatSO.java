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
 * System operation that saves a Rezultat in database.
 * 
 * @author Dule Djo
 */
public class SacuvajRezultatSO extends OpstaSistemskaOperacija {
	/**
	 * Validates an object before the system operation is executed.
	 * 
	 * @param entity Object that is sent as request argument.
	 * @throws Exception If there were any type errors.
	 */
	@Override
	public void validate(Object entity) throws Exception {
		List<Rezultat> list = (List<Rezultat>) entity;
		for (Rezultat r : list) {
			if (!(r instanceof Rezultat)) {
				throw new Exception("Invalid entity parameter!");
			}
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
		List<Rezultat> list = (List<Rezultat>) entity;
		for (Rezultat rezultat : list) {
			databaseBroker.sacuvajVoid(rezultat);
		}

	}

}
