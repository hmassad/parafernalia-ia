package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Log implements Serializable {

	// [IdLog] [int] IDENTITY(1,1) NOT NULL,
	private int idLog;

	// [descripcion] [varchar](255) NULL,
	private String descripcion;

	// [origen] [varchar](255) NULL,
	private String origen;

	public Log() {
	}

	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
}
