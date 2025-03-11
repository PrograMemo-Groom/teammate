import React from 'react';
import styles from "../../css/pages/Schedule.module.scss";

const Schedule = ({onClose}) => {
    return (<dialog className={styles.modalOverlay}>

        <div className={styles.modalContent}>
            <div className={styles.closeButton}>
                <button onClick={onClose}>×</button>
            </div>
            <div>
                <div>
                    <div className={styles.formGroup}>
                        <label>날짜</label>
                        <div className={styles.selectGroup}>
                            <input type="date"/>
                        </div>
                    </div>
                    <div className={styles.formGroup}>
                        <label>시간</label>
                        <div className={styles.selectGroup}>
                            <select>
                                {Array.from(Array(24),(_, i) =>
                                    (<option key={i} value={i}>
                                        {i.toString().padStart(2, "0")}
                                    </option>)
                                )}
                            </select>
                            <select>
                                {Array.from(Array(12), (_, i) => (
                                    <option key={i} value={i * 5}>
                                        {String(i * 5).padStart(2, "0")}
                                    </option>
                                ))}
                            </select>
                        </div>
                    </div>
                    <div className={styles.formGroup}>
                        <label>카테고리</label>
                        <select className={styles.selectGroup}>
                            <option>회의</option>
                            <option>일정</option>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div className={styles.formGroup}>
                    <label>일정 제목</label>
                    <input type="text" className={styles.textInput} placeholder="일정 제목을 입력하세요"/>
                </div>

                <div className={styles.formGroup}>
                    <label>일정 내용</label>
                    <textarea className={styles.textArea} placeholder="일정 내용을 입력하세요"></textarea>
                </div>
            </div>
            <div>
                <div className={styles.buttonGroup}>
                    <button className={styles.deleteButton}>삭제</button>
                    <button className={styles.saveButton}>등록</button>
                </div>
            </div>
        </div>
    </dialog>);
};

export default Schedule;
