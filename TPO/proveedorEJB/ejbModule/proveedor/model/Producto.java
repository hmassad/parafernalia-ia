package proveedor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = -487364569551425362L;

	@Id
	@Column
	private String codigo;

	@Column
	private String descripcion;

	@Column
	private String caracteristica;

	@Column
	private String marca;

	@Column
	private String origen;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.materiaPrima", cascade = { CascadeType.ALL })
	private Collection<MateriaPrimaProducto> materiasPrimasProducto;

	public Producto() {
		this.materiasPrimasProducto = new ArrayList<MateriaPrimaProducto>();
	}

	public Producto(String codigo, String descripcion, String caracteristica, String marca, String origen,
			Collection<MateriaPrimaProducto> materiasPrimasProductos) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.caracteristica = caracteristica;
		this.marca = marca;
		this.origen = origen;
		this.materiasPrimasProducto = materiasPrimasProductos;
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

	public Collection<MateriaPrimaProducto> getMateriasPrimasProducto() {
		return materiasPrimasProducto;
	}

	public void setMateriasPrimasProducto(Collection<MateriaPrimaProducto> materiasPrimasProducto) {
		this.materiasPrimasProducto = materiasPrimasProducto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caracteristica == null) ? 0 : caracteristica.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((materiasPrimasProducto == null) ? 0 : materiasPrimasProducto.hashCode());
		result = prime * result + ((origen == null) ? 0 : origen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (caracteristica == null) {
			if (other.caracteristica != null)
				return false;
		} else if (!caracteristica.equals(other.caracteristica))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (materiasPrimasProducto == null) {
			if (other.materiasPrimasProducto != null)
				return false;
		} else if (!materiasPrimasProducto.equals(other.materiasPrimasProducto))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		return true;
	}

	public String toString() {
		return getCodigo();
	}
}
