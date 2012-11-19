package proveedor.vo;

import java.io.Serializable;

public class PedidoCasaCentralItemVO implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	private ProductoVO producto;

	private int cantidad;

	public PedidoCasaCentralItemVO() {
	}

	public PedidoCasaCentralItemVO(ProductoVO producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public ProductoVO getProducto() {
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String toString() {
		return String
				.format("%s: %d", getProducto().getCodigo(), getCantidad());
	}
}
