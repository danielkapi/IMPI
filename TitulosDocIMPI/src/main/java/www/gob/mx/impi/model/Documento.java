package www.gob.mx.impi.model;

import java.util.Date;

public class Documento {
	private int id;
	private Date fechaRegistro;
	private byte[] documentoElectronico;   
	private String titulo; 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public byte[] getDocumentoElectronico() {
		return documentoElectronico;
	}
	public void setDocumentoElectronico(byte[] documentoElectronico) {
		this.documentoElectronico = documentoElectronico;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
