import React from 'react';
import { useNavigate } from 'react-router-dom';

function HeaderComponent() {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate('/');
    };

    return (
        <div>
            <header className="header">
                <div className="title">
                    <h1>Menú Principal</h1>
                </div>
                <nav></nav>
                <a className="btn" href="/agregar_estudiante">
                    
                </a>
            </header>
        </div>
    );
}

export default HeaderComponent;
