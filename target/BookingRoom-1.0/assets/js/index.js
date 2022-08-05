import diChuyen from "./base.js";
import {url, host} from "./base.js"
const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)
const nextBtn1 = $(".next-btn.slide-1");
const preBtn1 = $(".pre-btn.slide-1");
const nextBtn2 = $(".next-btn.slide-2");
const preBtn2 = $(".pre-btn.slide-2");
const nextBtn3 = $(".next-btn.slide-3");
const preBtn3 = $(".pre-btn.slide-3");


// nextBtn1.classList.add('disabled')
// preBtn1.onclick = ()=>{
//     diChuyen('left','.slide-1', 10, 3)
// }
// nextBtn1.onclick = ()=>{
//     diChuyen('right','.slide-1', 10, 3)
// }

// nextBtn2.classList.add('disabled')
// preBtn2.onclick = ()=>{
//     diChuyen('left','.slide-2', 10, 3)
// }
// nextBtn2.onclick = ()=>{
//     diChuyen('right','.slide-2', 10, 3)
// }

// nextBtn3.classList.add('disabled')
// preBtn3.onclick = ()=>{
//     diChuyen('left','.slide-3', 10, 3)
// }
// nextBtn3.onclick = ()=>{
//     diChuyen('right','.slide-3', 10, 3)
// }
renderImageSlide(url.slide, 'slide-1', 1, '2-4', 6, 6);
renderImageSlide(url.slide, 'slide-2', 2, '3', 6, 6);
renderImageSlide(url.slide, 'slide-3', 3, '4', 12, 4);

function moveSlide1() {
    const nextBtn = $(".next-btn.slide-1");
    const preBtn = $(".pre-btn.slide-1");
    const soluong1 = $$(".slide-item.slide-1").length-4;

    nextBtn.classList.add("disabled");
    preBtn.onclick = () => {
        diChuyen("left", ".slide-1", 10, soluong1);
    };
    nextBtn.onclick = () => {
        diChuyen("right", ".slide-1", 10, soluong1);
    };
}

function moveSlide2() {
    const nextBtn2 = $(".next-btn.slide-2");
    const preBtn2 = $(".pre-btn.slide-2");
    const soluong2 = $$(".slide-item.slide-2").length-3;

    nextBtn2.classList.add("disabled");
    preBtn2.onclick = () => {
        diChuyen("left", ".slide-2", 10, soluong2);
    };
    nextBtn2.onclick = () => {
        diChuyen("right", ".slide-2", 10, soluong2);
    };

}
function moveSlide3() {
    const nextBtn3 = $(".next-btn.slide-3");
    const preBtn3 = $(".pre-btn.slide-3");
    const soluong3 = $$(".slide-item.slide-2").length-3;

    nextBtn3.classList.add("disabled");
    preBtn3.onclick = () => {
        diChuyen("left", ".slide-3", 10, soluong3);
    };
    nextBtn3.onclick = () => {
        diChuyen("right", ".slide-3", 10, soluong3);
    };

    
}

function renderImageSlide(url, slideName, group, desktop, tablet, mobile) {
    let imageSlide = $$(".slide-list");
    
    fetch(host + url + '?group='+group)
        .then((response) => response.json())
        .then((slides) => slides.map((slide) =>{

            if(slideName ==='slide-1'){
            return`
                <div class="slide-item slide-${group} col l-${desktop} m-${tablet} c-${mobile}">                        
                    <a class = "product-wrapper" href="${host + slide.link}">
                        <div class="product__img" style="background-image: url(${host + slide.image.link});"></div>
                        <div class="moTa">
                            <h1 class="moTa__tieuDe">${slide.title}</h1>
                            <p class="moTa__soLuongCho"> ${slide.description}</p>
                        </div>
                    </a>
                </div>
                `
            }else if(slideName ==='slide-2'){
                return `
                <div class="slide-item slide-2 col l-${desktop} m-${tablet} c-${mobile}">                        
                    <a class = "product-wrapper" href="${slide.link}" >
                        <div class="product__img recommend-img" style="background-image: url(${host + slide.image.link});"></div>
                        <div class="recommend">
                            <h1 class="recommend__title">${slide.title}</h1>
                            <p class="recommend__intro">${slide.description}</p>
                        </div>
                    </a>
                </div>
                `
            }else if(slideName ==='slide-3'){
                
                return `
                <div class="slide-item slide-3 col l-${desktop} m-${tablet} c-${mobile}">                        
                    <a class = "product-wrapper" href="${slide.link}" >
                        <div class="product__img width-102" style="background-image: url(${host + slide.image.link})"></div>                                
                        <h1 class="discovery-label">${slide.title}</h1>
                        <p class="discovery-title">${slide.description}</p>                                
                    </a>
                </div>
                `
            }

            
        }

            )
        )
        .then((html) => html.join(""))
        .then((html) => {
            imageSlide[group-1].innerHTML = `
        <button class="tron-btn pre-btn ${slideName}">
            <i class="fas fa-chevron-left"></i>
        </button>
            ${html}
        <button class="tron-btn next-btn ${slideName}">
            <i class="fas fa-chevron-right"></i>
        </button>
        `;
        })
        .then(() => {
            if (slideName === 'slide-1') {
                moveSlide1()
            } else if (slideName === 'slide-2') {
                moveSlide2()
            }else if (slideName === 'slide-3') {
                moveSlide3()
            }
        })
        .catch((err) => console.error("error", err));
}
