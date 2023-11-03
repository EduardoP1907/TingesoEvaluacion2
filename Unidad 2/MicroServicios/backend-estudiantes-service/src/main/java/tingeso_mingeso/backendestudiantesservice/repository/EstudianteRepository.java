package tingeso_mingeso.backendestudiantesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {
    @Query("select e from Estudiante e where e.rut = :rut")
    Estudiante findByRut(@Param("rut") String rut);

    Optional<Estudiante> findById(Long estudianteId);
}

