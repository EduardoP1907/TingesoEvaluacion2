import axios from 'axios';

const ESTUDIANTE_API_URL = "http://localhost:8080/estudiantes/";

class EstudianteService {

    getEstudiantes() {
        return axios.get(ESTUDIANTE_API_URL);
    }

    getEstudianteByRut(rut) {
        return axios.get(ESTUDIANTE_API_URL + rut);
    }

    createEstudiante(estudiante) {
        return axios.post(ESTUDIANTE_API_URL, estudiante + '/registro');
    }

    obtenerAnosDesdeEgreso(estudianteId) {
        return axios.get(ESTUDIANTE_API_URL + estudianteId + '/anos-desde-egreso');
    }

    obtenerTipoColegioProcedencia(estudianteId) {
        return axios.get(ESTUDIANTE_API_URL + estudianteId + '/tipo-colegio-procedencia');
    }

}

export default new EstudianteService();