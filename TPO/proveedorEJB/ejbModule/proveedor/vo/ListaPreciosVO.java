package proveedor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ListaPreciosVO implements Serializable {

	private static final long serialVersionUID = 5223790124231719250L;

	private int id;

	private Date vigenciaDesde;
	
	private Date vigenciaHasta;

	private Collection<ListaPreciosItemVO> items;

	public ListaPreciosVO() {
		this.items = new ArrayList<ListaPreciosItemVO>();
	}

	public ListaPreciosVO(int id, Date vigenciaDesde, Date vigenciaHasta, Collection<ListaPreciosItemVO> items) {
		this.id = id;
		this.vigenciaDesde = vigenciaDesde;
		this.vigenciaHasta = vigenciaHasta;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	public Collection<ListaPreciosItemVO> getItems() {
		return items;
	}

	public void setItems(Collection<ListaPreciosItemVO> items) {
		this.items = items;
	}

	public String toString() {
		return Integer.toString(getId());
	}
}
