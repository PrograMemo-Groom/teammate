import React from 'react';
import { useSelector, useDispatch } from "react-redux";
import { toggleTodo, deleteTodo } from "../../store/reducer/TodoReducer.js";
import styles from "../../css/pages/Home.module.scss";

const TeamTodo = () => {
    const dispatch = useDispatch();
    const todos = useSelector((state) => state.todo.todos);

    return (
        <section className={styles.todoContainer}>
            {todos.map((user, userIndex) => (
                <article key={`todo-${userIndex}`}>
                    <h3>{user.userName}</h3>
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
