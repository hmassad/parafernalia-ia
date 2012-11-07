package proveedor.vo;

import proveedor.documentos.MatPri;

public class MatPriTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MatPri matPri1 = new MatPri();
		matPri1.setId(1);
		String xml = matPri1.serialize();

		System.out.println(xml);

		MatPri matPri2 = MatPri.deserialize(xml);

		assert (matPri1 == matPri2);

		System.out.println();

		System.out.println(matPri2.serialize());
	}

}
