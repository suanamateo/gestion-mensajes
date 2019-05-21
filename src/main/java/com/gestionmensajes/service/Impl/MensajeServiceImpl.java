package com.gestionmensajes.service.Impl;

import com.gestionmensajes.controller.dto.MensajeDTO;
import com.gestionmensajes.repository.entity.MensajeEntity;
import com.gestionmensajes.repository.MensajeRepository;
import com.gestionmensajes.service.MensajeService;
import com.gestionmensajes.service.converter.DTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    DTOEntityConverter mapper = new DTOEntityConverter();


    @Override
    public MensajeDTO generarMensaje(MensajeDTO mensaje) {
        MensajeDTO responseDTO = null;

        MensajeEntity mensajeEntity = mensajeRepository.save(mapper.mapDTOToEntity(mensaje));
        responseDTO = mapper.mapEntityToDTO(mensajeEntity);

        return responseDTO;
    }

    @Override
    public MensajeDTO buscarMensajeById(Long id) {
        MensajeDTO responseDTO = null;

        Optional<MensajeEntity> optionalMensajeEntity = mensajeRepository.findById(id);
        if (optionalMensajeEntity.isPresent()) {
            responseDTO = mapper.mapEntityToDTO(optionalMensajeEntity.get());
        }

        return responseDTO;
    }

    @Override
    public void eliminarMensajeById(Long id) {
        mensajeRepository.deleteById(id);
    }

    @Override
    public MensajeDTO actualizarMensajeById(Long id, MensajeDTO mensajeDTO) {
        MensajeDTO responseDTO = null;

        Optional<MensajeEntity> entityOptional = mensajeRepository.findById(id);

        if (entityOptional.isPresent()) {
            if (mensajeDTO.getTitulo() != null) {
                entityOptional.get().setTitulo(mensajeDTO.getTitulo());
            }
            if (mensajeDTO.getTexto() != null) {
                entityOptional.get().setTexto(mensajeDTO.getTexto());
            }
        }
        MensajeEntity entitySaved = mensajeRepository.save(entityOptional.get());
        responseDTO = mapper.mapEntityToDTO(entitySaved);

        return responseDTO;
    }

    @Override
    public MensajeDTO actualizarMensaje(MensajeDTO mensajeDTO) {
        MensajeDTO mensaje = null;

        if (validador(mensajeDTO)) {
            MensajeEntity entity = mensajeRepository.save(mapper.mapDTOToEntity(mensajeDTO));
            mensaje = mapper.mapEntityToDTO(entity);
        }

        return mensaje;
    }

    private boolean validador(MensajeDTO mensaje) {
        return  !StringUtils.isEmpty(mensaje.getTitulo()) &&
                !StringUtils.isEmpty(mensaje.getTexto());
    }
}



