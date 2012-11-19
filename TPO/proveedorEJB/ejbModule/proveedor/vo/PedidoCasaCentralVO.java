package proveedor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PedidoCasaCentralVO implements Serializable {

	private static final long serialVersionUID = -7509437759415362651L;

	private int id;

	private Date fecha;

	private boolean entregado;

	private String nroOrdenCompra;

	private Collection<PedidoCasaCentralItemVO> items;

	public PedidoCasaCentralVO() {
		items = new ArrayList<PedidoCasaCentralItemVO>();
	}

	public PedidoCasaCentralVO(int id, boolean entregado, Date fecha, String nroOrdenCompra, Collection<PedidoCasaCentralItemVO> items) {
		this.id = id;
		this.entregado = entregado;
		this.fecha = fecha;
		this.nroOrdenCompra = nroOrdenCompra;
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

	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}

	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
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
