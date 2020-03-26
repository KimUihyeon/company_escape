import React , { useState , useEffect } from "react";
import { runDateTimeThread } from "../util/dateTimes/DateTimeThread";
import { devConsole } from "../util/Util";


export function Clock(){

    let [ dateTime , setDateTime ] = useState({date : '0' , time : '0'});

    let date_interval = () => {
        runDateTimeThread((date)=>{
            devConsole(date);
            setDateTime(date);
        });
    }

    // 라이프 사이클 
    useEffect( () =>{
        window.addEventListener("load", date_interval);
        return () => {
            window.removeEventListener("load", date_interval);
        };
    },[]);
    
    return (
        <div>
            <p>
                {dateTime.date}  
            </p>
            <p>
                {dateTime.time}
            </p>
            
        </div>
    )
}