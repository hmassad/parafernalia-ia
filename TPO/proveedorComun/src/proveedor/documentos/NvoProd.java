package proveedor.documentos;

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

	private String cod__propietario;

	public NvoProd() {
	}

	public NvoProd(String codigoRodamiento, String caracteristica, String marca, String origen, String tipo, String cod__propietario) {
		this.codigoRodamiento = codigoRodamiento;
		this.caracteristica = caracteristica;
		this.marca = marca;
		this.origen = origen;
		this.tipo = tipo;
		this.cod__propietario = cod__propietario;
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

	public String getCod__propietario() {
		return cod__propietario;
	}

	public void setCod__propietario(String cod__propietario) {
		this.cod__propietario = cod__propietario;
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
