package proveedor.vo;

import proveedor.documentos.OrCompCCAceptada;

public class OrCompCCAceptadaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OrCompCCAceptada orCompCCAceptada1 = new OrCompCCAceptada();

		orCompCCAceptada1.setNroOrdenCompra("caracteristica");

		String xml = orCompCCAceptada1.serialize();

		System.out.println(xml);

		OrCompCCAceptada orCompCCAceptada2 = OrCompCCAceptada.deserialize(xml);

		assert (orCompCCAceptada1 == orCompCCAceptada2);

		System.out.println();
		System.out.println(orCompCCAceptada2.serialize());

	}

}
