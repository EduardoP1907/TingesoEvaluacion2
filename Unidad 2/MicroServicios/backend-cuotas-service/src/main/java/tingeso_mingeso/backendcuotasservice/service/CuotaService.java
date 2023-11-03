package tingeso_mingeso.backendcuotasservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso_mingeso.backendcuotasservice.entity.Cuota;
import tingeso_mingeso.backendcuotasservice.model.Estudiante;
import tingeso_mingeso.backendcuotasservice.repository.CuotaRepository;

import java.util.List;


@Service
public class CuotaService {
    @Autowired
    CuotaRepository cuotaRepository;

    @Autowired
    DescuentosService descuentosService;

    @Autowired
    RestTemplate restTemplate;

    public void descuentoArancel_generacionCuotas(Estudiante estudiante, int cantidadCuotas){
        if(descuentosService.PreguntarCuotas(estudiante.getTipo_colegio(), cantidadCuotas)){
            int descuentoTipoColegio = descuentosService.descuentoTipoColegio(estudiante.getTipo_colegio());
            int descuentoAnio = descuentosService.descuentoEgresoColegio(estudiante.getAnio_egreso(), estudiante.getAnio_ingreso());
            int descuento_total = descuentoTipoColegio + descuentoAnio;
            float descuento_decimal = (float) descuento_total/100;
            int valorArancel = (int) (descuentosService.getArancel() * (1-descuento_decimal));
            float cuota = (float) valorArancel/cantidadCuotas;
            int i = 1;
            Cuota cuotasEntity;
            while (i <= cantidadCuotas){
                cuotasEntity = new Cuota();
                cuotasEntity.setNumeroCuota(i);
                cuotasEntity.setValorCuota(cuota);
                cuotasEntity.setRut(estudiante.getRut());
                cuotasEntity.setEstado(true);
                cuotaRepository.save(cuotasEntity);
                i++;
            }
        }
    }

    public Estudiante findByRut(String rut){
        System.out.println("rut: "+rut);
        ResponseEntity<Estudiante> response = restTemplate.exchange(
                "http://localhost:8080/estudiante/"+rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Estudiante>() {}
        );
        return response.getBody();
    }
    public List<Cuota> findCuotaByRut(String rut){
        return cuotaRepository.findCuotaByRut(rut);
    }
}
