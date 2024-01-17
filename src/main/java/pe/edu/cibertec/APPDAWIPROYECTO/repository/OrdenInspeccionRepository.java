package pe.edu.cibertec.APPDAWIPROYECTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.OrdenInspeccion;

@Repository
public interface OrdenInspeccionRepository extends JpaRepository<OrdenInspeccion,
        Integer> {
}
