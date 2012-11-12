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

	public Producto(String codigo, String descripcion, String caracteristica,
			String marca, String origen,
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

	public void setMateriasPrimasProducto(
			Collection<MateriaPrimaProducto> materiasPrimasProducto) {
		this.materiasPrimasProducto = materiasPrimasProducto;
	}

	public String toString() {
		return getCodigo();
	}

	public static ProductoVO toProductoVO(Producto producto) {
		Collection<MateriaPrimaProductoVO> mppVOs = new ArrayList<MateriaPrimaProductoVO>();
		for (MateriaPrimaProducto mpp : producto.getMateriasPrimasProducto()) {
			mppVOs.add(MateriaPrimaProducto.toMateriaPrimaProductoVO(mpp));
		}
		return new ProductoVO(producto.getCodigo(), producto.getDescripcion(),
				producto.getCaracteristica(), producto.getMarca(),
				producto.getOrigen(), mppVOs);
	}

	public static Producto toProducto(ProductoVO productoVO) {
		Producto producto = new Producto();
		producto.setCodigo(productoVO.getCodigo());
		producto.setDescripcion(productoVO.getDescripcion());
		producto.setCaracteristica(productoVO.getCaracteristica());
		producto.setMarca(productoVO.getMarca());
		producto.setOrigen(productoVO.getOrigen());
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
