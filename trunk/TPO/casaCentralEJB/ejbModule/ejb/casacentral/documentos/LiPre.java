package ejb.casacentral.documentos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LiPre implements Serializable {

	private static final long serialVersionUID = -373736142287574683L;

	public static class Proveedor implements Serializable {

		private static final long serialVersionUID = 5124821825130245020L;

		private String cuit;

		private String razonSocial;

		private String telefono;

		private String direccion;

		private String ciudad;

		private String provincia;

		private String cod_postal;

		public Proveedor() {
		}

		public Proveedor(String cuit, String razonSocial, String telefono,
				String direccion, String ciudad, String provincia,
				String cod_postal) {
			this.cuit = cuit;
			this.razonSocial = razonSocial;
			this.telefono = telefono;
			this.direccion = direccion;
			this.ciudad = ciudad;
			this.provincia = provincia;
			this.cod_postal = cod_postal;
		}

		public String getCuit() {
			return cuit;
		}

		public void setCuit(String cuit) {
			this.cuit = cuit;
		}

		public String getRazonSocial() {
			return razonSocial;
		}

		public void setRazonSocial(String razonSocial) {
			this.razonSocial = razonSocial;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getCiudad() {
			return ciudad;
		}

		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}

		public String getProvincia() {
			return provincia;
		}

		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}

		public String getCod_postal() {
			return cod_postal;
		}

		public void setCod_postal(String cod_postal) {
			this.cod_postal = cod_postal;
		}
	}

	public static class Rodamiento implements Serializable {

		private static final long serialVersionUID = 2941310228731892480L;

		private String codigoRodamiento;

		private float precio;

		public Rodamiento() {
		}

		public Rodamiento(String codigoRodamiento, float precio) {
			this.codigoRodamiento = codigoRodamiento;
			this.precio = precio;
		}

		public String getCodigoRodamiento() {
			return codigoRodamiento;
		}

		public void setCodigoRodamiento(String codigoRodamiento) {
			this.codigoRodamiento = codigoRodamiento;
		}

		public float getPrecio() {
			return precio;
		}

		public void setPrecio(float precio) {
			this.precio = precio;
		}
	}

	private int nroLista;

	private Proveedor proveedor;

	private String vigenciaDesde;

	private String vigenciaHasta;

	private List<Rodamiento> itemsLP;

	public LiPre() {
		itemsLP = new ArrayList<Rodamiento>();
	}

	public LiPre(int nroLista, Proveedor proveedor, Date vigenciaDesde,
			Date vigenciaHasta, List<Rodamiento> itemsLP) {
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		this.nroLista = nroLista;
		this.proveedor = proveedor;
		if (vigenciaDesde != null)
			this.vigenciaDesde = sdf.format(vigenciaDesde);
		if (vigenciaHasta != null)
			this.vigenciaHasta = sdf.format(vigenciaHasta);
		this.itemsLP = itemsLP;
	}

	public int getNroLista() {
		return nroLista;
	}

	public void setNroLista(int nroLista) {
		this.nroLista = nroLista;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Date getVigenciaDesde() {
		try {
			DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if (vigenciaDesde != null)
				return sdf.parse(vigenciaDesde);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (vigenciaDesde != null)
			this.vigenciaDesde = sdf.format(vigenciaDesde);
	}

	public Date getVigenciaHasta() {
		try {
			DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			if (vigenciaHasta != null)
				return sdf.parse(vigenciaHasta);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (vigenciaHasta != null)
			this.vigenciaHasta = sdf.format(vigenciaHasta);
	}

	public List<Rodamiento> getItemsLP() {
		return itemsLP;
	}

	public void setItemsLP(List<Rodamiento> itemsLP) {
		this.itemsLP = itemsLP;
	}

	public synchronized static LiPre deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("ListaDePrecios", LiPre.class);
		xs.alias("Rodamiento", Rodamiento.class);
		return (LiPre) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("ListaDePrecios", LiPre.class);
		xs.alias("Rodamiento", Rodamiento.class);
		return xs.toXML(this);
	}
}
