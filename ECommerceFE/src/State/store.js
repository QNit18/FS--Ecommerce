import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import thunk from "redux-thunk";
import { authReducer } from "./Auth/Reducer";
import { customerProductReducer } from "./Product/Reducer";
import { cartReducer } from "./Cart/Reducer";
import { orderReducer } from "./Order/Reducer";

// Combine các reducer thành một rootReducer
const rootReducers = combineReducers({
  auth: authReducer,
  products:customerProductReducer,
  cart : cartReducer,
  order: orderReducer,
});

// Tạo store Redux
export const store = legacy_createStore(
  rootReducers, // Reducer chính
  applyMiddleware(thunk) // Sử dụng Redux Thunk middleware
);