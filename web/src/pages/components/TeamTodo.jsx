import React, { useState } from 'react';
import { useSelector, useDispatch } from "react-redux";
import { toggleTodo, deleteTodo, addTodo, editTodo } from "../../store/reducer/TodoReducer.js";
import styles from "../../css/pages/Home.module.scss";

const TeamTodo = () => {
    const dispatch = useDispatch();
    const todos = useSelector((state) => state.todo.todos);
    const [editingTodo, setEditingTodo] = useState(null);
    const [newTitle, setNewTitle] = useState("");

    const handleAddTodo = (userIndex) => {
        const newTodo = {
            title: `todo를 입력하세욤`,
            checked: false,
            maker: todos[userIndex].userName
        };
        dispatch(addTodo({ userIndex, newTodo }));
    };

    const handleEditStart = (userIndex, todoIndex, currentTitle) => {
        setEditingTodo({ userIndex, todoIndex });
        setNewTitle(currentTitle);
    };

    const handleEditComplete = () => {
        if (editingTodo) {
            dispatch(editTodo({
                userIndex: editingTodo.userIndex,
                todoIndex: editingTodo.todoIndex,
                newTitle
            }));
        }
        setEditingTodo(null);
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
                                    {editingTodo?.userIndex === userIndex && editingTodo?.todoIndex === todoIndex ? (
                                        <input
                                            type="text"
                                            value={newTitle}
                                            onChange={(e) => setNewTitle(e.target.value)}
                                            onBlur={handleEditComplete}
                                            onKeyDown={(e) => e.key === "Enter" && handleEditComplete()}
                                            autoFocus
                                        />
                                    ) : (
                                        <span
                                            onClick={() => handleEditStart(userIndex, todoIndex, todo.title)}
                                        >
                                            {todo.title}
                                        </span>
                                    )}
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
