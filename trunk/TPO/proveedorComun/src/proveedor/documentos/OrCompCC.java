package proveedor.documentos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class OrCompCC implements Serializable {

	private static final long serialVersionUID = -2044527635260542143L;

	public static class Cliente implements Serializable {

		private static final long serialVersionUID = -2063458233872041252L;

		private String cuit;

		public Cliente() {
		}

		public Cliente(String cuit) {
			this.cuit = cuit;
		}

		public String getCuit() {
			return cuit;
		}

		public void setCuit(String cuit) {
			this.cuit = cuit;
		}

	}

	public static class ItemOrdenCompraCC implements Serializable {

		public static class Rodamiento implements Serializable {

			private static final long serialVersionUID = 3621996720644983490L;

			private String codigoRodamiento;

			public Rodamiento() {
			}

			public Rodamiento(String codigoRodamiento) {
				this.codigoRodamiento = codigoRodamiento;
			}

			public String getCodigoRodamiento() {
				return codigoRodamiento;
			}

			public void setCodigoRodamiento(String codigoRodamiento) {
				this.codigoRodamiento = codigoRodamiento;
			}

		}

		private static final long serialVersionUID = 2941310228731892480L;

		private String nroItem;

		private Rodamiento rodamiento;

		private int cantidad;

		public ItemOrdenCompraCC() {
		}

		public String getNroItem() {
			return nroItem;
		}

		public void setNroItem(String nroItem) {
			this.nroItem = nroItem;
		}

		public Rodamiento getRodamiento() {
			return rodamiento;
		}

		public void setRodamiento(Rodamiento rodamiento) {
			this.rodamiento = rodamiento;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
	}

	private Date fecha;

	private String nroOrdenCompra;

	private Cliente cliente;

	private Collection<ItemOrdenCompraCC> itemsOCCC;

	public OrCompCC() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}

	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setProveedor(Cliente cliente) {
		this.cliente = cliente;
	}

	public Collection<ItemOrdenCompraCC> getItemsOCCC() {
		return itemsOCCC;
	}

	public void setItemsOCCC(Collection<ItemOrdenCompraCC> itemsOCCC) {
		this.itemsOCCC = itemsOCCC;
	}

	public synchronized static OrCompCC deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("OrdenCompraCC", OrCompCC.class);
		return (OrCompCC) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("OrdenCompraCC", OrCompCC.class);
		return xs.toXML(this);
	}
}
