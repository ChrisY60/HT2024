import logo from './logo.svg';
import React from 'react';
import './App.css';
import Login from "./components/Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./components/Register";
import Navbar from "./components/Navbar";
import Main from "./components/Main";
import Assignments from "./components/Assignments";
import Subject from "./components/Subject"
import Grades from "./components/Grades";
import {AuthProvider} from "./components/Auth";
import StudentAssignments from "./components/StudentAssignments";

function App() {
    return (
        <Router>
            <AuthProvider>
                <Navbar />
                <Routes>
                    <Route path="/" element={<Main />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path={"/assignment"} element={<Assignments />} />
                    <Route path={"/subject"} element={<Subject />} />
                    <Route path={"/grades"} element={<Grades />} />
                    <Route path={"/students"} element={<StudentAssignments />} />
                </Routes>
            </AuthProvider>
        </Router>
  );
}

export default App;
