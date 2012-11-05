package proveedor.beans;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.MateriaPrimaVO;

@Local
public interface MateriaPrimaSessionBeanLocal {

	void createMateriaPrima(MateriaPrimaVO materiaPrima);
	
	void deleteMateriaPrima(String codigo);
	
	Collection<MateriaPrimaVO> getMateriasPrimas();

	MateriaPrimaVO getMateriaPrima(String codigo);
	
}
