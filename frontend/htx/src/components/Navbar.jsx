import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => (
    <nav className="navbar navbar-expand-lg navbar-dark bg-success fixed-top">
        <div className="container">
            <Link to="/" className="navbar-brand font-weight-bold">Main</Link>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link to="/login" className="nav-link font-weight-bold">Login</Link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
);

export default Navbar;
