const action = {
    UPDATE : 'update',
    DELETE : 'delete',
    SELECT : 'select',
    SELECTLIST : 'selectList',
    RELOAD : 'reload',
}

const target = {
    Location : 'location',
    Clock : 'clock'
}


function typeStringJoinner(...typeArr){
    return typeArr.join('_')
}


export const LOCATION_RELOAD =  typeStringJoinner(target.Location,action.RELOAD);
export const CLOCK_UPDATE = typeStringJoinner(target.Clock, action.UPDATE);