/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.pacijent;

import domen.OpstiDomenskiObjekat;
import domen.Pacijent;
import java.util.List;
import so.OpstaSistemskaOperacija;

/**
 * System operation that returns all Pacijent objects.
 * 
 * @author Dule Djo
 */
public class VratiSvePacijenteSO extends OpstaSistemskaOperacija {
	/**
	 * List of OpstiDomenskiObjekat objects which will store the operation result as
	 * a list.
	 */
	private List<OpstiDomenskiObjekat> list;

	/**
	 * Validates an object before the system operation is executed.
	 * 
	 * @param entity Object that is sent as request argument.
	 * @throws Exception If there were any type errors.
	 */
	@Override
	public void validate(Object entity) throws Exception {
		if (!(entity instanceof Pacijent)) {
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
		list = databaseBroker.dajSve((OpstiDomenskiObjekat) entity);

	}

	/**
	 * Returns the operation result.
	 * 
	 * @return list List of objects which represents the operation result
	 */
	public List<OpstiDomenskiObjekat> getList() {
		return list;
	}

}
