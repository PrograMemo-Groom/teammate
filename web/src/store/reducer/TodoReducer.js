import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    todos: {
        "kimdev": [
            { id: 2, nickname: "김개발", userId: "kimdev", task: "냐는야 0308 컴포넌트 리팩토링", completed: true, createTime: "2025-03-08T18:06:17" },
            { id: 5, nickname: "김개발", userId: "kimdev", task: "냐는야 0309 UI 샤샥샥", completed: true, createTime: "2025-03-09T19:05:41" },
            { id: 13, nickname: "김개발", userId: "kimdev", task: "냐는야 0310 Redux 스토어 개선", completed: false, createTime: "2025-03-10T19:05:41" }
        ],
        "hong123": [
            { id: 1, nickname: "홍길동", userId: "hong123", task: "냐는야 0307 API 문서 작성", completed: false, createTime: "2025-03-07T18:06:17" },
            { id: 8, nickname: "홍길동", userId: "hong123", task: "냐는야 0309 코드 리뷰", completed: true, createTime: "2025-03-09T19:05:41" },
            { id: 10, nickname: "홍길동", userId: "hong123", task: "냐는야 0309 DB 스키마 수정", completed: false, createTime: "2025-03-09T19:05:41" }
        ]
    }
};

const todoSlice = createSlice({
    name: "todo",
    initialState,
    reducers: {
        toggleTodo: (state, action) => {
            const { userId, todoId } = action.payload;
            const userTodos = state.todos[userId];
            const todo = userTodos.find(todo => todo.id === todoId);
            if (todo) todo.completed = !todo.completed;
        },
        deleteTodo: (state, action) => {
            const { userId, todoId } = action.payload;
            state.todos[userId] = state.todos[userId].filter(todo => todo.id !== todoId);
        },
        addTodo: (state, action) => {
            const { userId, newTodo } = action.payload;
            state.todos[userId].push(newTodo);
        },
        editTodo: (state, action) => {
            const { userId, todoId, newTask } = action.payload;
            const todo = state.todos[userId].find(todo => todo.id === todoId);
            if (todo) todo.task = newTask;
        }
    }
});

export const {
    toggleTodo,
    deleteTodo,
    addTodo,
    editTodo } = todoSlice.actions;
export default todoSlice.reducer;
