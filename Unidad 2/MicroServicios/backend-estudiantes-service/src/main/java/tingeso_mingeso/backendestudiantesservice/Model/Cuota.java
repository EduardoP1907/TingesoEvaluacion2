package tingeso_mingeso.backendestudiantesservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tingeso_mingeso.backendestudiantesservice.entity.Estudiante;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuota {

    private Long id;
    private Double monto;
    private Double numeroCuota;
    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
    private boolean pagada;

    private Estudiante estudiante;

}
