package com.gestionmensajes.controller;

import com.gestionmensajes.controller.dto.MensajeDTO;
import com.gestionmensajes.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase resource de Mensajes.
 * Vamos a definir todos los métodos de un CRUD (CRUD = 4 métodos de manipulación de
 * una base de datos - funcionalidad básica de cualquier aplicación = crear recursos,
 * recuperar recursos, actualizar recursos o eliminar recursos.-
 */

@RestController
public class MensajeResource {

    @Autowired
    MensajeService mensajeService;

    /**
     * Este método sirve para crear un recurso nuevo
     * Dado modelo mensaje, persistiremos en la DB y devolveremos como
     * respuesta el mismo objeto creado junto a su identificador único.
     *
     * @param mensaje con la información del mensaje para dar de alta en el
     * sitema
     * @return ResponseEntity<MensajeDTO>
     */

    @RequestMapping(path = "/mensajes", method = RequestMethod.POST)
    public ResponseEntity<MensajeDTO> generarMensaje(@RequestBody MensajeDTO mensaje) {
        ResponseEntity<MensajeDTO> response = null;

        if (validateMensaje(mensaje)){
            MensajeDTO mensajeDTO = mensajeService.generarMensaje(mensaje);
            response = ResponseEntity.ok(mensajeDTO);
        } else {
            response = ResponseEntity.badRequest().body(mensaje);
        }
        return response;
    }

    /**
     * Metodo para recuperar un recurso en base a el id solicitado
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/mensajes/{id}", method = RequestMethod.GET)
    public ResponseEntity<MensajeDTO> getMensajeById(@PathVariable(name = "id")Long id){
        ResponseEntity<MensajeDTO> response = null;

        MensajeDTO mensajeDTO = mensajeService.buscarMensajeById(id);
        if(mensajeDTO != null) {
            response = ResponseEntity.ok(mensajeDTO);
        } else {
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    /**
     * Método que elimina mensajes dado su id
     *
     * @param id
     * @return
     */

    @RequestMapping(path = "/mensajes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity eliminarMensajeById(@PathVariable(name = "id") Long id){
        mensajeService.eliminarMensajeById(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * Metodo que actualizara datos de un mensaje dado su id
     *
     * @param id
     * @param mensajeDTO
     * @return
     */

    @RequestMapping(path = "/mensajes/{id}", method = RequestMethod.PATCH)
    public void actualizarMensajeById(@PathVariable(name = "id") Long id, @RequestBody MensajeDTO mensajeDTO) {
        ResponseEntity<MensajeDTO> responseDTO = null;

        MensajeDTO mensajeActualizado = mensajeService.actualizarMensajeById(id, mensajeDTO);
        responseDTO = ResponseEntity.ok(mensajeActualizado);
    }

    /**
     * Método que actualiza de forma independiente un mensaje en DB
     * @param mensajeDTO
     * @return
     */
    @RequestMapping(path = "/mensajes", method = RequestMethod.PUT)
    public ResponseEntity<MensajeDTO> actualizarMensaje(@RequestBody MensajeDTO mensajeDTO){
        ResponseEntity<MensajeDTO> responseDTO = null;
        MensajeDTO noticiaDTO = mensajeService.actualizarMensaje(mensajeDTO);
        if(mensajeDTO != null){
            responseDTO = ResponseEntity.ok(mensajeDTO);
        } else {
            responseDTO = ResponseEntity.badRequest().build();
        }
        return responseDTO;
    }

    private boolean validateMensaje(MensajeDTO mensaje){
        return (mensaje.getTitulo().isEmpty() || mensaje.getTexto().isEmpty()) ? false : true;
    }
}
