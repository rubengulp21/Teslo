package teslo.comunications.teslo.controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import teslo.comunications.teslo.model.Likes;
import teslo.comunications.teslo.repository.ILikesRepository;
import teslo.comunications.teslo.repository.IPublicacionRepository;

@RestController
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class LikeController {

	@Autowired
	private ILikesRepository repositorioLikes;

	@GetMapping("/Like/{publicacion}/{user}")
	public Likes setlike(@PathVariable String publicacion, @PathVariable String user) {
		
		if (repositorioLikes.getLikesByUserPublicacion(user,publicacion)==null) {
			Likes newLike=new Likes(publicacion, user);
			repositorioLikes.save(newLike);
			
			return repositorioLikes.save(newLike);
		}else {
			return null;
		}
		


	}
	@GetMapping("/Dislike/{publicacion}/{user}")
	public boolean setDislike(@PathVariable String publicacion, @PathVariable String user) throws Exception {
		boolean boo;
		try {
			repositorioLikes.delete(repositorioLikes.getLikesByUserPublicacion(user, publicacion));
			boo=true;
			
		} catch (IllegalArgumentException e) {
			boo=false;
		}
		
		
		return boo; 

	}
	
	@GetMapping("/Like/{publicacion}")
	public int getlike(@PathVariable String publicacion) {
		
		 
		return repositorioLikes.getLikesByPublicacion(publicacion).size();
		
	}
}
