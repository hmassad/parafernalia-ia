package proveedor.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ProductoVO implements Serializable {

	private static final long serialVersionUID = -487364569551425362L;

	private String codigo;

	private String descripcion;

	private String caracteristica;

	private String marca;

	private String origen;

	private String tipo;

	private String medida;

	private Collection<MateriaPrimaProductoVO> materiasPrimasProducto;

	public ProductoVO() {
		this.materiasPrimasProducto = new ArrayList<MateriaPrimaProductoVO>();
	}

	public ProductoVO(String codigo, String descripcion, String caracteristica, String marca, String origen, String tipo, String medida,
			Collection<MateriaPrimaProductoVO> materiasPrimasProducto) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.caracteristica = caracteristica;
		this.marca = marca;
		this.origen = origen;
		this.tipo = tipo;
		this.medida = medida;
		this.materiasPrimasProducto = materiasPrimasProducto;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public Collection<MateriaPrimaProductoVO> getMateriasPrimasProducto() {
		return materiasPrimasProducto;
	}

	public void setMateriasPrimas(Collection<MateriaPrimaProductoVO> materiasPrimasProducto) {
		this.materiasPrimasProducto = materiasPrimasProducto;
	}

	public String toString() {
		return getCodigo();
	}
}
