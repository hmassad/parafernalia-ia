package proveedor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PedidoMateriaPrimaVO implements Serializable {

	private static final long serialVersionUID = -1365725042156278969L;

	private int id;

	private boolean entregado;

	private Date fecha;

	private Collection<PedidoMateriaPrimaItemVO> items;

	public PedidoMateriaPrimaVO() {
		items = new ArrayList<PedidoMateriaPrimaItemVO>();
	}

	public PedidoMateriaPrimaVO(int id, boolean entregado, Date fecha,
			Collection<PedidoMateriaPrimaItemVO> items) {
		this.id = id;
		this.entregado = entregado;
		this.fecha = fecha;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
