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
public class ListaPrecios implements Serializable {

	private static final long serialVersionUID = 5223790124231719250L;

	@Id
	@Column
	private int id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.listaPrecios", cascade = { CascadeType.ALL })
	private Collection<ListaPreciosItem> items;

	public ListaPrecios() {
		this.items = new ArrayList<ListaPreciosItem>();
	}

	public ListaPrecios(int id, Collection<ListaPreciosItem> items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<ListaPreciosItem> getItems() {
		return items;
	}

	public void setItems(Collection<ListaPreciosItem> items) {
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
		ListaPrecios other = (ListaPrecios) obj;
		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(getId());
	}
}
