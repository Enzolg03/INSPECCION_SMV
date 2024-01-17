package pe.edu.cibertec.APPDAWIPROYECTO.model.request;

import lombok.Data;

@Data
public class MemorandumDto {
    private Integer id_memorandum;
    private Integer id_tipomemorandum;
    private String destinatario;
    private String emisor;
    private String asunto;
    private String referencia;
    private String fecha;
}
