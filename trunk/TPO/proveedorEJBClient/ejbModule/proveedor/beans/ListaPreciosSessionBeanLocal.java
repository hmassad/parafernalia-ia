package proveedor.beans;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.ListaPreciosVO;

@Local
public interface ListaPreciosSessionBeanLocal {

	void createListaPrecios(ListaPreciosVO listaPreciosVO);

	void deleteListaPrecios(int id);

	Collection<ListaPreciosVO> getListaPrecios();

	ListaPreciosVO getListaPrecios(int id);

	ListaPreciosVO getUltimaListaPrecios();
}
