package teslo.comunications.teslo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teslo.comunications.teslo.model.Conversacion;
import teslo.comunications.teslo.model.Mensaje;
import teslo.comunications.teslo.repository.IConversacion;
import teslo.comunications.teslo.repository.IMensajeRepository;
import teslo.comunications.teslo.repository.IUsuarioRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class ConversacionController {
    @Autowired
    private IMensajeRepository repositoryMensaje;

    @Autowired
    private IUsuarioRepository repositoryUser;

    @Autowired
    private IConversacion repositoryConver;

    @GetMapping("/Conversacion/{id}")
    public Conversacion getConver(@PathVariable String id) {
        Conversacion conver;
        List<Mensaje> mensajes;
        try {
            conver = repositoryConver.findById(id).get();
            return conver;
        } catch (Exception e) {
            return null;
        }
    }

    //@GetMapping("/Conversacion/{userId}")
    public List<Conversacion> getConvers(@PathVariable String userId) {
        List<Conversacion> conver;
        try {
            conver = repositoryConver.findAll();
            return conver;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/SendMensaje")
    public Conversacion setMensajes(@RequestParam Map<String, String> body) {
        Conversacion conver;
        Mensaje nuevo;
        try {
            nuevo = new Mensaje();
            nuevo.setEmisor(body.get("emisor"));
            nuevo.setReceptor(body.get("receptor"));
            nuevo.setTexto(body.get("texto"));
            nuevo.setEnviado(LocalDate.now().atTime(LocalTime.now()));

            conver = repositoryConver.findById(body.get("conver")).get();
            conver.getMensajes().add(nuevo);
            repositoryConver.save(conver);

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return conver;

    }

    @PostMapping("/Conversacion")
    public Conversacion setConversacion(@RequestParam Map<String, String> body) {
        Mensaje nuevo = new Mensaje();
        nuevo.setEmisor(body.get("emisor"));
        nuevo.setReceptor(body.get("receptor"));
        nuevo.setTexto(body.get("texto"));
        nuevo.setEnviado(LocalDate.now().atTime(LocalTime.now()));

        Conversacion conver = new Conversacion();


        ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
        mensajes.add(nuevo);
        conver.setMensajes(mensajes);
        repositoryMensaje.save(nuevo);

        ArrayList<String> integrantes = new ArrayList<String>();
        integrantes.add(body.get("emisor"));
        integrantes.add(body.get("receptor"));
        conver.setIntegrantes(integrantes);

        repositoryConver.save(conver);

        //nuevo.setIdConver(conver.getId());


        //repositoryMensaje.save(nuevo);
        return conver;

    }

}
