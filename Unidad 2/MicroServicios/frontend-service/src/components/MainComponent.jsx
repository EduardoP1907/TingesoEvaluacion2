import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import HeaderComponent from './Headers/HeaderComponent';
import '../styles/main.css';

const MainComponent = () => {
  const navigate = useNavigate();

  const redirectToRegistrarEstudiante = () => {
    navigate('/agregar_estudiante');
  };
  const handleClickGenerarCuotas = () => {
    navigate("/generar_cuotas");
  };
  const handleClickListaestudiantes = () => {
    navigate("/lista_estudiantes");
  };
  const handleClickListaCuotas = () => {
    navigate("/lista_cuotas");
  };

  return (
    <div className="container">
      <HeaderComponent></HeaderComponent>
      <h1>Menú Principal</h1>

      <button className="buttonStyle" onClick={handleClickListaestudiantes}>
        Lista de estudiantes
      </button>

      <button className="buttonStyle" onClick={handleClickGenerarCuotas}>
        Generar Cuota
      </button>

      <button className="buttonStyle" onClick={redirectToRegistrarEstudiante}>
        Registrar Estudiante
      </button>
      <button className="buttonStyle" onClick={handleClickListaCuotas}>
        Lista de Cuotas del estudiante
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
