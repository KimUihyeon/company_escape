
export function object_to_string_query (obj) {
    return Object.keys[obj].map((key)=>{
        return `${key}=${obj[key]}`;
    }).join('$')
}

