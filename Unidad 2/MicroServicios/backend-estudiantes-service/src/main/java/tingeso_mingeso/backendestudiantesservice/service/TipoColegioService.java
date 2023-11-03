package tingeso_mingeso.backendestudiantesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso_mingeso.backendestudiantesservice.entity.TipoColegio;
import tingeso_mingeso.backendestudiantesservice.repository.TipoColegioRepository;

@Service
public class TipoColegioService {
    @Autowired
    TipoColegioRepository tipoColegioRepository;

    public TipoColegio findByTipo(String tipo){
        TipoColegio tipoColegio = tipoColegioRepository.findByTipo(tipo);
        return tipoColegio;
    }
}
