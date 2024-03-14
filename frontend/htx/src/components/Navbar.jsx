import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => (
    <nav className="navbar navbar-expand-lg navbar-dark fixed-top shadow-sm" style={{backgroundColor: "#157145"}}>
        <div className="container">
            {/* Left side */}
            <div className="navbar-nav mr-auto">
                <Link to="/" className="nav-item nav-link font-weight-bold">Home</Link>
                <Link to="/" className="nav-item nav-link font-weight-bold">Grade</Link>
            </div>
            {/* Right side */}
            <div className="navbar-nav ml-auto">
                <Link to="/" className="nav-item nav-link font-weight-bold">Login</Link>
                <Link to="/register" className="nav-item nav-link font-weight-bold">Register</Link>
            </div>
        </div>
    </nav>
);

export default Navbar;
