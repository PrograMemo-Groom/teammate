import React, {useState, useRef} from 'react';
import styles from "../../css/pages/Schedule.module.scss";

const Schedule = ({onClose , onRecord}) => {

    const [date, setDate] = useState("");
    const [hour, setHour] = useState("00");
    const [minute, setMinute] = useState("00");
    const [category, setCategory] = useState("회의");
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const handleRegister = () => {
        if (!date || !hour || !minute || !category || !title || !description) {
            alert("모든 값을 입력해주세요");
            return;
        }

        console.log({ date, hour, minute, category, title, description });
        onRecord(date);
        alert("등록되었습니다.");
        onClose();
    };

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
                            <input type="date"
                                   value={date}
                                   onChange={(e) => setDate(e.target.value)}
                            />
                        </div>
                    </div>
                    <div className={styles.formGroup}>
                        <label>시간</label>
                        <div className={styles.selectGroup}>
                            <select value={hour}
                                    onChange={(e) => setHour(e.target.value)}>
                                {Array.from(Array(24),(_, i) =>
                                    (<option key={i} value={i}>
                                        {i.toString().padStart(2, "0")}
                                    </option>)
                                )}
                            </select>
                            <select value={minute}
                                    onChange={(e) => setMinute(e.target.value)}>
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
                        <select className={styles.selectGroup}
                                value={category}
                                onChange={(e) => setCategory(e.target.value)}>
                            <option>회의</option>
                            <option>일정</option>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div className={styles.formGroup}>
                    <label>일정 제목</label>
                    <input type="text" className={styles.textInput} placeholder="일정 제목을 입력하세요"
                           value={title}
                           onChange={(e) => setTitle(e.target.value)}/>
                </div>

                <div className={styles.formGroup}>
                    <label>일정 내용</label>
                    <textarea className={styles.textArea} placeholder="일정 내용을 입력하세요"
                              value={description}
                              onChange={(e) => setDescription(e.target.value)}>
                    </textarea>
                </div>
            </div>
            <div>
                <div className={styles.buttonGroup}>
                    <button className={styles.deleteButton}>삭제</button>
                    <button className={styles.saveButton}
                            onClick={handleRegister}
                        >등록</button>
                </div>
            </div>
        </div>
    </dialog>);
};

export default Schedule;
