package proveedor.beans.remote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import proveedor.documentos.LiPre;
import proveedor.documentos.LiPre.Proveedor;
import proveedor.documentos.LiPre.Rodamiento;
import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;
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
		ProveedorVO proveedorVO = fachadaSessionBeanRemote.getProveedor();
		if (proveedorVO == null)
			return null;
		
		ListaPreciosVO listaPreciosVO = fachadaSessionBeanRemote
				.getUltimaListaPrecios();
		if (listaPreciosVO == null)
			return null;

		LiPre liPre = new LiPre();
		liPre.setNroLista(listaPreciosVO.getId());
		liPre.setProveedor(new Proveedor(proveedorVO.getCuit(), proveedorVO
				.getRazonSocial(), proveedorVO.getTelefono(), proveedorVO
				.getDireccion(), proveedorVO.getCiudad(), proveedorVO
				.getProvincia(), proveedorVO.getCodigoPostal()));
		liPre.setVigenciaDesde(listaPreciosVO.getVigenciaDesde());
		liPre.setVigenciaHasta(listaPreciosVO.getVigenciaHasta());
		for (ListaPreciosItemVO lpi : listaPreciosVO.getItems()) {
			liPre.getItemsLP().add(
					new Rodamiento(lpi.getProducto().getCodigo(), lpi
							.getPrecioUnitario()));
		}
		return liPre.serialize();
	}

}
