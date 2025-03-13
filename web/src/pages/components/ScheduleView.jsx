import React, { useState } from 'react';
import styles from "../../css/pages/Home.module.scss";
import dayjs from "dayjs";
import Schedule from "./Schedule.jsx";

const dummySchedules = [
    {
        id: 1,
        teamCode: "TEAM001",
        title: "백엔드 스터디의 제모오오오오오오오오오오오오오오오오옥은요 이제에ㅔ에에ㅔ에에에ㅔ에에",
        description: "Spring Boot 학습 모임Spring Boot 학습 모임Spring Boot 학습 모임Spring Boot 학습 모임Spring Boot 학습 모임Spring Boot 학습 모임Spring Boot 학습 모임Spring Boot 학습 모임Spring Boot 학습 모임",
        category: "일정",
        startDateAt: "2025-03-09T14:00:00",
    },
    {
        id: 4,
        teamCode: "TEAM001",
        title: "팀회의 의 제모오오오오오오오오오오오오오오오오옥은요오오오오오오오오오오오오오오오오오오",
        description: "플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이 플젝 회의합시데이",
        category: "회의",
        startDateAt: "2025-03-10T14:00:00",
    },
];

const ScheduleView = ({ selectedDate, role }) => {
    const [isOpen, setIsOpen] = useState(false);
    const [selectedSchedule, setSelectedSchedule] = useState(null);

    const formattedDate = dayjs(selectedDate).format("YYYY-MM-DD");

    const filteredSchedules = dummySchedules.filter(schedule =>
        dayjs(schedule.startDateAt).format("YYYY-MM-DD") === formattedDate
    );

    // 팀장일 경우 일정 더블클릭 시 모달창 열기
    const handleDoubleClick = (schedule) => {
        if (role === "OWNER") {
            setSelectedSchedule(schedule);
            setIsOpen(true);
        }
    };

    return (
        <section className={styles.ScheduleViewContainer}>
            {filteredSchedules.length > 0 ? (
                filteredSchedules.map(schedule => (
                    <div
                        key={schedule.id}
                        onDoubleClick={() => handleDoubleClick(schedule)}
                        style={{ cursor: role === "OWNER" ? "pointer" : "default" }}
                    >
                        <h3>{schedule.title}</h3>
                        <p>{schedule.description}</p>
                    </div>
                ))
            ) : (
                <p>일정이 없습니다.</p>
            )}

            {/* 모달창 */}
            {isOpen && <Schedule onClose={() => setIsOpen(false)} schedule={selectedSchedule} />}
        </section>
    );
};

export default ScheduleView;
