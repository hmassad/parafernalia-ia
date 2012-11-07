package proveedor.documentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
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

		public Proveedor(String cuit, String razonSocial, String telefono, String direccion, String ciudad, String provincia, String cod_postal) {
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

		private String caracteristica;

		private String marca;

		private String origen;

		private String tipo;

		private String cod_propietario;

		private String medida;

		private float precio;

		public Rodamiento() {
		}

		public Rodamiento(String codigoRodamiento, String caracteristica, String marca, String origen, String tipo, String cod_propietario, String medida,
				float precio) {
			this.codigoRodamiento = codigoRodamiento;
			this.caracteristica = caracteristica;
			this.marca = marca;
			this.origen = origen;
			this.tipo = tipo;
			this.cod_propietario = cod_propietario;
			this.medida = medida;
			this.precio = precio;
		}

		public String getCodigoRodamiento() {
			return codigoRodamiento;
		}

		public void setCodigoRodamiento(String codigoRodamiento) {
			this.codigoRodamiento = codigoRodamiento;
		}

		public String getCaracteristica() {
			return caracteristica;
		}

		public void setCaracteristica(String caracteristica) {
			this.caracteristica = caracteristica;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getOrigen() {
			return origen;
		}

		public void setOrigen(String origen) {
			this.origen = origen;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getCod_propietario() {
			return cod_propietario;
		}

		public void setCod_propietario(String cod_propietario) {
			this.cod_propietario = cod_propietario;
		}

		public String getMedida() {
			return medida;
		}

		public void setMedida(String medida) {
			this.medida = medida;
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

	private Date vigenciaDesde;

	private Date vigenciaHasta;

	private List<Rodamiento> itemsLP;

	public LiPre() {
		itemsLP = new ArrayList<Rodamiento>();
	}

	public LiPre(int nroLista, Proveedor proveedor, Date vigenciaDesde, Date vigenciaHasta, List<Rodamiento> itemsLP) {
		this.nroLista = nroLista;
		this.proveedor = proveedor;
		this.vigenciaDesde = vigenciaDesde;
		this.vigenciaHasta = vigenciaHasta;
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
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
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
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return (LiPre) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("ListaDePrecios", LiPre.class);
		xs.alias("Rodamiento", Rodamiento.class);
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return xs.toXML(this);
	}
}
