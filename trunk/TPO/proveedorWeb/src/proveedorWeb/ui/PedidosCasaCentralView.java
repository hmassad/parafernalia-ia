package proveedorWeb.ui;

import java.util.Collection;

import proveedor.vo.PedidoCasaCentralItemVO;
import proveedor.vo.PedidoCasaCentralVO;
import proveedorWeb.ejb.ProveedorClient;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.terminal.gwt.client.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PedidosCasaCentralView extends VerticalLayout implements View {

	private Table table;

	public PedidosCasaCentralView() {
		setSizeFull();

		table = new Table();
		addComponent(table);
		table.setWidth("100%");
		table.setHeight("500px");
		table.setSelectable(false);
		table.setMultiSelect(false);
		table.setImmediate(false);
		table.setContainerDataSource(new BeanItemContainer<PedidoCasaCentralVO>(
				PedidoCasaCentralVO.class, null));
		Table.ColumnGenerator resultadosTableColumnGenerator = new Table.ColumnGenerator() {
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				PedidoCasaCentralVO pedidoCasaCentral = (PedidoCasaCentralVO) itemId;
				StringBuilder sb = new StringBuilder();
				if (columnId.equals("items")) {
					for (PedidoCasaCentralItemVO item : pedidoCasaCentral
							.getItems()) {
						if (sb.length() > 0)
							sb.append("<br/>");
						sb.append(String.format("%d x %s", item.getCantidad(),
								item.getProducto().getCodigo()));
					}
				}
				Label label = new Label(sb.toString());
				label.setContentMode(ContentMode.XHTML);
				return label;
			}
		};
		table.addGeneratedColumn("items", resultadosTableColumnGenerator);
		table.setVisibleColumns(new String[] { "id", "fecha", "entregado",
				"nroOrdenCompra", "items" });
		table.setColumnHeaders(new String[] { "ID", "Fecha", "Entregado",
				"Nro Orden Compra", "Detalle" });

	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		try {
			Collection<PedidoCasaCentralVO> pedidos = ProveedorClient.get()
					.getPedidosCasaCentral();
			table.setContainerDataSource(new BeanItemContainer<PedidoCasaCentralVO>(
					PedidoCasaCentralVO.class, pedidos));
			table.setVisibleColumns(new String[] { "id", "fecha", "entregado",
					"nroOrdenCompra", "items" });
		} catch (Exception e) {
			e.printStackTrace();
			new Notification("No se pueden obtener los Pedidos",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}
}
