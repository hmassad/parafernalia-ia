package proveedor.beans;

import java.util.Date;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class MateriaPrimaSessionBean
 */
@Stateless
public class MateriaPrimaSessionBean implements MateriaPrimaSessionBeanLocal {

	/**
	 * Default constructor.
	 */
	public MateriaPrimaSessionBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MateriaPrimaSessionBeanLocal#getDate()
	 */
	public Date getDate() {

		return new Date();

	}

}
