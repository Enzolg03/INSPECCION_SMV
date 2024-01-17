package pe.edu.cibertec.APPDAWIPROYECTO.model.bd;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "tb_proyecto_oficio")
public class ProyectoOficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_proyecto;
    @OneToOne
    @JoinColumn(name = "id_orden")
    private OrdenInspeccion ordenInspeccion;
    @Column(name = "asunto")
    private String asunto;
    @Column(name = "informacionrequerida")
    private String informacionrequerida;
    @Column(name = "referencia")
    private String referencia;

}
