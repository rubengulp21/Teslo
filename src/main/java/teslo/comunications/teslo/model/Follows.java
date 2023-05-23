package teslo.comunications.teslo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Follows")
public class Follows {
	@Id
	private String id;
	private String usuario;
	private String seguidor;
	
	public Follows() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSeguidor() {
		return seguidor;
	}
	public void setSeguidor(String seguidor) {
		this.seguidor = seguidor;
	}
	
	
}
