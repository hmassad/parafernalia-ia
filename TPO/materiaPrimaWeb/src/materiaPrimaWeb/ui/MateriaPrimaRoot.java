package materiaPrimaWeb.ui;

import java.util.Collection;

import materiaPrima.vo.PedidoMateriaPrimaVO;
import materiaPrimaWeb.ejb.MateriaPrimaClient;

import com.vaadin.annotations.Theme;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Root;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.VerticalLayout;

@Theme("materiaPrima")
@SuppressWarnings("serial")
public class MateriaPrimaRoot extends Root {

	VerticalLayout mainLayout;
	Table table;

	public void init(WrappedRequest request) {
		getApplication().setRootPreserved(true);

		getPage().setTitle("Materia Prima");

		buildLayout();

		fillTable();
	}

	private void buildLayout() {
		mainLayout = new VerticalLayout();
		addComponent(mainLayout);
		mainLayout.setSizeFull();

		final Label tituloLabel = new Label();
		tituloLabel.setStyleName("h1");
		tituloLabel.setValue("Pedidos de Materia Prima");
		mainLayout.addComponent(tituloLabel);

		table = new Table();
		mainLayout.addComponent(table);
		table.setSizeFull();

		table.addContainerProperty("id", Label.class, null, "ID", null,
				Align.CENTER);
		table.addContainerProperty("entregado", Label.class, null, "Entregado",
				null, Align.CENTER);
		table.addContainerProperty("fecha", Label.class, null, "Fecha", null,
				Align.CENTER);
		table.addContainerProperty("accionEntregar", Button.class, null,
				"Entregar", null, Align.CENTER);
	}

	private void fillTable() {
		try {
			Collection<PedidoMateriaPrimaVO> pedidoMateriaPrimas = MateriaPrimaClient
					.get().getPedidosMateriaPrima();

			for (PedidoMateriaPrimaVO pedidoMateriaPrima : pedidoMateriaPrimas) {

				Label idLabel = new Label(Integer.toString(pedidoMateriaPrima
						.getId()));
				Label entregadoLabel = new Label(
						pedidoMateriaPrima.getEntregado() ? "Entregado" : null);
				Label fechaLabel = new Label(pedidoMateriaPrima.getFecha()
						.toString());

				Button btn = new Button("Entregar");
				btn.setEnabled(true);
				btn.setImmediate(true);
				btn.setVisible(true);
				btn.setData(pedidoMateriaPrima.getId());
				btn.addListener(new ClickListener() {
					public void buttonClick(ClickEvent event) {
						try {
							MateriaPrimaClient.get().enviarPedidoMateriaPrima(
									(Integer) event.getButton().getData());
							new Notification("Pedido entregado",
									Notification.TYPE_HUMANIZED_MESSAGE)
									.show(getRoot().getPage());
						} catch (Exception e) {
							new Notification(
									"No se puede entregar el pedido de materia prima",
									e.getMessage(),
									Notification.TYPE_ERROR_MESSAGE)
									.show(getRoot().getPage());
						}
					}
				});

				table.addItem(new Object[] { idLabel, entregadoLabel,
						fechaLabel, btn });
			}
		} catch (Exception e) {
			e.printStackTrace();
			new Notification(
					"No se pueden obtener los pedidos de materia prima",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}
}
