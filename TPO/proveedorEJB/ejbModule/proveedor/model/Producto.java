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

import proveedor.vo.MateriaPrimaProductoVO;
import proveedor.vo.ProductoVO;

@Entity
public class Producto implements Serializable {

	private static final long serialVersionUID = -487364569551425362L;

	private String codigo;

	private String descripcion;

	private String caracteristica;

	private String marca;

	private String origen;

	private String tipo;

	private Collection<MateriaPrimaProducto> materiasPrimasProducto;

	public Producto() {
		this.materiasPrimasProducto = new ArrayList<MateriaPrimaProducto>();
	}

	public Producto(String codigo, String descripcion, String caracteristica,
			String marca, String origen, String tipo,
			Collection<MateriaPrimaProducto> materiasPrimasProductos) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.caracteristica = caracteristica;
		this.marca = marca;
		this.origen = origen;
		this.tipo = tipo;
		this.materiasPrimasProducto = materiasPrimasProductos;
	}

	@Id
	@Column
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	@Column
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@Column
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.producto", cascade = { CascadeType.ALL })
	public Collection<MateriaPrimaProducto> getMateriasPrimasProducto() {
		return materiasPrimasProducto;
	}

	public void setMateriasPrimasProducto(
			Collection<MateriaPrimaProducto> materiasPrimasProducto) {
		this.materiasPrimasProducto = materiasPrimasProducto;
	}

	public String toString() {
		return getCodigo();
	}

	public static ProductoVO toProductoVO(Producto producto) {
		Collection<MateriaPrimaProductoVO> mppVOs = new ArrayList<MateriaPrimaProductoVO>();
		if (producto.getMateriasPrimasProducto() != null)
			for (MateriaPrimaProducto mpp : producto
					.getMateriasPrimasProducto()) {
				mppVOs.add(MateriaPrimaProducto.toMateriaPrimaProductoVO(mpp));
			}
		return new ProductoVO(producto.getCodigo(), producto.getDescripcion(),
				producto.getCaracteristica(), producto.getMarca(),
				producto.getOrigen(), producto.getTipo(), mppVOs);
	}

	public static Producto toProducto(ProductoVO productoVO) {
		Producto producto = new Producto();
		producto.setCodigo(productoVO.getCodigo());
		producto.setDescripcion(productoVO.getDescripcion());
		producto.setCaracteristica(productoVO.getCaracteristica());
		producto.setMarca(productoVO.getMarca());
		producto.setOrigen(productoVO.getOrigen());
		producto.setTipo(productoVO.getTipo());
		for (MateriaPrimaProductoVO mppVO : productoVO
				.getMateriasPrimasProducto()) {
			MateriaPrima mp = MateriaPrima.toMateriaPrima(mppVO
					.getMateriaPrima());
			MateriaPrimaProducto mpp = new MateriaPrimaProducto(producto, mp,
					mppVO.getCantidad());
			producto.getMateriasPrimasProducto().add(mpp);
		}
		return producto;
	}
}
