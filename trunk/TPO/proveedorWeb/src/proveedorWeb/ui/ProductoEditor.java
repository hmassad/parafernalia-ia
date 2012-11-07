package proveedorWeb.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import proveedor.beans.remote.FachadaSessionBeanRemote;
import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.ProductoVO;
import proveedor.vo.UnidadVO;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class ProductoEditor extends Panel {

	@EJB
	FachadaSessionBeanRemote fachadaSessionBeanRemote;

	public interface SaveListener extends Serializable {
		public void save(SaveEvent event);
	}

	public class SaveEvent implements Serializable {

		final ProductoEditor productoEditor;

		public SaveEvent(ProductoEditor productoEditor) {
			this.productoEditor = productoEditor;
		}

		public ProductoEditor getProductoEditor() {
			return productoEditor;
		}

		public ProductoVO getProducto() {
			return productoEditor.getProducto();
		}
	}

	private void fireSaveEvent() {
		if (saveListeners != null) {
			SaveEvent event = new SaveEvent(ProductoEditor.this);
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

		final ProductoEditor productoEditor;

		public DiscardEvent(ProductoEditor productoEditor) {
			this.productoEditor = productoEditor;
		}

		public ProductoEditor getProductoEditor() {
			return productoEditor;
		}
	}

	private void fireDiscardEvent() {
		if (discardListeners != null) {
			DiscardEvent event = new DiscardEvent(ProductoEditor.this);
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
	private TextField codigoTextField;
	private TextField descripcionTextField;
	private TextField caracteristicaTextField;
	private TextField marcaTextField;
	private TextField origenTextField;
	private GridLayout materiasPrimasProductoLayout;
	private HorizontalLayout actionsLayout;
	private Button saveButton;
	private Button discardButton;

	public ProductoEditor() {
		this(null);
	}

	public ProductoEditor(ProductoVO producto) {
		buildMainLayout();
		setProducto(producto);
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

		codigoTextField = new TextField();
		fieldsLayout.addComponent(codigoTextField);
		codigoTextField.setInputPrompt("Código");
		codigoTextField.setNullRepresentation("");
		codigoTextField.setWidth("362px");

		descripcionTextField = new TextField();
		fieldsLayout.addComponent(descripcionTextField);
		descripcionTextField.setInputPrompt("Descripción");
		descripcionTextField.setNullRepresentation("");
		descripcionTextField.setWidth("362px");

		caracteristicaTextField = new TextField();
		fieldsLayout.addComponent(caracteristicaTextField);
		caracteristicaTextField.setInputPrompt("Característica");
		caracteristicaTextField.setNullRepresentation("");
		caracteristicaTextField.setWidth("362px");

		marcaTextField = new TextField();
		fieldsLayout.addComponent(marcaTextField);
		marcaTextField.setInputPrompt("Marca");
		marcaTextField.setNullRepresentation("");
		marcaTextField.setWidth("362px");

		origenTextField = new TextField();
		fieldsLayout.addComponent(origenTextField);
		origenTextField.setInputPrompt("Origen");
		origenTextField.setNullRepresentation("");
		origenTextField.setWidth("362px");

		VerticalLayout materiasPrimasContainer = new VerticalLayout();
		mainLayout.addComponent(materiasPrimasContainer);

		materiasPrimasProductoLayout = new GridLayout(4, 1);
		materiasPrimasContainer.addComponent(materiasPrimasProductoLayout);
		materiasPrimasProductoLayout.setSpacing(true);

		Button addMateriasPrimasButton = new Button("Agregar Materia Prima");
		materiasPrimasContainer.addComponent(addMateriasPrimasButton);
		addMateriasPrimasButton.setStyleName(BaseTheme.BUTTON_LINK);
		addMateriasPrimasButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				addMateriaPrimaProducto(null);
				try {
					((Focusable) materiasPrimasProductoLayout.getComponent(0,
							materiasPrimasProductoLayout.getRows() - 1))
							.focus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void addMateriaPrimaProducto(
			MateriaPrimaProductoVO materiaPrimaProductoVO) {
		int row = materiasPrimasProductoLayout.getRows();
		if (row == 1) {
			if (materiasPrimasProductoLayout.getComponent(0, 0) == null)
				row = 0;
		}
		if (row >= materiasPrimasProductoLayout.getRows())
			materiasPrimasProductoLayout.setRows(row + 1);

		final ComboBox materiaPrimaComboBox = new ComboBox();
		materiasPrimasProductoLayout.addComponent(materiaPrimaComboBox, 0, row);
		materiaPrimaComboBox.setImmediate(true);
		materiaPrimaComboBox.setInputPrompt("Materia Prima");
		materiaPrimaComboBox.setWidth("100px");

		try {
			for (MateriaPrimaVO mpVO : fachadaSessionBeanRemote
					.getMateriasPrimas()) {
				materiaPrimaComboBox.addItem(mpVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (materiaPrimaProductoVO != null) {
			MateriaPrimaVO materiaPrimaVO = materiaPrimaProductoVO
					.getMateriaPrima();
			if (materiaPrimaVO != null) {
				if (materiaPrimaComboBox.getItem(materiaPrimaVO) == null)
					materiaPrimaComboBox.addItem(materiaPrimaVO);
				materiaPrimaComboBox.setValue(materiaPrimaVO);
			}
		}

		TextField cantidadTextField = new TextField();
		materiasPrimasProductoLayout.addComponent(cantidadTextField, 1, row);
		cantidadTextField.setNullRepresentation("");
		cantidadTextField.setWidth("250px");
		if (materiaPrimaProductoVO != null)
			cantidadTextField.setValue(materiaPrimaProductoVO.getCantidad());

		final ComboBox unidadComboBox = new ComboBox();
		materiasPrimasProductoLayout.addComponent(unidadComboBox, 2, row);
		unidadComboBox.setImmediate(true);
		unidadComboBox.setInputPrompt("Unidad");
		unidadComboBox.setWidth("100px");

		try {
			for (UnidadVO uVO : fachadaSessionBeanRemote.getUnidades()) {
				unidadComboBox.addItem(uVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (materiaPrimaProductoVO != null) {
			UnidadVO unidadVO = materiaPrimaProductoVO.getUnidad();
			if (unidadVO != null) {
				if (unidadComboBox.getItem(unidadVO) == null)
					unidadComboBox.addItem(unidadVO);
				unidadComboBox.setValue(unidadVO);
			}
		}

		Button deleteButton = new Button();
		materiasPrimasProductoLayout.addComponent(deleteButton, 3, row);
		materiasPrimasProductoLayout.setComponentAlignment(deleteButton,
				Alignment.MIDDLE_LEFT);
		deleteButton.setDescription("Borrar materia prima");
		deleteButton.setIcon(new ThemeResource("images/trash-16x16.png"));
		deleteButton.setStyleName(BaseTheme.BUTTON_LINK);
		deleteButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (materiasPrimasProductoLayout.getRows() == 1) {
					materiasPrimasProductoLayout.removeRow(0);
					addMateriaPrimaProducto(null);
					try {
						((Focusable) materiasPrimasProductoLayout.getComponent(
								0, materiasPrimasProductoLayout.getRows() - 1))
								.focus();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					for (int i = 0; i < materiasPrimasProductoLayout.getRows(); i++) {
						if (materiasPrimasProductoLayout.getComponent(3, i) == event
								.getButton()) {
							materiasPrimasProductoLayout.removeRow(i);
							break;
						}
					}
				}
			}
		});
	}

	public ProductoVO getProducto() {
		ProductoVO producto = new ProductoVO();
		producto.setCodigo(codigoTextField.getValue());
		producto.setDescripcion(descripcionTextField.getValue());
		producto.setCaracteristica(caracteristicaTextField.getValue());
		producto.setMarca(marcaTextField.getValue());
		producto.setOrigen(origenTextField.getValue());
		for (int i = 0; i < materiasPrimasProductoLayout.getRows(); i++) {
			try {
				MateriaPrimaVO materiaPrimaVO = (MateriaPrimaVO) ((ComboBox) materiasPrimasProductoLayout
						.getComponent(0, i)).getValue();
				Integer cantidad = null;
				try {
					cantidad = new Integer(
							Integer.parseInt(((TextField) materiasPrimasProductoLayout
									.getComponent(1, i)).getValue()));
				} catch (NumberFormatException ex) {
					// leave cantidad null, the string is invalid
				}
				UnidadVO unidadVO = (UnidadVO) ((ComboBox) materiasPrimasProductoLayout
						.getComponent(2, i)).getValue();
				if (materiaPrimaVO != null && cantidad != null
						&& unidadVO != null) {
					producto.getMateriasPrimasProducto().add(
							new MateriaPrimaProductoVO(materiaPrimaVO,
									cantidad, unidadVO));
				}
			} catch (NullPointerException e) {
				// HACK GridLayout no tiene filas.
				break;
			}
		}
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		for (int i = 0; i < materiasPrimasProductoLayout.getRows(); i++)
			materiasPrimasProductoLayout.removeRow(i);
		materiasPrimasProductoLayout.removeAllComponents();

		if (producto == null) {
			codigoTextField.setValue(null);
			descripcionTextField.setValue(null);
			caracteristicaTextField.setValue(null);
			marcaTextField.setValue(null);
			origenTextField.setValue(null);
			addMateriaPrimaProducto(null);
		} else {
			codigoTextField.setValue(producto.getCodigo());
			descripcionTextField.setValue(producto.getDescripcion());
			caracteristicaTextField.setValue(producto.getCaracteristica());
			marcaTextField.setValue(producto.getMarca());
			origenTextField.setValue(producto.getOrigen());
			if (producto.getMateriasPrimasProducto().size() == 0) {
				addMateriaPrimaProducto(null);
			} else {
				for (MateriaPrimaProductoVO mppVO : producto
						.getMateriasPrimasProducto()) {
					addMateriaPrimaProducto(mppVO);
				}
			}
		}
	}

}
