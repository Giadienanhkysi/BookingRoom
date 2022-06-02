import diChuyen from "./base.js";

const nextBtn1 = document.querySelector(".next-btn.slide-1");
const preBtn1 = document.querySelector(".pre-btn.slide-1");
const nextBtn2 = document.querySelector(".next-btn.slide-2");
const preBtn2 = document.querySelector(".pre-btn.slide-2");
const nextBtn3 = document.querySelector(".next-btn.slide-3");
const preBtn3 = document.querySelector(".pre-btn.slide-3");



nextBtn1.classList.add('disabled')
preBtn1.onclick = ()=>{
    diChuyen('left','.slide-1', 10, 3)
}
nextBtn1.onclick = ()=>{
    diChuyen('right','.slide-1', 10, 3)
}

nextBtn2.classList.add('disabled')
preBtn2.onclick = ()=>{
    diChuyen('left','.slide-2', 10, 3)
}
nextBtn2.onclick = ()=>{
    diChuyen('right','.slide-2', 10, 3)
}

nextBtn3.classList.add('disabled')
preBtn3.onclick = ()=>{
    diChuyen('left','.slide-3', 10, 3)
}
nextBtn3.onclick = ()=>{
    diChuyen('right','.slide-3', 10, 3)
}