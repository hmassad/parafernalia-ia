package proveedorWeb.ui;

import java.util.Collection;

import javax.naming.NamingException;

import proveedor.vo.PedidoCasaCentralVO;
import proveedorWeb.ejb.ProveedorClient;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PedidosCasaCentralView extends VerticalLayout implements View {

	private Table pedidosTable;

	public PedidosCasaCentralView() {
		setSizeFull();

		pedidosTable = new Table();
		addComponent(pedidosTable);
		pedidosTable.setWidth("100%");
		pedidosTable.setHeight("500px");
		pedidosTable.setSelectable(false);
		pedidosTable.setMultiSelect(false);
		pedidosTable.setImmediate(false);
		pedidosTable.setContainerDataSource(new BeanItemContainer<PedidoCasaCentralVO>(PedidoCasaCentralVO.class, null));
		pedidosTable.setVisibleColumns(new String[] { "id" });
		pedidosTable.setColumnHeaders(new String[] { "ID" });

	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		try {
			Collection<PedidoCasaCentralVO> pedidos = ProveedorClient.get().getPedidosCasaCentral();
			pedidosTable.setContainerDataSource(new BeanItemContainer<PedidoCasaCentralVO>(PedidoCasaCentralVO.class, pedidos));
			pedidosTable.setVisibleColumns(new String[] { "ID" });
		} catch (NamingException e) {
			e.printStackTrace();
			new Notification("No se pueden obtener los Pedidos", e.getMessage(), Notification.TYPE_ERROR_MESSAGE).show(getRoot().getPage());
		}
	}
}
