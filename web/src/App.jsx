import React from "react";
import { Provider } from "react-redux";
import {Route, Routes, BrowserRouter as Router} from "react-router-dom";
import Store from "./store/Store";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import Home from "./pages/Home.jsx";
import Setting from "./pages/Setting.jsx";
import UserProfile from "./pages/UserProfile.jsx";
import SignUp from "./pages/SignUp/SignUp.jsx";

function App() {
    return (
        <Provider store={Store}>
            <Router>
                <Routes>
                    <Route>
                        <Route path="/" element={<Home />}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="/SignUp" element={<SignUp/>}/>
                        <Route path="/register" element={<Register/>}/>
                        <Route path="/setting" element={<Setting/>}/>
                        <Route path="/profile" element={<UserProfile/>}/>
                    </Route>
                </Routes>
            </Router>
        </Provider>
    );
}

export default App;
