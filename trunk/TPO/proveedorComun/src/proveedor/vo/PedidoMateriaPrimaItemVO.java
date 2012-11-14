package proveedor.vo;

import java.io.Serializable;

public class PedidoMateriaPrimaItemVO implements Serializable {

	private static final long serialVersionUID = -9091108485176472960L;

	private MateriaPrimaVO materiaPrima;

	private int cantidad;

	public PedidoMateriaPrimaItemVO() {
	}

	public PedidoMateriaPrimaItemVO(MateriaPrimaVO materiaPrima, int cantidad) {
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
		return String.format("%s: %d", getMateriaPrima().getCodigo(),
				getCantidad());
	}
}
