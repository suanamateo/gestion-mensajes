package com.gestionmensajes.service.converter;

import com.gestionmensajes.controller.dto.MensajeDTO;
import com.gestionmensajes.repository.entity.MensajeEntity;

public class DTOEntityConverter {

    public MensajeEntity mapDTOToEntity(MensajeDTO mensajeDTO) {
        MensajeEntity mensajeEntity = null;

        mensajeEntity = new MensajeEntity()
                .setId(mensajeDTO.getId())
                .setTitulo(mensajeDTO.getTitulo())
                .setTexto(mensajeDTO.getTexto());

        return mensajeEntity;
    }

    public MensajeDTO mapEntityToDTO(MensajeEntity mensajeEntity){
        MensajeDTO mensajeDTO = null;

        mensajeDTO = new MensajeDTO()
                .setId(mensajeEntity.getId())
                .setTitulo(mensajeEntity.getTitulo())
                .setTexto(mensajeEntity.getTexto());

        return mensajeDTO;
    }
}

