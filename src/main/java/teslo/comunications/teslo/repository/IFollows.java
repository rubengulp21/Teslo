package teslo.comunications.teslo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import teslo.comunications.teslo.model.Follows;
import teslo.comunications.teslo.model.Likes;
import teslo.comunications.teslo.model.Usuario;

public interface IFollows  extends MongoRepository<Follows, String> {
	@Query(value="{ 'usuario' : ?0}" )
	public List<Follows> getFollowsByUser(String user);
	
	@Query(value="{ 'seguidor' : ?0}" )
	public List<Follows> getSeguidoresByUser(String user);
	
	@Query(value="{ 'seguidor' : ?0,'usuario':?1}" )
	public Follows getFollow(String esSeguido ,String user);

}
