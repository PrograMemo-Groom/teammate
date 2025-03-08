function SignUpValidation() {
    const userId = document.querySelector("input[id=userId]");
    const password = document.querySelector("input[id=password]");
    const passwordCheck = document.querySelector("input[id=passwordCheck]");
    const email = document.querySelector("input[id=email]");
    const nickName = document.querySelector("input[id=nickName]");

    if (userId.value === "") {
        alert("아이디를 입력하세요.");
        userId.focus();
        return false;
    }

    const userIdRegExp = /^[a-zA-Z0-9]+$/;
    if (!userIdRegExp.test(userId.value)) {
        alert("아이디가 올바르지 않습니다.");
        userId.focus();
        userId.value = "";
        return false;
    }

    if (password.value === "") {
        alert("비밀번호를 입력하세요.");
        password.focus();
        return false;
    }

    if (password.value === email.value) {
        alert("비밀번호는 이메일과 일치하면 안 됩니다.");
        password.focus();
        password.value = "";
        return false;
    }

    const passwordRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
    if (!passwordRegExp.test(password.value)) {
        alert("영문 + 숫자 + 특수문자 조합으로 8~15자리 입력해주세요.");
        password.focus();
        password.value = "";
        return false;
    }

    if (passwordCheck.value === "") {
        alert("비밀번호 입력을 확인하세요.");
        passwordCheck.focus();
        return false;
    }

    if (passwordCheck.value !== password.value) {
        alert("비밀번호가 일치하지 않습니다.");
        passwordCheck.focus();
        passwordCheck.value = "";
        return false;
    }

    if (nickName.value === "") {
        alert("닉네임을 입력하세요.");
        nickName.focus();
        return false;
    }

    const nickNameRegExp = /^[가-힣a-zA-Z]+$/;
    if (!nickNameRegExp.test(nickName.value)) {
        alert("닉네임이 올바르지 않습니다.");
        nickName.focus();
        nickName.value = "";
        return false;
    }

    if (email.value === "") {
        alert("이메일을 입력하세요.");
        email.focus();
        return false;
    }

    const emailRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/
    if (!emailRegExp.test(email.value)) {
        alert("올바른 이메일을 입력해주세요.");
        email.focus();
        email.value = "";
        return false;
    }

    return true;
}

export default SignUpValidation;