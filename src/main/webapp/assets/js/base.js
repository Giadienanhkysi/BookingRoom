
//ham xu ly slide
function diChuyen( huong, thuTuSlide='', margin, soLuong=6){
    var clss = ".slide-item"+ thuTuSlide
    var btn = ".next-btn"+ thuTuSlide
    // console.log(clss, btn)
    const nextBtn = document.querySelector(btn)
    const items = document.querySelectorAll(clss)
    var d = items[0].offsetWidth + margin*2    
    // console.log(d)
    // console.log(items[0])
    items.forEach(element => {
        if(huong=="left"){
            nextBtn.classList.remove('disabled')
            element.style.left = +element.style.left.replace('px','') - d + 'px'
            if(parseInt(element.style.left.replace('px','')) < -(element.offsetWidth * soLuong)){
                element.style.left = '0px'                        
            }
        }
        else{
            element.style.left = +element.style.left.replace('px','') + d + 'px'
            if(parseInt(element.style.left.replace('px','')) > 0){                               
                element.style.left = '0px'   
                nextBtn.classList.add('disabled')
            }            

        }    
          
    });
}
export function falsyFilter(param){
    if(param !== undefined && param !== null && param !== 0)
        return param
    return ''
}

export function formatPrice(price){
    return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'VND' }).format(price);
}
export function tinhGia(giaCu, giam, soluong){
    let giaMoi = giaCu - giaCu*giam
    if(!isNaN(soluong)){
        giaMoi = giaMoi*soluong
    }
    return formatPrice(giaMoi)
}
export function tinhGia2(giaCu, giam){
    let giaMoi = giaCu - giaCu*giam
    return giaMoi
}
export function getParentElement (element, selector){
    while(element.parentElement){
        if(element.parentElement.matches(selector)){                    
            return element.parentElement
        }
        element = element.parentElement
    }
}
export default diChuyen
