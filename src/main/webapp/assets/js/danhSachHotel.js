import {falsyFilter, formatPrice, tinhGia} from './base.js'
const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);
const queryString = decodeURIComponent(window.location.search)
const urlParams = new URLSearchParams(queryString);
const host = 'http://127.0.0.1:8080/bookingroom/'
// function formatPrice(price){
//     return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'VND' }).format(price);
// }

async function findFirstRoom(hotelId, room) {
    const roomAPI = host + "api-room?hotelId=" + hotelId
    await fetch(roomAPI)
    .then(response => response.json())
    .then(rooms => room = rooms[0])
    .catch(err => console.log('error', err))
}


async function render() {
    let thanhPho = $(".thanhPho")
    let hotelList = $(".hotelList")
    let tenThanhPho = urlParams.get('location')
    
    const hotelAPI = host + "api-hotel?location=" + tenThanhPho
    var url = {
        image: 'api-image?qty=1&hotelId=',
        room: 'api-room?hotelId='
    }
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    fetch(hotelAPI)
    .then(response => response.json())
    .then(hotels =>{
        if(hotels.length){
            hotelList.innerHTML =''
            thanhPho.innerHTML = falsyFilter(tenThanhPho && `<h1>Khách sạn ở ${tenThanhPho}</h1>`)
        }
        return hotels.map(hotel=>renderAHotel(url, hotelList,hotel))
    })
    .catch(err => console.log('error',err))
}

async function renderAHotel(url, hotelList, hotel){
    const imageResp = fetch(host + url.image + hotel.id)
                .then(response => response.json())
                .then(img => img && img.link);
    const roomResp = fetch(host + url.room + hotel.id)
                .then(response => response.json())
                .then(room => room!==null? room[0]: null);
    const image = await imageResp
    const room = await roomResp
    
    
    hotelList.innerHTML +=  
    `<div class="col l-3 m-4 c-6" >
        <a href="chiTietHotel.html?id=${hotel.id}" class="khach-san">
        <div class="khach-san__img" style="background-image: url(${falsyFilter(image && host + image)});"></div>
            <h4 class="khach-san__ten">${hotel.name}</h4>
            <div class="khach-san__gia">
                ${falsyFilter(room && room.discount && `<span class="khach-san__gia-cu">${formatPrice(room.price)}</span>:`)}
                <span class="khach-san__gia-hien-tai">${falsyFilter(room && tinhGia(room.price, room.discount))}</span>
            </div>

            <div class="khach-san__action">
                <div class="khach-san__rating">
                    ${falsyFilter(hotel && renderStar(hotel.star))}
                </div>
            </div>

            <div class="khach-san__origin">
                <span class="khach-san__diachi">${hotel.address}</span>
                <span class="khach-san__nuoc">Việt Nam</span>
            </div>

            ${falsyFilter(room && room.discount &&
                `<div class="khach-san__giam-gia">
                <span class="khach-san__giam-gia-phan-tram">${room.discount *100}%</span>
                <span class="khach-san__giam-gia-nhan">GIẢM</span>
            </div>`)}
        </a>
    </div>`
}

function renderStar(numStar){
    var html = ''
    for(var i=0; i<numStar; i++){
        html += '<i class="fas fa-star khach-san__star--gold"></i>'
    }
    for(var i=0; i<5-numStar; i++){
        html += '<i class="fas fa-star"></i>'
    }
    return html
}
render()
tinhGia()
