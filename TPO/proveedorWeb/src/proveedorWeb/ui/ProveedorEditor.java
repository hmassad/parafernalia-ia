package proveedorWeb.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proveedor.vo.ProveedorVO;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProveedorEditor extends Panel {

	public interface SaveListener extends Serializable {
		public void save(SaveEvent event);
	}

	public class SaveEvent implements Serializable {

		final ProveedorEditor proveedorEditor;

		public SaveEvent(ProveedorEditor proveedorEditor) {
			this.proveedorEditor = proveedorEditor;
		}

		public ProveedorEditor getProveedorEditor() {
			return proveedorEditor;
		}

		public ProveedorVO getProveedor() {
			return proveedorEditor.getProveedor();
		}
	}

	private void fireSaveEvent() {
		if (saveListeners != null) {
			SaveEvent event = new SaveEvent(ProveedorEditor.this);
			for (SaveListener listener : saveListeners) {
				listener.save(event);
			}
		}
	}

	public void addListener(SaveListener listener) {
		if (saveListeners == null) {
			saveListeners = new ArrayList<SaveListener>();
		}
		saveListeners.add(listener);
	}

	public void removeListener(SaveListener listener) {
		if (saveListeners == null) {
			saveListeners = new ArrayList<SaveListener>();
		}
		saveListeners.remove(listener);
	}

	public interface DiscardListener extends Serializable {
		public void discard(DiscardEvent event);
	}

	public class DiscardEvent implements Serializable {

		final ProveedorEditor proveedorEditor;

		public DiscardEvent(ProveedorEditor proveedorEditor) {
			this.proveedorEditor = proveedorEditor;
		}

		public ProveedorEditor getProveedorEditor() {
			return proveedorEditor;
		}
	}

	private void fireDiscardEvent() {
		if (discardListeners != null) {
			DiscardEvent event = new DiscardEvent(ProveedorEditor.this);
			for (DiscardListener listener : discardListeners) {
				listener.discard(event);
			}
		}
	}

	public void addListener(DiscardListener listener) {
		if (discardListeners == null) {
			discardListeners = new ArrayList<DiscardListener>();
		}
		discardListeners.add(listener);
	}

	public void removeListener(DiscardListener listener) {
		if (discardListeners == null) {
			discardListeners = new ArrayList<DiscardListener>();
		}
		discardListeners.remove(listener);
	}

	private List<SaveListener> saveListeners;
	private List<DiscardListener> discardListeners;

	private VerticalLayout fieldsLayout;
	private TextField razonSocialTextField;
	private TextField cuitTextField;
	private TextField direccionTextField;
	private TextField ciudadTextField;
	private TextField provinciaTextField;
	private TextField codigoPostalTextField;
	private TextField telefonoTextField;
	private HorizontalLayout actionsLayout;
	private Button saveButton;
	private Button discardButton;

	public ProveedorEditor() {
		this(null);
	}

	public ProveedorEditor(ProveedorVO proveedor) {
		buildMainLayout();
		setProveedor(proveedor);
	}

	private void buildMainLayout() {
		setSizeFull();

		VerticalLayout mainLayout = new VerticalLayout();
		addComponent(mainLayout);

		mainLayout.setSpacing(true);
		mainLayout.setSizeFull();

		fieldsLayout = new VerticalLayout();
		mainLayout.addComponent(fieldsLayout);
		fieldsLayout.setCaption("Datos de Proveedor");
		fieldsLayout.setSizeFull();
		fieldsLayout.setSpacing(true);

		razonSocialTextField = new TextField();
		fieldsLayout.addComponent(razonSocialTextField);
		razonSocialTextField.setInputPrompt("Razón Social");
		razonSocialTextField.setNullRepresentation("");
		razonSocialTextField.setWidth("250px");

		cuitTextField = new TextField();
		fieldsLayout.addComponent(cuitTextField);
		cuitTextField.setInputPrompt("CUIT");
		cuitTextField.setNullRepresentation("");
		cuitTextField.setWidth("250px");

		telefonoTextField = new TextField();
		fieldsLayout.addComponent(telefonoTextField);
		telefonoTextField.setInputPrompt("Teléfono");
		telefonoTextField.setNullRepresentation("");
		telefonoTextField.setWidth("250px");

		direccionTextField = new TextField();
		fieldsLayout.addComponent(direccionTextField);
		direccionTextField.setInputPrompt("Dirección");
		direccionTextField.setNullRepresentation("");
		direccionTextField.setWidth("250px");

		ciudadTextField = new TextField();
		fieldsLayout.addComponent(ciudadTextField);
		ciudadTextField.setInputPrompt("Ciudad");
		ciudadTextField.setNullRepresentation("");
		ciudadTextField.setWidth("250px");

		provinciaTextField = new TextField();
		fieldsLayout.addComponent(provinciaTextField);
		provinciaTextField.setInputPrompt("Provincia");
		provinciaTextField.setNullRepresentation("");
		provinciaTextField.setWidth("250px");

		codigoPostalTextField = new TextField();
		fieldsLayout.addComponent(codigoPostalTextField);
		codigoPostalTextField.setInputPrompt("Código Postal");
		codigoPostalTextField.setNullRepresentation("");
		codigoPostalTextField.setWidth("250px");

		actionsLayout = new HorizontalLayout();
		mainLayout.addComponent(actionsLayout);

		saveButton = new Button("Guardar");
		actionsLayout.addComponent(saveButton);
		saveButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				fireSaveEvent();
			}
		});

		discardButton = new Button("Cancelar");
		actionsLayout.addComponent(discardButton);
		discardButton.addListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				fireDiscardEvent();
			}
		});
	}

	public ProveedorVO getProveedor() {
		ProveedorVO proveedor = new ProveedorVO();
		proveedor.setCuit(cuitTextField.getValue());
		proveedor.setRazonSocial(razonSocialTextField.getValue());
		proveedor.setTelefono(telefonoTextField.getValue());
		proveedor.setDireccion(direccionTextField.getValue());
		proveedor.setCiudad(ciudadTextField.getValue());
		proveedor.setProvincia(provinciaTextField.getValue());
		proveedor.setCodigoPostal(codigoPostalTextField.getValue());
		return proveedor;
	}

	public void setProveedor(ProveedorVO proveedor) {
		if (proveedor == null) {
			cuitTextField.setValue(null);
			razonSocialTextField.setValue(null);
			telefonoTextField.setValue(null);
			direccionTextField.setValue(null);
			ciudadTextField.setValue(null);
			provinciaTextField.setValue(null);
			codigoPostalTextField.setValue(null);
		} else {
			cuitTextField.setValue(proveedor.getCuit());
			razonSocialTextField.setValue(proveedor.getRazonSocial());
			telefonoTextField.setValue(proveedor.getTelefono());
			direccionTextField.setValue(proveedor.getDireccion());
			ciudadTextField.setValue(proveedor.getCiudad());
			provinciaTextField.setValue(proveedor.getProvincia());
			codigoPostalTextField.setValue(proveedor.getCodigoPostal());
		}
	}

}
