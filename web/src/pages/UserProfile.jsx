import React, { useState } from "react";
import styles from "../css/pages/Profile.module.scss";
import {useNavigate} from "react-router-dom";

const UserProfile = () => {
    const [image, setImage] = useState(null);
    const navigate = useNavigate();

    const handleImageChange = (event) => {
        const file = event.target.files[0];
        if (file) {
            setImage(URL.createObjectURL(file));
        }
    };
    const handleGotoHome = () => {
        navigate("/");
    }

    const [isStackEditing, setIsStackEditing] = useState(false);
    const [techStacks, setTechStacks] = useState(["JavaScript", "React", "Figma"]);
    const [newTech, setNewTech] = useState("");

    const handleStackEditClick = () => {
        setIsStackEditing(true);
    };

    const handleStackSaveClick = () => {
        if (!newTech.trim()) {
            alert("입력된 내용이 없습니다");
            return;
        }

        setTechStacks((prev) => [...prev, newTech]);
        setNewTech("");
        setIsStackEditing(false);
    };

    const handleStackDelete = (index) => {
        setTechStacks((prev) => prev.filter((_, i) => i !== index)); // ✅ 클릭한 항목만 삭제
    };

    return (
        <div className={styles.container}>
            <h2 className={styles.title}>프로필 설정</h2>

            <div className={styles.profileSection}>
                <div className={styles.profileImageWrapper}>
                    <div className={styles.profileImage}>
                        {image ? <img src={image} alt="프로필" /> : <div className={styles.placeholder} />}
                    </div>
                    <div className={styles.buttonGroup}>
                        <button className={styles.imageSelect}>프로필 이미지 설정</button>
                        <label className={styles.uploadButton}>
                            이미지 선택
                            <input type="file" accept="image/*" onChange={handleImageChange} />
                        </label>
                    </div>
                </div>

                <div className={styles.inputSection}>
                    <input type="text" placeholder="닉네임 변경" className={styles.input} />
                    <textarea placeholder="상태 메시지" className={styles.textarea} />
                    <input type="text" placeholder="한 줄 소개" className={styles.input} />
                    <input type="text" placeholder="URL" className={styles.input} />

                    <div className={styles.position}>
                        <p>선호 포지션</p>
                        <div className={styles.positionTag}>FrontEnd</div>

                    </div>

                    <div className={styles.techStack}>
                        <p>기술 스택</p>
                        <div className={styles.stackTags}>
                            {/*<span>JavaScript</span>*/}
                            {/*<span>JavaScript</span>*/}
                            {/*<span>JavaScript</span>*/}
                            {techStacks.map((tech, index) => (
                                <span key={index} className={styles.stackTag}>
                                    {tech}
                                    <button className={styles.deleteButton}
                                            onClick={() => handleStackDelete(index)}>
                                        X
                                    </button>
                                </span>
                            ))}
                        </div>
                        {isStackEditing ? (
                            <div>
                                <input
                                    type="text"
                                    className={styles.textInput}
                                    value={newTech}
                                    onChange={(e) => setNewTech(e.target.value)}
                                    placeholder="입력해주세요"
                                />
                                <button className={styles.tagButton}
                                        onClick={handleStackSaveClick}>
                                    저장
                                </button>
                            </div>
                        ) : (
                            <button className={styles.tagButton}
                                    onClick={handleStackEditClick}>
                                추가
                            </button>
                        )}
                    </div>
                </div>
            </div>

            {/* 버튼 그룹 */}
            <div className={styles.buttonContainer}>
                <button className={styles.saveButton}>저장</button>
                <button className={styles.cancelButton} onClick={handleGotoHome}>취소</button>
            </div>
        </div>
    );
};

export default UserProfile;
