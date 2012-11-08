package proveedor.beans.local;

import java.util.Collection;

import javax.ejb.Local;

import proveedor.vo.ListaPreciosVO;

@Local
public interface ListaPreciosSessionBeanLocal {

	void createListaPrecios(ListaPreciosVO listaPreciosVO);

	void deleteListaPrecios(int id);

	ListaPreciosVO getListaPrecios(int id);

	Collection<ListaPreciosVO> getListasPrecios();

	ListaPreciosVO getUltimaListaPrecios();

}
