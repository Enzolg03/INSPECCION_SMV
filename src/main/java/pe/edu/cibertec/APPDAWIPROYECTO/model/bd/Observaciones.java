package pe.edu.cibertec.APPDAWIPROYECTO.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "observaciones")
public class Observaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_observacion;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "observacion1")
    private String observacion1;

    @Column(name = "observacion2")
    private String observacion2 ;

    @Column(name = "observacion3")
    private String observacion3 ;

}
