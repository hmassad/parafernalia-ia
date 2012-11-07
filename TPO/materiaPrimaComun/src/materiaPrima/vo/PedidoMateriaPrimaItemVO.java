package materiaPrima.vo;

import java.io.Serializable;

public class PedidoMateriaPrimaItemVO implements Serializable {

	private static final long serialVersionUID = -9091108485176472960L;

	private int id;

	private String codigo;

	private int cantidad;

	private String unidad;

	public PedidoMateriaPrimaItemVO() {
	}

	public PedidoMateriaPrimaItemVO(int id, String codigo, int cantidad,
			String unidad) {
		this.id = id;
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.unidad = unidad;
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

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String toString() {
		return String.format("%s: %d %s", getCodigo(), getCantidad(),
				getUnidad());
	}
}
