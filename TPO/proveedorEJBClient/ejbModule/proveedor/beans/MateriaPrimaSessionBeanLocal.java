package proveedor.beans;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface MateriaPrimaSessionBeanLocal {

	Date getDate();

}
