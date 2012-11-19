package proveedorWeb.ui;

import java.util.Collection;

import proveedor.vo.PedidoMateriaPrimaItemVO;
import proveedor.vo.PedidoMateriaPrimaVO;
import proveedorWeb.ejb.ProveedorClient;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.terminal.gwt.client.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class PedidosMateriaPrimaView extends VerticalLayout implements View {

	private Table table;

	public PedidosMateriaPrimaView() {
		setSizeFull();

		table = new Table();
		addComponent(table);
		table.setWidth("100%");
		table.setHeight("500px");
		table.setSelectable(false);
		table.setMultiSelect(false);
		table.setImmediate(false);
		table.setContainerDataSource(new BeanItemContainer<PedidoMateriaPrimaVO>(
				PedidoMateriaPrimaVO.class, null));
		Table.ColumnGenerator resultadosTableColumnGenerator = new Table.ColumnGenerator() {
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				PedidoMateriaPrimaVO pedidoMateriaPrima = (PedidoMateriaPrimaVO) itemId;
				StringBuilder sb = new StringBuilder();
				if (columnId.equals("items")) {
					for (PedidoMateriaPrimaItemVO item : pedidoMateriaPrima
							.getItems()) {
						if (sb.length() > 0)
							sb.append("<br/>");
						sb.append(String.format("%d x %s", item.getCantidad(),
								item.getMateriaPrima().getCodigo()));
					}
				}
				Label label = new Label(sb.toString());
				label.setContentMode(ContentMode.XHTML);
				return label;
			}
		};
		table.addGeneratedColumn("items", resultadosTableColumnGenerator);
		table.setVisibleColumns(new String[] { "id", "fecha", "entregado",
				"items" });
		table.setColumnHeaders(new String[] { "ID", "Fecha", "Entregado",
				"Detalle" });

	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		try {
			Collection<PedidoMateriaPrimaVO> pedidos = ProveedorClient.get()
					.getPedidosMateriaPrima();
			table.setContainerDataSource(new BeanItemContainer<PedidoMateriaPrimaVO>(
					PedidoMateriaPrimaVO.class, pedidos));
			table.setVisibleColumns(new String[] { "id", "fecha", "entregado",
					"items" });
		} catch (Exception e) {
			e.printStackTrace();
			new Notification("No se pueden obtener los Pedidos",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}
}
