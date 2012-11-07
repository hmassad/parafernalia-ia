package proveedor.vo;

import proveedor.documentos.NvoProd;

public class NvoProdTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NvoProd nvoProd1 = new NvoProd();

		nvoProd1.setCaracteristica("caracteristica");
		nvoProd1.setCod_propietario("cod_propietario");
		nvoProd1.setCodigoRodamiento("codigoRodamiento");
		nvoProd1.setMarca("marca");
		nvoProd1.setOrigen("origen");
		nvoProd1.setTipo("tipo");

		String xml = nvoProd1.serialize();

		System.out.println(xml);

		NvoProd nvoProd2 = NvoProd.deserialize(xml);

		assert (nvoProd1 == nvoProd2);

		System.out.println();
		System.out.println(nvoProd2.serialize());
	}

}
