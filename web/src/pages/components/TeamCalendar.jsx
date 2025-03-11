import React, { useState } from "react";
import styles from "../../css/pages/Home.module.scss";
import { LocalizationProvider, StaticDatePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import Schedule from "./Schedule.jsx";

const TeamCalendar = ({ selectedDate, setSelectedDate }) => {
    const [isOpen, setIsOpen] = useState(false);
    const [role, setRole] = useState("OWNER"); // 일단 더미 데이터, 나중에 API 연동코드로 변경 필요 !!!!!!!!!
    //const [role, setRole] = useState("MEMBER");

    const handleModalOpen = (value) => {
        setIsOpen(value);
        console.log("modal open clicked", isOpen);
    };

    const eventDates = ["2025-03-12", "2025-03-18", "2025-03-26"];

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

                            if (eventDates.includes(formattedDate)) {
                                return {
                                    sx: {
                                        position: "relative",
                                        "&::after": {
                                            content: '"●"',
                                            color: "red",
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
