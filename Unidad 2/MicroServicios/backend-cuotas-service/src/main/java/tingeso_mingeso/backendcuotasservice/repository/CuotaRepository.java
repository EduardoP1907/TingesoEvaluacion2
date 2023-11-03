package tingeso_mingeso.backendcuotasservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso_mingeso.backendcuotasservice.entity.Cuota;
import tingeso_mingeso.backendcuotasservice.model.Estudiante;

import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, String> {

    List<Cuota> findCuotaByRut(@Param("rut") String rut);

    List<Cuota> findByEstudiante(Estudiante estudiante);

    Cuota findById(Long cuotaId);
}
