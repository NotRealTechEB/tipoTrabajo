package cl.example.tipoTrabajo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.example.tipoTrabajo.modelo.TipoTrabajo;

@Repository
public interface TipoTrabajoRepository extends JpaRepository<TipoTrabajo, Long> {
}