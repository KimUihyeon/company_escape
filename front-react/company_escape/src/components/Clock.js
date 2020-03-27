import React , { useState , useEffect } from "react";
import { runDateTimeThread } from "../util/dateTimes/DateTimeService";
import { devConsole } from "../util/Util";

class props {
    date;
    time;
}

export function Clock(props){
    return (
        <div>
            <p>
                {props.date}  
            </p>
            <p>
                {props.time}
            </p>
            
        </div>
    )
}