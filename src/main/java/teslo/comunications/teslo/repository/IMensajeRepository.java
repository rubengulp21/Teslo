package teslo.comunications.teslo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import teslo.comunications.teslo.model.Mensaje;
@Repository
public interface IMensajeRepository  extends MongoRepository<Mensaje, String> {

	@Query(value="{ 'receptor' : ?0, 'emisor' : ?1}"  , sort = "{ 'posteado': -1 } ")
	public List<Mensaje> findMensajesConver(String receptor, String emisor );
	
	@Query(value="{ 'emisor' : ?0}" , sort = "{ 'posteado': -1 } " )
	public List<Mensaje> findMeMensaje(String emisor );
	
	@Query(value="{ 'idConver' : ?0}" , sort = "{ 'posteado': -1 } " )
	public List<Mensaje> findByConver(String emisor );
	
}
