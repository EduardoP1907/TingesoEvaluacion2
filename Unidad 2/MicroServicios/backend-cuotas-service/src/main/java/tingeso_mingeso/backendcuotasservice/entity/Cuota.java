package tingeso_mingeso.backendcuotasservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "cuotas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuota {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rut;
    private int numeroCuota;
    private float valorCuota;
    private boolean estado;
}
