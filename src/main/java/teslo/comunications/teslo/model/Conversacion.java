package teslo.comunications.teslo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Conversacion")
public class Conversacion {
	@Id
	private String id;
	private List<String> integrantes;
	private List<Mensaje> mensajes;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getIntegrantes() {
		return this.integrantes;
	}
	public void setIntegrantes(List<String> integrantes) {
		this.integrantes = integrantes;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

}
