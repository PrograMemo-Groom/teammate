import React from "react";
import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import Home from "./pages/Home.jsx";
import Setting from "./pages/Setting.jsx";
import UserProfile from "./pages/UserProfile.jsx";

function App() {
    return (
        <Router>
            <Routes>
                <Route>
                    <Route path="/" element={<Home />}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route path="/setting" element={<Setting/>}/>
                    <Route path="/profile" element={<UserProfile/>}/>
                </Route>
            </Routes>
        </Router>
    );
}

export default App;
