package proveedor.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ListaDePrecios")
public class ListaDePrecios implements Serializable {

	private static final long serialVersionUID = -5153698428700309348L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int nroLista;
	
	
	private Proveedor proveedor;

	private Date vigenciaDesde;
	
	private Collection<ListaDePreciosItem> items;
}
