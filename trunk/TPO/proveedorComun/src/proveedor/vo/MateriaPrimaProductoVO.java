package proveedor.vo;

import java.io.Serializable;

public class MateriaPrimaProductoVO implements Serializable {

	private static final long serialVersionUID = 895148808369880007L;

	private MateriaPrimaVO materiaPrima;

	private int cantidad;

	public MateriaPrimaProductoVO() {
	}

	public MateriaPrimaProductoVO(MateriaPrimaVO materiaPrima, int cantidad) {
		this.materiaPrima = materiaPrima;
		this.cantidad = cantidad;
	}

	public MateriaPrimaVO getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrimaVO materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String toString() {
		return String.format("%s (%d %s)", getMateriaPrima().getCodigo(),
				getCantidad(), getMateriaPrima().getDescripcion());
	}
}
