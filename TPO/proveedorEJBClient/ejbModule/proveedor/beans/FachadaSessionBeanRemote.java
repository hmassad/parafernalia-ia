package proveedor.beans;

import javax.ejb.Remote;

@Remote
public interface FachadaSessionBeanRemote extends ListaPreciosSessionBeanLocal, MateriaPrimaSessionBeanLocal, ProductosSessionBeanLocal,
		ProveedorSessionBeanLocal, UnidadesSessionBeanLocal, PedidoMateriaPrimaSessionBeanLocal, PedidoCasaCentralSessionBeanLocal {

}
