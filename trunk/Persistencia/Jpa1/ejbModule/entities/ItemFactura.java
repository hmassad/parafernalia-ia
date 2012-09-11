package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemFactura implements Serializable {

	// [IdItemFactura] [int] IDENTITY(1,1) NOT NULL,
	private int idItemFactura;

	// [Cantidad] [int] NOT NULL,
	private int cantidad;

	// [TipoItemFactura] [varchar](255) NULL,
	private String tipoItemFactura;

	// [IdFactura] [int] NULL,
	private Factura factura;

	// [Referencia] [int] NULL,
	private Articulo articulo;

	public ItemFactura() {
	}

	public int getIdItemFactura() {
		return idItemFactura;
	}

	public void setIdItemFactura(int idItemFactura) {
		this.idItemFactura = idItemFactura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipoItemFactura() {
		return tipoItemFactura;
	}

	public void setTipoItemFactura(String tipoItemFactura) {
		this.tipoItemFactura = tipoItemFactura;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
}
