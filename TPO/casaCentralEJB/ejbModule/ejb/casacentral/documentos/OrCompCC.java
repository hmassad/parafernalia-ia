package ejb.casacentral.documentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public static class Item implements Serializable {

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

		public Item() {
		}

		public Item(String nroItem, Rodamiento rodamiento, int cantidad) {
			this.nroItem = nroItem;
			this.rodamiento = rodamiento;
			this.cantidad = cantidad;
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

	private List<Item> itemsOCCC;

	public OrCompCC() {
		itemsOCCC = new ArrayList<OrCompCC.Item>();
	}

	public OrCompCC(Date fecha, String nroOrdenCompra, Cliente cliente,
			List<Item> itemsOCCC) {
		this.fecha = fecha;
		this.nroOrdenCompra = nroOrdenCompra;
		this.cliente = cliente;
		this.itemsOCCC = itemsOCCC;
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItemsOCCC() {
		return itemsOCCC;
	}

	public void setItemsOCCC(List<Item> itemsOCCC) {
		this.itemsOCCC = itemsOCCC;
	}

	public synchronized static OrCompCC deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("OrdenCompraCC", OrCompCC.class);
		xs.alias("ItemOrdenCompraCC", Item.class);
		return (OrCompCC) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("OrdenCompraCC", OrCompCC.class);
		xs.alias("ItemOrdenCompraCC", Item.class);
		return xs.toXML(this);
	}
}
