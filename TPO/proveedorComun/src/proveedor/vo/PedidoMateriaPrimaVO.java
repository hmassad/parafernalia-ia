package proveedor.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PedidoMateriaPrimaVO {

	private int id;

	private Date fecha;

	private Collection<PedidoMateriaPrimaItemVO> items;

	public PedidoMateriaPrimaVO() {
		items = new ArrayList<PedidoMateriaPrimaItemVO>();
	}

	public PedidoMateriaPrimaVO(int id, Date fecha, Collection<PedidoMateriaPrimaItemVO> items) {
		this.id = id;
		this.fecha=fecha;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
