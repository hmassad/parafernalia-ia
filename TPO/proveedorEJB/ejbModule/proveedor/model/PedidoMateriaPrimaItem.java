package proveedor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import proveedor.vo.PedidoMateriaPrimaItemVO;

@Entity
public class PedidoMateriaPrimaItem implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	@ManyToOne
	private PedidoMateriaPrima pedidoMateriaPrima;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String codigo;

	@Column
	private int cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	private Unidad unidad;

	public PedidoMateriaPrimaItem() {
	}

	public PedidoMateriaPrimaItem(PedidoMateriaPrima pedidoMateriaPrima, int id, String codigo, int cantidad, Unidad unidad) {
		this.pedidoMateriaPrima = pedidoMateriaPrima;
		this.id = id;
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}

	public PedidoMateriaPrima getPedidoMateriaPrima() {
		return pedidoMateriaPrima;
	}

	public void setPedidoMateriaPrima(PedidoMateriaPrima pedidoMateriaPrima) {
		this.pedidoMateriaPrima = pedidoMateriaPrima;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((pedidoMateriaPrima == null) ? 0 : pedidoMateriaPrima.hashCode());
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
		PedidoMateriaPrimaItem other = (PedidoMateriaPrimaItem) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (pedidoMateriaPrima == null) {
			if (other.pedidoMateriaPrima != null)
				return false;
		} else if (!pedidoMateriaPrima.equals(other.pedidoMateriaPrima))
			return false;
		return true;
	}

	public String toString() {
		return String.format("%s: %d %s", getCodigo(), getCantidad(), getUnidad());
	}

	public static PedidoMateriaPrimaItemVO toPedidoMateriaPrimaItemVO(PedidoMateriaPrimaItem pedidoMateriaPrimaItem) {
		return new PedidoMateriaPrimaItemVO(pedidoMateriaPrimaItem.getId(), pedidoMateriaPrimaItem.getCodigo(), pedidoMateriaPrimaItem.getCantidad(),
				Unidad.toUnidadVO(pedidoMateriaPrimaItem.getUnidad()));
	}

	public static PedidoMateriaPrimaItem toPedidoMateriaPrimaItem(PedidoMateriaPrima pedidoMateriaPrima, PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO) {
		return new PedidoMateriaPrimaItem(pedidoMateriaPrima, pedidoMateriaPrimaItemVO.getId(), pedidoMateriaPrimaItemVO.getCodigo(),
				pedidoMateriaPrimaItemVO.getCantidad(), Unidad.toUnidad(pedidoMateriaPrimaItemVO.getUnidad()));
	}
}
