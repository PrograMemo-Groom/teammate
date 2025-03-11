import React, { useState } from "react";
import styles from "../../css/pages/Home.module.scss";
import { LocalizationProvider, StaticDatePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import Schedule from "./Schedule.jsx";

const TeamCalendar = ({ selectedDate, setSelectedDate }) => {
    const [isOpen, setIsOpen] = useState(false);
    const [role, setRole] = useState("OWNER"); // 일단 더미 데이터, 나중에 API 연동코드로 변경 필요 !!!!!!!!!

    // 더미 일정 데이터 !!!!!!!!
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

    const handleModalOpen = (value) => {
        setIsOpen(value);
        console.log("modal open clicked", isOpen);
    };

    // 날짜별 일정 데이터 매핑
    const eventDates = dummySchedules.reduce((acc, schedule) => {
        const date = dayjs(schedule.startDateAt).format("YYYY-MM-DD");
        acc[date] = schedule.category;
        return acc;
    }, {});

    return (
        <section>
            <div className={styles.calendarContainer}>
                <p>{dayjs(selectedDate).format("YYYY년 M월")}</p>
                {role === "OWNER" && (
                    <button onClick={() => handleModalOpen(true)}>+일정등록</button>
                )}
                {isOpen && <Schedule onClose={() => setIsOpen(false)} />}
            </div>

            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <StaticDatePicker
                    displayStaticWrapperAs="desktop"
                    value={dayjs(selectedDate)}
                    onChange={(newValue) => setSelectedDate(newValue.format("YYYY-MM-DD"))}
                    slotProps={{
                        day: (params) => {
                            const formattedDate = dayjs(params.day).format("YYYY-MM-DD");
                            const category = eventDates[formattedDate];

                            if (category) {
                                return {
                                    sx: {
                                        position: "relative",
                                        "&::after": {
                                            content: '"●"',
                                            color: category === "회의" ? "pink" : "lightgreen",
                                            fontSize: "12px",
                                            position: "absolute",
                                            bottom: -1,
                                            left: "50%",
                                            transform: "translateX(-50%)",
                                        },
                                    },
                                };
                            }
                        },
                    }}
                />
            </LocalizationProvider>
        </section>
    );
};

export default TeamCalendar;
