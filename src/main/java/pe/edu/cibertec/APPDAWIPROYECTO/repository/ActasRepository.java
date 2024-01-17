package pe.edu.cibertec.APPDAWIPROYECTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.APPDAWIPROYECTO.model.bd.Actas;

@Repository
public interface ActasRepository extends JpaRepository<Actas, Integer> {
}
