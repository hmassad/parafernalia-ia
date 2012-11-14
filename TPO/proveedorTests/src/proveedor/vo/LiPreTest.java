package proveedor.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import proveedor.documentos.LiPre;
import proveedor.documentos.LiPre.Proveedor;
import proveedor.documentos.LiPre.Rodamiento;

public class LiPreTest {

	public static void main(String[] args) {
		ProveedorVO proveedorVO = new ProveedorVO("cuit", "razonSocial",
				"telefono", "direccion", "ciudad", "provincia", "codigoPostal");

		Collection<ListaPreciosItemVO> listaPreciosItemVOs = new ArrayList<ListaPreciosItemVO>();
		listaPreciosItemVOs
				.add(new ListaPreciosItemVO(new ProductoVO("codigo1",
						"descripcion1", "caractaristica1", "marca1", "origen1",
						"tipo1", new ArrayList<MateriaPrimaProductoVO>()), 1));
		listaPreciosItemVOs
				.add(new ListaPreciosItemVO(new ProductoVO("codigo2",
						"descripcion2", "caractaristica2", "marca2", "origen2",
						"tipo2", new ArrayList<MateriaPrimaProductoVO>()), 1));
		listaPreciosItemVOs
				.add(new ListaPreciosItemVO(new ProductoVO("codigo3",
						"descripcion3", "caractaristica3", "marca3", "origen3",
						"tipo3", new ArrayList<MateriaPrimaProductoVO>()), 1));
		listaPreciosItemVOs
				.add(new ListaPreciosItemVO(new ProductoVO("codigo4",
						"descripcion4", "caractaristica4", "marca4", "origen4",
						"tipo4", new ArrayList<MateriaPrimaProductoVO>()), 1));
		ListaPreciosVO listaPreciosVO = new ListaPreciosVO(1, new Date(),
				new Date(), listaPreciosItemVOs);

		LiPre liPre1 = new LiPre();
		liPre1.setNroLista(listaPreciosVO.getId());
		liPre1.setProveedor(new Proveedor(proveedorVO.getCuit(), proveedorVO
				.getRazonSocial(), proveedorVO.getTelefono(), proveedorVO
				.getDireccion(), proveedorVO.getCiudad(), proveedorVO
				.getProvincia(), proveedorVO.getCodigoPostal()));
		liPre1.setVigenciaDesde(listaPreciosVO.getVigenciaDesde());
		liPre1.setVigenciaHasta(listaPreciosVO.getVigenciaHasta());
		for (ListaPreciosItemVO lpi : listaPreciosVO.getItems()) {
			liPre1.getItemsLP().add(
					new Rodamiento(lpi.getProducto().getCodigo(), lpi
							.getPrecioUnitario()));
		}
		String xml = liPre1.serialize();

		System.out.println(xml);

		LiPre liPre2 = LiPre.deserialize(xml);

		assert (liPre1 == liPre2);

		System.out.println(liPre2.serialize());
	}
}
