import axios from "axios";
import React, {useEffect, useState} from "react";
import "./App.css";
import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import Home from "./pages/Home.jsx";

function App() {
    return (
        <Router>
            <Routes>
                <Route>
                    <Route path="/" element={<Home />}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                </Route>
            </Routes>
        </Router>
    );
}

export default App;
