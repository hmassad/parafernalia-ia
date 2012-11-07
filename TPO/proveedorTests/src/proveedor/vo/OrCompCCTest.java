package proveedor.vo;

import java.util.Date;

import proveedor.documentos.OrCompCC;
import proveedor.documentos.OrCompCC.Cliente;
import proveedor.documentos.OrCompCC.Item;
import proveedor.documentos.OrCompCC.Item.Rodamiento;

public class OrCompCCTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		OrCompCC orCompCC1 = new OrCompCC();

		orCompCC1.setNroOrdenCompra("nroOrdenCompra1");
		orCompCC1.setFecha(new Date());
		orCompCC1.setCliente(new Cliente("cuit"));

		orCompCC1.getItemsOCCC().add(
				new Item("nroItem1", new Rodamiento(
						"codigoRodamiento1"), 1));
		orCompCC1.getItemsOCCC().add(
				new Item("nroItem2", new Rodamiento(
						"codigoRodamiento2"), 2));
		orCompCC1.getItemsOCCC().add(
				new Item("nroItem3", new Rodamiento(
						"codigoRodamiento3"), 3));

		String xml = orCompCC1.serialize();

		System.out.println(xml);

		OrCompCC orCompCC2 = OrCompCC.deserialize(xml);

		assert (orCompCC1 == orCompCC2);

		System.out.println();
		System.out.println(orCompCC2.serialize());

	}

}
