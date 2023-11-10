import axios from 'axios';

const CUOTAS_API_URL = "http://localhost:8080/cuotas/";

class CuotasService {

    generarCuotas(rut, cuotas) {
        return axios.post(CUOTAS_API_URL + "generar-cuotas/" + rut, cuotas);
    }

    getCuotas(rut) {
        return axios.get(CUOTAS_API_URL + "estudiante/" + rut);
    }

    pagarCuota(cuotaId) {
        return axios.post(CUOTAS_API_URL + "pagar", { cuotaId });
    }

    pagarCuotaVista(cuotaId) {
        return axios.post(CUOTAS_API_URL + "cuotas/pagar", { cuotaId });
    }

    obtenerCuotasPendientes(estudiante) {
        return axios.get(CUOTAS_API_URL + "cuotasPendientes", { params: { estudiante } });
    }
}

export default new CuotasService();