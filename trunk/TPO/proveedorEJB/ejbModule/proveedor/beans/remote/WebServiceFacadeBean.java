package proveedor.beans.remote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import proveedor.documentos.LiPre;
import proveedor.documentos.LiPre.Proveedor;
import proveedor.documentos.LiPre.Rodamiento;
import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;
import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.ProductoVO;
import proveedor.vo.ProveedorVO;

/**
 * Session Bean implementation class PublicacionListaPreciosSessionBean
 */
@Stateless
@WebService(targetNamespace = "http://servicios/")
public class WebServiceFacadeBean implements WebServiceFacade {

	@EJB
	FachadaSessionBeanRemote fachadaSessionBeanRemote;

	public WebServiceFacadeBean() {
	}

	@WebMethod
	public String getListaPrecios() {
		// ProveedorVO proveedorVO = fachadaSessionBeanRemote.getProveedor();
		// if (proveedorVO == null)
		// return null;
		// ListaPreciosVO listaPreciosVO =
		// fachadaSessionBeanRemote.getUltimaListaPrecios();
		// if (listaPreciosVO == null)
		// return null;

		ProveedorVO proveedorVO = new ProveedorVO("cuit", "razonSocial", "telefono", "direccion", "ciudad", "provincia", "codigoPostal");

		Collection<ListaPreciosItemVO> listaPreciosItemVOs = new ArrayList<ListaPreciosItemVO>();
		listaPreciosItemVOs.add(new ListaPreciosItemVO(new ProductoVO("codigo1", "descripcion1", "caractaristica1", "marca1", "origen1", "tipo1", "medida1",
				new ArrayList<MateriaPrimaProductoVO>()), 1));
		listaPreciosItemVOs.add(new ListaPreciosItemVO(new ProductoVO("codigo2", "descripcion2", "caractaristica2", "marca2", "origen2", "tipo2", "medida2",
				new ArrayList<MateriaPrimaProductoVO>()), 2));
		listaPreciosItemVOs.add(new ListaPreciosItemVO(new ProductoVO("codigo3", "descripcion3", "caractaristica3", "marca3", "origen3", "tipo3", "medida3",
				new ArrayList<MateriaPrimaProductoVO>()), 3));
		listaPreciosItemVOs.add(new ListaPreciosItemVO(new ProductoVO("codigo4", "descripcion4", "caractaristica4", "marca4", "origen4", "tipo4", "medida4",
				new ArrayList<MateriaPrimaProductoVO>()), 4));
		ListaPreciosVO listaPreciosVO = new ListaPreciosVO(1, new Date(), new Date(), listaPreciosItemVOs);

		LiPre liPre = new LiPre();
		liPre.setNroLista(listaPreciosVO.getId());
		liPre.setProveedor(new Proveedor(proveedorVO.getCuit(), proveedorVO.getRazonSocial(), proveedorVO.getTelefono(), proveedorVO.getDireccion(),
				proveedorVO.getCiudad(), proveedorVO.getProvincia(), proveedorVO.getCodigoPostal()));
		liPre.setVigenciaDesde(listaPreciosVO.getVigenciaDesde());
		liPre.setVigenciaHasta(listaPreciosVO.getVigenciaHasta());
		for (ListaPreciosItemVO lpi : listaPreciosVO.getItems()) {
			liPre.getItemsLP().add(
					new Rodamiento(lpi.getProducto().getCodigo(), lpi.getProducto().getCaracteristica(), lpi.getProducto().getMarca(), lpi.getProducto()
							.getOrigen(), lpi.getProducto().getTipo(), lpi.getProducto().getCodigo(), lpi.getProducto().getMedida(), lpi.getPrecioUnitario()));
		}
		return liPre.serialize();
	}

}
