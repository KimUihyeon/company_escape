const moment = require('moment');
const stringSplitor = '$$$';

function getDate(){
    let momentDate = moment().format(`YYYY-MM-DD${stringSplitor}hh:mm:ss`);
    let [date , time] = momentDate.split(stringSplitor);

    let dateObj = {
        date,
        time
    }
    return dateObj;
}

export function runDateTimeThread(callback){
    callback(getDate());
    setInterval(()=>{
        callback(getDate());
    }, 1000);
}
