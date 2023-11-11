package tingeso_mingeso.backendcuotasservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tingeso_mingeso.backendcuotasservice.entity.Cuota;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Estudiante {
    private Long id;

    private String nombres;

    private String fechaNacimiento;

    private String apellidos;

    private String rut;


    private String tipoColegioDeProcedencia;

    private int anoEgresoColegio;
    private String nombreColegio;

    private boolean pagado;

    private double arancelMensual;

    private int numeroCuotas;


    private List<Cuota> cuotasPagos;
}