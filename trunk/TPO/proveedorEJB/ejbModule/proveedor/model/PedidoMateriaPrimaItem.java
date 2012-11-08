package proveedor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import proveedor.vo.PedidoMateriaPrimaItemVO;

@Entity
public class PedidoMateriaPrimaItem implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	@Id
	@Column
	private String codigo;

	@Column
	private int cantidad;

	@ManyToOne
	private PedidoMateriaPrima pedidoMateriaPrima;

	public PedidoMateriaPrimaItem() {
	}

	public PedidoMateriaPrimaItem(PedidoMateriaPrima pedidoMateriaPrima,
			String codigo, int cantidad) {
		this.pedidoMateriaPrima = pedidoMateriaPrima;
		this.codigo = codigo;
		this.cantidad = cantidad;
	}

	public PedidoMateriaPrima getPedidoMateriaPrima() {
		return pedidoMateriaPrima;
	}

	public void setPedidoMateriaPrima(PedidoMateriaPrima pedidoMateriaPrima) {
		this.pedidoMateriaPrima = pedidoMateriaPrima;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((pedidoMateriaPrima == null) ? 0 : pedidoMateriaPrima
						.hashCode());
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
		return String.format("%s: %d %s", getCodigo(), getCantidad());
	}

	public static PedidoMateriaPrimaItemVO toPedidoMateriaPrimaItemVO(
			PedidoMateriaPrimaItem pedidoMateriaPrimaItem) {
		return new PedidoMateriaPrimaItemVO(pedidoMateriaPrimaItem.getCodigo(),
				pedidoMateriaPrimaItem.getCantidad());
	}

	public static PedidoMateriaPrimaItem toPedidoMateriaPrimaItem(
			PedidoMateriaPrima pedidoMateriaPrima,
			PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO) {
		return new PedidoMateriaPrimaItem(pedidoMateriaPrima,
				pedidoMateriaPrimaItemVO.getCodigo(),
				pedidoMateriaPrimaItemVO.getCantidad());
	}
}
