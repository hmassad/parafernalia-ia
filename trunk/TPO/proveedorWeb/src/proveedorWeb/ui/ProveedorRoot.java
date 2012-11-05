package proveedorWeb.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;

@Theme("proveedor")
@SuppressWarnings("serial")
public class ProveedorRoot extends Root {

	VerticalLayout mainLayout;
	Navigator navigator;

	public void init(WrappedRequest request) {
		getApplication().setRootPreserved(true);

		getPage().setTitle("Proveedor");

		buildLayout();

		navigator.navigateTo("proveedor");
	}

	private void buildLayout() {
		mainLayout = new VerticalLayout();
		addComponent(mainLayout);
		mainLayout.setSizeFull();

		final HorizontalLayout menuLayout = new HorizontalLayout();
		mainLayout.addComponent(menuLayout);

		final MenuBar menuBar = new MenuBar();
		menuLayout.addComponent(menuBar);
		menuBar.setHtmlContentAllowed(true);

		CreateMenuItem(menuBar, "<b><em>Proveedor</em></b>", "default");
		CreateMenuItem(menuBar, "Proveedor", "proveedor");
		CreateMenuItem(menuBar, "Productos", "productos");
		CreateMenuItem(menuBar, "Lista de Precios", "listaPrecios");
		CreateMenuItem(menuBar, "Materia Prima", "materiaPrima");
		CreateMenuItem(menuBar, "Unidades", "unidades");
		CreateMenuItem(menuBar, "Pedidos de Casa Central", "pedidosCasaCentral");
		CreateMenuItem(menuBar, "Pedidos de Materia Prima", "pedidosMateriaPrima");

		final Panel navigatorContainer = new Panel();
		addComponent(navigatorContainer);
		navigator = new Navigator(navigatorContainer);
		navigator.addView("proveedor", new ProveedorView());
		navigator.addView("productos", new ProductosView());
		// navigator.addView("listaPrecios", new ListaPreciosView());
		navigator.addView("materiaPrima", new MateriaPrimaView());
		navigator.addView("unidades", new UnidadesView());
		navigator.addView("pedidosCasaCentral", new PedidosMateriaPrimaView());
		navigator.addView("pedidosMateriaPrima", new PedidosCasaCentralView());
	}

	private MenuBar.MenuItem CreateMenuItem(MenuBar menuBar, String display, final String viewAndParameters) {
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
