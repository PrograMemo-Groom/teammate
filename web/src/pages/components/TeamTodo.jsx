import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { toggleTodo, deleteTodo, addTodo, editTodo } from "../../store/reducer/TodoReducer.js";
import styles from "../../css/pages/Home.module.scss";
import dayjs from "dayjs";

const TeamTodo = ({ selectedDate }) => {
    const dispatch = useDispatch();
    const todos = useSelector((state) => state.todo.todos) || {}; // todos가 없을 경우 빈 객체 처리
    const [editingTodo, setEditingTodo] = useState(null);
    const [newTitle, setNewTitle] = useState("");

    // 날짜 기반 TODO 필터링
    const filteredTodos = Object.entries(todos).reduce((acc, [userId, userTodos]) => {
        const userFilteredTodos = userTodos.filter(todo =>
            dayjs(todo.createTime).format("YYYY-MM-DD") === selectedDate
        );
        acc[userId] = userFilteredTodos; // 존재하지 않는 경우 빈 배열을 유지
        return acc;
    }, {});

    const handleAddTodo = (userId) => {
        const newTodo = {
            id: Date.now(),
            task: "새로운 할 일",
            completed: false,
            userId,
            nickname: todos[userId]?.[0]?.nickname || "익명",
            createTime: selectedDate + "T00:00:00"
        };
        dispatch(addTodo({ userId, newTodo }));
    };

    const handleEditStart = (userId, todoId, currentTask) => {
        setEditingTodo({ userId, todoId });
        setNewTitle(currentTask);
    };

    const handleEditComplete = () => {
        if (editingTodo) {
            dispatch(editTodo({
                userId: editingTodo.userId,
                todoId: editingTodo.todoId,
                newTask: newTitle
            }));
        }
        setEditingTodo(null);
    };

    return (
        <section className={styles.todoContainer}>
            <h2>{selectedDate}의 팀 TODO</h2>
            {Object.keys(todos).map((userId) => {
                const tasks = filteredTodos[userId] || []; // 해당 날짜에 TODO가 없을 경우 빈 배열 유지
                const userNickname = todos[userId]?.[0]?.nickname || "익명";

                return (
                    <article key={userId}>
                        <div>
                            <h3>{userNickname} ({userId})</h3>
                            <button onClick={() => handleAddTodo(userId)}>todo 추가</button>
                        </div>
                        {tasks.length > 0 ? (
                            <ul>
                                {tasks.map(todo => (
                                    <li key={todo.id}>
                                        <div>
                                            <input
                                                type="checkbox"
                                                checked={todo.completed}
                                                onChange={() => dispatch(toggleTodo({ userId, todoId: todo.id }))}
                                            />
                                            {editingTodo?.userId === userId && editingTodo?.todoId === todo.id ? (
                                                <input
                                                    type="text"
                                                    value={newTitle}
                                                    onChange={(e) => setNewTitle(e.target.value)}
                                                    onBlur={handleEditComplete}
                                                    onKeyDown={(e) => e.key === "Enter" && handleEditComplete()}
                                                    autoFocus
                                                />
                                            ) : (
                                                <span onClick={() => handleEditStart(userId, todo.id, todo.task)}>
                                                    {todo.task}
                                                </span>
                                            )}
                                        </div>
                                        <div>
                                            <button
                                                onClick={() => dispatch(deleteTodo({ userId, todoId: todo.id }))}
                                                className={styles.deleteButton}
                                            >
                                                X
                                            </button>
                                        </div>
                                    </li>
                                ))}
                            </ul>
                        ) : (
                            <p>앗... 이사람 투두가 없다 !! 생성하라 ! 🔥</p> // 할 일이 없을 경우 메시지 표시
                        )}
                    </article>
                );
            })}
        </section>
    );
};

export default TeamTodo;
