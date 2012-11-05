package proveedor.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class MateriaPrimaProductoPk implements Serializable {

	private static final long serialVersionUID = -7655231103229499772L;

	@ManyToOne(fetch = FetchType.LAZY)
	private Producto producto;

	@ManyToOne(fetch = FetchType.LAZY)
	private MateriaPrima materiaPrima;

	public MateriaPrimaProductoPk() {
	}

	public MateriaPrimaProductoPk(Producto producto, MateriaPrima materiaPrima) {
		this.producto = producto;
		this.materiaPrima = materiaPrima;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materiaPrima == null) ? 0 : materiaPrima.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
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
		MateriaPrimaProductoPk other = (MateriaPrimaProductoPk) obj;
		if (materiaPrima == null) {
			if (other.materiaPrima != null)
				return false;
		} else if (!materiaPrima.equals(other.materiaPrima))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}
}
