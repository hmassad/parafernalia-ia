package proveedor.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ListaPreciosItemPk implements Serializable {

	private static final long serialVersionUID = -3594072828811225019L;

	@ManyToOne
	private ListaPrecios listaPrecios;

	@ManyToOne
	private Producto producto;

	public ListaPreciosItemPk() {

	}

	public ListaPreciosItemPk(ListaPrecios listaPrecios, Producto producto) {
		this.listaPrecios = listaPrecios;
		this.producto = producto;
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

	public int hashCode() {
		return listaPrecios.hashCode() + producto.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ListaPreciosItemPk))
			return false;
		ListaPreciosItemPk pk = (ListaPreciosItemPk) obj;
		return pk.listaPrecios == listaPrecios && pk.producto == producto;
	}

}
