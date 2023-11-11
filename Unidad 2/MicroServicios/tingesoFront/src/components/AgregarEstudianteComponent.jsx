import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import HeaderComponent from "./Headers/HeaderComponent";
import 'bootstrap/dist/css/bootstrap.css'
import Swal from 'sweetalert2';
import { Form, Button } from 'react-bootstrap';
import EstudianteService from "../services/EstudianteService";

function AgregarEstudianteComponent(props){

    const initialState = {
        rut: "",
        nombres: "",
        apellidos: "",
        fecha_nacimiento: "",
        tipo_colegio: "",
        nombre_colegio: "",
        anio_egreso: "",
        numero_cuotas: 1, // Agregamos el campo para el número de cuotas
    };

    const [input, setInput] = useState(initialState);
    const navigate = useNavigate();
    const navigateHome = () => {
        navigate("/");
    };
    
    const changeRutHandler = event => {
        setInput({ ...input, rut: event.target.value });
    };
    const changeNombresHandler = event => {
        setInput({ ...input, nombres: event.target.value });
    };
    const changeApellidoHandler = event => {
        setInput({ ...input, apellidos: event.target.value });
    };
    const changeFechaNacimientoHandler = event => {
        setInput({ ...input, fecha_nacimiento: event.target.value });
    };
    const changeAnioEgresoIDHandler = event => {
        setInput({ ...input, anio_egreso: event.target.value });
    };
    const changeTipoColegioHandler = event => {
        // Al cambiar el tipo de colegio, ajustamos automáticamente el número de cuotas
        const tipoColegio = event.target.value;
        let maxCuotas;
        if (tipoColegio === "Municipal") {
            maxCuotas = 10;
        } else if (tipoColegio === "Subvencionado") {
            maxCuotas = 7;
        } else if (tipoColegio === "Privado") {
            maxCuotas = 4;
        } else {
            maxCuotas = 1; // Valor predeterminado
        }
        setInput({ ...input, tipo_colegio: tipoColegio, numero_cuotas: 1, max_cuotas: maxCuotas });
    };
    const changeNombreColegioHandler = event => {
        setInput({ ...input, nombre_colegio: event.target.value });
    };
    const changeNumeroCuotasHandler = event => {
        setInput({ ...input, numero_cuotas: event.target.value });
    };

    
    const ingresarEstudiante = (event) => {
        Swal.fire({
            title: "¿Desea registrar el estudiante?",
            text: "No podrá cambiarse en caso de equivocación",
            icon: "question",
            showDenyButton: true,
            confirmButtonText: "Confirmar",
            confirmButtonColor: "rgb(68, 194, 68)",
            denyButtonText: "Cancelar",
            denyButtonColor: "rgb(190, 54, 54)",
        }).then((result) => {
            if (result.isConfirmed) {
                let newEstudiante = {
                    rut: input.rut,
                    nombres: input.nombres,
                    apellidos: input.apellidos,
                    fecha_nacimiento: input.fecha_nacimiento,
                    tipo_colegio: input.tipo_colegio,
                    nombre_colegio: input.nombre_colegio,
                    anio_egreso: input.anio_egreso,
                    anio_ingreso: "",
                    numero_cuotas: input.numero_cuotas,
                };
                EstudianteService.createEstudiante(newEstudiante);
                Swal.fire({
                    title: "Enviado",
                    timer: 2000,
                    icon: "success",
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                      },
                    })
                navigateHome();
            }
        });
    };

    return(
        <div className="general">
            <HeaderComponent />
            <div className="container-create" style={{ maxWidth: "600px", margin: "auto" }}>
                <Form>
                    <Form.Group className="mb-3" controlId="rut" value={input.rut} onChange={changeRutHandler}>
                        <Form.Label className="agregar">Rut:</Form.Label>
                        <Form.Control className="agregar" type="text" name="rut"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="nombres" value={input.nombres} onChange={changeNombresHandler}>
                        <Form.Label className="agregar">Nombres:</Form.Label>
                        <Form.Control className="agregar" type="text" name="nombres"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="apellidos" value={input.apellido} onChange={changeApellidoHandler}>
                        <Form.Label className="agregar">Apellidos:</Form.Label>
                        <Form.Control className="agregar" type="text" name="apellidos"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="fecha_nacimiento" value={input.fecha_nacimiento} onChange={changeFechaNacimientoHandler}>
                        <Form.Label className="agregar">Fecha de Nacimiento:</Form.Label>
                        <Form.Control className="agregar" type="date" name="fecha_nacimiento"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="anio_egreso" value={input.anio_egreso} onChange={changeAnioEgresoIDHandler}>
                        <Form.Label className="agregar">Año de egreso del colegio:</Form.Label>
                        <Form.Control className="agregar" type="date" name="anio_egreso"/>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="tipo_colegio">
                        <Form.Label className="agregar"> Tipo de colegio:</Form.Label>
                        <select className="agregar" name="tipo_colegio" required value={input.tipo_colegio} onChange={changeTipoColegioHandler}>
                            <option value="" disabled>Tipo colegio</option>
                            <option value="Municipal">Municipal</option>
                            <option value="Subvencionado">Subvencionado</option>
                            <option value="Privado">Privado</option>
                        </select>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="numero_cuotas" value={input.numero_cuotas} onChange={changeNumeroCuotasHandler}>
                        <Form.Label className="agregar">Número de Cuotas:</Form.Label>
                        <Form.Control className="agregar" as="select" name="numero_cuotas">
                            {[...Array(input.max_cuotas)].map((_, index) => (
                                <option key={index + 1} value={index + 1}>{index + 1}</option>
                            ))}
                        </Form.Control>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="nombre_colegio" value={input.nombre_colegio} onChange={changeNombreColegioHandler}>
                        <Form.Label className="agregar">Nombre del colegio:</Form.Label>
                        <Form.Control className="agregar" type="text" name="nombre_colegio"/>
                    </Form.Group>

                    <Button className="boton" onClick={ingresarEstudiante}>Registrar Estudiante</Button>
                </Form>
            </div>
        </div>
    );
}

export default AgregarEstudianteComponent;
