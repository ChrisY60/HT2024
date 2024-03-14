import React, { useState } from 'react';
import {Link, useNavigate} from 'react-router-dom';
import axios from 'axios';
//import { useAuth } from './Auth';

const Login = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    //const { login } = useAuth();

    /*const handleLogin = async () => {
        try {
            const response = await axios.post(`${API_BASE_URL}${LOGIN_ENDPOINT}`, {
                email: email,
                password: password,
            });

            const token = response.data.token;

            login(token);
            navigate('/');
            console.log('Login successful');
        } catch (error) {
            console.error('Login failed', error);
        }
    };*/

    return (
        <div className="d-flex align-items-center justify-content-center vh-100">
            <div className="card bg-white p-4 rounded-lg shadow-sm">
                <h1 className="text-3xl font-weight-bold mb-4">Login</h1>
                <form>
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
                    <button
                        type="button"
                        //onClick={handleLogin}
                        className="btn btn-success btn-lg btn-block mt-4"
                    >
                        Login
                    </button>
                </form>
                <p className="mt-2">
                    Don't have an account? <a href="/register" className="text-decoration-none text-primary">Register</a>
                </p>
            </div>
        </div>
    );

};

export default Login;
