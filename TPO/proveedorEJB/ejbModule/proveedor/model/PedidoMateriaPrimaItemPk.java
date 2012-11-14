package proveedor.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PedidoMateriaPrimaItemPk implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	private PedidoMateriaPrima pedidoMateriaPrima;

	private MateriaPrima materiaPrima;

	public PedidoMateriaPrimaItemPk() {
	}

	public PedidoMateriaPrimaItemPk(PedidoMateriaPrima pedidoMateriaPrima,
			MateriaPrima materiaPrima) {
		this.pedidoMateriaPrima = pedidoMateriaPrima;
		this.materiaPrima = materiaPrima;
	}

	@ManyToOne
	public PedidoMateriaPrima getPedidoMateriaPrima() {
		return pedidoMateriaPrima;
	}

	public void setPedidoMateriaPrima(PedidoMateriaPrima pedidoMateriaPrima) {
		this.pedidoMateriaPrima = pedidoMateriaPrima;
	}

	@ManyToOne
	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((materiaPrima == null) ? 0 : materiaPrima.hashCode());
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
		PedidoMateriaPrimaItemPk other = (PedidoMateriaPrimaItemPk) obj;
		if (materiaPrima == null) {
			if (other.materiaPrima != null)
				return false;
		} else if (!materiaPrima.equals(other.materiaPrima))
			return false;
		if (pedidoMateriaPrima == null) {
			if (other.pedidoMateriaPrima != null)
				return false;
		} else if (!pedidoMateriaPrima.equals(other.pedidoMateriaPrima))
			return false;
		return true;
	}

}
