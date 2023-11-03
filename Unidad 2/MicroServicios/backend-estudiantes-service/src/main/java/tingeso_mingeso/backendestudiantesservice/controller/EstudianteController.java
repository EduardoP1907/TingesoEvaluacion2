package tingeso_mingeso.backendestudiantesservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;

import tingeso_mingeso.backendestudiantesservice.service.EstudianteService;

import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @PostMapping()
    public ResponseEntity<Estudiante> newEstudiante(@RequestBody Estudiante estudiante) {

        LocalDate anio_ingreso = LocalDate.now();
        estudiante.setAnio_ingreso(anio_ingreso);
        estudianteService.save(estudiante);
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/listaestudiantes")
    public ResponseEntity<List<Estudiante>> listar() {
        List<Estudiante> estudiante = estudianteService.findAll();
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Estudiante> findByRut(@PathVariable("rut") String rut) {
        Estudiante estudiante = estudianteService.findByRut(rut);
        System.out.println(estudiante);
        return ResponseEntity.ok(estudiante);
    }
}