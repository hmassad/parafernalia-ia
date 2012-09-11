package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemPedido implements Serializable {

	// [IdItemPedido] [int] IDENTITY(1,1) NOT NULL,
	private int idItemPedido;

	// [StockSolicitado] [int] NOT NULL,
	private int stockSolicitado;

	// [StockPendiente] [int] NOT NULL,
	private int stockPendiente;

	// [Referencia] [int] NULL,
	private int referencia;

	// [IdPedido] [int] NULL,
	private Pedido pedido;

	public ItemPedido() {
	}

	public int getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public int getStockSolicitado() {
		return stockSolicitado;
	}

	public void setStockSolicitado(int stockSolicitado) {
		this.stockSolicitado = stockSolicitado;
	}

	public int getStockPendiente() {
		return stockPendiente;
	}

	public void setStockPendiente(int stockPendiente) {
		this.stockPendiente = stockPendiente;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
