package tingeso_mingeso.backendestudiantesservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tingeso_mingeso.backendestudiantesservice.Excepciones.EstudianteNotFoundException;
import tingeso_mingeso.backendestudiantesservice.Model.Cuota;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;
import tingeso_mingeso.backendestudiantesservice.service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;
    private final RestTemplate restTemplate;


    @Autowired
    public EstudianteController(EstudianteService estudianteService, RestTemplate restTemplate) {
        this.estudianteService = estudianteService;
        this.restTemplate = restTemplate;

    }


    @PostMapping("/registro")
    public String procesarFormularioRegistro(@ModelAttribute Estudiante estudiante, @RequestParam int numeroCuotas) {
        System.out.println(estudiante);
        estudianteService.registrarEstudiante(estudiante);
        estudianteService.generarCuotasDePago(estudiante.getId(), numeroCuotas);
        return "index";
    }

    @GetMapping("/lista")
    public String mostrarListaEstudiantes(org.springframework.ui.Model model) {

        List<Estudiante> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        System.out.printf(estudiantes.get(2).toString());
        return "listaEstudiante";
    }

    @GetMapping("/obtenerestudiante/{id}")

    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id, Model model) {


        try {
            Estudiante estudiante = estudianteService.obtenerEstudiantePorId(id);

            return ResponseEntity.ok(estudiante);

        } catch (EstudianteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("registro")
    public String registro(Model model){
        return "registro";
    }

    @GetMapping("/buscar-estudiante2")
    public String mostrarBusquedaEstudiante2(Model model) {

        return "buscarestudianteporrut2";
    }

    @GetMapping("/buscar-estudiante-y-pagar")
    public String verCuotasYPagar(@RequestParam String rut, Model model) {
        Estudiante estudiante = estudianteService.obtenerestudianteporrut(rut);

        if (estudiante != null) {
            ResponseEntity<List<Cuota>> response = restTemplate.exchange(
                    "http://localhost:8080/cuotas/cuotasPendientes" + estudiante.getId(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Cuota>>() {}
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                List<Cuota> cuotas = response.getBody();
                model.addAttribute("estudiante", estudiante);
                model.addAttribute("cuotas", cuotas);
            }
        }

        return "pagarcuotas";
    }

    @GetMapping("/{estudianteId}/anos-desde-egreso")
    public ResponseEntity<Integer> obtenerAnosDesdeEgreso(@PathVariable Long estudianteId) {
        try {
            Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
            int anosDesdeEgreso = estudianteService.anosDesdeEgreso(estudiante);
            return ResponseEntity.ok(anosDesdeEgreso);
        } catch (EstudianteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{estudianteId}/tipo-colegio-procedencia")
    public ResponseEntity<String> obtenerTipoColegioProcedencia(@PathVariable Long estudianteId) {
        try {
            String tipoColegioProcedencia = estudianteService.obtenerTipoColegioProcedencia(estudianteId);
            if (tipoColegioProcedencia != null) {
                return ResponseEntity.ok(tipoColegioProcedencia);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (EstudianteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

