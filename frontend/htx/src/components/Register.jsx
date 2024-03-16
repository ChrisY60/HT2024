import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useAuth } from './Auth';

const Register = () => {
    const navigate = useNavigate();
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [school, setSchool] = useState('');
    const [accessCode, setAccessCode] = useState('');
    const [role, setRole] = useState('');
    const [studentClass, setStudentClass] = useState(0);
    const [studentClassLetter, setStudentClassLetter] = useState('');
    const [classNumber, setClassNumber] = useState(0);
    const { login } = useAuth();

    const handleRegister = async () => {
        try {
            var response;
            const [firstName, middleName, lastName] = name.split(' ');
            const studentClassInfo = studentClass + studentClassLetter;

            if(role === 'STUDENT') {
                response = await axios.post(`http://localhost:8080/api/v1/auth/register-student`, {
                    firstName: firstName,
                    middleName: middleName,
                    lastName: lastName,
                    email: email,
                    password: password,
                    school: school,
                    accessCode: accessCode,
                    classNumber: classNumber,
                    studentClass: studentClassInfo,
                });
            } else {
                response = await axios.post(`http://localhost:8080/api/v1/auth/register-teacher`, {
                    firstName: firstName,
                    middleName: middleName,
                    lastName: lastName,
                    email: email,
                    password: password,
                    school: school,
                    accessCode: accessCode,
                    role: role,
            });}

            const token = response.data.token;

            login(token);
            navigate('/');
            console.log('Registration successful');
        } catch (error) {
            console.error('Registration failed', error);
        }
    };

    return (
        <div className="d-flex align-items-center justify-content-center">
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
                        <label className="form-label">School:</label>
                        <select
                            className="form-control"
                            value={school}
                            onChange={(e) => setSchool(e.target.value)}
                        >
                            <option value="">Choose...</option>
                            <option value="TUES">TUES</option>
                            <option value="2">Two</option>
                            <option value="3">Three</option>
                        </select>
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
                    <div className="mb-2">
                        <label className="form-label">Role:</label>
                        <select
                            className="form-control"
                            value={role}
                            onChange={(e) => setRole(e.target.value)}
                        >
                            <option value="">Choose...</option>
                            <option value="TEACHER">Teacher</option>
                            <option value="STUDENT">Student</option>
                        </select>
                    </div>
                    <div className="mb-2">
                        <label className="form-label">Access Code:</label>
                        <input
                            type="text"
                            value={accessCode}
                            onChange={(e) => setAccessCode(e.target.value)}
                            className="form-control"
                        />
                    </div>
                    {role === 'STUDENT' && (
                        <>
                            <div className="mb-2">
                                <label className="form-label">Class:</label>
                                <select
                                    className="form-control"
                                    value={studentClass}
                                    onChange={(e) => setStudentClass(e.target.value)}
                                >
                                    <option value="">Choose...</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>
                            </div>
                            <div className="mb-2">
                                <label className="form-label">Class Letter:</label>
                                <select
                                    className="form-control"
                                    value={studentClassLetter}
                                    onChange={(e) => setStudentClassLetter(e.target.value)}
                                >
                                    <option value="">Choose...</option>
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="V">V</option>
                                    <option value="G">G</option>
                                </select>
                            </div>
                            <div className="mb-2">
                                <label className="form-label">Class Number:</label>
                                <input
                                    type="number"
                                    placeholder="Class Number"
                                    className="form-control"
                                    value={classNumber}
                                    onChange={(e) => setClassNumber(e.target.value)}
                                />
                            </div>
                        </>
                    )}
                    <button
                        type="button"
                        onClick={handleRegister}
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
