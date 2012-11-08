package proveedor.vo;

import java.io.Serializable;

public class PedidoMateriaPrimaItemVO implements Serializable {

	private static final long serialVersionUID = -9091108485176472960L;

	private String codigo;

	private int cantidad;

	public PedidoMateriaPrimaItemVO() {
	}

	public PedidoMateriaPrimaItemVO(String codigo, int cantidad) {
		this.codigo = codigo;
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String toString() {
		return String.format("%s: %d", getCodigo(), getCantidad());
	}
}
