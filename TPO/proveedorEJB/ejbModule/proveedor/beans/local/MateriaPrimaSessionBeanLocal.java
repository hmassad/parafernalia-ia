package proveedor.beans.local;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.MateriaPrimaVO;

@Local
public interface MateriaPrimaSessionBeanLocal {

	void createMateriaPrima(MateriaPrimaVO materiaPrima);

	void deleteMateriaPrima(String codigo);

	Collection<MateriaPrimaVO> getMateriasPrimas();

	MateriaPrimaVO getMateriaPrima(String codigo);

	void ingresarStock(String codigo, int cantidad);

	void descontarStock(String codigo, int cantidad);

}
