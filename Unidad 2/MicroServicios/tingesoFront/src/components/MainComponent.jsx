import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import HeaderComponent from './Headers/HeaderComponent';
import '../styles/main.css';

const MainComponent = () => {
  const navigate = useNavigate();

  const redirectToRegistrarEstudiante = () => {
    navigate('/estudiantes/registro');
  };

  return (
    <div className="container">
      <HeaderComponent></HeaderComponent>
      <h1>Menú Principal</h1>

      <Link to="/estudiantes/buscar-estudiante">
        <button className="buttonStyle">Reporte Estudiante</button>
      </Link>

      <Link to="/estudiantes/buscar-estudiante2">
        <button className="buttonStyle">Pagar cuota</button>
      </Link>

      <button className="buttonStyle" onClick={redirectToRegistrarEstudiante}>
        Registrar Estudiante
      </button>

      <Link to="/planillapago/buscar-estudiante">
        <button className="buttonStyle">Buscar Planilla de Pago</button>
      </Link>

      <h2>Importar Archivo de Notas</h2>
      <form action="/notas-examen/importar" method="post" encType="multipart/form-data">
        <input type="file" name="archivo" accept=".xlsx, .xls" className="buttonStyle" />
        <button type="submit" className="buttonStyle">
          Importar
        </button>
      </form>
    </div>
  );
};

export default MainComponent;