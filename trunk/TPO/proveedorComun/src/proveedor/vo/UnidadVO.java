package proveedor.vo;

import java.io.Serializable;

public class UnidadVO implements Serializable {

	private static final long serialVersionUID = 8035658268559047572L;

	private String codigo;

	private String descripcion;

	public UnidadVO() {

	}

	public UnidadVO(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
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

	public String toString() {
		return getDescripcion();
	}
}
