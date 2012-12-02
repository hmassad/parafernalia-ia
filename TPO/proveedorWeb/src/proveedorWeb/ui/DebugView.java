package proveedorWeb.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import proveedor.vo.ListaPreciosItemVO;
import proveedor.vo.ListaPreciosVO;
import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.MateriaPrimaVO;
import proveedor.vo.ProductoVO;
import proveedorWeb.ejb.ProveedorClient;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DebugView extends VerticalLayout implements View {

	public DebugView() {
		setSizeFull();
		setSpacing(true);

		final Button generarMateriasPrimasButton = new Button();
		addComponent(generarMateriasPrimasButton);
		generarMateriasPrimasButton.setCaption("Generar Materias Primas");
		generarMateriasPrimasButton.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				try {
					ProveedorClient.get().createMateriaPrima(
							new MateriaPrimaVO("AA11", "Acero Templado", 0));
					ProveedorClient.get().createMateriaPrima(
							new MateriaPrimaVO("BB22", "Acero Inoxidable", 0));
					ProveedorClient.get().createMateriaPrima(
							new MateriaPrimaVO("CC33", "Tubo Caño", 0));
					ProveedorClient.get().createMateriaPrima(
							new MateriaPrimaVO("DD44",
									"Aluminio Alta Densidad", 0));
					ProveedorClient.get().createMateriaPrima(
							new MateriaPrimaVO("DD55",
									"Aluminio Baja Densidad", 0));

					new Notification("Materia Prima Generada",
							Notification.TYPE_HUMANIZED_MESSAGE).show(getRoot()
							.getPage());
				} catch (Exception e) {
					e.printStackTrace();
					new Notification("No se puede generar la materia prima", e
							.getMessage(), Notification.TYPE_ERROR_MESSAGE)
							.show(getRoot().getPage());
				}
			}
		});

		final Button generarProductosButton = new Button();
		addComponent(generarProductosButton);
		generarProductosButton.setCaption("Generar Productos");
		generarProductosButton.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				try {
					ProductoVO producto;
					Collection<MateriaPrimaProductoVO> mpp;

					mpp = new ArrayList<MateriaPrimaProductoVO>();
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"AA11", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB22", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"CC33", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD44", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD55", null, 0), 1));
					producto = new ProductoVO("22310", "Rod 1", "CCW33", "ZKL",
							"Japón", "", mpp);
					ProveedorClient.get().createProducto(producto);

					mpp = new ArrayList<MateriaPrimaProductoVO>();
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"AA11", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB22", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"CC33", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD44", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD55", null, 0), 1));
					producto = new ProductoVO("6200", "Rod 2", "2RS", "SNR",
							"Francia", "", mpp);
					ProveedorClient.get().createProducto(producto);

					mpp = new ArrayList<MateriaPrimaProductoVO>();
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"AA11", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB22", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"CC33", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD44", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD55", null, 0), 1));
					producto = new ProductoVO("6204", "Rod 3", "2RSC3", "SFK",
							"Brasil", "", mpp);
					ProveedorClient.get().createProducto(producto);

					mpp = new ArrayList<MateriaPrimaProductoVO>();
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"AA11", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB22", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"CC33", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD44", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD55", null, 0), 1));
					producto = new ProductoVO("K25580", "Rod 4", "AR123",
							"ZKL", "Japón", "", mpp);
					ProveedorClient.get().createProducto(producto);

					mpp = new ArrayList<MateriaPrimaProductoVO>();
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"AA11", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB22", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"CC33", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD44", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD55", null, 0), 1));
					producto = new ProductoVO("NJ 208", "Rod 5", "EMC3", "SNR",
							"Francia", "", mpp);
					ProveedorClient.get().createProducto(producto);

					mpp = new ArrayList<MateriaPrimaProductoVO>();
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"AA11", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB22", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"CC33", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD44", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"DD55", null, 0), 1));
					producto = new ProductoVO("6205", "Rod 6", "2RSC4", "SFK",
							"Brasil", "", mpp);
					ProveedorClient.get().createProducto(producto);

					new Notification("Productos generados",
							Notification.TYPE_HUMANIZED_MESSAGE).show(getRoot()
							.getPage());
				} catch (Exception e) {
					e.printStackTrace();
					new Notification("No se pueden generar el Producto", e
							.getMessage(), Notification.TYPE_ERROR_MESSAGE)
							.show(getRoot().getPage());
				}
			}
		});

		final Button generarListaPreciosButton = new Button();
		addComponent(generarListaPreciosButton);
		generarListaPreciosButton.setCaption("Generar Lista de Precios");
		generarListaPreciosButton.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				try {
					ListaPreciosVO listaPrecios = new ListaPreciosVO();

					ProductoVO p;
					p = ProveedorClient.get().getProducto("22310");
					if (p != null)
						listaPrecios.getItems().add(
								new ListaPreciosItemVO(p, 310.71f));
					p = ProveedorClient.get().getProducto("6200");
					if (p != null)
						listaPrecios.getItems().add(
								new ListaPreciosItemVO(p, 7.1f));
					p = ProveedorClient.get().getProducto("6204");
					if (p != null)
						listaPrecios.getItems().add(
								new ListaPreciosItemVO(p, 13.49f));
					p = ProveedorClient.get().getProducto("K25580");
					if (p != null)
						listaPrecios.getItems().add(
								new ListaPreciosItemVO(p, 67.09f));
					p = ProveedorClient.get().getProducto("NJ 208");
					if (p != null)
						listaPrecios.getItems().add(
								new ListaPreciosItemVO(p, 107.86f));
					p = ProveedorClient.get().getProducto("6205");
					if (p != null)
						listaPrecios.getItems().add(
								new ListaPreciosItemVO(p, 22.30f));

					Date now = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.DAY_OF_MONTH, 1);
					Date tomorrow = cal.getTime();
					listaPrecios.setVigenciaDesde(now);
					listaPrecios.setVigenciaHasta(tomorrow);
					ProveedorClient.get().createListaPrecios(listaPrecios);

					new Notification("Lista de Precios generada",
							Notification.TYPE_HUMANIZED_MESSAGE).show(getRoot()
							.getPage());
				} catch (Exception e) {
					e.printStackTrace();
					new Notification("No se puede generar la Lista de Precios",
							e.getMessage(), Notification.TYPE_ERROR_MESSAGE)
							.show(getRoot().getPage());
				}
			}
		});
	}

	public void navigateTo(String fragmentParameters) {
	}
}
