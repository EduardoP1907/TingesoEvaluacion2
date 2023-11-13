import React, { useState, useEffect } from 'react';
import CuotasService from '../services/CuotasService';
import HeaderComponent from './Headers/HeaderComponent';
import EstudianteService from '../services/EstudianteService';



function CuotasEstudianteComponent() {
    const [rutEstudiante, setRutEstudiante] = useState('');
    const [cuotas, setCuotas] = useState([]);
    const [estudiante, setEstudiante] = useState(null);
  
    const buscarEstudiante = () => {
      if (rutEstudiante) {
        EstudianteService.getEstudianteByRut(rutEstudiante).then((res) => {
          setEstudiante(res.data);
          cargarCuotas(res.data.rut);
        });
      }
    };
  
    const cargarCuotas = (rut) => {
      CuotasService.getCuotas(rut).then((res) => {
        setCuotas(res.data);
      });
    };
  
    const pagarCuota = (idCuota) => {
      CuotasService.pagarCuota(idCuota).then((res) => {
        setCuotas((prevCuotas) =>
          prevCuotas.map((cuota) =>
            cuota.id === idCuota ? { ...cuota, estado: true } : cuota
          )
        );
      });
    };
  
    return (
      <div className="general">
        <HeaderComponent />
        <div align="center" className="container-2">
          <h1>
            <b>Listado de Cuotas</b>
          </h1>
          <div>
            <label>RUT del Estudiante:</label>
            <input
              type="text"
              value={rutEstudiante}
              onChange={(e) => setRutEstudiante(e.target.value)}
            />
            <button onClick={buscarEstudiante}>Buscar</button>
          </div>
          {estudiante && (
            <div>
              <h2>Estudiante: {estudiante.nombres} {estudiante.apellidos}</h2>
              <table border="1" className="content-table">
                <thead>
                  <tr>
                    <th>ID Cuota</th>
                    <th>Número de Cuota</th>
                    <th>Valor Cuota</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {cuotas.map((cuota) => (
                    <tr key={cuota.id}>
                      <td>{cuota.id}</td>
                      <td>{cuota.numeroCuota}</td>
                      <td>{cuota.valorCuota}</td>
                      <td>{cuota.estado ? 'Pagada' : 'Pendiente'}</td>
                      <td>
                        {!cuota.estado && (
                          <button onClick={() => pagarCuota(cuota.id)}>
                            Pagar Cuota
                          </button>
                        )}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    );
  }
  
  export default CuotasEstudianteComponent;