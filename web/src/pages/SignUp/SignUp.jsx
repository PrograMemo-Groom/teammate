import {useState} from "react";
import SignUpValidation from "./Validation.js";
import "./SignUp.scss";

const SignUp = () => {
    const [form, setForm] = useState({
        userId: "",
        password: "",
        passwordCheck: "",
        email: "",
        nickName: "",
    });

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = SignUpValidation(form);

        if (validationErrors) {
            setErrors(validationErrors);
        } else {
            alert("회원가입 성공!");
            setErrors({});
        }
    };

    return (
        <div className="signup-container">
            <div className="signup-box">
                <h2 className="signup-title">회원가입</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>아이디</label>
                        <input type="text" name="userId" value={form.userId} onChange={handleChange} />
                        {errors.userId && <p className="error-text">{errors.userId}</p>}
                    </div>

                    <div className="form-group">
                        <label>비밀번호</label>
                        <input type="password" name="password" value={form.password} onChange={handleChange} />
                        {errors.password && <p className="error-text">{errors.password}</p>}
                    </div>

                    <div className="form-group">
                        <label>비밀번호 확인</label>
                        <input type="password" name="passwordCheck" value={form.passwordCheck} onChange={handleChange} />
                        {errors.passwordCheck && <p className="error-text">{errors.passwordCheck}</p>}
                    </div>

                    <div className="form-group">
                        <label>닉네임</label>
                        <input type="text" name="nickName" value={form.nickName} onChange={handleChange} />
                        {errors.nickName && <p className="error-text">{errors.nickName}</p>}
                    </div>

                    <div className="form-group">
                        <label>이메일</label>
                        <input type="email" name="email" value={form.email} onChange={handleChange} />
                        {errors.email && <p className="error-text">{errors.email}</p>}
                    </div>

                    <button type="submit" className="signup-button">회원가입</button>
                </form>
            </div>
        </div>
    );
};

export default SignUp;