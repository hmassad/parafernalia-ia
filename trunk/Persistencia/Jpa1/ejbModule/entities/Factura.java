package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Factura implements Serializable {

	// [IdFactura] [int] IDENTITY(1,1) NOT NULL,
	private int idFactura;

	// [Fecha] [datetime] NULL,
	private Date fecha;

	// [TipoFactura] [varchar](255) NULL,
	private String tipoFactura;

	// [Total] [float] NOT NULL,
	private float total;

	// [NombreCliente] [varchar](255) NULL,
	private String nombreCliente;

	// [DomicilioCliente] [varchar](255) NULL,
	private String domicilioCliente;

	// [TelefonoCliente] [varchar](255) NULL,
	private String telefonoCliente;

	// [NombreVendedor] [varchar](255) NULL,
	private String nombreVendedor;

	// [Observaciones] [varchar](255) NULL,
	private String observaciones;

	private List<ItemFactura> itemsFactura;

	public Factura() {
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDomicilioCliente() {
		return domicilioCliente;
	}

	public void setDomicilioCliente(String domicilioCliente) {
		this.domicilioCliente = domicilioCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<ItemFactura> getItemsFactura() {
		return itemsFactura;
	}

	public void setItemsFactura(List<ItemFactura> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}
}
