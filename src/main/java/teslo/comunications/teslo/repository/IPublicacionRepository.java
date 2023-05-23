package teslo.comunications.teslo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import teslo.comunications.teslo.model.Publicacion;
import teslo.comunications.teslo.model.Usuario;

public interface IPublicacionRepository extends MongoRepository<Publicacion, String> {
	@Query(value = "{ 'usuario' : ?0}", sort = "{ 'posteado': -1 } ")
	public List<Publicacion> getPublicacionByUser(Optional<Usuario> user);

	@Query(value = "{ 'usuario' : ?0}", sort = "{ 'posteado': -1 } ")
	public List<Publicacion> getPublicacionByUser(Usuario user);



}
