package proveedor.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import proveedor.vo.MateriaPrimaProductoVO;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "pk.producto", joinColumns = @JoinColumn(name = "producto", nullable = false)),
		@AssociationOverride(name = "pk.materiaPrima", joinColumns = @JoinColumn(name = "materiaPrima", nullable = false)) })
public class MateriaPrimaProducto {

	private MateriaPrimaProductoPk pk;

	private int cantidad;

	public MateriaPrimaProducto() {
		pk = new MateriaPrimaProductoPk();
	}

	public MateriaPrimaProducto(Producto producto, MateriaPrima materiaPrima,
			int cantidad) {
		pk = new MateriaPrimaProductoPk(producto, materiaPrima);
		this.cantidad = cantidad;
	}

	@EmbeddedId
	public MateriaPrimaProductoPk getPk() {
		return pk;
	}

	public void setPk(MateriaPrimaProductoPk pk) {
		this.pk = pk;
	}

	@Transient
	public Producto getProducto() {
		return pk.getProducto();
	}

	public void setProducto(Producto producto) {
		pk.setProducto(producto);
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
		MateriaPrimaProducto other = (MateriaPrimaProducto) obj;
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
		return String.format("%s %d", getMateriaPrima().getCodigo(),
				getCantidad());
	}

	public static MateriaPrimaProductoVO toMateriaPrimaProductoVO(
			MateriaPrimaProducto materiaPrimaProducto) {
		return new MateriaPrimaProductoVO(
				MateriaPrima.toMateriaPrimaVO(materiaPrimaProducto
						.getMateriaPrima()), materiaPrimaProducto.getCantidad());
	}

	public static MateriaPrimaProducto toMateriaPrimaProducto(
			Producto producto, MateriaPrimaProductoVO materiaPrimaProductoVO) {
		return new MateriaPrimaProducto(producto,
				MateriaPrima.toMateriaPrima(materiaPrimaProductoVO
						.getMateriaPrima()),
				materiaPrimaProductoVO.getCantidad());
	}
}
