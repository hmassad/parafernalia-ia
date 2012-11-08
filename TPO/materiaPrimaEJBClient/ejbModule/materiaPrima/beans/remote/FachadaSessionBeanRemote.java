package materiaPrima.beans.remote;

import javax.ejb.Remote;

import materiaPrima.beans.local.PedidoMateriaPrimaSessionBeanLocal;

@Remote
public interface FachadaSessionBeanRemote extends PedidoMateriaPrimaSessionBeanLocal {

}
