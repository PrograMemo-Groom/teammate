import React from 'react';
import styles from "../../css/pages/Home.module.scss";

const ScheduleView = () => {
    const handleOnchangeChecked = (e) => {
        console.log(e.target.checked);
    }

    return (
        <section className={styles.ScheduleViewContainer}>
            스케줄 뷰 창
        </section>
    );
};

export default ScheduleView;