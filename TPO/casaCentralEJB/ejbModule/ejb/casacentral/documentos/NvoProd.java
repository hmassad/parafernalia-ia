package ejb.casacentral.documentos;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Nuevo producto, se envía a Casa central cuando se crea un producto
 */
public class NvoProd implements Serializable {

	private static final long serialVersionUID = 6394492200907710647L;

	private String codigoRodamiento;

	private String caracteristica;

	private String marca;

	private String origen;

	private String tipo;

	private String cod_propietario;

	public NvoProd() {
	}

	public NvoProd(String codigoRodamiento, String caracteristica, String marca, String origen, String tipo, String cod_propietario) {
		this.codigoRodamiento = codigoRodamiento;
		this.caracteristica = caracteristica;
		this.marca = marca;
		this.origen = origen;
		this.tipo = tipo;
		this.cod_propietario = cod_propietario;
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

	public String toString() {
		return "NvoProd [codigoRodamiento=" + codigoRodamiento
				+ ", caracteristica=" + caracteristica + ", marca=" + marca
				+ ", origen=" + origen + ", tipo=" + tipo
				+ ", cod_propietario=" + cod_propietario + "]";
	}

	public synchronized static NvoProd deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("Rodamiento", NvoProd.class);
		return (NvoProd) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("Rodamiento", NvoProd.class);
		return xs.toXML(this);
	}
}
