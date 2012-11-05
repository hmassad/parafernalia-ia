package proveedor.beans;

import java.util.Collection;

import javax.ejb.Stateless;

import proveedor.vo.ListaPreciosVO;

/**
 * Session Bean implementation class ListaPreciosSessionBean
 */
@Stateless
public class ListaPreciosSessionBean implements ListaPreciosSessionBeanLocal {

	public ListaPreciosSessionBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createListaPrecios(ListaPreciosVO listaPreciosVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteListaPrecios(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<ListaPreciosVO> getListaPrecios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaPreciosVO getListaPrecios(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaPreciosVO getUltimaListaPrecios() {
		// TODO Auto-generated method stub
		return null;
	}

}
