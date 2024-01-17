package pe.edu.cibertec.APPDAWIPROYECTO.model.request;

import lombok.Data;

@Data
public class ProyectoOficioDto {
    private Integer id_proyecto;
    private Integer id_orden;
    private String asunto;
    private String informacionrequerida;
    private String referencia;

}
