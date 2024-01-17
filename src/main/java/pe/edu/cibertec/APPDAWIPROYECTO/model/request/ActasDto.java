package pe.edu.cibertec.APPDAWIPROYECTO.model.request;

import lombok.Data;

@Data
public class ActasDto {

    private Integer id_acta;
    private Integer id_tipoacta;
    private String destinatario;
    private String emisor;
    private String fecha;
    private String documento;

}
