package proveedor.beans.local;

import javax.ejb.Local;

import proveedor.vo.ProveedorVO;

@Local
public interface ProveedorSessionBeanLocal {

	ProveedorVO getProveedor();

}
