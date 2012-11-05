package proveedor.beans;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.UnidadVO;

@Local
public interface UnidadesSessionBeanLocal {

	void createUnidad(UnidadVO unidadVo);

	void deleteUnidad(String codigo);

	Collection<UnidadVO> getUnidades();

	UnidadVO getUnidad(String codigo);

}
