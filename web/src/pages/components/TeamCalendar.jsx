import styles from "../../css/pages/Home.module.scss";
import React, { useState } from "react";
import { LocalizationProvider, StaticDatePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";
import Schedule from "./Schedule.jsx";

const TeamCalendar = () => {
    const [date, setDate] = useState(dayjs());
    const [isOpen, setIsOpen] = React.useState(false);

    const handleModalOpen = (value) => {
        setIsOpen(value);
        console.log("modal open clicked", isOpen);
    }

    // const eventDates = ["2025-03-12", "2025-03-18", "2025-03-26"];
    const [eventDates, setEventDates] = useState(["2025-03-12", "2025-03-18", "2025-03-26"]);

    const handleAddEvent = (recordDate) => {
        if (!recordDate) return;

        const formattedDate = dayjs(recordDate).format("YYYY-MM-DD");

        setEventDates((prev) => [...new Set([...prev, formattedDate])]); // 중복 방지
        setIsOpen(false);
    };


    return (
        <section>
            <div className={styles.calendarContainer}>
                <p>{dayjs().format('YYYY년 M월')}</p>
                <button onClick={() => handleModalOpen(true)}>+일정등록</button>
                {isOpen && (<Schedule onClose={() => setIsOpen(false)}
                    onRecord={handleAddEvent}/>)}

            </div>

            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <StaticDatePicker
                    displayStaticWrapperAs="desktop"
                    value={date}
                    onChange={(newValue) => setDate(newValue)}
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
