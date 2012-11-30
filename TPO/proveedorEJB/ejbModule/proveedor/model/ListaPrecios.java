package proveedor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;

@Entity
public class ListaPrecios implements Serializable {

	private static final long serialVersionUID = 5223790124231719250L;

	private int id;

	private Date vigenciaDesde;

	private Date vigenciaHasta;

	private Collection<ListaPreciosItem> items;

	public ListaPrecios() {
		this.items = new ArrayList<ListaPreciosItem>();
	}

	public ListaPrecios(int id, Collection<ListaPreciosItem> items) {
		this.items = items;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.listaPrecios", cascade = { CascadeType.ALL })
	public Collection<ListaPreciosItem> getItems() {
		return items;
	}

	public void setItems(Collection<ListaPreciosItem> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((vigenciaDesde == null) ? 0 : vigenciaDesde.hashCode());
		result = prime * result
				+ ((vigenciaHasta == null) ? 0 : vigenciaHasta.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaPrecios other = (ListaPrecios) obj;
		if (id != other.id)
			return false;
		if (vigenciaDesde == null) {
			if (other.vigenciaDesde != null)
				return false;
		} else if (!vigenciaDesde.equals(other.vigenciaDesde))
			return false;
		if (vigenciaHasta == null) {
			if (other.vigenciaHasta != null)
				return false;
		} else if (!vigenciaHasta.equals(other.vigenciaHasta))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	public String toString() {
		return Integer.toString(getId());
	}

	public static ListaPreciosVO toListaPreciosVO(ListaPrecios listaPrecios) {
		Collection<ListaPreciosItemVO> lpiVOs = new ArrayList<ListaPreciosItemVO>();
		for (ListaPreciosItem lpi : listaPrecios.getItems()) {
			lpiVOs.add(ListaPreciosItem.toListaPreciosItemVO(lpi));
		}
		return new ListaPreciosVO(listaPrecios.getId(),
				listaPrecios.getVigenciaDesde(),
				listaPrecios.getVigenciaHasta(), lpiVOs);
	}

	public static ListaPrecios toListaPrecios(ListaPreciosVO listaPreciosVO) {
		ListaPrecios listaPrecios = new ListaPrecios();
		listaPrecios.setId(listaPreciosVO.getId());
		listaPrecios.setVigenciaDesde(listaPreciosVO.getVigenciaDesde());
		listaPrecios.setVigenciaHasta(listaPreciosVO.getVigenciaHasta());
		for (ListaPreciosItemVO lpiVO : listaPreciosVO.getItems()) {
			listaPrecios.getItems().add(
					ListaPreciosItem.toListaPreciosItem(listaPrecios, lpiVO));
		}
		return listaPrecios;
	}
}
