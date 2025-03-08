import styles from "../../css/pages/Home.module.scss";
import React, { useState } from "react";
import { LocalizationProvider, StaticDatePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import dayjs from "dayjs";

const TeamCalendar = () => {
    const [date, setDate] = useState(dayjs());

    const eventDates = ["2025-03-12", "2025-03-18", "2025-03-26"];

    return (
        <section>
            <div className={styles.calendarContainer}>
                <p>{dayjs().format('YYYY년 M월')}</p>
                <button>+일정등록</button>
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
