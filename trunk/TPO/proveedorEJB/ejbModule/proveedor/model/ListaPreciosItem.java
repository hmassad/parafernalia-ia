package proveedor.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ProductoVO;

@Entity
@AssociationOverrides({ @AssociationOverride(name = "pk.listaPrecios", joinColumns = { @JoinColumn(name = "listaPrecios", nullable = false) }),
		@AssociationOverride(name = "pk.producto", joinColumns = { @JoinColumn(name = "produco", nullable = false) }) })
public class ListaPreciosItem implements Serializable {

	private static final long serialVersionUID = 6783081308724466103L;

	@EmbeddedId
	private ListaPreciosItemPk pk;

	@Column(name = "PrecioUnitario")
	private float precioUnitario;

	public ListaPreciosItem() {
		pk = new ListaPreciosItemPk();
	}

	public ListaPreciosItem(ListaPrecios listaPrecios, Producto producto, float precioUntario) {
		pk = new ListaPreciosItemPk(listaPrecios, producto);
		this.precioUnitario = precioUntario;
	}

	public ListaPreciosItemPk getPk() {
		return pk;
	}

	public void setPk(ListaPreciosItemPk pk) {
		this.pk = pk;
	}

	public ListaPrecios getListaPrecios() {
		return pk.getListaPrecios();
	}

	public void setListaPrecios(ListaPrecios listaPrecios) {
		pk.setListaPrecios(listaPrecios);
	}

	public Producto getProducto() {
		return pk.getProducto();
	}

	public void setProducto(Producto producto) {
		pk.setProducto(producto);
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
		result = prime * result + ((pk.getListaPrecios() == null) ? 0 : pk.getListaPrecios().hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + Float.floatToIntBits(precioUnitario);
		result = prime * result + ((pk.getProducto() == null) ? 0 : pk.getProducto().hashCode());
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
		if (pk.getListaPrecios() == null) {
			if (other.pk.getListaPrecios() != null)
				return false;
		} else if (!pk.getListaPrecios().equals(other.pk.getListaPrecios()))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (Float.floatToIntBits(precioUnitario) != Float.floatToIntBits(other.precioUnitario))
			return false;
		if (pk.getProducto() == null) {
			if (other.pk.getProducto() != null)
				return false;
		} else if (!pk.getProducto().equals(other.pk.getProducto()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s: $%.02f", getProducto(), getPrecioUnitario());
	}

	public static ListaPreciosItemVO toListaPreciosItemVO(ListaPreciosItem listaPreciosItem) {
		ProductoVO productoVO = Producto.toProductoVO(listaPreciosItem.getProducto());
		return new ListaPreciosItemVO(productoVO, listaPreciosItem.getPrecioUnitario());
	}

	public static ListaPreciosItem toListaPreciosItem(ListaPrecios listaPrecios, ListaPreciosItemVO listaPreciosItemVO) {
		ListaPreciosItem lpi = new ListaPreciosItem();
		lpi.setListaPrecios(listaPrecios);
		lpi.setProducto(Producto.toProducto(listaPreciosItemVO.getProducto()));
		lpi.setPrecioUnitario(listaPreciosItemVO.getPrecioUnitario());
		return lpi;
	}
}
