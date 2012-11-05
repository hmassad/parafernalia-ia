package proveedor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ListaPreciosVO implements Serializable {

	private static final long serialVersionUID = 5223790124231719250L;

	private int id;

	private Collection<ListaPreciosItemVO> items;

	public ListaPreciosVO() {
		this.items = new ArrayList<ListaPreciosItemVO>();
	}

	public ListaPreciosVO(int id, Collection<ListaPreciosItemVO> items) {
		this.id = id;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<ListaPreciosItemVO> getItems() {
		return items;
	}

	public void setItems(Collection<ListaPreciosItemVO> items) {
		this.items = items;
	}

	public String toString() {
		return Integer.toString(getId());
	}
}
