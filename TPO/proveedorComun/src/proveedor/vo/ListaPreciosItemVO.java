package proveedor.vo;

import java.io.Serializable;

public class ListaPreciosItemVO implements Serializable {

	private static final long serialVersionUID = 6783081308724466103L;

	private ProductoVO producto;

	private float precioUnitario;

	public ListaPreciosItemVO() {
	}

	public ListaPreciosItemVO(ProductoVO producto, float precioUntario) {
		this.producto = producto;
		this.precioUnitario = precioUntario;
	}

	public ProductoVO getProducto() {
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		this.producto = producto;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String toString() {
		return String.format("%s: $%.02f", getProducto(), getPrecioUnitario());
	}
}
