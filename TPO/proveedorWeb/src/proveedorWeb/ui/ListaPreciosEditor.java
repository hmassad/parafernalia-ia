package proveedorWeb.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ListaPreciosEditor extends Panel {

	public interface SaveListener extends Serializable {
		public void save(SaveEvent event);
	}

	public class SaveEvent implements Serializable {

		final ListaPreciosEditor listaPreciosEditor;

		public SaveEvent(ListaPreciosEditor listaPreciosEditor) {
			this.listaPreciosEditor = listaPreciosEditor;
		}

		public ListaPreciosEditor getListaPreciosEditor() {
			return listaPreciosEditor;
		}

		public ListaPreciosVO getListaPrecios() {
			return listaPreciosEditor.getListaPrecios();
		}
	}

	private void fireSaveEvent() {
		if (saveListeners != null) {
			SaveEvent event = new SaveEvent(ListaPreciosEditor.this);
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

		final ListaPreciosEditor listaPreciosEditor;

		public DiscardEvent(ListaPreciosEditor listaPreciosEditor) {
			this.listaPreciosEditor = listaPreciosEditor;
		}

		public ListaPreciosEditor getListaPreciosEditor() {
			return listaPreciosEditor;
		}
	}

	private void fireDiscardEvent() {
		if (discardListeners != null) {
			DiscardEvent event = new DiscardEvent(ListaPreciosEditor.this);
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

	private ListaPreciosVO listaPrecios;
	private List<SaveListener> saveListeners;
	private List<DiscardListener> discardListeners;

	private VerticalLayout fieldsLayout;
	private DateField vigenciaDesdeDateField;
	private DateField vigenciaHastaDateField;
	private GridLayout listaPreciosItemLayout;
	private HorizontalLayout actionsLayout;
	private Button saveButton;
	private Button discardButton;

	public ListaPreciosEditor() {
		this(null);
	}

	public ListaPreciosEditor(ListaPreciosVO listaPrecios) {
		buildMainLayout();
		setListaPrecios(listaPrecios);
	}

	private void buildMainLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		addComponent(mainLayout);

		mainLayout.setSpacing(true);

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

		fieldsLayout = new VerticalLayout();
		mainLayout.addComponent(fieldsLayout);
		fieldsLayout.setWidth("100%");

		vigenciaDesdeDateField = new DateField();
		fieldsLayout.addComponent(vigenciaDesdeDateField);
		vigenciaDesdeDateField.setCaption("Vigencia Desde");
		vigenciaDesdeDateField.setWidth("362px");
		vigenciaDesdeDateField.setEnabled(true);
		vigenciaDesdeDateField.setDateFormat("dd/MM/yyyy");

		vigenciaHastaDateField = new DateField();
		fieldsLayout.addComponent(vigenciaHastaDateField);
		vigenciaHastaDateField.setCaption("Vigencia Hasta");
		vigenciaHastaDateField.setWidth("362px");
		vigenciaHastaDateField.setEnabled(true);
		vigenciaHastaDateField.setDateFormat("dd/MM/yyyy");

		VerticalLayout productosContainer = new VerticalLayout();
		mainLayout.addComponent(productosContainer);

		listaPreciosItemLayout = new GridLayout(2, 1);
		productosContainer.addComponent(listaPreciosItemLayout);
		listaPreciosItemLayout.setSpacing(true);
	}

	private void addListaPreciosItem(ListaPreciosItemVO listaPreciosItemVO) {
		if (listaPreciosItemVO == null)
			return;

		int row = listaPreciosItemLayout.getRows();
		if (row == 1) {
			if (listaPreciosItemLayout.getComponent(0, 0) == null)
				row = 0;
		}
		if (row >= listaPreciosItemLayout.getRows())
			listaPreciosItemLayout.setRows(row + 1);

		final Label productoLabel = new Label();
		listaPreciosItemLayout.addComponent(productoLabel, 0, row);
		productoLabel.setValue(listaPreciosItemVO.getProducto().getCodigo());

		TextField precioTextField = new TextField();
		listaPreciosItemLayout.addComponent(precioTextField, 1, row);
		precioTextField.setNullRepresentation("");
		precioTextField.setValue(Float.toString(listaPreciosItemVO
				.getPrecioUnitario()));

	}

	public ListaPreciosVO getListaPrecios() {

		if (listaPrecios == null)
			return null;

		try {
			listaPrecios.setVigenciaDesde(vigenciaDesdeDateField.getValue());
			listaPrecios.setVigenciaHasta(vigenciaHastaDateField.getValue());

			for (int i = 0; i < listaPreciosItemLayout.getRows(); i++) {
				for (ListaPreciosItemVO lpi : listaPrecios.getItems()) {
					if (lpi.getProducto()
							.getCodigo()
							.equals(((Label) listaPreciosItemLayout
									.getComponent(0, i)).getValue())) {

						Float precio = null;
						try {
							precio = new Float(
									Float.parseFloat(((TextField) listaPreciosItemLayout
											.getComponent(1, i)).getValue()));
						} catch (NumberFormatException ex) {
							// leave precio null, the string is invalid
						}
						lpi.setPrecioUnitario(precio);

						break;
					}
				}
			}
		} catch (NullPointerException e) {
			// HACK GridLayout no tiene filas.
		}
		return listaPrecios;
	}

	public void setListaPrecios(ListaPreciosVO listaPrecios) {
		this.listaPrecios = listaPrecios;

		for (int i = 0; i < listaPreciosItemLayout.getRows(); i++)
			listaPreciosItemLayout.removeRow(i);
		listaPreciosItemLayout.removeAllComponents();

		if (listaPrecios == null) {
			vigenciaDesdeDateField.setValue(null);
			vigenciaHastaDateField.setValue(null);
			addListaPreciosItem(null);
		} else {
			vigenciaDesdeDateField.setValue(listaPrecios.getVigenciaDesde());
			vigenciaHastaDateField.setValue(listaPrecios.getVigenciaHasta());
			if (listaPrecios.getItems().size() == 0) {
				// addListaPreciosItem(null);
			} else {
				for (ListaPreciosItemVO lpiVO : listaPrecios.getItems()) {
					addListaPreciosItem(lpiVO);
				}
			}
		}
	}

}
