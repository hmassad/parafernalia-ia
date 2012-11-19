package materiaPrima.documentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SolMatPri implements Serializable {

	private static final long serialVersionUID = 8127354232135662637L;

	public static class Item {

		private int id;

		private String codigo;

		private int cantidad;

		public Item() {
		}

		public Item(int id, String codigo, int cantidad) {
			this.id = id;
			this.codigo = codigo;
			this.cantidad = cantidad;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
	}

	private int id;

	private Date fecha;

	private List<Item> items;

	public SolMatPri() {
		items = new ArrayList<SolMatPri.Item>();
	}

	public SolMatPri(int id, Date fecha, List<Item> items) {
		this.id = id;
		this.fecha = fecha;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public synchronized static SolMatPri deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("SolMatPri", SolMatPri.class);
		xs.alias("Item", SolMatPri.Item.class);
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return (SolMatPri) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("SolMatPri", SolMatPri.class);
		xs.alias("Item", SolMatPri.Item.class);
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return xs.toXML(this);
	}

}
