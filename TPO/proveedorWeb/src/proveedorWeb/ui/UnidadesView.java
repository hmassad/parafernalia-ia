package proveedorWeb.ui;

import java.util.Collection;

import javax.naming.NamingException;

import proveedor.vo.UnidadVO;
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
public class UnidadesView extends VerticalLayout implements View {

	private HorizontalLayout fieldsLayout;
	private TextField codigoTextField;
	private TextField descripcionTextField;
	private Button addButton;
	private Button deleteButton;
	private Table resultadosTable;

	public UnidadesView() {
		setSizeFull();

		fieldsLayout = new HorizontalLayout();
		addComponent(fieldsLayout);
		fieldsLayout.setSpacing(true);

		codigoTextField = new TextField();
		fieldsLayout.addComponent(codigoTextField);
		codigoTextField.setInputPrompt("C�digo");
		codigoTextField.setNullRepresentation("");
		codigoTextField.setWidth("362px");

		descripcionTextField = new TextField();
		fieldsLayout.addComponent(descripcionTextField);
		descripcionTextField.setInputPrompt("Descripci�n");
		descripcionTextField.setNullRepresentation("");
		descripcionTextField.setWidth("362px");

		addButton = new Button("Agregar");
		fieldsLayout.addComponent(addButton);
		addButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				String codigo = codigoTextField.getValue();
				String descripcion = descripcionTextField.getValue();
				if (codigo != null && descripcion != null) {
					UnidadVO unidadVO = new UnidadVO(codigo, descripcion);
					try {
						ProveedorClient.get().createUnidad(unidadVO);
						new Notification("Se cre� el producto", String.format("c�digo: %s, descripci�n: %s", unidadVO.getCodigo(), unidadVO.getDescripcion()),
								Notification.TYPE_HUMANIZED_MESSAGE).show(getRoot().getPage());
						codigoTextField.setValue("");
						descripcionTextField.setValue("");
					} catch (Exception e) {
						new Notification("No se cre� el producto", e.getMessage(), Notification.TYPE_ERROR_MESSAGE).show(getRoot().getPage());
					}
					resetView();
				}
			}
		});

		deleteButton = new Button("Borrar");
		fieldsLayout.addComponent(deleteButton);
		fieldsLayout.setComponentAlignment(deleteButton, Alignment.MIDDLE_RIGHT);
		deleteButton.setEnabled(false);
		deleteButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {

				ConfirmDialog.show(getRoot(), "�Borrar Unidad?", "Una vez borrada, no se puede volver atr�s.", "Borrar", "Cancelar",
						new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed())
									try {
										UnidadVO unidad = (UnidadVO) resultadosTable.getValue();
										ProveedorClient.get().deleteUnidad(unidad.getCodigo());
										resetView();
										new Notification("Unidad borrada", unidad.getCodigo(), Notification.TYPE_HUMANIZED_MESSAGE).show(getRoot().getPage());
									} catch (Exception e) {
										e.printStackTrace();
										new Notification("No se borr� la unidad", e.getMessage(), Notification.TYPE_ERROR_MESSAGE).show(getRoot().getPage());
									}
								dialog.close();
							}
						});
			}
		});

		resultadosTable = new Table();
		addComponent(resultadosTable);
		resultadosTable.setWidth("100%");
		resultadosTable.setHeight("500px");
		resultadosTable.setSelectable(true);
		resultadosTable.setMultiSelect(false);
		resultadosTable.setImmediate(true);
		resultadosTable.setContainerDataSource(new BeanItemContainer<UnidadVO>(UnidadVO.class, null));
		resultadosTable.setVisibleColumns(new String[] { "codigo", "descripcion" });
		resultadosTable.setColumnHeaders(new String[] { "C�digo", "Descripci�n" });
		resultadosTable.addListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				deleteButton.setEnabled(resultadosTable.getValue() != null);
			}
		});

	}

	public void navigateTo(String fragmentParameters) {
		resetView();
	}

	private void resetView() {
		try {
			Collection<UnidadVO> unidades = ProveedorClient.get().getUnidades();
			resultadosTable.setContainerDataSource(new BeanItemContainer<UnidadVO>(UnidadVO.class, unidades));
			resultadosTable.setVisibleColumns(new String[] { "codigo", "descripcion" });
		} catch (NamingException e) {
			e.printStackTrace();
			new Notification("No se pueden obtener las unidades", e.getMessage(), Notification.TYPE_ERROR_MESSAGE).show(getRoot().getPage());
		}
	}
}
