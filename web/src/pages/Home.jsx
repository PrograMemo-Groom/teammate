import React, {useEffect, useState} from 'react';
import axios from "axios";
import Users from "./components/Users.jsx";
import TeamCalendar from "./components/TeamCalendar.jsx";
import TeamTodo from "./components/TeamTodo.jsx";
import styles from "../css/pages/Home.module.scss";

const Home = () => {
    const [data, setData] = useState("");

    useEffect(() => {
        axios
            .get('/api/data')
            .then((res) => setData(res.data))
            .catch((err) => console.log(err));
    }, []);

    return (
        <div className={styles.container}>
            <section>
                <Users />
                <TeamCalendar />
            </section>
            <section>
                <TeamTodo />
            </section>
        </div>
    );
};

export default Home;
