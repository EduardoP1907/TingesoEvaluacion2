package tingeso_mingeso.backendestudiantesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso_mingeso.backendestudiantesservice.Excepciones.EstudianteNotFoundException;
import tingeso_mingeso.backendestudiantesservice.Model.Cuota;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;
import tingeso_mingeso.backendestudiantesservice.repository.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.*;


@Service
public class EstudianteService {


    @Autowired
    private RestTemplate restTemplate;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService() {
        this.estudianteRepository = estudianteRepository;
    }


    public Estudiante registrarEstudiante(Estudiante estudiante) {

        return estudianteRepository.save(estudiante);
    }

    public Estudiante obtenerestudianteporrut(String rut) {
        try {
            String jpql = "SELECT e FROM Estudiante e WHERE e.rut = :rut";
            TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);
            query.setParameter("rut", rut);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }
    public Estudiante obtenerEstudiantePorId(Long estudianteId) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudianteId);
        if (estudianteOptional.isPresent()) {
            return estudianteOptional.get();
        } else {
            throw new EstudianteNotFoundException("Estudiante no encontrado con ID: " + estudianteId);
        }
    }
    public int anosDesdeEgreso(Estudiante estudiante) {

        int anoEgreso = estudiante.getAnoEgresoColegio();
        LocalDate fechaActual = LocalDate.now();
        int anoActual = fechaActual.getYear();
        int anosDesdeEgreso = anoActual - anoEgreso;

        return anosDesdeEgreso;
    }


    public void generarCuotasDePago(Long estudianteId, int numeroCuotas) {
        Estudiante estudiante = obtenerEstudiantePorId(estudianteId);


        String cuotaServiceUrl = "http://localhost:8080/cuotas/generar-cuotas";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("estudianteId", estudiante.getId());
        requestBody.put("numeroCuotas", numeroCuotas);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                cuotaServiceUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Cuotas generadas con éxito");
        } else {
            System.err.println("Error al generar cuotas");
        }
    }
    public List<Cuota> listarCuotasDeEstudiante(Long estudianteId) {

        String cuotaServiceUrl = "http://localhost:8080/cuotas";

        ResponseEntity<List<Cuota>> response = restTemplate.exchange(
                cuotaServiceUrl + "/cuotas/estudiante/" + estudianteId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cuota>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Cuota> cuotas = response.getBody();
            return cuotas;
        } else {
            return Collections.emptyList();
        }
    }
    public String obtenerTipoColegioProcedencia(Long estudianteId) {
        Estudiante estudiante = obtenerEstudiantePorId(estudianteId);

        if (estudiante != null) {
            return estudiante.getTipoColegioProcedencia();
        } else {
            throw new EstudianteNotFoundException("No se encontró un estudiante con el ID proporcionado.");
        }
    }
    public double obtenerPromedioNotas(Long estudianteId) {
        String endpoint = "http://localhost:8080/estudiantes/" + estudianteId + "/promedio-notas";
        return restTemplate.getForObject(endpoint, Double.class);
    }

}
