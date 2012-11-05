package proveedor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class PedidoCasaCentralVO implements Serializable {

	private static final long serialVersionUID = -7509437759415362651L;

	private int id;

	private Collection<PedidoCasaCentralItemVO> items;

	public PedidoCasaCentralVO() {
		items = new ArrayList<PedidoCasaCentralItemVO>();
	}

	public PedidoCasaCentralVO(int id, Collection<PedidoCasaCentralItemVO> items) {
		this.id = id;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<PedidoCasaCentralItemVO> getItems() {
		return items;
	}

	public void setItems(Collection<PedidoCasaCentralItemVO> items) {
		this.items = items;
	}

	public String toString() {
		return Integer.toString(getId());
	}
}
