package proveedor.beans.remote;

import javax.ejb.Remote;

import proveedor.beans.local.ListaPreciosSessionBeanLocal;
import proveedor.beans.local.MateriaPrimaSessionBeanLocal;
import proveedor.beans.local.PedidoCasaCentralSessionBeanLocal;
import proveedor.beans.local.PedidoMateriaPrimaSessionBeanLocal;
import proveedor.beans.local.ProductosSessionBeanLocal;
import proveedor.beans.local.ProveedorSessionBeanLocal;
import proveedor.beans.local.UnidadesSessionBeanLocal;

@Remote
public interface FachadaSessionBeanRemote extends ListaPreciosSessionBeanLocal, MateriaPrimaSessionBeanLocal, ProductosSessionBeanLocal,
		ProveedorSessionBeanLocal, UnidadesSessionBeanLocal, PedidoMateriaPrimaSessionBeanLocal, PedidoCasaCentralSessionBeanLocal {

}
