package pe.edu.cibertec.APPDAWIPROYECTO.model.bd;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "tb_ordeninspeccion")
public class OrdenInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_orden;

    @Column(name = "numeroruc")
    private String numeroruc;

    @Column(name = "nombreempresa")
    private String nombreempresa;

    @Column(name = "direccion")
    private String direccion ;

    @Column(name = "email")
    private String email ;

    @Column(name = "responsable")
    private String responsable ;


}

