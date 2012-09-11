package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Articulo implements Serializable {

	// [TipoArticulo] [varchar](31) NOT NULL,
	private String tipoArticulo;

	// [Referencia] [int] NOT NULL,
	private int referencia;
	
	// [Seccion] [varchar](255) NULL,
	private String seccion;
	
	// [Descripcion] [varchar](255) NULL,
	private String descripcion;
	
	// [PrecioUnitario] [float] NOT NULL,
	private float precioUnitario;
	
	// [PrecioOferta] [float] NOT NULL,
	private float precioOferta;
	
	// [Color] [varchar](255) NULL,
	private String color;
	
	// [Linea] [varchar](255) NULL,
	private String linea;
	
	// [Ofad] [tinyint] NOT NULL,
	private int ofad;
	
	// [StockActual] [int] NOT NULL,
	private int stockActual;
	
	// [PuntoPedido] [int] NOT NULL,
	private int puntoPedido;
	
	// [Talle] [varchar](255) NULL,
	private String talle;
	
	// [Origen] [varchar](255) NULL,
	private String origen;
	
	// [Nombre] [varchar](255) NULL,
	private String nombre;
	
	// [Composicion] [varchar](255) NULL,
	private String composicion;
	
	// [Medidas] [varchar](255) NULL,
	private String medidas;
	
	// [Categoria] [varchar](255) NULL,
	private String categoria;

	public Articulo() {
	}

	public String getTipoArticulo() {
		return tipoArticulo;
	}

	public void setTipoArticulo(String tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getPrecioOferta() {
		return precioOferta;
	}

	public void setPrecioOferta(float precioOferta) {
		this.precioOferta = precioOferta;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public int getOfad() {
		return ofad;
	}

	public void setOfad(int ofad) {
		this.ofad = ofad;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public int getPuntoPedido() {
		return puntoPedido;
	}

	public void setPuntoPedido(int puntoPedido) {
		this.puntoPedido = puntoPedido;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComposicion() {
		return composicion;
	}

	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}

	public String getMedidas() {
		return medidas;
	}

	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
