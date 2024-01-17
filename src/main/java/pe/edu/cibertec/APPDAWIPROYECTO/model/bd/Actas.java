package pe.edu.cibertec.APPDAWIPROYECTO.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "actas")
public class Actas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_acta;

    @OneToOne
    @JoinColumn(name = "tipo")
    private TipoActas tipoActas;

    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "emisor")
    private String emisor;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "documento")
    private String documento;
}
