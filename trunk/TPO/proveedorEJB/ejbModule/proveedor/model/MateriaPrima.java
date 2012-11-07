package proveedor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import proveedor.vo.MateriaPrimaVO;

@Entity
public class MateriaPrima {

	@Id
	@Column
	private String codigo;

	@Column
	private String descripcion;

	@Column
	private int stock;

	public MateriaPrima() {
	}

	public MateriaPrima(String codigo, String descripcion, int stock) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.stock = stock;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
		MateriaPrima other = (MateriaPrima) obj;
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

	public static MateriaPrimaVO toMateriaPrimaVO(MateriaPrima materiaPrima) {
		return new MateriaPrimaVO(materiaPrima.getCodigo(), materiaPrima.getDescripcion(), materiaPrima.getStock());
	}

	public static MateriaPrima toMateriaPrima(MateriaPrimaVO materiaPrimaVO) {
		return new MateriaPrima(materiaPrimaVO.getCodigo(), materiaPrimaVO.getDescripcion(), materiaPrimaVO.getStock());
	}
}
