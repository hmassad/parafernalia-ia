package proveedor.beans;

import javax.ejb.Local;

import proveedor.vo.ProveedorVO;

@Local
public interface ProveedorSessionBeanLocal {

	void updateProveedor(ProveedorVO proveedor);

	ProveedorVO getProveedor();
}
