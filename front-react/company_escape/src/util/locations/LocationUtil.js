

/**
 * points = x,y 로 이루어진 Array
 * 일단은 사각형만 가능 ..
 * @param {*} points    = [ {x:1 , y:2} ,,]  4개
 * @param {*} currentPosition  = {x:1 , y:2} 
 */
function contains( points , currentPosition ){
    let xArr = points.map(p => Number(p.x));
    let yArr = points.map(p => p.y); 

    let xRange = [ Math.min.apply(null, xArr) , Math.max.apply(null, xArr)];
    let yRange = [ Math.min.apply(null, yArr) , Math.max.apply(null, yArr)];

    if( (xRange[0] <= currentPosition.x && xRange[1] >= currentPosition.x) &&
        (yRange[0] <= currentPosition.y && yRange[1] >= currentPosition.y) ){
         return true;
    }
    else {
        return false;
    }
}

export let location = {
    contains : contains
}