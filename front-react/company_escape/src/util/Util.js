
export function object_to_string_query (obj) {
    return Object.keys[obj].map((key)=>{
        return `${key}=${obj[key]}`;
    }).join('$')
}

export function devConsole(msg){
    if(process.env.REACT_APP_DEV_MODE){
        console.log(msg)
    }
}

export function devAlert(msg){
    if(process.env.REACT_APP_DEV_MODE){
        alert(msg);
    }
}