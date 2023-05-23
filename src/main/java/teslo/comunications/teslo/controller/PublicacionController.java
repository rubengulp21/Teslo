package teslo.comunications.teslo.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import teslo.comunications.teslo.model.Follows;
import teslo.comunications.teslo.model.Likes;
import teslo.comunications.teslo.model.Publicacion;
import teslo.comunications.teslo.repository.IFollows;
import teslo.comunications.teslo.repository.ILikesRepository;
import teslo.comunications.teslo.repository.IPublicacionRepository;
import teslo.comunications.teslo.repository.IUsuarioRepository;

//@PreAuthorize("authenticated")
@RestController
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class PublicacionController {

	@Autowired
	private IPublicacionRepository repositorioPublicacion;
	@Autowired
	private IUsuarioRepository repositorioUsuario;
	@Autowired
	private IFollows followRepository;
	@Autowired
	private ILikesRepository repositoryLikes;

	@GetMapping("/Publicaciones/{id}")
	public @ResponseBody List<Publicacion> getPublicacion(@PathVariable String id) {
		List<Publicacion> list = null;
		List<Follows> follows = followRepository
				.getFollowsByUser(id);
		System.out.println(follows);
		for (int i = 0; i < follows.size(); i++) {
			if (i == 0) {
				list = repositorioPublicacion.getPublicacionByUser(repositorioUsuario.findByUid(follows.get(i).getSeguidor()));
				list.addAll(repositorioPublicacion.getPublicacionByUser(repositorioUsuario.findByUid(follows.get(i).getUsuario())));
			} else {
				list.addAll(repositorioPublicacion.getPublicacionByUser(repositorioUsuario.findByUid(follows.get(i).getSeguidor())));
			}

		}

		if(list == null) {
			list = new ArrayList();
		}
		Collections.sort(list);
		Collections.reverse(list);

		for (int j = 0; j < list.size(); j++) {
			Likes likesau=repositoryLikes.getLikesByUserPublicacion(id, list.get(j).getId());
			if (likesau == null) {
				list.get(j).setLike(false);
			} else {
				list.get(j).setLike(true);
			}
			list.get(j).setLikes(repositoryLikes.getLikesByPublicacion(list.get(j).getId()).size());
		}
		
		return list;
	}

	@GetMapping("/Publicaciones/me/{user}")
	public @ResponseBody List<Publicacion> getPublicacionM(@PathVariable String user) {
		List<Publicacion> list = repositorioPublicacion.getPublicacionByUser(repositorioUsuario.findByUid(user));
		for (int j = 0; j < list.size(); j++) {
			if (repositoryLikes.getLikesByPublicacion(list.get(j).getId()) != null) {
				list.get(j).setLike(true);
			} else {
				list.get(j).setLike(false);
			}
			list.get(j).setLikes(repositoryLikes.getLikesByPublicacion(list.get(j).getId()).size());
		}
		if(list == null) {
			list = new ArrayList();
		}
		
		return list;
	}

	@PostMapping("/Publicaciones/{user}")
	public Publicacion setPublicacion(@RequestParam Map<String, String> body, @PathVariable String user) {

		Publicacion publicacion = new Publicacion();
		publicacion.setText(body.get("text"));
		publicacion.setUsuario(repositorioUsuario.findByUid(user));
		publicacion.setPosteado(LocalDate.now().atTime(LocalTime.now()));

		return repositorioPublicacion.save(publicacion);

	}

	@GetMapping("/Publicaciones/me/like/{user}")
	public @ResponseBody List<Publicacion> getPublicacionLikeadas(@PathVariable String user) {
		ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();
		List<Likes> likes = repositoryLikes.getLikesByUser(user);
		for (int i = 0; i < likes.size(); i++) {

			publicaciones.add(repositorioPublicacion.findById(likes.get(i).getPublicacion()).get());

		}
		if(publicaciones == null) {
			publicaciones = new ArrayList();
		}
		return publicaciones;

	}

}
