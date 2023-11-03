package tingeso_mingeso.backendcuotasservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso_mingeso.backendcuotasservice.entity.Cuota;
import tingeso_mingeso.backendcuotasservice.model.Estudiante;
import tingeso_mingeso.backendcuotasservice.service.CuotaService;

import java.util.List;

@RestController
@RequestMapping("/cuotas")
public class CuotasController {
    @Autowired
    CuotaService cuotasService;

    @GetMapping("/{rut}/{cuotas}")
    public ResponseEntity<List<Cuota>> cuotas(@PathVariable("rut") String rut, @PathVariable("cuotas") String cuotas){
        Estudiante estudiante = cuotasService.findByRut(rut);
        System.out.println(estudiante);
        if(estudiante != null){
            if(cuotasService.findCuotaByRut(estudiante.getRut()).isEmpty()){
                cuotasService.descuentoArancel_generacionCuotas(estudiante, Integer.parseInt(cuotas));
                List<Cuota> cuotasentity = cuotasService.findCuotaByRut(estudiante.getRut());
                System.out.println(cuotasentity);
                return ResponseEntity.ok(cuotasentity);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<List<Cuota>> listado(@PathVariable("rut") String rut){
        System.out.println("Listar");
        System.out.println("rut: "+rut+"\n");
        Estudiante estudianteEntity = cuotasService.findByRut(rut);
        System.out.println(estudianteEntity);
        if(estudianteEntity != null){
            List<Cuota> cuotasEntities = cuotasService.findCuotaByRut(estudianteEntity.getRut());
            return ResponseEntity.ok(cuotasEntities);
        }
        return ResponseEntity.ok(null);
    }
}
