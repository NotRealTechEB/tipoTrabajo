package cl.example.tipoTrabajo.dto;

import jakarta.validation.constraints.NotBlank;

public class TipoTrabajoDTO {

    @NotBlank(message = "El nombre del trabajo no puede estar vacío")
    private String nombreTrabajo;

  

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }
}