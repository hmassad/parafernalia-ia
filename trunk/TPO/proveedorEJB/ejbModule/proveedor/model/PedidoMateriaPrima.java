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

import proveedor.vo.PedidoMateriaPrimaItemVO;
import proveedor.vo.PedidoMateriaPrimaVO;

@Entity
public class PedidoMateriaPrima implements Serializable {

	private static final long serialVersionUID = -2765474910705830418L;

	private int id;

	private boolean entregado;

	private Date fecha;

	private Collection<PedidoMateriaPrimaItem> items;

	public PedidoMateriaPrima() {
		items = new ArrayList<PedidoMateriaPrimaItem>();
	}

	public PedidoMateriaPrima(int id, boolean entregado, Date fecha,
			Collection<PedidoMateriaPrimaItem> items) {
		this.id = id;
		this.entregado = entregado;
		this.fecha = fecha;
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
	public boolean getEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.pedidoMateriaPrima", cascade = { CascadeType.ALL })
	public Collection<PedidoMateriaPrimaItem> getItems() {
		return items;
	}

	public void setItems(Collection<PedidoMateriaPrimaItem> items) {
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
		PedidoMateriaPrima other = (PedidoMateriaPrima) obj;
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

	public static PedidoMateriaPrimaVO toPedidoMateriaPrimaVO(
			PedidoMateriaPrima pedidoMateriaPrima) {
		Collection<PedidoMateriaPrimaItemVO> items = new ArrayList<PedidoMateriaPrimaItemVO>();
		for (PedidoMateriaPrimaItem pedidoMateriaPrimaItem : pedidoMateriaPrima
				.getItems()) {
			items.add(PedidoMateriaPrimaItem
					.toPedidoMateriaPrimaItemVO(pedidoMateriaPrimaItem));
		}
		return new PedidoMateriaPrimaVO(pedidoMateriaPrima.getId(),
				pedidoMateriaPrima.getEntregado(),
				pedidoMateriaPrima.getFecha(), items);
	}

	public static PedidoMateriaPrima toPedidoMateriaPrima(
			PedidoMateriaPrimaVO pedidoMateriaPrimaVO) {
		PedidoMateriaPrima pedidoMateriaPrima = new PedidoMateriaPrima();
		pedidoMateriaPrima.setId(pedidoMateriaPrimaVO.getId());
		pedidoMateriaPrima.setEntregado(pedidoMateriaPrimaVO.getEntregado());
		pedidoMateriaPrima.setFecha(pedidoMateriaPrimaVO.getFecha());
		for (PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO : pedidoMateriaPrimaVO
				.getItems()) {
			pedidoMateriaPrima.getItems().add(
					PedidoMateriaPrimaItem.toPedidoMateriaPrimaItem(
							pedidoMateriaPrima, pedidoMateriaPrimaItemVO));
		}
		return pedidoMateriaPrima;
	}
}
