package teslo.comunications.teslo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import teslo.comunications.teslo.model.Usuario;

@Repository
public interface IUsuarioRepository extends MongoRepository<Usuario, String> {
	 @Query("{ 'nombre' : { '$regex' : ?0 }}")
	public List< Usuario >findByUsername(String username );
	
	 @Query("{ 'nombre' : { '$regex' : ?0 }}")
		public Usuario findByUsernameOne(String username );
		
	
	@Query("{ 'uid' : ?0}" )
	public Usuario findByUid(String uid );
	
	
}
