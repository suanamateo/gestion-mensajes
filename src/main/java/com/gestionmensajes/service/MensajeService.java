package com.gestionmensajes.service;

import com.gestionmensajes.controller.dto.MensajeDTO;

/**
 * Interfaz que contendrá los métodos de la lógica de negocio para el
 * resoure Mensaje
 */

public interface MensajeService {

    /**
     * Método que crea un nuevo mensaje en base a los datos recibidos
     * desde el resource
     * @param mensaje para almacenar en base de datos
     * @return MensajeDTO con el ID único
     */

    MensajeDTO generarMensaje (MensajeDTO mensaje);

    /**
     * Método que devolverá un objeto MensajeDTO siempre que se encuentre el Id en
     * la DB
     *
     * @param id del mensaje
     * @return MensajeDTO
     */

    MensajeDTO buscarMensajeById (Long id);

    /**
     * Método que elimina un mensaje del recurso en base a un Id
     * @param id
     * @return
     */

    void eliminarMensajeById (Long id);

    /**
     * Método para actualizar un mensaje en base a los datos recibidos en base
     * a un Id de identificación
     * @param id
     * @param mensajeDTO
     * @return
     */

    MensajeDTO actualizarMensajeById (Long id, MensajeDTO mensajeDTO);

    /**
     * Metodo que actualiza de forma idempotente una noticia en DB
     *
     * @param mesaje
     * @return
     */

    MensajeDTO actualizarMensaje(MensajeDTO mensajeDTO);
}
