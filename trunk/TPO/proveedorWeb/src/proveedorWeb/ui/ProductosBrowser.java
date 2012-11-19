package proveedorWeb.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.ProductoVO;
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
public class ProductosBrowser extends VerticalLayout {

	public interface ProductoChangeListener extends Serializable {
		public void productoChanged(ProductoChangeEvent event);
	}

	public class ProductoChangeEvent implements Serializable {

		final ProductosBrowser productosBrowser;

		public ProductoChangeEvent(ProductosBrowser productosBrowser) {
			this.productosBrowser = productosBrowser;
		}

		public ProductosBrowser getProductosBrowser() {
			return productosBrowser;
		}

		public ProductoVO getProducto() {
			return productosBrowser.getProducto();
		}
	}

	private void fireProductoChangedEvent() {
		if (productoChangeListeners != null) {
			ProductoChangeEvent event = new ProductoChangeEvent(
					ProductosBrowser.this);
			for (ProductoChangeListener listener : productoChangeListeners) {
				listener.productoChanged(event);
			}
		}
	}

	public void addListener(ProductoChangeListener listener) {
		if (productoChangeListeners == null) {
			productoChangeListeners = new ArrayList<ProductoChangeListener>();
		}
		productoChangeListeners.add(listener);
	}

	public void removeListener(ProductoChangeListener listener) {
		if (productoChangeListeners == null) {
			productoChangeListeners = new ArrayList<ProductoChangeListener>();
		}
		productoChangeListeners.remove(listener);
	}

	private List<ProductoChangeListener> productoChangeListeners;
	private ProductoVO producto;

	private Table table;

	public ProductosBrowser() {
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
		table.setContainerDataSource(new BeanItemContainer<ProductoVO>(
				ProductoVO.class, null));
		Table.ColumnGenerator resultadosTableColumnGenerator = new Table.ColumnGenerator() {
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				ProductoVO producto = (ProductoVO) itemId;
				StringBuilder sb = new StringBuilder();
				if (columnId.equals("mp")) {
					for (MateriaPrimaProductoVO materiaPrimaProducto : producto
							.getMateriasPrimasProducto()) {
						if (sb.length() > 0)
							sb.append("<br/>");
						sb.append(String.format("%d x %s", materiaPrimaProducto
								.getCantidad(), materiaPrimaProducto
								.getMateriaPrima().getCodigo()));
					}
				}
				Label label = new Label(sb.toString());
				label.setContentMode(ContentMode.XHTML);
				return label;
			}
		};
		table.addGeneratedColumn("mp", resultadosTableColumnGenerator);
		table.setVisibleColumns(new String[] { "codigo", "descripcion",
				"caracteristica", "marca", "origen", "mp" });
		table.setColumnHeaders(new String[] { "Código", "Descripción",
				"Característica", "Marca", "Origen", "Materias Primas" });
		table.addListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				setProducto((ProductoVO) table.getValue());
			}
		});
	}

	public void refresh() {
		try {
			setTableRows(ProveedorClient.get().getProductos());
			setProducto(null);
		} catch (NamingException e) {
			e.printStackTrace();
			new Notification("No se pueden obtener los productos",
					e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
					.show(getRoot().getPage());
		}
	}

	private void setTableRows(Collection<ProductoVO> productos) {
		table.setContainerDataSource(new BeanItemContainer<ProductoVO>(
				ProductoVO.class, productos));
		table.setVisibleColumns(new String[] { "codigo", "descripcion",
				"caracteristica", "marca", "origen", "mp" });
	}

	public ProductoVO getProducto() {
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		if (producto != this.producto) {
			this.producto = producto;
			if (producto == null)
				table.select(null);
			fireProductoChangedEvent();
		}
	}
}
