import React from 'react';
import styles from "../../css/pages/Home.module.scss";
import dayjs from "dayjs";

const dummySchedules = [
    {
        id: 1,
        teamCode: "TEAM001",
        title: "백엔드 스터디",
        description: "Spring Boot 학습 모임",
        category: "일정",
        startDateAt: "2025-03-09T14:00:00",
    },
    {
        id: 4,
        teamCode: "TEAM001",
        title: "팀회의",
        description: "플젝 회의합시데이",
        category: "회의",
        startDateAt: "2025-03-10T14:00:00",
    },
];

const ScheduleView = ({ selectedDate }) => {
    const formattedDate = dayjs(selectedDate).format("YYYY-MM-DD");

    const filteredSchedules = dummySchedules.filter(schedule =>
        dayjs(schedule.startDateAt).format("YYYY-MM-DD") === formattedDate
    );

    return (
        <section className={styles.ScheduleViewContainer}>
            {filteredSchedules.length > 0 ? (
                filteredSchedules.map(schedule => (
                    <div key={schedule.id}>
                        <h3>{schedule.title}</h3>
                        <p>{schedule.description}</p>
                    </div>
                ))
            ) : (
                <p>일정이 없습니다.</p>
            )}
        </section>
    );
};

export default ScheduleView;
