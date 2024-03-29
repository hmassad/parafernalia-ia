package proveedorWeb.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Root;

@Theme("proveedor")
@SuppressWarnings("serial")
public class ProveedorRoot extends Root {

	Navigator navigator;

	public void init(WrappedRequest request) {
		getApplication().setRootPreserved(true);

		getPage().setTitle("Proveedor G6");

		buildLayout();

		navigator.navigateTo("materiaPrima");
	}

	private void buildLayout() {
		final HorizontalLayout headerLayout = new HorizontalLayout();
		addComponent(headerLayout);
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");

		final Label logoLabel = new Label();
		logoLabel.setCaption("Proveedor G6");
		logoLabel.setStyleName("h1");
		headerLayout.addComponent(logoLabel);
		headerLayout.setComponentAlignment(logoLabel, Alignment.BOTTOM_LEFT);

		final Embedded logoImage = new Embedded("", new ThemeResource(
				"images/uade.png"));
		logoImage.setType(Embedded.TYPE_IMAGE);
		headerLayout.addComponent(logoImage);
		headerLayout.setComponentAlignment(logoImage, Alignment.BOTTOM_RIGHT);
		logoImage.setHeight("50px");

		final MenuBar menuBar = new MenuBar();
		addComponent(menuBar);
		menuBar.setWidth("100%");

		CreateMenuItem(menuBar, "Materia Prima", "materiaPrima");
		CreateMenuItem(menuBar, "Productos", "productos");
		CreateMenuItem(menuBar, "Lista de Precios", "listaPrecios");
		CreateMenuItem(menuBar, "Pedidos de Casa Central", "pedidosCasaCentral");
		CreateMenuItem(menuBar, "Pedidos de Materia Prima",
				"pedidosMateriaPrima");
		CreateMenuItem(menuBar, "Debug", "debug");

		final Panel navigatorContainer = new Panel();
		addComponent(navigatorContainer);
		navigator = new Navigator(navigatorContainer);
		navigator.addView("productos", new ProductosView());
		navigator.addView("listaPrecios", new ListaPreciosView());
		navigator.addView("materiaPrima", new MateriaPrimaView());
		navigator.addView("pedidosMateriaPrima", new PedidosMateriaPrimaView());
		navigator.addView("pedidosCasaCentral", new PedidosCasaCentralView());
		navigator.addView("debug", new DebugView());
	}

	private MenuBar.MenuItem CreateMenuItem(MenuBar menuBar, String display,
			final String viewAndParameters) {
		Command command = null;
		if (viewAndParameters != null) {
			command = new Command() {
				public void menuSelected(MenuItem selectedItem) {
					navigator.navigateTo(viewAndParameters);
				}
			};
		}
		return menuBar.addItem(display, command);
	}

}
