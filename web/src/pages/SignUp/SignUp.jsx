import {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import SignUpValidation from "./Validation.js";
import "./SignUp.scss";

const SignUp = () => {
    const navigate = useNavigate();

    const [form, setForm] = useState({
        userId: "",
        password: "",
        passwordCheck: "",
        email: "",
        nickname: "",
        teamCode: "",
        introduction: "",
        preferredPosition: "",
        statusMessage: "",
        profileImg: null,
    });

    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState("");
    const [teamCodeGenerated, setTeamCodeGenerated] = useState(false);

    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value});
    };

    // 팀 코드 자동 생성 (4자리 랜덤 영문 + 숫자)
    const generateHexCode = () => {
        return Math.floor(Math.random() * 0x10000)
            .toString(16) // 16진수 문자열 변환
            .toUpperCase()
            .padStart(4, "0");
    };

    const handleGenerateTeamCode = async () => {
        try {
            const newTeamCode = generateHexCode(); // 팀 코드 생성
            setForm({...form, teamCode: newTeamCode});
            // const response = await axios.post("http://localhost:8080/users/teams/generate-code");
            // setForm({...form, teamCode: response.data.teamCode});
            setTeamCodeGenerated(true); // 생성 버튼 비활성화
        } catch (error) {
            console.error("팀 코드 생성 실패:", error.response?.data || error.message);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const validationErrors = SignUpValidation(form);

        if (validationErrors) {
            setErrors(validationErrors);
            return;
        }

        try {
            const response = await axios.post("http://localhost:8080/users/signup", form, {
                headers: {
                    "Content-Type": "application/json", // JSON 타입 명확히 설정
                },
            });
            if (response.status === 200) {
                setSuccessMessage("회원가입 성공! 로그인 페이지로 이동합니다.");
                setTimeout(() => navigate("/login"), 2000);
            }
        } catch (error) {
            console.error("회원가입 실패:", error.response?.data || error.message);
            const errorMessage = error.response?.data?.error || "회원가입 중 오류가 발생했습니다.";
            setErrors({ apiError: errorMessage });
        }
    };

    return (
        <div className="signup-container">
            <div className="signup-box">
                <h2 className="signup-title">회원가입</h2>
                {successMessage && <p className="success-text">{successMessage}</p>}
                {errors.apiError && <p className="error-text">{errors.apiError}</p>}
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>아이디</label>
                        <input type="text" name="userId" value={form.userId} onChange={handleChange}/>
                        {errors.userId && <p className="error-text">{errors.userId}</p>}
                    </div>
                    <div className="form-group">
                        <label>비밀번호</label>
                        <input type="password" name="password" value={form.password} onChange={handleChange}/>
                        {errors.password && <p className="error-text">{errors.password}</p>}
                    </div>
                    <div className="form-group">
                        <label>비밀번호 확인</label>
                        <input type="password" name="passwordCheck" value={form.passwordCheck} onChange={handleChange}/>
                        {errors.passwordCheck && <p className="error-text">{errors.passwordCheck}</p>}
                    </div>
                    <div className="form-group">
                        <label>닉네임</label>
                        <input type="text" name="nickName" value={form.nickName} onChange={handleChange}/>
                        {errors.nickName && <p className="error-text">{errors.nickName}</p>}
                    </div>
                    <div className="form-group">
                        <label>이메일</label>
                        <input type="email" name="email" value={form.email} onChange={handleChange}/>
                        {errors.email && <p className="error-text">{errors.email}</p>}
                    </div>
                    <div className="form-group-teamcode">
                        <div className="team-code-container">
                            <input type="text" name="teamCode" value={form.teamCode || ""} onChange={handleChange}
                                   disabled={teamCodeGenerated}/>
                            <button type="button" className="team-generate-button" onClick={handleGenerateTeamCode}
                                    disabled={teamCodeGenerated}>팀 코드 생성
                            </button>
                        </div>
                        <button type="button" className="team-input-button">팀 코드 입력</button>
                    </div>
                    <button type="submit" className="signup-button">회원가입</button>
                </form>
            </div>
        </div>
    );
};

export default SignUp;