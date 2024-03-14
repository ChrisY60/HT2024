import logo from './logo.svg';
import React from 'react';
import './App.css';
import Login from "./components/Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./components/Register";
import Navbar from "./components/Navbar";

function App() {
  return (
    <Router>
        <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

export default App;
