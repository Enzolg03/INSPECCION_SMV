package pe.edu.cibertec.APPDAWIPROYECTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.OrdenInspeccion;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.TipoMemorandun;

public interface TipoMemorandunRepository  extends JpaRepository<TipoMemorandun,
        Integer> {

}
