package proveedor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import proveedor.vo.UnidadVO;

@Entity(name = "Unidad")
public class Unidad {

	@Id
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descripcion", length = 50)
	private String descripcion;

	public Unidad() {
	}

	public Unidad(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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
		Unidad other = (Unidad) obj;
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
		return true;
	}

	public String toString() {
		return getDescripcion();
	}

	public static UnidadVO toUnidadVO(Unidad unidad) {
		return new UnidadVO(unidad.getCodigo(), unidad.getDescripcion());
	}

	public static Unidad toUnidad(UnidadVO unidadVO) {
		return new Unidad(unidadVO.getCodigo(), unidadVO.getDescripcion());
	}
}
