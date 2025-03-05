import React, {useEffect, useState} from 'react';
import axios from "axios";
import Users from "./components/Users.jsx";

const Home = () => {
    const [data, setData] = useState("");

    useEffect(() => {
        axios
            .get('/api/data')
            .then((res) => setData(res.data))
            .catch((err) => console.log(err));
    }, []);

    return (
        <div>
            <Users />
            메인 페이지 입니다. (팀 코드 생성 및 참가 페이지)
            <div>
                <img src="./vite.svg" alt="logo"/>
                <div>받아온 값 : {data}</div>
            </div>
        </div>
    );
};

export default Home;
