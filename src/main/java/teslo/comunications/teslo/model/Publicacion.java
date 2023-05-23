package teslo.comunications.teslo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Publicacion")
public class Publicacion implements Comparable<Publicacion> {
	@Id
	private String id;
	private Usuario usuario;
	private String text;
	private String file;
	private LocalDateTime posteado;
	private boolean like;
	private int likes;

	public Publicacion() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public LocalDateTime getPosteado() {
		return posteado;
	}

	public void setPosteado(LocalDateTime posteado) {
		this.posteado = posteado;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean isLike) {
		this.like = isLike;
	}

	@Override
	public int compareTo(Publicacion o) {

		return this.getPosteado().compareTo(o.getPosteado());
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}
