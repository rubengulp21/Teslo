package teslo.comunications.teslo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import teslo.comunications.teslo.model.Likes;
import teslo.comunications.teslo.model.Publicacion;

public interface ILikesRepository  extends MongoRepository<Likes, String>{
	@Query(value="{ 'publicacion' : ?0}" )
	public List<Likes> getLikesByPublicacion(String publicacion);

	@Query(value="{ 'username' : ?0}" )
	public List<Likes> getLikesByUser(String username);
	
	@Query(value="{ 'username' : ?0 ,'publicacion':?1}" )
	public Likes getLikesByUserPublicacion(String username,String publicacion);
}
