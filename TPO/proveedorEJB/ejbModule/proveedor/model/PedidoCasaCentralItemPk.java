package proveedor.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PedidoCasaCentralItemPk implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	private PedidoCasaCentral pedidoCasaCentral;

	private Producto producto;

	public PedidoCasaCentralItemPk() {
	}

	public PedidoCasaCentralItemPk(PedidoCasaCentral pedidoCasaCentral,
			Producto producto) {
		this.pedidoCasaCentral = pedidoCasaCentral;
		this.producto = producto;
	}

	@ManyToOne
	public PedidoCasaCentral getPedidoCasaCentral() {
		return pedidoCasaCentral;
	}

	public void setPedidoCasaCentral(PedidoCasaCentral pedidoCasaCentral) {
		this.pedidoCasaCentral = pedidoCasaCentral;
	}

	@ManyToOne
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((pedidoCasaCentral == null) ? 0 : pedidoCasaCentral
						.hashCode());
		result = prime * result
				+ ((producto == null) ? 0 : producto.hashCode());
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
		PedidoCasaCentralItemPk other = (PedidoCasaCentralItemPk) obj;
		if (pedidoCasaCentral == null) {
			if (other.pedidoCasaCentral != null)
				return false;
		} else if (!pedidoCasaCentral.equals(other.pedidoCasaCentral))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

}
