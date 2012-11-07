package proveedor.documentos;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Remito de Materia Prima, se manda cuando el proveedorMateriaPrima manda la
 * materiaPrima pedida
 */
public class MatPri {

	// TODO Implementar MatPri
	
	public synchronized static MatPri deserialize(String s) {
		XStream xs = new XStream(new DomDriver());
		// xs.alias("ListaDePrecios", LiPre.class);
		// xs.alias("Rodamiento", Rodamiento.class);
		xs.addDefaultImplementation(java.sql.Date.class, java.util.Date.class);
		xs.addDefaultImplementation(java.sql.Timestamp.class, java.util.Date.class);
		xs.addDefaultImplementation(java.sql.Time.class, java.util.Date.class);
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return (MatPri) xs.fromXML(s);
	}

	public synchronized String serialize() {
		XStream xs = new XStream(new DomDriver());
		// xs.alias("ListaDePrecios", LiPre.class);
		// xs.alias("Rodamiento", Rodamiento.class);
		xs.addDefaultImplementation(java.sql.Date.class, java.util.Date.class);
		xs.addDefaultImplementation(java.sql.Timestamp.class, java.util.Date.class);
		xs.addDefaultImplementation(java.sql.Time.class, java.util.Date.class);
		xs.registerConverter(new DateConverter("yyyyMMdd", new String[12]));
		return xs.toXML(this);
	}
}
