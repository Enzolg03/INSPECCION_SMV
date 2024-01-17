package pe.edu.cibertec.APPDAWIPROYECTO.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipoactas")
public class TipoActas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipoacta;
    @Column(name = "descripcion")
    private String descripcion;
}
