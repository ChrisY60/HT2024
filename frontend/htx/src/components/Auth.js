import React, { createContext, useContext, useState } from 'react';
import * as jose from 'jose';
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem('token') || '');
    const navigate = useNavigate();

    const login = (newToken) => {
        setToken(newToken);
        localStorage.setItem('token', newToken);
    };

    const logout = () => {
        setToken('');
        localStorage.removeItem('token');
        navigate('/login');
    };

    const isTokenExpired = () => {
        try {
            const decodedToken = jose.decodeJwt(token);

            if (decodedToken && 'exp' in decodedToken) {
                const expirationTime = decodedToken.exp;
                const currentTime = Math.floor(Date.now() / 1000);

                if (currentTime >= expirationTime) {
                    alert('Your token has expired. Please log in again.');
                    logout();
                }
            }
        } catch (error) {
            console.error('Error decoding or validating token:', error);
        }
    };

    return (
        <AuthContext.Provider value={{ token, login, logout, isTokenExpired }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
