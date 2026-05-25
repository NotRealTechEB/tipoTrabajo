package cl.example.tipoTrabajo.service;

import cl.example.tipoTrabajo.dto.TipoTrabajoDTO;
import cl.example.tipoTrabajo.modelo.TipoTrabajo;
import cl.example.tipoTrabajo.repository.TipoTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        TipoTrabajo tipo = new TipoTrabajo();
        tipo.setNombreTrabajo(dto.getNombreTrabajo());
        return repository.save(tipo);
    }

    public TipoTrabajo actualizarTipoTrabajo(Long id, TipoTrabajoDTO dto) {
        Optional<TipoTrabajo> existente = repository.findById(id);
        if (existente.isPresent()) {
            TipoTrabajo tipo = existente.get();
            tipo.setNombreTrabajo(dto.getNombreTrabajo());
            return repository.save(tipo);
        }
        return null;
    }

    public void eliminarTipoTrabajo(Long id) {
        repository.deleteById(id);
    }
}