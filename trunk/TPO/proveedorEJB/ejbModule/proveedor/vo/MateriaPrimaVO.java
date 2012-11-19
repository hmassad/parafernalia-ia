package proveedor.vo;

import java.io.Serializable;

public class MateriaPrimaVO implements Serializable {

	private static final long serialVersionUID = -8186139701415675322L;

	private String codigo;

	private String descripcion;

	private int stock;

	public MateriaPrimaVO() {
	}

	public MateriaPrimaVO(String codigo, String descripcion, int stock) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.stock = stock;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String toString() {
		return getDescripcion();
	}
}
