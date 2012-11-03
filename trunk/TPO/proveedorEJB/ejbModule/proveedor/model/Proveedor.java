package proveedor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Proveedor {

	@Id
	private String cuit;

	private String razonSocial;
	
	private String telefono;
	
	private String direccion;
	
	private String ciudad;
	
	private String provincia;
	
	private String codigoPostal;

	public Proveedor(){
	}
	
	public Proveedor(String cuit, String razonSocial, String telefono, String direccion, String ciudad, String provincia, String codigoPostal) {
		super();
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.telefono = telefono;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

}
