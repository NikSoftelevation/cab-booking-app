import thunk from "redux-thunk";
import { authReducer } from "./Auth/Reducer";

const { legacy_createStore, combineReducers, applyMiddleware } = require("redux");


const rootReducers = combineReducers({ auth: authReducer})


export const store = legacy_createStore(rootReducers, applyMiddleware(thunk));