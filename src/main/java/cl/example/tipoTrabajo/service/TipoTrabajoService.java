package cl.example.tipoTrabajo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.example.tipoTrabajo.dto.TipoTrabajoDTO;
import cl.example.tipoTrabajo.modelo.TipoTrabajo;
import cl.example.tipoTrabajo.repository.TipoTrabajoRepository;

@Service
public class TipoTrabajoService {

    @Autowired
    private TipoTrabajoRepository repository;

    public List<TipoTrabajo> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<TipoTrabajo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public TipoTrabajo guardarTipoTrabajo(TipoTrabajoDTO dto) {
        
        if (repository.existsByNombreTrabajo(dto.getNombreTrabajo())) {
            throw new RuntimeException("Error: El tipo de trabajo '" + dto.getNombreTrabajo() + "' ya existe.");
        }

        TipoTrabajo tipo = new TipoTrabajo();
        tipo.setNombreTrabajo(dto.getNombreTrabajo());
        return repository.save(tipo);
    }

    public TipoTrabajo actualizarTipoTrabajo(Long id, TipoTrabajoDTO dto) {
        Optional<TipoTrabajo> existente = repository.findById(id);
        
        if (existente.isPresent()) {
            // REGLA 2: Si le vamos a cambiar el nombre, verificar que el nuevo nombre no lo tenga ya otro ID
            Optional<TipoTrabajo> tipoMismoNombre = repository.findByNombreTrabajo(dto.getNombreTrabajo());
            if (tipoMismoNombre.isPresent() && !tipoMismoNombre.get().getId().equals(id)) {
                throw new RuntimeException("Error: Ya existe otro tipo de trabajo con el nombre '" + dto.getNombreTrabajo() + "'.");
            }

            TipoTrabajo tipo = existente.get();
            tipo.setNombreTrabajo(dto.getNombreTrabajo());
            return repository.save(tipo);
        } else {
            // REGLA 3: Si el ID no existe, pegamos un "grito" para que el GlobalExceptionHandler lo atrape
            throw new RuntimeException("Error: No se puede actualizar. El Tipo de Trabajo con ID " + id + " no existe.");
        }
    }

    public void eliminarTipoTrabajo(Long id) {
        // REGLA 4: Verificar si existe antes de intentar eliminar
        if (!repository.existsById(id)) {
            throw new RuntimeException("Error: No se puede eliminar. El Tipo de Trabajo con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }
}