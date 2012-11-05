package materiaPrima.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class PedidoMateriaPrimaVO implements Serializable {

	private static final long serialVersionUID = -767324130101613042L;

	private int id;

	private boolean entregado;

	private Collection<PedidoMateriaPrimaItemVO> items;

	public PedidoMateriaPrimaVO() {
		items = new ArrayList<PedidoMateriaPrimaItemVO>();
	}

	public PedidoMateriaPrimaVO(int id, boolean entregado, Collection<PedidoMateriaPrimaItemVO> items) {
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
