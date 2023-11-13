import React, { useState, useEffect } from "react";
import EstudianteService from '../services/EstudianteService'
import HeaderComponent from './Headers/HeaderComponent'
import '../styles/ListadoEstudianteComponent.css';

function ListadoEstudianteComponent() {

    const [estudianteEntity, setEstudianteEntity] = useState([]);

    useEffect(() => {
        EstudianteService.getEstudiantes().then((res) => {
            console.log("Response data Estudiante:", res.data);
            setEstudianteEntity(res.data);
        });
    }, []);

    return (
        <div className="general">
            <HeaderComponent />
            <div align="center" className="container-2">
                <h1><b>Listado de Acopio de Leche</b></h1>
                <table className="content-table">
                    <thead>
                        <tr>
                            <th>RUT</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Fecha de Nacimiento</th>
                            <th>Tipo de Colegio</th>
                            <th>Nombre de Colegio</th>
                            <th>Año de egreso</th>
                            <th>Año de ingreso</th>
                        </tr>
                    </thead>
                    <tbody>
                        {estudianteEntity.map((estudiante) => (
                            <tr key={estudiante.rut}>
                                <td>{estudiante.rut}</td>
                                <td>{estudiante.nombres}</td>
                                <td>{estudiante.apellidos}</td>
                                <td>{estudiante.fecha_nacimiento}</td>
                                <td>{estudiante.tipo_colegio}</td>
                                <td>{estudiante.nombre_colegio}</td>
                                <td>{estudiante.anio_egreso}</td>
                                <td>{estudiante.anio_ingreso ? estudiante.anio_ingreso : 'No disponible'}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <div className="legend-container">
                    <h2> Leyenda </h2>
                    <p><strong>Tipo Colegio 1:</strong> Municipal</p>
                    <p><strong>Tipo Colegio 2:</strong> Subvencionado</p>
                    <p><strong>Tipo Colegio 3:</strong> Privado</p>
                </div>
            </div>
        </div>
    )
}

export default ListadoEstudianteComponent;

