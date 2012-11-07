package proveedor.beans.local;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proveedor.model.ListaPrecios;
import proveedor.vo.ListaPreciosVO;

/**
 * Session Bean implementation class ListaPreciosSessionBean
 */
@Stateless
public class ListaPreciosSessionBean implements ListaPreciosSessionBeanLocal {

	@PersistenceContext(unitName = "proveedor")
	private EntityManager entityManager;

	public ListaPreciosSessionBean() {
	}

	public void createListaPrecios(ListaPreciosVO listaPreciosVO) {
		entityManager.persist(ListaPrecios.toListaPrecios(listaPreciosVO));
	}

	public void deleteListaPrecios(int id) {
		ListaPrecios listaPrecios = entityManager.find(ListaPrecios.class, id);
		entityManager.remove(listaPrecios);
	}

	public ListaPreciosVO getListaPrecios(int id) {
		ListaPrecios listaPreciosVO = entityManager.find(ListaPrecios.class, id);
		return ListaPrecios.toListaPreciosVO(listaPreciosVO);
	}

	@SuppressWarnings("unchecked")
	public Collection<ListaPreciosVO> getListasPrecios() {
		Query query = entityManager.createQuery("SELECT LP FROM ListaPrecios LP");
		Collection<ListaPreciosVO> listasPreciosVO = new ArrayList<ListaPreciosVO>();
		for (ListaPrecios listaPrecios : (Collection<ListaPrecios>) query.getResultList()) {
			listasPreciosVO.add(ListaPrecios.toListaPreciosVO(listaPrecios));
		}
		return listasPreciosVO;
	}

	public ListaPreciosVO getUltimaListaPrecios() {
		Query query = entityManager.createQuery("SELECT LP FROM ListaPrecios LP ORDER BY vigenciaHasta DESC");
		query.setMaxResults(1);
		return ListaPrecios.toListaPreciosVO((ListaPrecios) query.getSingleResult());
	}
}
