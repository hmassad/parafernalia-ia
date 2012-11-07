package proveedor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import proveedor.vo.PedidoMateriaPrimaItemVO;

@Entity
public class PedidoMateriaPrimaItem implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String codigo;

	@Column
	private int cantidad;

	@ManyToOne
	private PedidoMateriaPrima pedidoMateriaPrima;

	public PedidoMateriaPrimaItem() {
	}

	public PedidoMateriaPrimaItem(int id, String codigo, int cantidad, PedidoMateriaPrima pedidoMateriaPrima) {
		this.id = id;
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.pedidoMateriaPrima = pedidoMateriaPrima;
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoMateriaPrimaItem other = (PedidoMateriaPrimaItem) obj;
		if (cantidad != other.cantidad)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public String toString() {
		return String.format("%s: %d", getCodigo(), getCantidad());
	}

	public static PedidoMateriaPrimaItemVO toPedidoMateriaPrimaItemVO(PedidoMateriaPrimaItem pedidoMateriaPrimaItem) {
		return new PedidoMateriaPrimaItemVO(pedidoMateriaPrimaItem.getId(), pedidoMateriaPrimaItem.getCodigo(), pedidoMateriaPrimaItem.getCantidad());
	}

	public static PedidoMateriaPrimaItem toPedidoMateriaPrimaItem(PedidoMateriaPrima pedidoMateriaPrima, PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO) {
		return new PedidoMateriaPrimaItem(pedidoMateriaPrimaItemVO.getId(), pedidoMateriaPrimaItemVO.getCodigo(), pedidoMateriaPrimaItemVO.getCantidad(),
				pedidoMateriaPrima);
	}
}