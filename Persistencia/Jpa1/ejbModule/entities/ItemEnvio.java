package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemEnvio implements Serializable {

	// [IdItemEnvio] [int] IDENTITY(1,1) NOT NULL,
	private int idItemEnvio;

	// [StockRecibido] [int] NOT NULL,
	private int stockRecibido;

	// [Referencia] [int] NULL,
	private Articulo articulo;

	// [IdEnvio] [int] NULL,
	private Envio envio;

	public ItemEnvio() {
	}

	public int getIdItemEnvio() {
		return idItemEnvio;
	}

	public void setIdItemEnvio(int idItemEnvio) {
		this.idItemEnvio = idItemEnvio;
	}

	public int getStockRecibido() {
		return stockRecibido;
	}

	public void setStockRecibido(int stockRecibido) {
		this.stockRecibido = stockRecibido;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
}
