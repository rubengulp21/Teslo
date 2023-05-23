package teslo.comunications.teslo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teslo.comunications.teslo.model.Follows;
import teslo.comunications.teslo.repository.IFollows;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class FollowController {
	@Autowired
	private IFollows followRepository;


	@GetMapping("/Follow/{user}/{seguido}")
	public Follows setFollow(@PathVariable String user,@PathVariable String seguido) throws Exception {

		Follows nuevo = new Follows();
		nuevo.setUsuario(user);
		nuevo.setSeguidor(seguido);

		
		return followRepository.save(nuevo);

	}


	@GetMapping("/Unfollow/{user}/{seguido}")
	public void setUnfollow(@PathVariable String user,@PathVariable String seguido) throws Exception {
		followRepository.delete(followRepository.getFollow(user, seguido));

	}
	
	@GetMapping("/Follows/{user}")
	public @ResponseBody List<Follows> getFollows(@PathVariable String user) {
		List<Follows> list=followRepository.getFollowsByUser(user);
		if(list == null) {
			list = new ArrayList<Follows>();
		}
		return list;
	}
	
	@GetMapping("/Follow/seguidos/{user}")
	public @ResponseBody List<Follows> getFollowsSeguidos(@PathVariable String user) {
		List<Follows> list=followRepository.getSeguidoresByUser(user);
		if(list == null) {
			list = new ArrayList<Follows>();
		}
		return list;
	}
}
