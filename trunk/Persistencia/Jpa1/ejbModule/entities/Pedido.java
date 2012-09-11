package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Pedido implements Serializable {

	// [IdPedido] [int] IDENTITY(1,1) NOT NULL,
	private int idPedido;

	// [Fecha] [datetime] NULL,
	private Date fecha;

	// [Tienda] [int] NOT NULL,
	private int tienda;

	private List<ItemPedido> itemsPedido;

	public Pedido() {
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTienda() {
		return tienda;
	}

	public void setTienda(int tienda) {
		this.tienda = tienda;
	}

	public List<ItemPedido> getItemsPedido() {
		return itemsPedido;
	}

	public void setItemsPedido(List<ItemPedido> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}
}
