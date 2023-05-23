package teslo.comunications.teslo.model;

import org.springframework.data.annotation.Id;

public class Notificaciones {

	@Id
	String id;
	String emisor;
	String mensaje;
	String receptor;

	public Notificaciones(String emisor, String mensaje, String receptor) {
		super();
		this.emisor = emisor;
		this.mensaje = mensaje;
		this.receptor = receptor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	@Override
	public String toString() {
		return emisor +  mensaje + receptor;
	}
	
	

}
