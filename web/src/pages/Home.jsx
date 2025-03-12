import React, { useEffect, useState } from 'react';
import axios from "axios";
import Users from "./components/Users.jsx";
import TeamCalendar from "./components/TeamCalendar.jsx";
import TeamTodo from "./components/TeamTodo.jsx";
import ScheduleView from "./components/ScheduleView.jsx"
import styles from "../css/pages/Home.module.scss";
import dayjs from "dayjs";

const Home = () => {
    const [data, setData] = useState("");
    const [selectedDate, setSelectedDate] = useState(dayjs().format("YYYY-MM-DD"));
    const [role, setRole] = useState("OWNER"); // <<<--- 더미데이터 !!!!!

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
                <TeamCalendar selectedDate={selectedDate} setSelectedDate={setSelectedDate} role={role} />
                <ScheduleView selectedDate={selectedDate} role={role} />
            </section>
            <section>
                <TeamTodo selectedDate={selectedDate} />
            </section>
        </div>
    );
};

export default Home;
