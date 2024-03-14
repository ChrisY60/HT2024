import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
//import { API_BASE_URL, REGISTER_ENDPOINT } from '../api';
//import { useAuth } from './Auth';

const Register = () => {
    const navigate = useNavigate();
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    //const { login } = useAuth();

    /*const handleRegister = async () => {
        try {
            const response = await axios.post(`${API_BASE_URL}${REGISTER_ENDPOINT}`, {
                name: name,
                email: email,
                password: password,
            });

            const token = response.data.token;

            login(token);
            navigate('/');
            console.log('Registration successful');
        } catch (error) {
            console.error('Registration failed', error);
        }
    };*/

    return (
        <div className="d-flex align-items-center justify-content-center vh-100">
            <div className="card bg-white p-4 rounded-lg shadow-sm">
                <h1 className="text-3xl font-weight-bold mb-4">Register</h1>
                <form>
                    <div className="mb-2">
                        <label className="form-label">Name:</label>
                        <input
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            className="form-control"
                        />
                    </div>
                    <div className="mb-2">
                        <label className="form-label">Email:</label>
                        <input
                            type="text"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="form-control"
                        />
                    </div>
                    <div className="mb-2">
                        <label className="form-label">Password:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className="form-control"
                        />
                    </div>
                    <div className="mb-2">
                        <label className="form-label">Confirm Password:</label>
                        <input
                            type="password"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            className="form-control"
                        />
                    </div>
                    <button
                        type="button"
                        //onClick={handleRegister}
                        className="btn btn-success btn-lg btn-block mt-4"
                    >
                        Register
                    </button>
                </form>
                <p className="mt-2">
                    Already have an account? <a href="/login" className="text-decoration-none text-primary">Login</a>
                </p>
            </div>
        </div>
    );
};

export default Register;
