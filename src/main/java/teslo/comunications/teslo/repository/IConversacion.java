package teslo.comunications.teslo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import teslo.comunications.teslo.model.Conversacion;


public interface IConversacion extends MongoRepository<Conversacion, String> {
	

}
