package tingeso_mingeso.backendcuotasservice.entity;

import lombok.*;
import tingeso_mingeso.backendcuotasservice.model.Estudiante;
import tingeso_mingeso.backendcuotasservice.model.PlanillaPago;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "cuotapago")
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    private Double numeroCuota;
    private LocalDate fechaVencimiento;
    private LocalDate fechaPago;
    private boolean pagada;
    @ManyToOne
    @JoinColumn(name = "estudiante_id") // Nombre de la columna que mapea la relación
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "planilla_pago_id")
    private PlanillaPago planillaPago;

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
}
