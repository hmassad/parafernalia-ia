package proveedor.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import proveedor.vo.PedidoCasaCentralItemVO;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "pk.pedidoCasaCentral", joinColumns = @JoinColumn(name = "pedidoCasaCentral", nullable = false)),
		@AssociationOverride(name = "pk.producto", joinColumns = @JoinColumn(name = "producto", nullable = false)) })
public class PedidoCasaCentralItem implements Serializable {

	private static final long serialVersionUID = -8938777789442961126L;

	private PedidoCasaCentralItemPk pk;

	private int cantidad;

	public PedidoCasaCentralItem() {
		pk = new PedidoCasaCentralItemPk();
	}

	public PedidoCasaCentralItem(PedidoCasaCentral pedidoCasaCentral,
			Producto producto, int cantidad) {
		this.pk = new PedidoCasaCentralItemPk(pedidoCasaCentral, producto);
		this.cantidad = cantidad;
	}

	@EmbeddedId
	public PedidoCasaCentralItemPk getPk() {
		return pk;
	}

	public void setPk(PedidoCasaCentralItemPk pk) {
		this.pk = pk;
	}

	@Transient
	public PedidoCasaCentral getPedidoCasaCentral() {
		return pk.getPedidoCasaCentral();
	}

	public void setPedidoCasaCentral(PedidoCasaCentral pedidoCasaCentral) {
		pk.setPedidoCasaCentral(pedidoCasaCentral);
	}

	@Transient
	public Producto getProducto() {
		return pk.getProducto();
	}

	public void setProducto(Producto producto) {
		pk.setProducto(producto);
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
		PedidoCasaCentralItem other = (PedidoCasaCentralItem) obj;
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
		return String
				.format("%s: %d", getProducto().getCodigo(), getCantidad());
	}

	public static PedidoCasaCentralItemVO toPedidoCasaCentralItemVO(
			PedidoCasaCentralItem pedidoCasaCentralItem) {
		return new PedidoCasaCentralItemVO(
				Producto.toProductoVO(pedidoCasaCentralItem.getProducto()),
				pedidoCasaCentralItem.getCantidad());
	}

	public static PedidoCasaCentralItem toPedidoCasaCentralItem(
			PedidoCasaCentral pedidoCasaCentral,
			PedidoCasaCentralItemVO pedidoCasaCentralItemVO) {

		PedidoCasaCentralItem pcci = new PedidoCasaCentralItem();
		pcci.setPedidoCasaCentral(pedidoCasaCentral);
		pcci.setProducto(Producto.toProducto(pedidoCasaCentralItemVO
				.getProducto()));
		pcci.setCantidad(pedidoCasaCentralItemVO.getCantidad());
		return pcci;
	}

}
