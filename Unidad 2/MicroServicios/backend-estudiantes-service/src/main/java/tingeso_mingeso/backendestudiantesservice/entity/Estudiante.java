package tingeso_mingeso.backendestudiantesservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "estudiante")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Estudiante", unique = true, nullable = false, columnDefinition = "serial")
    private Long id;
    private String rut;
    private String nombres;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private int tipo_colegio;
    private String nombre_colegio;
    private LocalDate anio_egreso;
    private LocalDate anio_ingreso;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
