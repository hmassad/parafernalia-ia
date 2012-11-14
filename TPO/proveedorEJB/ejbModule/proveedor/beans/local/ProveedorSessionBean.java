package proveedor.beans.local;

import javax.ejb.Stateless;

import proveedor.configuration.Configuration;
import proveedor.vo.ProveedorVO;

@Stateless
public class ProveedorSessionBean implements ProveedorSessionBeanLocal {

	public ProveedorSessionBean() {
	}

	public ProveedorVO getProveedor() {
		return Configuration.Proveedor;
	}
}
