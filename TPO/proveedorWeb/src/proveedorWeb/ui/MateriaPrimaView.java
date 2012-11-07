package proveedorWeb.ui;

import java.util.Collection;

import javax.ejb.EJB;

import proveedor.beans.remote.FachadaSessionBeanRemote;
import proveedor.vo.MateriaPrimaVO;

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

	@EJB
	FachadaSessionBeanRemote fachadaSessionBeanRemote;

	private HorizontalLayout fieldsLayout;
	private TextField codigoTextField;
	private TextField descripcionTextField;
	private Button addButton;
	private Button deleteButton;
	private Table resultadosTable;

	public MateriaPrimaView() {
		setSizeFull();

		fieldsLayout = new HorizontalLayout();
		addComponent(fieldsLayout);
		fieldsLayout.setSpacing(true);

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
						fachadaSessionBeanRemote
								.createMateriaPrima(materiaPrimaVO);
						new Notification(
								"Se creó la Materia Prima",
								String.format(
										"código: %s, descripción: %s; stock: %d",
										materiaPrimaVO.getCodigo(),
										materiaPrimaVO.getDescripcion(),
										materiaPrimaVO.getStock()),
								Notification.TYPE_HUMANIZED_MESSAGE)
								.show(getRoot().getPage());
						codigoTextField.setValue("");
						descripcionTextField.setValue("");
					} catch (Exception e) {
						new Notification("No se creó la Materia Prima", e
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

				ConfirmDialog.show(getRoot(), "¿Borrar Materia Prima?",
						"Una vez borrada, no se puede volver atrás.", "Borrar",
						"Cancelar", new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed())
									try {
										MateriaPrimaVO materiaPrima = (MateriaPrimaVO) resultadosTable
												.getValue();
										fachadaSessionBeanRemote
												.deleteMateriaPrima(materiaPrima
														.getCodigo());
										resetView();
										new Notification(
												"Unidad borrada",
												materiaPrima.getCodigo(),
												Notification.TYPE_HUMANIZED_MESSAGE)
												.show(getRoot().getPage());
									} catch (Exception e) {
										e.printStackTrace();
										new Notification(
												"No se borró la Materia Prima",
												e.getMessage(),
												Notification.TYPE_ERROR_MESSAGE)
												.show(getRoot().getPage());
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
		resultadosTable
				.setContainerDataSource(new BeanItemContainer<MateriaPrimaVO>(
						MateriaPrimaVO.class, null));
		resultadosTable.setVisibleColumns(new String[] { "codigo",
				"descripcion" });
		resultadosTable
				.setColumnHeaders(new String[] { "Código", "Descripción" });
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
			Collection<MateriaPrimaVO> unidades = fachadaSessionBeanRemote
					.getMateriasPrimas();
			resultadosTable
					.setContainerDataSource(new BeanItemContainer<MateriaPrimaVO>(
							MateriaPrimaVO.class, unidades));
			resultadosTable.setVisibleColumns(new String[] { "codigo",
					"descripcion" });
		} catch (Exception e) {
			e.printStackTrace();
			new Notification("No se pueden obtener las unidades",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}
}
