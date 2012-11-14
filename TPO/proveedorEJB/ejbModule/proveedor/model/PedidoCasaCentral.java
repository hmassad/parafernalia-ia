package proveedor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;

@Entity
public class PedidoCasaCentral implements Serializable {

	private static final long serialVersionUID = -104577832298987023L;

	private int id;

	private Date fecha;

	private boolean entregado;

	private String nroOrdenCompra;

	private Collection<PedidoCasaCentralItem> items;

	public PedidoCasaCentral() {
		items = new ArrayList<PedidoCasaCentralItem>();
	}

	public PedidoCasaCentral(int id, boolean entregado, Date fecha,
			String nroOrdenCompra, Collection<PedidoCasaCentralItem> items) {
		this.id = id;
		this.entregado = entregado;
		this.fecha = fecha;
		this.nroOrdenCompra = nroOrdenCompra;
		this.items = items;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column
	public boolean getEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	@Column
	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}

	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}

	@OneToMany(mappedBy = "pk.pedidoCasaCentral", cascade = { CascadeType.ALL })
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

	public static PedidoCasaCentralVO toPedidoCasaCentralVO(
			PedidoCasaCentral pedidoCasaCentral) {
		Collection<PedidoCasaCentralItemVO> items = new ArrayList<PedidoCasaCentralItemVO>();
		for (PedidoCasaCentralItem pedidoCasaCentralItem : pedidoCasaCentral
				.getItems()) {
			items.add(PedidoCasaCentralItem
					.toPedidoCasaCentralItemVO(pedidoCasaCentralItem));
		}
		return new PedidoCasaCentralVO(pedidoCasaCentral.getId(),
				pedidoCasaCentral.getEntregado(), pedidoCasaCentral.getFecha(),
				pedidoCasaCentral.getNroOrdenCompra(), items);
	}

	public static PedidoCasaCentral toPedidoCasaCentral(
			PedidoCasaCentralVO pedidoCasaCentralVO) {
		PedidoCasaCentral pedidoCasaCentral = new PedidoCasaCentral();
		pedidoCasaCentral.setId(pedidoCasaCentralVO.getId());
		pedidoCasaCentral.setEntregado(pedidoCasaCentralVO.getEntregado());
		pedidoCasaCentral.setFecha(pedidoCasaCentralVO.getFecha());
		pedidoCasaCentral.setNroOrdenCompra(pedidoCasaCentralVO
				.getNroOrdenCompra());
		for (PedidoCasaCentralItemVO pedidoCasaCentralItemVO : pedidoCasaCentralVO
				.getItems()) {
			pedidoCasaCentral.getItems().add(
					PedidoCasaCentralItem.toPedidoCasaCentralItem(
							pedidoCasaCentral, pedidoCasaCentralItemVO));
		}
		return pedidoCasaCentral;
	}
}
