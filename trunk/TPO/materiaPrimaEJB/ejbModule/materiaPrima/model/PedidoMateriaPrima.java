package materiaPrima.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PedidoMateriaPrima implements Serializable {

	private static final long serialVersionUID = -1742341522965186933L;

	@Id
	@Column
	private int id;

	@Column
	private boolean entregado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoMateriaPrima", cascade = { CascadeType.ALL })
	private Collection<PedidoMateriaPrimaItem> items;

	public PedidoMateriaPrima() {
		items = new ArrayList<PedidoMateriaPrimaItem>();
	}

	public PedidoMateriaPrima(int id, boolean entregado, Collection<PedidoMateriaPrimaItem> items) {
		this.id = id;
		this.entregado = entregado;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

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
		result = prime * result + (entregado ? 1231 : 1237);
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
		if (entregado != other.entregado)
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
}
