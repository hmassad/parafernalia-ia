package proveedorWeb.ui;

import java.util.ArrayList;
import java.util.Collection;

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

import ejb.casacentral.webservice.CasaCentralClient;

@SuppressWarnings("serial")
public class DatosInicialesView extends VerticalLayout implements View {

	public DatosInicialesView() {
		setSizeFull();
		setSpacing(true);

		final Button generarMateriasPrimasButton = new Button();
		addComponent(generarMateriasPrimasButton);
		generarMateriasPrimasButton.setCaption("Generar Materias Primas");
		generarMateriasPrimasButton.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				try {
					ProveedorClient.get()
							.createMateriaPrima(
									new MateriaPrimaVO("AA11",
											"Materia Prima AA11", 0));
					ProveedorClient.get()
							.createMateriaPrima(
									new MateriaPrimaVO("AA22",
											"Materia Prima AA22", 0));
					ProveedorClient.get()
							.createMateriaPrima(
									new MateriaPrimaVO("AA33",
											"Materia Prima AA33", 0));
					ProveedorClient.get()
							.createMateriaPrima(
									new MateriaPrimaVO("BB11",
											"Materia Prima BB11", 0));
					ProveedorClient.get()
							.createMateriaPrima(
									new MateriaPrimaVO("BB22",
											"Materia Prima BB22", 0));
					ProveedorClient.get()
							.createMateriaPrima(
									new MateriaPrimaVO("BB33",
											"Materia Prima BB33", 0));

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
							"AA22", null, 0), 1));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"AA33", null, 0), 1));
					producto = new ProductoVO("P1", "Producto 1",
							"Característica 1", "Marca 1", "Origen 1",
							"Tipo 1", mpp);
					CasaCentralClient.nuevoRodamiento(producto);
					ProveedorClient.get().createProducto(producto);

					mpp = new ArrayList<MateriaPrimaProductoVO>();
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB11", null, 0), 2));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB22", null, 0), 2));
					mpp.add(new MateriaPrimaProductoVO(new MateriaPrimaVO(
							"BB33", null, 0), 2));
					producto = new ProductoVO("P2", "Producto 2",
							"Característica 2", "Marca 2", "Origen 2",
							"Tipo 2", mpp);
					CasaCentralClient.nuevoRodamiento(producto);
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

	}

	public void navigateTo(String fragmentParameters) {
	}
}
