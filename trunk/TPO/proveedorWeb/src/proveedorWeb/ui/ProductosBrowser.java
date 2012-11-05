package proveedorWeb.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.ProductoVO;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.gwt.client.ui.label.ContentMode;
import com.vaadin.ui.Label;
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
			ProductoChangeEvent event = new ProductoChangeEvent(ProductosBrowser.this);
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

	private Table resultadosTable;

	public ProductosBrowser() {
		buildMainLayout();

		setTableRows(null);
	}

	private void buildMainLayout() {
		// setHeight("100%");
		// setWidth(null);
		setSpacing(true);

		resultadosTable = new Table();
		addComponent(resultadosTable);
		resultadosTable.setWidth("100%");
		resultadosTable.setHeight("500px");
		// resultadosTable.setHeight(null);
		resultadosTable.setSelectable(true);
		resultadosTable.setMultiSelect(false);
		resultadosTable.setImmediate(true);
		resultadosTable.setContainerDataSource(new BeanItemContainer<ProductoVO>(ProductoVO.class, null));
		Table.ColumnGenerator resultadosTableColumnGenerator = new Table.ColumnGenerator() {
			public Object generateCell(Table source, Object itemId, Object columnId) {
				ProductoVO producto = (ProductoVO) itemId;
				StringBuilder sb = new StringBuilder();
				if (columnId.equals("mp")) {
					for (MateriaPrimaProductoVO materiaPrimaProducto : producto.getMateriasPrimasProducto()) {
						if (sb.length() == 0)
							sb.append("<br/>");
						sb.append(String.format("%s (%d %s)", materiaPrimaProducto.getMateriaPrima().getCodigo(), materiaPrimaProducto.getCantidad(),
								materiaPrimaProducto.getUnidad().getCodigo()));
					}
				}
				Label label = new Label(sb.toString());
				label.setContentMode(ContentMode.XHTML);
				return label;
			}
		};
		resultadosTable.addGeneratedColumn("mp", resultadosTableColumnGenerator);
		resultadosTable.setVisibleColumns(new String[] { "codigo", "descripcion", "caracteristica", "marca", "origen", "mp" });
		resultadosTable.setColumnHeaders(new String[] { "Código", "Descripción", "Característica", "Marca", "Origen", "Materias Primas" });
		resultadosTable.addListener(new ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				setProducto((ProductoVO) resultadosTable.getValue());
			}
		});
	}

	public void refresh() {
		setProducto(null);
	}

	private void setTableRows(List<ProductoVO> productos) {
		resultadosTable.setContainerDataSource(new BeanItemContainer<ProductoVO>(ProductoVO.class, productos));
		resultadosTable.setVisibleColumns(new String[] { "codigo", "descripcion", "caracteristica", "marca", "origen", "mp" });
	}

	public ProductoVO getProducto() {
		return producto;
	}

	public void setProducto(ProductoVO producto) {
		if (producto != this.producto) {
			this.producto = producto;
			if (producto == null)
				resultadosTable.select(null);
			fireProductoChangedEvent();
		}
	}
}
