import React , { useState , useEffect } from "react";
const moment = require('moment')

export function Clock(){

    let [ dateTime , setDateTime ] = useState();
    let [ date , time ] = ['', ''];

    let date_interval = () => setInterval(()=>{

        let fullDate = moment().format('YYYY-MM-DD$$$hh:mm:ss');
        setDateTime(fullDate);
        console.log('call Interval');
    }, 1000);

    // 라이프 사이클 
    useEffect( () =>{
        window.addEventListener("load", date_interval);
        return () => {
            window.removeEventListener("load", date_interval);
        };
    },[]);

    if(dateTime){
        [ date , time ] = dateTime?.split('$$$');
    }

    
    return (
        <div>
            <p>
                {date}  
            </p>
            <p>
            {time}
            </p>
            
        </div>
    )
}