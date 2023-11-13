package tingeso_mingeso.backendestudiantesservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso_mingeso.backendestudiantesservice.entity.EstudianteEntity;
import tingeso_mingeso.backendestudiantesservice.service.EstudianteService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    EstudianteService estudianteService;

    @PostMapping()
    public ResponseEntity<EstudianteEntity> newEstudiante(@RequestBody EstudianteEntity estudiante) {
        LocalDate anio_ingreso = LocalDate.now();
        estudiante.setAnio_ingreso(anio_ingreso);
        estudianteService.save(estudiante);
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/")
    public ResponseEntity<List<EstudianteEntity>> listar() {
        List<EstudianteEntity> estudianteEntities = estudianteService.findAll();
        return ResponseEntity.ok(estudianteEntities);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<EstudianteEntity> findByRut(@PathVariable("rut") String rut) {
        EstudianteEntity estudianteEntity = estudianteService.findByRut(rut);
        System.out.println(estudianteEntity);
        return ResponseEntity.ok(estudianteEntity);
    }
}
