const SignUpValidation = (form) => {
    let errors = {};
    const userIdRegExp = /^[a-zA-Z0-9]+$/;
    const passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
    const nickNameRegExp = /^[가-힣a-zA-Z]+$/;
    const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

    if (!form.userId.trim()) {
        errors.userId = "아이디를 입력하세요.";
    } else if (!userIdRegExp.test(form.userId)) {
        errors.userId = "아이디가 올바르지 않습니다.";
    }

    if (!form.password.trim()) {
        errors.password = "비밀번호를 입력하세요.";
    } else if (!passwordRegExp.test(form.password)) {
        errors.password = "영문 + 숫자 + 특수문자 조합으로 8~15자리 입력해주세요.";
    }

    if (form.password !== form.passwordCheck) {
        errors.passwordCheck = "비밀번호가 일치하지 않습니다.";
    }

    if (!form.nickName.trim()) {
        errors.nickName = "닉네임을 입력하세요.";
    } else if (!nickNameRegExp.test(form.nickName)) {
        errors.nickName = "닉네임이 올바르지 않습니다.";
    }

    if (!form.email.trim()) {
        errors.email = "이메일을 입력하세요.";
    } else if (!emailRegExp.test(form.email)) {
        errors.email = "올바른 이메일을 입력해주세요.";
    }

    return Object.keys(errors).length > 0 ? errors : null;
};

export default SignUpValidation;