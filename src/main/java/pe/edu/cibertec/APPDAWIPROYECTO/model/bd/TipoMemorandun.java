package pe.edu.cibertec.APPDAWIPROYECTO.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipomemorandun")
public class TipoMemorandun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipomemorandum;
    @Column(name = "descripcion")
    private String descripcion;
}
