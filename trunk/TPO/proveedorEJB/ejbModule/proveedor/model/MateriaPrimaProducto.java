package proveedor.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import proveedor.vo.MateriaPrimaProductoVO;

@Entity
@AssociationOverrides({ @AssociationOverride(name = "pk.producto", joinColumns = { @JoinColumn(name = "producto", nullable = false) }),
		@AssociationOverride(name = "pk.materiaPrima", joinColumns = { @JoinColumn(name = "materiaPrima", nullable = false) }) })
public class MateriaPrimaProducto {

	@EmbeddedId
	private MateriaPrimaProductoPk pk;

	@Column
	private int cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	private Unidad unidad;

	public MateriaPrimaProducto() {
		pk = new MateriaPrimaProductoPk();
	}

	public MateriaPrimaProducto(Producto producto, MateriaPrima materiaPrima, int cantidad, Unidad unidad) {
		pk = new MateriaPrimaProductoPk(producto, materiaPrima);
		this.cantidad = cantidad;
		this.unidad = unidad;
	}

	public MateriaPrimaProductoPk getPk() {
		return pk;
	}

	public void setPk(MateriaPrimaProductoPk pk) {
		this.pk = pk;
	}

	public Producto getProducto() {
		return pk.getProducto();
	}

	public void setProducto(Producto producto) {
		pk.setProducto(producto);
	}

	public MateriaPrima getMateriaPrima() {
		return pk.getMateriaPrima();
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		pk.setMateriaPrima(materiaPrima);
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((unidad == null) ? 0 : unidad.hashCode());
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
		if (unidad == null) {
			if (other.unidad != null)
				return false;
		} else if (!unidad.equals(other.unidad))
			return false;
		return true;
	}

	public String toString() {
		return String.format("%s (%d %s)", getMateriaPrima(), getCantidad(), getUnidad().getCodigo());
	}

	public static MateriaPrimaProductoVO toMateriaPrimaProductoVO(MateriaPrimaProducto materiaPrimaProducto) {
		return new MateriaPrimaProductoVO(MateriaPrima.toMateriaPrimaVO(materiaPrimaProducto.getMateriaPrima()), materiaPrimaProducto.getCantidad(),
				Unidad.toUnidadVO(materiaPrimaProducto.getUnidad()));
	}

	public static MateriaPrimaProducto toMateriaPrimaProducto(Producto producto, MateriaPrimaProductoVO materiaPrimaProductoVO) {
		return new MateriaPrimaProducto(producto, MateriaPrima.toMateriaPrima(materiaPrimaProductoVO.getMateriaPrima()), materiaPrimaProductoVO.getCantidad(),
				Unidad.toUnidad(materiaPrimaProductoVO.getUnidad()));
	}
}
