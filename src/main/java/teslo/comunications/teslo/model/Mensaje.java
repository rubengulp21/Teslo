package teslo.comunications.teslo.model;

import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Mensaje")
public class Mensaje implements Comparable<Mensaje>{
	@Id
	private String id;
	private String emisor;
	private String receptor;
	private String texto;
	private LocalDateTime enviado;


	public Mensaje() {

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

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getEnviado() {
		return enviado;
	}

	public void setEnviado(LocalDateTime enviado) {
		this.enviado = enviado;
	}
	@Override
	public int compareTo(Mensaje o) {
		
		return this.getEnviado().compareTo(o.getEnviado());
	}



}
