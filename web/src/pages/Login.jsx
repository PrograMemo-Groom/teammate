import React from 'react';
import styles from '../css/pages/Login.module.scss';

const Login = () => {

    const [userId, setUserId] = React.useState(null);
    const [userPassword, setUserPassword] = React.useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
    }
    const handleChangeinputForm = (state, e) => {
        if (state === "id") {
            setUserId(e.target.value);
        } else {
            setUserPassword(e.target.value);
        }
    }
    return (
        <div className={styles.container}>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="userId">아이디</label>
                    <input type="text" onChange={(e) => handleChangeinputForm("id", e)} id="userId"/>
                </div>
                <div>
                    <label htmlFor="userPw">비밀번호</label>
                    <input type="password" onChange={(e) => handleChangeinputForm("pw", e)} id="userPw"/>
                </div>
                <div>
                    <button>로그인</button>
                    <button>회원가입</button>
                </div>
            </form>
        </div>
    );
};

export default Login;
