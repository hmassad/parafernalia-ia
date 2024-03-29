package proveedorWeb.ui;

import java.util.Calendar;
import java.util.Date;

import javax.naming.NamingException;

import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;
import proveedor.vo.ProductoVO;
import proveedorWeb.ejb.ProveedorClient;
import proveedorWeb.ui.ListaPreciosEditor.DiscardEvent;
import proveedorWeb.ui.ListaPreciosEditor.SaveEvent;
import proveedorWeb.ui.ListaPreciosEditor.SaveListener;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ListaPreciosView extends HorizontalLayout implements View {

	ListaPreciosBrowser listaPreciosBrowser;
	ListaPreciosEditor listaPreciosEditor;
	Button addButton;
	Button deleteButton;

	public ListaPreciosView() {
		setSizeFull();

		// ListaPreciosBrowser a la izquierda
		VerticalLayout left = new VerticalLayout();
		left.setSizeFull();
		addComponent(left);
		setExpandRatio(left, 1.0f); // use all available space
		left.setSpacing(true);

		HorizontalLayout actionsContainer = new HorizontalLayout();
		left.addComponent(actionsContainer);
		actionsContainer.setSpacing(true);

		addButton = new Button("Agregar");
		actionsContainer.addComponent(addButton);
		addButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					ListaPreciosVO lp = new ListaPreciosVO();
					Date now = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.DAY_OF_MONTH, 1);
					Date tomorrow = cal.getTime();

					lp.setVigenciaDesde(now);
					lp.setVigenciaHasta(tomorrow);
					for (ProductoVO p : ProveedorClient.get().getProductos()) {
						ListaPreciosItemVO lpi = new ListaPreciosItemVO();
						lpi.setProducto(p);
						lpi.setPrecioUnitario(0);
						lp.getItems().add(lpi);
					}

					listaPreciosBrowser.setEnabled(false);
					listaPreciosEditor.setListaPrecios(lp);
					listaPreciosEditor.setEnabled(true);
					addButton.setEnabled(false);
				} catch (NamingException e) {
					e.printStackTrace();
					new Notification("error", e.getMessage(),
							Notification.TYPE_ERROR_MESSAGE).show(getRoot()
							.getPage());
				}
			}
		});

		deleteButton = new Button("Borrar");
		actionsContainer.addComponent(deleteButton);
		deleteButton.setEnabled(false);
		deleteButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {

				ConfirmDialog.show(getRoot(), "�Borrar Lista de Precios?",
						"Una vez borrado, no se puede volver atr�s.", "Borrar",
						"Cancelar", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed())
									try {
										ListaPreciosVO listaPrecios = listaPreciosBrowser
												.getListaPrecios();
										ProveedorClient.get()
												.deleteListaPrecios(
														listaPrecios.getId());
										resetView();
										new Notification(
												"Lista de Precios borrada",
												Integer.toString(listaPrecios
														.getId()),
												Notification.TYPE_HUMANIZED_MESSAGE)
												.show(getRoot().getPage());
									} catch (Exception e) {
										e.printStackTrace();
										new Notification(
												"No se borr� la lista de precios",
												e.getMessage(),
												Notification.TYPE_ERROR_MESSAGE)
												.show(getRoot().getPage());
									}
								dialog.close();
							}
						});
			}
		});

		listaPreciosBrowser = new ListaPreciosBrowser();
		left.addComponent(listaPreciosBrowser);
		listaPreciosBrowser.setSizeFull();

		// ListaPreciosEditor a la derecha
		listaPreciosEditor = new ListaPreciosEditor(null);
		listaPreciosEditor.setHeight("100%");
		listaPreciosEditor.setWidth(null);
		listaPreciosEditor.getContent().setSizeUndefined();
		addComponent(listaPreciosEditor);
		listaPreciosEditor.setEnabled(false);
		listaPreciosEditor.addListener(new SaveListener() {
			public void save(SaveEvent event) {
				try {
					ProveedorClient.get().createListaPrecios(
							event.getListaPrecios());
					new Notification("Se cre� la lista de precios",
							Notification.TYPE_HUMANIZED_MESSAGE).show(getRoot()
							.getPage());
				} catch (Exception e) {
					new Notification("No se cre� la lista de precios", e
							.getMessage(), Notification.TYPE_ERROR_MESSAGE)
							.show(getRoot().getPage());
				}
				resetView();
			}
		});
		listaPreciosEditor
				.addListener(new ListaPreciosEditor.DiscardListener() {
					public void discard(DiscardEvent event) {
						resetView();
					}
				});
	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		listaPreciosBrowser.setEnabled(true);
		listaPreciosBrowser.refresh();
		listaPreciosEditor.setEnabled(false);
		addButton.setEnabled(true);
	}
}
