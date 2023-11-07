package tingeso_mingeso.backendcuotasservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso_mingeso.backendcuotasservice.Excepciones.CuotaPagoAlreadyPaidException;
import tingeso_mingeso.backendcuotasservice.Excepciones.CuotaPagoNotFoundException;
import tingeso_mingeso.backendcuotasservice.entity.Cuota;
import tingeso_mingeso.backendcuotasservice.model.Estudiante;
import tingeso_mingeso.backendcuotasservice.service.CuotaService;

import java.util.List;

@RestController
@RequestMapping("/cuotas")
public class CuotaController {

    private final CuotaService cuotaService;



    @Autowired
    public CuotaController(CuotaService cuotaService) {
        this.cuotaService = cuotaService;

    }

    @PostMapping("/generar-cuotas/{estudianteId}")
    public String generarCuotasDePago(@PathVariable Long estudianteId, int numeroCuotas) {
        cuotaService.obtenerCuotaPorId(estudianteId);
        cuotaService.generarCuotasDePago(estudianteId, numeroCuotas);
        return "redirect:/estudiantes/lista";
    }

    @GetMapping("/estudiante/{estudianteId}")
    public List<Cuota> listarCuotasDeEstudiante(@PathVariable Long estudianteId) {
        return cuotaService.listarCuotasDeEstudiante(estudianteId);
    }
    @PostMapping("/pagar")
    public ResponseEntity<String> pagarCuota(@RequestParam Long cuotaId) {
        try {
            cuotaService.pagarCuota(cuotaId);
            return new ResponseEntity<String>("pagada", HttpStatus.OK);
        } catch (CuotaPagoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cuota.");
        } catch (CuotaPagoAlreadyPaidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuota ya ha sido pagada.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el pago de la cuota.");
        }
    }
    @PostMapping("/cuotas/pagar")
    public String pagarCuotavista(@RequestParam("cuotaId") Long cuotaId) {
        Cuota cuota = cuotaService.obtenerCuotaPorId(cuotaId);
        cuota.setPagada(true);
        cuotaService.guardarCuota(cuota);

        return "pagarcuotas";
    }
    @GetMapping("/cuotasPendientes")
    public List<Cuota> cuotasPendientes(Estudiante estudiante){
        return cuotaService.obtenerCuotasPendientesPorEstudiante(estudiante);
    }

}