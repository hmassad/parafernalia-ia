package proveedor.vo;

import java.util.ArrayList;
import java.util.Collection;

public class PedidoMateriaPrimaVO {

	private int id;

	private Collection<PedidoMateriaPrimaItemVO> items;

	public PedidoMateriaPrimaVO() {
		items = new ArrayList<PedidoMateriaPrimaItemVO>();
	}

	public PedidoMateriaPrimaVO(int id, Collection<PedidoMateriaPrimaItemVO> items) {
		this.id = id;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<PedidoMateriaPrimaItemVO> getItems() {
		return items;
	}

	public void setItems(Collection<PedidoMateriaPrimaItemVO> items) {
		this.items = items;
	}

	public String toString() {
		return Integer.toString(getId());
	}

}
