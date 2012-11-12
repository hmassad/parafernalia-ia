package proveedorWeb.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;
import proveedorWeb.ejb.ProveedorClient;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.gwt.client.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ListaPreciosBrowser extends VerticalLayout {

	public interface ListaPreciosChangeListener extends Serializable {
		public void listaPreciosChanged(ListaPreciosChangeEvent event);
	}

	public class ListaPreciosChangeEvent implements Serializable {

		final ListaPreciosBrowser listaPreciosBrowser;

		public ListaPreciosChangeEvent(ListaPreciosBrowser listaPreciosBrowser) {
			this.listaPreciosBrowser = listaPreciosBrowser;
		}

		public ListaPreciosBrowser getListaPreciosBrowser() {
			return listaPreciosBrowser;
		}

		public ListaPreciosVO getListaPrecios() {
			return listaPreciosBrowser.getListaPrecios();
		}
	}

	private void fireListaPreciosChangedEvent() {
		if (listaPreciosChangeListeners != null) {
			ListaPreciosChangeEvent event = new ListaPreciosChangeEvent(
					ListaPreciosBrowser.this);
			for (ListaPreciosChangeListener listener : listaPreciosChangeListeners) {
				listener.listaPreciosChanged(event);
			}
		}
	}

	public void addListener(ListaPreciosChangeListener listener) {
		if (listaPreciosChangeListeners == null) {
			listaPreciosChangeListeners = new ArrayList<ListaPreciosChangeListener>();
		}
		listaPreciosChangeListeners.add(listener);
	}

	public void removeListener(ListaPreciosChangeListener listener) {
		if (listaPreciosChangeListeners == null) {
			listaPreciosChangeListeners = new ArrayList<ListaPreciosChangeListener>();
		}
		listaPreciosChangeListeners.remove(listener);
	}

	private List<ListaPreciosChangeListener> listaPreciosChangeListeners;
	private ListaPreciosVO listaPrecios;

	private Table table;

	public ListaPreciosBrowser() {
		buildMainLayout();

		setTableRows(null);
	}

	private void buildMainLayout() {
		// setHeight("100%");
		// setWidth(null);
		setSpacing(true);

		table = new Table();
		addComponent(table);
		table.setWidth("100%");
		table.setHeight("500px");
		// resultadosTable.setHeight(null);
		table.setSelectable(true);
		table.setMultiSelect(false);
		table.setImmediate(true);
		table.setContainerDataSource(new BeanItemContainer<ListaPreciosVO>(
				ListaPreciosVO.class, null));
		Table.ColumnGenerator resultadosTableColumnGenerator = new Table.ColumnGenerator() {
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				ListaPreciosVO listaPrecios = (ListaPreciosVO) itemId;
				StringBuilder sb = new StringBuilder();
				if (columnId.equals("i")) {
					for (ListaPreciosItemVO listaPreciosItem : listaPrecios
							.getItems()) {
						if (sb.length() == 0)
							sb.append("<br/>");
						sb.append(String.format("%d $%.2f", listaPreciosItem
								.getProducto().getCodigo(), listaPreciosItem
								.getPrecioUnitario()));
					}
				}
				Label label = new Label(sb.toString());
				label.setContentMode(ContentMode.XHTML);
				return label;
			}
		};
		table.addGeneratedColumn("i", resultadosTableColumnGenerator);
		table.setVisibleColumns(new String[] { "id", "vigenciaDesde",
				"vigenciaHasta", "i" });
		table.setColumnHeaders(new String[] { "ID", "Vigencia Desde",
				"Vigencia Hasta", "Items" });
		table.addListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				setListaPrecios((ListaPreciosVO) table.getValue());
			}
		});
	}

	public void refresh() {
		try {
			setTableRows(ProveedorClient.get().getListasPrecios());
			setListaPrecios(null);
		} catch (NamingException e) {
			e.printStackTrace();
			new Notification("No se pueden obtener las listas de precios",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}

	private void setTableRows(Collection<ListaPreciosVO> listaPrecios) {
		table.setContainerDataSource(new BeanItemContainer<ListaPreciosVO>(
				ListaPreciosVO.class, listaPrecios));
		table.setVisibleColumns(new String[] { "id", "vigenciaDesde",
				"vigenciaHasta", "i" });
	}

	public ListaPreciosVO getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(ListaPreciosVO listaPrecios) {
		if (listaPrecios != this.listaPrecios) {
			this.listaPrecios = listaPrecios;
			if (listaPrecios == null)
				table.select(null);
			fireListaPreciosChangedEvent();
		}
	}
}
