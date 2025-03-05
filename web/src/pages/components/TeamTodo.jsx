import React from 'react';
import styles from "../../css/pages/Home.module.scss";

const TeamTodo = () => {
    const handleOnchangeChecked = (e) => {
        console.log(e.target.checked);
    }

    return (
        <section className={styles.todoContainer}>
            {dummyData.map((item, index) => (
                <article key={`todo-${index}`}>
                    <h3>{item.userName}</h3>
                    <ul>
                        {item.todos.map((item, index) => (
                            <li key={`${item.maker}+${index}`}><input type="checkbox" checked={item.checked} onChange={() => handleOnchangeChecked()}/>{item.title}</li>
                        ))}
                    </ul>
                </article>
            ))}
        </section>
    );
};

const dummyData = [
    {
        userName: "김재홍", todos: [{
            title: "UI만들기", checked: false, maker: "김재홍"
        }, {
            title: "UI만들기", checked: false, maker: "김재홍"
        }, {
            title: "UI만들기", checked: false, maker: "김재홍"
        }]
    },
    {
        userName: "정경희", todos: [{
            title: "UI만들기1", checked: false, maker: "정경희"
        }, {
            title: "UI만들기2", checked: false, maker: "정경희"
        }, {
            title: "UI만들기3", checked: false, maker: "정경희"
        }]
    },

]

export default TeamTodo;
