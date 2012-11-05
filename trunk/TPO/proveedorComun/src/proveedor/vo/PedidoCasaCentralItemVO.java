package proveedor.vo;

import java.io.Serializable;

public class PedidoCasaCentralItemVO implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	private int id;

	private String codigo;

	private int cantidad;

	public PedidoCasaCentralItemVO() {
	}

	public PedidoCasaCentralItemVO(int id, String codigo, int cantidad) {
		this.id = id;
		this.codigo = codigo;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
