package cl.example.tipoTrabajo.controller;

import cl.example.tipoTrabajo.dto.TipoTrabajoDTO;
import cl.example.tipoTrabajo.modelo.TipoTrabajo;
import cl.example.tipoTrabajo.service.TipoTrabajoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-trabajo")
@Tag(name = "Tipos de Trabajo", description = "API de gestión de tipos de trabajo")
public class TipoTrabajoController {

    @Autowired
    private TipoTrabajoService service;

    @Operation(
        summary = "Obtener todos los tipos de trabajo",
        description = "Retorna una lista completa de todos los tipos de trabajo registrados en el sistema"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de tipos de trabajo obtenida exitosamente",
        content = @Content(
            mediaType = "application/json",
            examples = @ExampleObject(
                value = "[{\"id\": 1, \"nombreTrabajo\": \"Mantenimiento\"}]"
            )
        )
    )
    @GetMapping
    public List<TipoTrabajo> obtenerTodos() {
        return service.obtenerTodos();
    }

    @Operation(
        summary = "Obtener tipo de trabajo por ID",
        description = "Retorna un tipo de trabajo específico según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tipo de trabajo encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"id\": 1, \"nombreTrabajo\": \"Mantenimiento\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de trabajo no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"error\": \"Tipo de trabajo no encontrado con ID: 1\"}"
                )
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoTrabajo> obtenerPorId(
            @Parameter(description = "ID del tipo de trabajo a buscar", example = "1")
            @PathVariable Long id) {
        Optional<TipoTrabajo> tipo = service.obtenerPorId(id);
        return tipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear un nuevo tipo de trabajo",
        description = "Registra un nuevo tipo de trabajo en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Tipo de trabajo creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"id\": 1, \"nombreTrabajo\": \"Mantenimiento\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"nombreTrabajo\": \"El nombre del trabajo no puede estar vacío\"}"
                )
            )
        )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos del nuevo tipo de trabajo",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TipoTrabajoDTO.class),
            examples = @ExampleObject(
                name = "Tipo de Trabajo Válido",
                description = "Ejemplo de tipo de trabajo con datos correctos",
                value = "{\"nombreTrabajo\": \"Mantenimiento\"}"
            )
        )
    )
    @PostMapping
    public ResponseEntity<TipoTrabajo> guardarTipoTrabajo(@Valid @RequestBody TipoTrabajoDTO dto) {
        return new ResponseEntity<>(service.guardarTipoTrabajo(dto), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Actualizar un tipo de trabajo existente",
        description = "Actualiza los datos de un tipo de trabajo específico según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tipo de trabajo actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"id\": 1, \"nombreTrabajo\": \"Mantenimiento Avanzado\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de trabajo no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"error\": \"Tipo de trabajo no encontrado con ID: 1\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"nombreTrabajo\": \"El nombre del trabajo no puede estar vacío\"}"
                )
            )
        )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos actualizados del tipo de trabajo",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TipoTrabajoDTO.class),
            examples = @ExampleObject(
                value = "{\"nombreTrabajo\": \"Mantenimiento Avanzado\"}"
            )
        )
    )
    @PutMapping("/{id}")
    public ResponseEntity<TipoTrabajo> actualizarTipoTrabajo(
            @Parameter(description = "ID del tipo de trabajo a actualizar", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody TipoTrabajoDTO dto) {
        TipoTrabajo actualizado = service.actualizarTipoTrabajo(id, dto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar un tipo de trabajo",
        description = "Elimina un tipo de trabajo del sistema según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Tipo de trabajo eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Tipo de trabajo no encontrado",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"error\": \"Tipo de trabajo no encontrado con ID: 1\"}"
                )
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoTrabajo(
            @Parameter(description = "ID del tipo de trabajo a eliminar", example = "1")
            @PathVariable Long id) {
        service.eliminarTipoTrabajo(id);
        return ResponseEntity.noContent().build();
    }
}