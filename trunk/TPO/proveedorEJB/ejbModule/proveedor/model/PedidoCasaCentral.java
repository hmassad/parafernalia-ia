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

import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;

@Entity
public class PedidoCasaCentral implements Serializable {

	private static final long serialVersionUID = -104577832298987023L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private Date fecha;

	@Column
	private String nroOrdenCompra;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoCasaCentral", cascade = { CascadeType.ALL })
	private Collection<PedidoCasaCentralItem> items;

	public PedidoCasaCentral() {
		items = new ArrayList<PedidoCasaCentralItem>();
	}

	public PedidoCasaCentral(int id, Date fecha, String nroOrdenCompra, Collection<PedidoCasaCentralItem> items) {
		this.id = id;
		this.fecha = fecha;
		this.nroOrdenCompra = nroOrdenCompra;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}

	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}

	public Collection<PedidoCasaCentralItem> getItems() {
		return items;
	}

	public void setItems(Collection<PedidoCasaCentralItem> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + id;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoCasaCentral other = (PedidoCasaCentral) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id != other.id)
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

	public static PedidoCasaCentralVO toPedidoCasaCentralVO(PedidoCasaCentral pedidoCasaCentral) {
		Collection<PedidoCasaCentralItemVO> items = new ArrayList<PedidoCasaCentralItemVO>();
		for (PedidoCasaCentralItem pedidoCasaCentralItem : pedidoCasaCentral.getItems()) {
			items.add(PedidoCasaCentralItem.toPedidoCasaCentralItemVO(pedidoCasaCentralItem));
		}
		return new PedidoCasaCentralVO(pedidoCasaCentral.getId(), pedidoCasaCentral.getFecha(), pedidoCasaCentral.getNroOrdenCompra(), items);
	}

	public static PedidoCasaCentral toPedidoCasaCentral(PedidoCasaCentralVO pedidoCasaCentralVO) {
		PedidoCasaCentral pedidoCasaCentral = new PedidoCasaCentral();
		pedidoCasaCentral.setId(pedidoCasaCentralVO.getId());
		pedidoCasaCentral.setFecha(pedidoCasaCentralVO.getFecha());
		pedidoCasaCentral.setNroOrdenCompra(pedidoCasaCentralVO.getNroOrdenCompra());
		for (PedidoCasaCentralItemVO pedidoCasaCentralItemVO : pedidoCasaCentralVO.getItems()) {
			pedidoCasaCentral.getItems().add(PedidoCasaCentralItem.toPedidoCasaCentralItem(pedidoCasaCentral, pedidoCasaCentralItemVO));
		}
		return pedidoCasaCentral;
	}
}
