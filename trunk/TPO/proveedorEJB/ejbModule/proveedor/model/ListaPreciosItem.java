package proveedor.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
@AssociationOverrides({
	@AssociationOverride(name = "pk.listaPrecios", joinColumns = { @JoinColumn(name = "listaPrecios", nullable = false) }),
	@AssociationOverride(name = "pk.producto", joinColumns = { @JoinColumn(name = "produco",nullable = false) })
})
public class ListaPreciosItem implements Serializable {

	private static final long serialVersionUID = 6783081308724466103L;

	@EmbeddedId
	private ListaPreciosItemPk pk;

	@Transient
	private ListaPrecios listaPrecios;

	@Transient
	private Producto producto;

	@Column(name = "PrecioUnitario")
	private float precioUnitario;

	public ListaPreciosItem() {
	}

	public ListaPreciosItem(Producto producto, float precioUntario) {
		this.producto = producto;
		this.precioUnitario = precioUntario;
	}

	public ListaPreciosItemPk getPk() {
		return pk;
	}

	public void setPk(ListaPreciosItemPk pk) {
		this.pk = pk;
	}

	public ListaPrecios getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(ListaPrecios listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaPrecios == null) ? 0 : listaPrecios.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + Float.floatToIntBits(precioUnitario);
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
		ListaPreciosItem other = (ListaPreciosItem) obj;
		if (listaPrecios == null) {
			if (other.listaPrecios != null)
				return false;
		} else if (!listaPrecios.equals(other.listaPrecios))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (Float.floatToIntBits(precioUnitario) != Float.floatToIntBits(other.precioUnitario))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s: $%.02f", getProducto(), getPrecioUnitario());
	}
	
	
}
