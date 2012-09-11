package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Envio implements Serializable {

	// [IdEnvio] [int] IDENTITY(1,1) NOT NULL,
	private int idEnvio;

	// [Fecha] [datetime] NULL,
	private Date fecha;

	private List<ItemEnvio> envios;

	public Envio() {
	}

	public int getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<ItemEnvio> getEnvios() {
		return envios;
	}

	public void setEnvios(List<ItemEnvio> envios) {
		this.envios = envios;
	}
}
