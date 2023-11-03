package tingeso_mingeso.backendestudiantesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tingeso_mingeso.backendestudiantesservice.entity.TipoColegio;

public interface TipoColegioRepository extends JpaRepository<TipoColegio, String> {
    @Query("select e from TipoColegio e where e.tipo = :tipo")
    TipoColegio findByTipo(@Param("tipo") String tipo);
}

