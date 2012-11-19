package materiaPrima.documentos;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Remito de Materia Prima, se manda cuando el proveedorMateriaPrima manda la
 * materiaPrima pedida
 */
public class MatPri implements Serializable {

	private static final long serialVersionUID = 8127354232135662637L;

	private int id;

	public MatPri() {
	}

	public MatPri(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public synchronized static MatPri deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("MatPri", MatPri.class);
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return (MatPri) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		xs.alias("MatPri", MatPri.class);
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return xs.toXML(this);
	}
}
