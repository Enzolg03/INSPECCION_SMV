package pe.edu.cibertec.APPDAWIPROYECTO.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "memorandum")
public class Memorandum {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_memorandum;
        @ManyToOne
        @JoinColumn(name = "tipo")
        private TipoMemorandun tipoMemorandun;
        @Column(name = "destinatario")
        private String destinatario;
        @Column(name = "emisor")
        private String emisor;
        @Column(name = "asunto")
        private String asunto;
        @Column(name = "referencia")
        private String referencia;
        @Column(name = "fecha")
        private String fecha;

}

