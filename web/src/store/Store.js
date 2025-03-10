import { configureStore } from "@reduxjs/toolkit";
import todoReducer from "../store/reducer/TodoReducer.js";

export const Store = configureStore({
    reducer: {
        todo: todoReducer,
    }
});

export default Store;
