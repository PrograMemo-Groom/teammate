import axios from "axios";
import React, { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [data, setData] = useState("");

  useEffect(() => {
    axios
      .get('/api/data')
      .then((res) => setData(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <>
      <div>
          <img src="./vite.svg" alt="logo" />
        <div>받아온 값 : {data}</div>
      </div>
    </>
  );
}

export default App;
