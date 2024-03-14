import logo from './logo.svg';
import React from 'react';
import './App.css';
import Login from "./components/Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./components/Register";
import Navbar from "./components/Navbar";
import Main from "./components/Main";
import Assignments from "./components/Assignments";

function App() {
  return (
    <Router>
        <Navbar />
      <Routes>
        <Route path="/" element={<Main />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path={"/assignment"} element={<Assignments />} />
      </Routes>
    </Router>
  );
}

export default App;
