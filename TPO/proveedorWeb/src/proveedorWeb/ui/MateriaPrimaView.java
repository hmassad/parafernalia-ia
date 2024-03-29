package proveedorWeb.ui;

import java.util.Collection;

import proveedor.vo.MateriaPrimaVO;
import proveedorWeb.ejb.ProveedorClient;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MateriaPrimaView extends VerticalLayout implements View {

	private HorizontalLayout fieldsLayout;
	private TextField codigoTextField;
	private TextField descripcionTextField;
	private Button addButton;
	private Button deleteButton;
	private Table table;

	public MateriaPrimaView() {
		setSizeFull();

		fieldsLayout = new HorizontalLayout();
		addComponent(fieldsLayout);
		fieldsLayout.setSpacing(true);
		fieldsLayout.setMargin(false, false, true, false);
		fieldsLayout.addStyleName("bordered");

		codigoTextField = new TextField();
		fieldsLayout.addComponent(codigoTextField);
		codigoTextField.setInputPrompt("C�digo");
		codigoTextField.setNullRepresentation("");
		codigoTextField.setWidth("100px");

		descripcionTextField = new TextField();
		fieldsLayout.addComponent(descripcionTextField);
		descripcionTextField.setInputPrompt("Descripci�n");
		descripcionTextField.setNullRepresentation("");
		descripcionTextField.setWidth("100px");

		addButton = new Button("Agregar");
		fieldsLayout.addComponent(addButton);
		addButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				String codigo = codigoTextField.getValue();
				String descripcion = descripcionTextField.getValue();
				if (codigo != null && descripcion != null) {
					MateriaPrimaVO materiaPrimaVO = new MateriaPrimaVO(codigo,
							descripcion, 0);
					try {
						ProveedorClient.get()
								.createMateriaPrima(materiaPrimaVO);
						new Notification(
								"Se cre� la Materia Prima",
								String.format(
										"c�digo: %s, descripci�n: %s; stock: %d",
										materiaPrimaVO.getCodigo(),
										materiaPrimaVO.getDescripcion(),
										materiaPrimaVO.getStock()),
								Notification.TYPE_HUMANIZED_MESSAGE)
								.show(getRoot().getPage());
						codigoTextField.setValue("");
						descripcionTextField.setValue("");
					} catch (Exception e) {
						new Notification("No se cre� la Materia Prima", e
								.getMessage(), Notification.TYPE_ERROR_MESSAGE)
								.show(getRoot().getPage());
					}
					resetView();
				}
			}
		});

		deleteButton = new Button("Borrar");
		fieldsLayout.addComponent(deleteButton);
		fieldsLayout
				.setComponentAlignment(deleteButton, Alignment.MIDDLE_RIGHT);
		deleteButton.setEnabled(false);
		deleteButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {

				ConfirmDialog.show(getRoot(), "�Borrar Materia Prima?",
						"Una vez borrada, no se puede volver atr�s.", "Borrar",
						"Cancelar", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed())
									try {
										MateriaPrimaVO materiaPrima = (MateriaPrimaVO) table
												.getValue();
										ProveedorClient.get()
												.deleteMateriaPrima(
														materiaPrima
																.getCodigo());
										resetView();
										new Notification(
												"Materia Prima borrada",
												materiaPrima.getCodigo(),
												Notification.TYPE_HUMANIZED_MESSAGE)
												.show(getRoot().getPage());
									} catch (Exception e) {
										e.printStackTrace();
										new Notification(
												"No se borr� la Materia Prima",
												e.getMessage(),
												Notification.TYPE_ERROR_MESSAGE)
												.show(getRoot().getPage());
									}
								dialog.close();
							}
						});
			}
		});

		table = new Table();
		addComponent(table);
		table.setWidth("100%");
		table.setHeight("500px");
		table.setSelectable(true);
		table.setMultiSelect(false);
		table.setImmediate(true);
		table.setContainerDataSource(new BeanItemContainer<MateriaPrimaVO>(
				MateriaPrimaVO.class, null));
		table.setVisibleColumns(new String[] { "codigo", "descripcion", "stock" });
		table.setColumnHeaders(new String[] { "C�digo", "Descripci�n", "Stock" });
		table.addListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				deleteButton.setEnabled(table.getValue() != null);
			}
		});

	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		try {
			Collection<MateriaPrimaVO> materiaPrimaVO = ProveedorClient.get()
					.getMateriasPrimas();
			table.setContainerDataSource(new BeanItemContainer<MateriaPrimaVO>(
					MateriaPrimaVO.class, materiaPrimaVO));
			table.setVisibleColumns(new String[] { "codigo", "descripcion",
					"stock" });
		} catch (Exception e) {
			e.printStackTrace();
			new Notification("No se pueden obtener las Materias Primas",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}
}
