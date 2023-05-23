package teslo.comunications.teslo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teslo.comunications.teslo.model.Usuario;
import teslo.comunications.teslo.repository.IFollows;
import teslo.comunications.teslo.repository.IPublicacionRepository;
import teslo.comunications.teslo.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UsuarioController {

	@Autowired
	private IUsuarioRepository repositorio;

	@Autowired
	private FollowController controller;
	@Autowired
	private ConversacionController mensajeController;
	@Autowired
	private LikeController LikeController;

	@Autowired
	private IPublicacionRepository publicaionRepository;
	@Autowired
	private IFollows followsrepository;

	@GetMapping("/Usuario")
	public @ResponseBody List<Usuario> getAllUsuario() {
	List <Usuario> usuarios=repositorio.findAll();
	
	

		return usuarios;

	}

	@GetMapping("/Usuario/{user}/{yo}")
	public @ResponseBody List<Usuario> getUsuario(@PathVariable String user,@PathVariable String yo) {
		ArrayList<Usuario> list = (ArrayList<Usuario>) repositorio.findByUsername(user);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {

				if (followsrepository.getFollow(yo, list.get(i).getId()) != null) {

					list.get(i).setSigo(true);
				} else {
					list.get(i).setSigo(false);
				}
			}
		} else {
			Usuario usuario = new Usuario();
			usuario.setNombre("no encontrado");
			list = new ArrayList<Usuario>();
			list.add(usuario);
			return list;
		}

		return list;

	}

	@PostMapping("/Usuario")
	public @ResponseBody int setUsuario(@RequestParam Map<String, String> body) {
		Usuario usuario = new Usuario();
		usuario.setUid(body.get("id"));
		usuario.setNombre(body.get("nombre"));


		try {
			if(repositorio.findByUid(body.get("id")) == null) {
				repositorio.save(usuario);
				controller.setFollow(body.get("id"), "ElVaAjiWlDeEtGR8OjK6H2ZH1063");
				LikeController.setlike(publicaionRepository.getPublicacionByUser(repositorio.findByUid("ElVaAjiWlDeEtGR8OjK6H2ZH1063")).get(0).getId(), body.get("id"));
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("conver", "new");
				map.put("emisor", "ElVaAjiWlDeEtGR8OjK6H2ZH1063");
				map.put("receptor", body.get("id"));
				map.put("texto", "Hola");
			//	mensajeController.setMensaje(map);
			}
				
		} catch (Exception e) {

		}
		return Response.SC_OK;

	}
}