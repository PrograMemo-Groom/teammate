import axios from "axios";
import React, {useEffect, useState} from "react";
import "./App.css";
import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";

function App() {
    const [data, setData] = useState("");

    useEffect(() => {
        axios
            .get('/api/data')
            .then((res) => setData(res.data))
            .catch((err) => console.log(err));
    }, []);

    const Main = () => (
        <div>
            <img src="./vite.svg" alt="logo"/>
            <div>받아온 값 : {data}</div>
        </div>
    )
    return (
        <Router>
            <Routes>
                <Route>
                    <Route path="/" element={<Main />}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                </Route>
            </Routes>
        </Router>
    );
}

export default App;
