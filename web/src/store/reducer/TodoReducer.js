import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    todos: [
        {
            userName: "김재홍",
            todos: [
                { title: "UI만들기", checked: false, maker: "김재홍" },
                { title: "UI만들기", checked: false, maker: "김재홍" },
                { title: "UI만들기", checked: false, maker: "김재홍" }
            ]
        },
        {
            userName: "정경희",
            todos: [
                { title: "UI만들기1", checked: false, maker: "정경희" },
                { title: "UI만들기2", checked: false, maker: "정경희" },
                { title: "UI만들기3", checked: false, maker: "정경희" }
            ]
        }
    ]
};

const todoSlice = createSlice({
    name: "todo",
    initialState,
    reducers: {
        toggleTodo: (state, action) => {
            const { userIndex, todoIndex } = action.payload;
            state.todos[userIndex].todos[todoIndex].checked = !state.todos[userIndex].todos[todoIndex].checked;
        },
        deleteTodo: (state, action) => {
            const { userIndex, todoIndex } = action.payload;
            state.todos[userIndex].todos.splice(todoIndex, 1); // ✅ 해당 투두 삭제
        }
    }
});

export const { toggleTodo , deleteTodo} = todoSlice.actions;
export default todoSlice.reducer;
