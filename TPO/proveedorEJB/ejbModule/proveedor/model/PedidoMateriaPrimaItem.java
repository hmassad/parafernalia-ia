package proveedor.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import proveedor.vo.PedidoMateriaPrimaItemVO;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "pk.pedidoMateriaPrima", joinColumns = @JoinColumn(name = "pedidoMateriaPrima", nullable = false)),
		@AssociationOverride(name = "pk.materiaPrima", joinColumns = @JoinColumn(name = "materiaPrima", nullable = false)) })
public class PedidoMateriaPrimaItem implements Serializable {

	private static final long serialVersionUID = -8241932010498640786L;

	private PedidoMateriaPrimaItemPk pk;

	private int cantidad;

	public PedidoMateriaPrimaItem() {
		pk = new PedidoMateriaPrimaItemPk();
	}

	public PedidoMateriaPrimaItem(PedidoMateriaPrima pedidoMateriaPrima,
			MateriaPrima materiaPrima, int cantidad) {
		this.pk = new PedidoMateriaPrimaItemPk(pedidoMateriaPrima, materiaPrima);
		this.cantidad = cantidad;
	}

	@EmbeddedId
	public PedidoMateriaPrimaItemPk getPk() {
		return pk;
	}

	public void setPk(PedidoMateriaPrimaItemPk pk) {
		this.pk = pk;
	}

	@Transient
	public PedidoMateriaPrima getPedidoMateriaPrima() {
		return pk.getPedidoMateriaPrima();
	}

	public void setPedidoMateriaPrima(PedidoMateriaPrima pedidoMateriaPrima) {
		pk.setPedidoMateriaPrima(pedidoMateriaPrima);
	}

	@Transient
	public MateriaPrima getMateriaPrima() {
		return pk.getMateriaPrima();
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		pk.setMateriaPrima(materiaPrima);
	}

	@Column
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		PedidoMateriaPrimaItem other = (PedidoMateriaPrimaItem) obj;
		if (cantidad != other.cantidad)
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public String toString() {
		return String.format("%s: %d", getMateriaPrima().getCodigo(),
				getCantidad());
	}

	public static PedidoMateriaPrimaItemVO toPedidoMateriaPrimaItemVO(
			PedidoMateriaPrimaItem pedidoMateriaPrimaItem) {
		return new PedidoMateriaPrimaItemVO(
				MateriaPrima.toMateriaPrimaVO(pedidoMateriaPrimaItem
						.getMateriaPrima()),
				pedidoMateriaPrimaItem.getCantidad());
	}

	public static PedidoMateriaPrimaItem toPedidoMateriaPrimaItem(
			PedidoMateriaPrima pedidoMateriaPrima,
			PedidoMateriaPrimaItemVO pedidoMateriaPrimaItemVO) {
		PedidoMateriaPrimaItem pmpi = new PedidoMateriaPrimaItem();
		pmpi.setPedidoMateriaPrima(pedidoMateriaPrima);
		pmpi.setMateriaPrima(MateriaPrima
				.toMateriaPrima(pedidoMateriaPrimaItemVO.getMateriaPrima()));
		pmpi.setCantidad(pedidoMateriaPrimaItemVO.getCantidad());
		return pmpi;
	}
}
