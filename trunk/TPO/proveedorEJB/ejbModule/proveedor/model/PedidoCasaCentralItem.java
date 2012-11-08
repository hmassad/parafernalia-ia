package proveedor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import proveedor.vo.PedidoCasaCentralItemVO;

@Entity
public class PedidoCasaCentralItem implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	@Id
	@Column
	private String codigo;

	@Column
	private int cantidad;

	@ManyToOne
	private PedidoCasaCentral pedidoCasaCentral;

	public PedidoCasaCentralItem() {
	}

	public PedidoCasaCentralItem(String codigo, int cantidad, PedidoCasaCentral pedidoCasaCentral) {
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.pedidoCasaCentral = pedidoCasaCentral;
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

	public PedidoCasaCentral getPedidoCasaCentral() {
		return pedidoCasaCentral;
	}

	public void setPedidoCasaCentral(PedidoCasaCentral pedidoCasaCentral) {
		this.pedidoCasaCentral = pedidoCasaCentral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((pedidoCasaCentral == null) ? 0 : pedidoCasaCentral.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoCasaCentralItem other = (PedidoCasaCentralItem) obj;
		if (cantidad != other.cantidad)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (pedidoCasaCentral == null) {
			if (other.pedidoCasaCentral != null)
				return false;
		} else if (!pedidoCasaCentral.equals(other.pedidoCasaCentral))
			return false;
		return true;
	}

	public String toString() {
		return String.format("%s: %d", getCodigo(), getCantidad());
	}

	public static PedidoCasaCentralItemVO toPedidoCasaCentralItemVO(PedidoCasaCentralItem pedidoCasaCentralItem) {
		return new PedidoCasaCentralItemVO(pedidoCasaCentralItem.getCodigo(), pedidoCasaCentralItem.getCantidad());
	}

	public static PedidoCasaCentralItem toPedidoCasaCentralItem(PedidoCasaCentral pedidoCasaCentral, PedidoCasaCentralItemVO pedidoCasaCentralItemVO) {
		return new PedidoCasaCentralItem(pedidoCasaCentralItemVO.getCodigo(), pedidoCasaCentralItemVO.getCantidad(),
				pedidoCasaCentral);
	}

}
