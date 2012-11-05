package proveedor.model;

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
public class PedidoCasaCentral implements Serializable {

	private static final long serialVersionUID = -104577832298987023L;

	@Id
	@Column
	private int id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedidoCasaCentral", cascade = { CascadeType.ALL })
	private Collection<PedidoCasaCentralItem> items;

	public PedidoCasaCentral() {
		items = new ArrayList<PedidoCasaCentralItem>();
	}

	public PedidoCasaCentral(int id, Collection<PedidoCasaCentralItem> items) {
		this.id = id;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
