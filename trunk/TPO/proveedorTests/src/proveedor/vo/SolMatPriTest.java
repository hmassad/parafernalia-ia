package proveedor.vo;

import java.util.Date;

import proveedor.documentos.SolMatPri;
import proveedor.documentos.SolMatPri.Item;

public class SolMatPriTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SolMatPri solMatPri1 = new SolMatPri();
		solMatPri1.setId(1);
		solMatPri1.setFecha(new Date());
		solMatPri1.getItems().add(new Item(1, "codigo1", 1, "u1"));
		solMatPri1.getItems().add(new Item(2, "codigo2", 2, "u2"));
		solMatPri1.getItems().add(new Item(3, "codigo3", 3, "u3"));
		solMatPri1.getItems().add(new Item(4, "codigo4", 4, "u4"));
		solMatPri1.getItems().add(new Item(5, "codigo5", 5, "u5"));
		String xml = solMatPri1.serialize();

		System.out.println(xml);

		SolMatPri solMatPri2 = SolMatPri.deserialize(xml);

		assert (solMatPri1 == solMatPri2);

		System.out.println();

		System.out.println(solMatPri2.serialize());
	}

}
