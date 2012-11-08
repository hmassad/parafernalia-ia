package proveedorWeb.ui;

import proveedor.vo.ProductoVO;
import proveedorWeb.ejb.ProveedorClient;
import proveedorWeb.ui.ProductoEditor.DiscardEvent;
import proveedorWeb.ui.ProductoEditor.SaveEvent;
import proveedorWeb.ui.ProductoEditor.SaveListener;
import proveedorWeb.ui.ProductosBrowser.ProductoChangeEvent;
import proveedorWeb.ui.ProductosBrowser.ProductoChangeListener;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProductosView extends HorizontalLayout implements View {

	ProductosBrowser productosBrowser;
	ProductoEditor productoEditor;
	Button addButton;
	Button deleteButton;

	public ProductosView() {
		setSizeFull();

		// ProductosBrowser a la izquierda
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
				productosBrowser.setEnabled(false);
				productoEditor.setProducto(null);
				productoEditor.setEnabled(true);
				addButton.setEnabled(false);
			}
		});

		deleteButton = new Button("Borrar");
		actionsContainer.addComponent(deleteButton);
		deleteButton.setEnabled(false);
		deleteButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {

				ConfirmDialog.show(getRoot(), "¿Borrar Produucto?",
						"Una vez borrado, no se puede volver atrás.", "Borrar",
						"Cancelar", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed())
									try {
										ProductoVO producto = productosBrowser
												.getProducto();
										ProveedorClient.get().deleteProducto(
												producto.getCodigo());
										resetView();
										new Notification(
												"Producto borrado",
												producto.getCodigo(),
												Notification.TYPE_HUMANIZED_MESSAGE)
												.show(getRoot().getPage());
									} catch (Exception e) {
										e.printStackTrace();
										new Notification(
												"No se borró el producto", e
														.getMessage(),
												Notification.TYPE_ERROR_MESSAGE)
												.show(getRoot().getPage());
									}
								dialog.close();
							}
						});
			}
		});

		productosBrowser = new ProductosBrowser();
		left.addComponent(productosBrowser);
		productosBrowser.setSizeFull();
		productosBrowser.addListener(new ProductoChangeListener() {
			public void productoChanged(ProductoChangeEvent event) {
				ProductoVO producto = event.getProducto();
				productoEditor.setProducto(producto);
				productoEditor.setEnabled(producto != null);
				deleteButton.setEnabled(producto != null);
			}
		});

		// ProductoEditor a la derecha
		productoEditor = new ProductoEditor(null);
		productoEditor.setHeight("100%");
		productoEditor.setWidth(null);
		productoEditor.getContent().setSizeUndefined();
		addComponent(productoEditor);
		productoEditor.setEnabled(false);
		productoEditor.addListener(new SaveListener() {
			public void save(SaveEvent event) {
				try {
					ProveedorClient.get().createProducto(event.getProducto());
					new Notification("Se creó el producto", event.getProducto()
							.getCodigo(), Notification.TYPE_HUMANIZED_MESSAGE)
							.show(getRoot().getPage());
				} catch (Exception e) {
					new Notification("No se creó el producto", e.getMessage(),
							Notification.TYPE_ERROR_MESSAGE).show(getRoot()
							.getPage());
				}
				resetView();
			}
		});
		productoEditor.addListener(new ProductoEditor.DiscardListener() {
			public void discard(DiscardEvent event) {
				resetView();
			}
		});
	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		productosBrowser.setEnabled(true);
		productosBrowser.refresh();
		productoEditor.setEnabled(false);
		addButton.setEnabled(true);
	}
}
