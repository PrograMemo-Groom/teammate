import React from 'react';
import { useSelector, useDispatch } from "react-redux";
import { toggleTodo, deleteTodo, addTodo } from "../../store/reducer/TodoReducer.js";
import styles from "../../css/pages/Home.module.scss";

const TeamTodo = () => {
    const dispatch = useDispatch();
    const todos = useSelector((state) => state.todo.todos);

    const handleAddTodo = (userIndex) => {
        const newTodo = {
            title: `todo를 입력하세욤`,
            checked: false,
            maker: todos[userIndex].userName
        };
        dispatch(addTodo({ userIndex, newTodo }));
    };

    return (
        <section className={styles.todoContainer}>
            {todos.map((user, userIndex) => (
                <article key={`todo-${userIndex}`}>
                    <div>
                        <h3>{user.userName}</h3>
                        <button onClick={() => handleAddTodo(userIndex)}>todo 추가</button>
                    </div>
                    <ul>
                    {user.todos.map((todo, todoIndex) => (
                            <li key={`${todo.maker}-${todoIndex}`}>
                                <div>
                                    <input
                                        type="checkbox"
                                        checked={todo.checked}
                                        onChange={() => dispatch(toggleTodo({userIndex, todoIndex}))}
                                    />
                                    <span>{todo.title}</span>
                                </div>
                                <div>
                                    <button
                                        onClick={() => dispatch(deleteTodo({userIndex, todoIndex}))}
                                        className={styles.deleteButton}
                                    >
                                        X
                                    </button>
                                </div>
                            </li>
                        ))}
                    </ul>
                </article>
            ))}
        </section>
    );
};

export default TeamTodo;
