package tingeso_mingeso.backendcuotasservice.model;

import tingeso_mingeso.backendcuotasservice.entity.Cuota;

import java.util.List;

public class PlanillaPago {
    private Long id;
    private int mes;
    private int ano;
    private Double montoTotal;
    private String rutEstudiante;
    private Estudiante estudiante;
    private List<Cuota> cuotas;
}
