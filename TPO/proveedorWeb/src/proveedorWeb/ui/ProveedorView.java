package proveedorWeb.ui;

import proveedorWeb.ejb.ProveedorClient;
import proveedorWeb.ui.ProveedorEditor.DiscardEvent;
import proveedorWeb.ui.ProveedorEditor.DiscardListener;
import proveedorWeb.ui.ProveedorEditor.SaveEvent;
import proveedorWeb.ui.ProveedorEditor.SaveListener;

import com.vaadin.navigator.View;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProveedorView extends VerticalLayout implements View {

	ProveedorEditor proveedorEditor;

	public ProveedorView() {
		setSizeFull();

		proveedorEditor = new ProveedorEditor(null);
		proveedorEditor.setSizeFull();
		proveedorEditor.getContent().setSizeUndefined();
		addComponent(proveedorEditor);
		proveedorEditor.addListener(new SaveListener() {
			public void save(SaveEvent event) {
				try {
					ProveedorClient.get().updateProveedor(event.getProveedor());
					new Notification("Se guardaron los cambios", event
							.getProveedor().getRazonSocial(),
							Notification.TYPE_HUMANIZED_MESSAGE).show(getRoot()
							.getPage());
				} catch (Exception e) {
					new Notification("No se guardaron los cambios", e
							.getMessage(), Notification.TYPE_ERROR_MESSAGE)
							.show(getRoot().getPage());
				}
				resetView();
			}
		});
		proveedorEditor.addListener(new DiscardListener() {
			public void discard(DiscardEvent event) {
				resetView();
			}
		});
	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		try {
			proveedorEditor.setProveedor(ProveedorClient.get().getProveedor());
		} catch (Exception e) {
			e.printStackTrace();
			new Notification("Ocurrión un error", e.getMessage(),
					Notification.TYPE_ERROR_MESSAGE).show(getRoot().getPage());
		}
	}
}
