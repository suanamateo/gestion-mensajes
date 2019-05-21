package com.gestionmensajes.repository;

import com.gestionmensajes.repository.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository <MensajeEntity, Long> {

}
