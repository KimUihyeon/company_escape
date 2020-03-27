// import { ui } from './ui' ;
import { clock } from './Clock';
import { location } from "./Location";
import { combineReducers } from "redux";

export const reducers =  combineReducers({location, clock});