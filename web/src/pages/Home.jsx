import React, {useEffect, useState} from 'react';
import axios from "axios";
import Users from "./components/Users.jsx";
import TeamCalendar from "./components/TeamCalendar.jsx";
import TeamTodo from "./components/TeamTodo.jsx";
import ScheduleView from "./components/ScheduleView.jsx"
import styles from "../css/pages/Home.module.scss";
import dayjs from "dayjs";

const Home = () => {
    const [data, setData] = useState("");
    const [selectedDate, setSelectedDate] = useState(dayjs().format("YYYY-MM-DD")); // 캘린더에서 선택된 날짜,,,!

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
                {/* 선택된 날짜를 변경할 수 있도록 setSelectedDate 전달 */}
                <TeamCalendar selectedDate={selectedDate} setSelectedDate={setSelectedDate} />
                <ScheduleView />
            </section>
            <section>
                {/* 선택된 날짜의 todo 만 표시하도록 전달 */}
                <TeamTodo selectedDate={selectedDate} />
            </section>
        </div>
    );
};

export default Home;
