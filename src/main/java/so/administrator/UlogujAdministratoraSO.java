/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.administrator;

import domen.*;
import domen.Administrator;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 * System operation that logs in the admin.
 * 
 * @author Dule Djo
 */
public class UlogujAdministratoraSO extends OpstaSistemskaOperacija {
	/**
	 * Admin object which will store the operation result.
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
		if (!(entity instanceof Administrator)) {
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
		Administrator a = (Administrator) entity;
		generalEntity = databaseBroker.uloguj(a, a.getKorisnickoIme(), a.getLozinka());
	}

	/**
	 * Returns the operation result.
	 * 
	 * @return generalEntity Object which represents the operation result
	 */
	public OpstiDomenskiObjekat getResult() {
		return generalEntity;
	}
}
