import React from 'react';
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

const TeamCalendar = () => {
    const [date, setDate] = React.useState(new Date());
    return (
        <div>
            <h2>캘린더</h2>
            <Calendar
                onChange={setDate}
                value={date}
                locale="ko-KR"
                tileContent={({date}) => {
                    if ([12, 18, 26].includes(date.getDate())) {
                        return <span style={{ color: "red" }}>●</span>;
                    }
                }}
            />
        </div>
    );
};

export default TeamCalendar;
