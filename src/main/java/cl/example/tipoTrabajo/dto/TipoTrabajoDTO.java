package cl.example.tipoTrabajo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
    name = "TipoTrabajoDTO",
    description = "DTO para la creación y actualización de tipos de trabajo en el sistema"
)
public class TipoTrabajoDTO {

    @Schema(
        description = "Nombre del tipo de trabajo",
        example = "Mantenimiento",
        required = true,
        minLength = 1,
        maxLength = 100
    )
    @NotBlank(message = "El nombre del trabajo no puede estar vacío")
    private String nombreTrabajo;

    // ========== GETTER Y SETTER ==========

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }
}