package proveedor.beans;

import javax.ejb.MessageDriven;
import javax.jms.MessageProducer;

/**
 * Message-Driven Bean implementation class for: EntregarOrdenCompra
 *
 */
@MessageDriven(mappedName = "entregaOrdenCompra", messageListenerInterface = MessageProducer.class)
public class EntregarOrdenCompra {

    /**
     * Default constructor. 
     */
    public EntregarOrdenCompra() {
        // TODO Auto-generated constructor stub
    }

}
