package proveedorWeb.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;
import proveedor.vo.ProductoVO;
import proveedorWeb.ejb.ProveedorClient;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

// TODO arreglar DateField de vigenciaDesde y vigenciaHasta
// TODO cambiar el editor por un gridlayout con checkbox (incluir o no), label (codigo/descripcion), textfield (precio)

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

		vigenciaDesdeDateField = new DateField();
		fieldsLayout.addComponent(vigenciaDesdeDateField);
		vigenciaDesdeDateField.setCaption("Vigencia Desde");
		vigenciaDesdeDateField.setWidth("362px");

		vigenciaHastaDateField = new DateField();
		fieldsLayout.addComponent(vigenciaHastaDateField);
		vigenciaHastaDateField.setCaption("Vigencia Hasta");
		vigenciaHastaDateField.setWidth("362px");

		VerticalLayout productosContainer = new VerticalLayout();
		mainLayout.addComponent(productosContainer);

		listaPreciosItemLayout = new GridLayout(3, 1);
		productosContainer.addComponent(listaPreciosItemLayout);
		listaPreciosItemLayout.setSpacing(true);

		Button addProductoButton = new Button("Agregar Producto");
		productosContainer.addComponent(addProductoButton);
		addProductoButton.setStyleName(BaseTheme.BUTTON_LINK);
		addProductoButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				addListaPreciosItem(null);
				try {
					((Focusable) listaPreciosItemLayout.getComponent(0,
							listaPreciosItemLayout.getRows() - 1)).focus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void addListaPreciosItem(ListaPreciosItemVO listaPreciosItemVO) {
		int row = listaPreciosItemLayout.getRows();
		if (row == 1) {
			if (listaPreciosItemLayout.getComponent(0, 0) == null)
				row = 0;
		}
		if (row >= listaPreciosItemLayout.getRows())
			listaPreciosItemLayout.setRows(row + 1);

		final ComboBox productoComboBox = new ComboBox();
		listaPreciosItemLayout.addComponent(productoComboBox, 0, row);
		productoComboBox.setImmediate(true);
		productoComboBox.setInputPrompt("Producto");
		productoComboBox.setWidth("100px");

		try {
			for (ProductoVO pVO : ProveedorClient.get().getProductos()) {
				productoComboBox.addItem(pVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (listaPreciosItemVO != null) {
			ProductoVO productoVO = listaPreciosItemVO.getProducto();
			if (productoVO != null) {
				if (productoComboBox.getItem(productoVO) == null)
					productoComboBox.addItem(productoVO);
				productoComboBox.setValue(productoVO);
			}
		}

		TextField precioTextField = new TextField();
		listaPreciosItemLayout.addComponent(precioTextField, 1, row);
		precioTextField.setNullRepresentation("");
		precioTextField.setWidth("250px");
		if (listaPreciosItemVO != null)
			precioTextField.setValue(listaPreciosItemVO.getPrecioUnitario());

		Button deleteButton = new Button();
		listaPreciosItemLayout.addComponent(deleteButton, 2, row);
		listaPreciosItemLayout.setComponentAlignment(deleteButton,
				Alignment.MIDDLE_LEFT);
		deleteButton.setDescription("Borrar producto");
		deleteButton.setIcon(new ThemeResource("images/trash-16x16.png"));
		deleteButton.setStyleName(BaseTheme.BUTTON_LINK);
		deleteButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (listaPreciosItemLayout.getRows() == 1) {
					listaPreciosItemLayout.removeRow(0);
					addListaPreciosItem(null);
					try {
						((Focusable) listaPreciosItemLayout.getComponent(0,
								listaPreciosItemLayout.getRows() - 1)).focus();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					for (int i = 0; i < listaPreciosItemLayout.getRows(); i++) {
						if (listaPreciosItemLayout.getComponent(2, i) == event
								.getButton()) {
							listaPreciosItemLayout.removeRow(i);
							break;
						}
					}
				}
			}
		});
	}

	public ListaPreciosVO getListaPrecios() {
		ListaPreciosVO listaPrecios = new ListaPreciosVO();
		listaPrecios.setVigenciaDesde(vigenciaDesdeDateField.getValue());
		listaPrecios.setVigenciaHasta(vigenciaHastaDateField.getValue());
		for (int i = 0; i < listaPreciosItemLayout.getRows(); i++) {
			try {
				ProductoVO productoVO = (ProductoVO) ((ComboBox) listaPreciosItemLayout
						.getComponent(0, i)).getValue();
				Float precio = null;
				try {
					precio = new Float(
							Float.parseFloat(((TextField) listaPreciosItemLayout
									.getComponent(1, i)).getValue()));
				} catch (NumberFormatException ex) {
					// leave precio null, the string is invalid
				}
				if (productoVO != null && precio != null) {
					listaPrecios.getItems().add(
							new ListaPreciosItemVO(productoVO, precio));
				}
			} catch (NullPointerException e) {
				// HACK GridLayout no tiene filas.
				break;
			}
		}
		return listaPrecios;
	}

	public void setListaPrecios(ListaPreciosVO listaPrecios) {
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
				addListaPreciosItem(null);
			} else {
				for (ListaPreciosItemVO lpiVO : listaPrecios.getItems()) {
					addListaPreciosItem(lpiVO);
				}
			}
		}
	}

}
