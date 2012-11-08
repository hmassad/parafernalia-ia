package materiaPrimaWeb.ui;

import java.util.Collection;

import javax.ejb.EJB;

import materiaPrima.beans.remote.FachadaSessionBeanRemote;
import materiaPrima.vo.PedidoMateriaPrimaVO;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Root;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@Theme("materiaPrima")
@SuppressWarnings("serial")
public class MateriaPrimaRoot extends Root {

	@EJB
	private FachadaSessionBeanRemote fachadaSessionBeanLocal;

	VerticalLayout mainLayout;
	Table materiasPrimasTable;

	public void init(WrappedRequest request) {
		getApplication().setRootPreserved(true);

		getPage().setTitle("MateriaPrima");

		buildLayout();

		fillTable();
	}

	private void buildLayout() {
		mainLayout = new VerticalLayout();
		addComponent(mainLayout);
		mainLayout.setSizeFull();

		final Label tituloLabel = new Label();
		tituloLabel.setStyleName("h1");
		tituloLabel.setValue("Pedidos de Materia Prima");
		mainLayout.addComponent(tituloLabel);

		materiasPrimasTable = new Table();
		mainLayout.addComponent(materiasPrimasTable);
		materiasPrimasTable.setSizeFull();
	}

	// TODO agregar bot�n de entregar a los no entregados
	private void fillTable() {
		try {
			Collection<PedidoMateriaPrimaVO> pedidoMateriaPrimas = fachadaSessionBeanLocal
					.getPedidosMateriaPrima();
			materiasPrimasTable
					.setContainerDataSource(new BeanItemContainer<PedidoMateriaPrimaVO>(
							PedidoMateriaPrimaVO.class, pedidoMateriaPrimas));
			materiasPrimasTable.setVisibleColumns(new String[] { "codigo",
					"descripcion" });
		} catch (Exception e) {
			e.printStackTrace();
			new Notification(
					"No se pueden obtener los pedidos de materia prima",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}
}