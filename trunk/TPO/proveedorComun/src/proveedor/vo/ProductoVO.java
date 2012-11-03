package proveedor.vo;

import java.io.Serializable;

public class ProductoVO implements Serializable {

	private static final long serialVersionUID = -487364569551425362L;

	private String codigo;

	private String descripcion;

	private String caracteristica;

	private String marca;

	private String origen;

	private float precioUnitario;

	public ProductoVO() {
		super();
	}

	public ProductoVO(String codigo, String descripcion, String caracteristica, String marca, String origen, float precioUnitario) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.caracteristica = caracteristica;
		this.marca = marca;
		this.origen = origen;
		this.precioUnitario = precioUnitario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (codigo == null ? 0 : codigo.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProductoVO other = (ProductoVO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
