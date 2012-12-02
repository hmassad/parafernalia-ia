package ejb.casacentral.documentos;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class OrCompCCAceptada implements Serializable {

	private static final long serialVersionUID = 5435782002153112742L;

	private String nroOrdenCompra;

	public OrCompCCAceptada() {
	}

	public OrCompCCAceptada(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}

	public String getNroOrdenCompra() {
		return nroOrdenCompra;
	}

	public void setNroOrdenCompra(String nroOrdenCompra) {
		this.nroOrdenCompra = nroOrdenCompra;
	}

	public synchronized static OrCompCCAceptada deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("OrdenCompraCCAcep", OrCompCCAceptada.class);
		return (OrCompCCAceptada) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("OrdenCompraCCAcep", OrCompCCAceptada.class);
		return xs.toXML(this);
	}

}
