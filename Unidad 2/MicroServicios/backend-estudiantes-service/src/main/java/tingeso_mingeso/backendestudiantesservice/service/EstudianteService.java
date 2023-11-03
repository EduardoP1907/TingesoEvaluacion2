package tingeso_mingeso.backendestudiantesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;
import tingeso_mingeso.backendestudiantesservice.entity.TipoColegio;
import tingeso_mingeso.backendestudiantesservice.repository.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TipoColegioService tipoColegioService;


    public Estudiante registrarEstudiante(Estudiante estudiante) {

        return estudianteRepository.save(estudiante);
    }


    public int findIdByTipo(String tipo){
       TipoColegio tipoColegio = tipoColegioService.findByTipo(tipo);
       return tipoColegio.getId();
    }

    public void save(Estudiante estudiante){
        estudianteRepository.save(estudiante);
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }



    public Estudiante findByRut(String rut){
        return estudianteRepository.findByRut(rut);
    }
    public Estudiante obtenerEstudiantePorId(Long estudianteId) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudianteId);

        return estudianteOptional.orElse(null);
    }
    public List<Estudiante> findAll(){
        return estudianteRepository.findAll();
    }

}
