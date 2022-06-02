import diChuyen, { tinhGia2 } from "./base.js";
import { falsyFilter, formatPrice, tinhGia, getParentElement } from "./base.js";
const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);


const timPhongBtn = $('.tim-phong-btn')

const queryString = decodeURIComponent(window.location.search);
const urlParams = new URLSearchParams(queryString);
const hotelId = urlParams.get("id");
var host = "http://127.0.0.1:8080/bookingroom/";
var url = {
    hotel: "api-hotel?id=",
    hotelImage: "api-image?hotelId=",
    roomImage: "api-image?roomId=",
    rooms: "api-room?hotelId=",
    room: "api-room?roomId=",
    type: "api-type?id=",
    amenities: "api-amenities-room?roomId=",
    amenitiesName: "api-amenities?id=",
    booking: "api-booking"
};

timPhongBtn.onclick = () => {
    let checkIn = $('.ngay.checkIn').innerHTML.split('-').reverse().join('-')
    let checkOut = $('.ngay.checkOut').innerHTML.split('-').reverse().join('-')
    renderRooms(url, checkIn, checkOut)

}
renderImageSlide(url.hotelImage, hotelId, 'slide-1', 0, 6, 6, 6);
renderHotelInfo(url.hotel, hotelId)
renderRooms(url)

function moveSlide1() {
    const nextBtn = $(".next-btn.slide-1");
    const preBtn = $(".pre-btn.slide-1");
    const soluong1 = $$(".slide-item.slide-1").length - 1;

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
    const soluong2 = $$(".slide-item.slide-2").length;


    nextBtn2.classList.add("disabled");
    preBtn2.onclick = () => {
        diChuyen("left", ".slide-2", 10, soluong2);
    };
    nextBtn2.onclick = () => {
        diChuyen("right", ".slide-2", 10, soluong2);
    };

}

function hienThiChiTiet() {
    const rooms = $$(".phong__loai");
    const chiTietPhongWrapper = $(".chi-tiet-phong-wrapper");
    for (const room of rooms) {
        room.onclick = async () => {
            chiTietPhongWrapper.style.display = "flex";
            await fetch(host + url.room + room.getAttribute('roomId'))
                .then(response => response.json())
                .then(room => {
                    console.log(room)
                    renderRoomDetail(url, $('.overlay.phong'), room, "chi-tiet")
                })
            

            $("body").style.overflow = "hidden";
        };
    }
}
function hienDatPhong() {
    const datPhongBtns = $$('.dat-phong-btn')
    const chiTietPhongWrapper = $(".chi-tiet-phong-wrapper");
    for (const datPhongBtn of datPhongBtns) {
        datPhongBtn.onclick = () => {
            chiTietPhongWrapper.style.display = "flex";
            // console.log(getgetParentElement(datPhongBtn, '.phong__dat'))
            renderDatPhong($('.overlay.phong'), datPhongBtn)
            // renderDatPhong($('.overlay.phong'))
            $("body").style.overflow = "hidden";
        };
    }
}

function renderDatPhong(element, datPhongBtn, room) {
    let phong_dat = getParentElement(datPhongBtn, '.phong__dat')
    let soluong = phong_dat.querySelector('.so-luong-phong')
    let phong_item = getParentElement(phong_dat, '.phong-item')
    let phong_loai = phong_item.querySelector('.phong__loai')
    // let gioDen = $('#chon-gio-den').value
    // let gioTra = $('#chon-gio-tra').value
    // let checkIn = new Date(`${$('.ngay-nhan').value.split('-').reverse().join('-')} ${gioDen}`).getTime()
    // let checkOut = new Date(`${$('.ngay-tra').value.split('-').reverse().join('-')} ${gioTra}`).getTime()
    let checkIn = $('.ngay.checkIn').innerHTML
    let checkOut = $('.ngay.checkOut').innerHTML
    
    element.innerHTML =
        `
    <div class="row booking">
        <div class="col l-12 c-12 m-12 booking-detail">
            <div class="booking__date">
                <div class="booking__date-value">
                    <h2 class="dat-phong__ngay-nhan">Ngày nhận phòng</h2>
                    <i class="fas fa-calendar-alt lich-icon"></i>
                    <input class="ngay-nhan" value="${checkIn}"></input>
                </div>
                <div class="booking__time-value">
                    <h2 class="gio-nhan">Giờ nhận</h2>
                    <select name="chon-gio" id="chon-gio-den" class="chon-gio">
                        <option value="00:00">00:00</option>
                        <option value="01:00">01:00</option>
                        <option value="02:00">02:00</option>
                        <option value="03:00">03:00</option>
                        <option value="04:00">04:00</option>
                        <option value="05:00">05:00</option>
                        <option value="06:00">06:00</option>
                        <option value="07:00">07:00</option>
                        <option value="08:00">08:00</option>
                        <option value="09:00">09:00</option>
                        <option value="10:00">10:00</option>
                        <option value="11:00">11:00</option>
                        <option value="12:00">12:00</option>
                        <option value="13:00">13:00</option>
                        <option value="14:00">14:00</option>
                        <option value="15:00">15:00</option>
                        <option value="16:00">16:00</option>
                        <option value="17:00">17:00</option>
                        <option value="18:00">18:00</option>
                        <option value="19:00">19:00</option>
                        <option value="20:00">20:00</option>
                        <option value="21:00">21:00</option>
                        <option value="22:00">22:00</option>
                        <option value="23:00">23:00</option>
                    </select>
                </div>
            </div>
            <div class="booking__date">
                <div class="booking__date-value">
                    <h2 class="dat-phong__ngay-tra">Ngày trả phòng</h2>
                    <i class="fas fa-calendar-alt lich-icon"></i>
                    <input class="ngay-tra" value="${checkOut}">
                    
                </div>
                <div class="booking__time-value">
                    <h2 class="gio-tra">Giờ trả</h2>
                    <select name="chon-gio" id="chon-gio-tra" class="chon-gio">
                        <option value="00:00">00:00</option>
                        <option value="01:00">01:00</option>
                        <option value="02:00">02:00</option>
                        <option value="03:00">03:00</option>
                        <option value="04:00">04:00</option>
                        <option value="05:00">05:00</option>
                        <option value="06:00">06:00</option>
                        <option value="07:00">07:00</option>
                        <option value="08:00">08:00</option>
                        <option value="09:00">09:00</option>
                        <option value="10:00">10:00</option>
                        <option value="11:00">11:00</option>
                        <option value="12:00">12:00</option>
                        <option value="13:00">13:00</option>
                        <option value="14:00">14:00</option>
                        <option value="15:00">15:00</option>
                        <option value="16:00">16:00</option>
                        <option value="17:00">17:00</option>
                        <option value="18:00">18:00</option>
                        <option value="19:00">19:00</option>
                        <option value="20:00">20:00</option>
                        <option value="21:00">21:00</option>
                        <option value="22:00">22:00</option>
                        <option value="23:00">23:00</option>
                    </select>
                </div>
            </div>
            <div class="booking__item-wrapper">
                <div class="booking-item booking__phong ">
                    <h2 class="booking-item-title booking__loai-phong" >Loại phòng</h2>
                    <div class="booking-item-value booking__ten-loai-phong" roomId="${phong_loai.getAttribute('roomId')}">${phong_loai.innerHTML}</div>
                </div>
                <div class="booking-item">
                    <h2 class="booking-item-title">Số lượng</h2>
                    <div class="booking-item-value" id="so-luong">${soluong.value}</div>
                </div>
            </div>
            <div class="booking__item-wrapper">
                <div class="booking-item">
                    <h2 class="booking-item-title">Giá phòng</h2>
                    <div class="booking-item-value">${phong_dat.querySelector('.gia-hien-tai').innerHTML}</div>
                </div>
               
                <div class="booking-item">
                    <h2 class="booking-item-title">Tổng tiền</h2>
                    <div class="booking-item-value" id="tong-tien"></div>
                </div>
            </div>
            <div class="booking__btn">
                <button class="btn--small booking__huy-btn">Hủy</button>
                <button class="btn--small booking__xac-nhan-btn">Xác nhận</button>
            </div>

        </div>
    </div>
    `
    renderTongTien(phong_dat, soluong, checkIn, checkOut)
    anChiTiet('.booking__huy-btn');
    postBooking(url, 1);
}
function dayDiff(checkIn, checkOut){
    //chưa tối ưu
    let time1 = $('#chon-gio-den').value
    let time2 = $('#chon-gio-tra').value
    const diffTime = Math.abs(new Date(checkOut.split('-').reverse().join('-') + ' ' + time2) - new Date(checkIn.split('-').reverse().join('-') + ' ' + time1));
    const diffDays = (diffTime / (1000 * 60 * 60 * 24))
    return diffDays
}
function renderTongTien(phong_dat, soluong, checkIn, checkOut){
    //chưa tối ưu
    const tongTien = $('#tong-tien')
    let den = $('#chon-gio-den')
    let di = $('#chon-gio-tra')
    den.onchange = ()=>{
        tongTien.innerHTML = formatPrice(phong_dat.querySelector('.gia-hien-tai').getAttribute('price') * soluong.value * dayDiff(checkIn, checkOut))
    }
    di.onchange = ()=>{
        tongTien.innerHTML = formatPrice(phong_dat.querySelector('.gia-hien-tai').getAttribute('price') * soluong.value * dayDiff(checkIn, checkOut))
    }
    tongTien.innerHTML = formatPrice(phong_dat.querySelector('.gia-hien-tai').getAttribute('price') * soluong.value * dayDiff(checkIn, checkOut))

}
function postBooking(url, userId, status=1) {
    const btn = $('.booking__xac-nhan-btn')
    let soLuong = +$('#so-luong').innerHTML
    let roomId = +$('.booking__ten-loai-phong').getAttribute('roomId')
    let gioDen = $('#chon-gio-den').value
    let gioTra = $('#chon-gio-tra').value
    let checkIn = new Date(`${$('.ngay-nhan').value.split('-').reverse().join('-')} ${gioDen}`).getTime()
    let checkOut = new Date(`${$('.ngay-tra').value.split('-').reverse().join('-')} ${gioTra}`).getTime()
    
    
    btn.onclick = () => {

        let booking = {
            "user_id": userId,
            "room_id": roomId,
            "qty": soLuong,
            "status": status,
            "discount_percent": 0,
            "check_in": checkIn,
            "check_out": checkOut
        }
        var raw = JSON.stringify(booking);

        var requestOptions = {
            method: 'POST',
            body: raw,
        };

        
        fetch(host + url.booking, requestOptions)
            .then(response => response.json())
            .then((response)=> {
                if(response === null)
                    alert('Phòng đã được sử dụng vui lòng chọn lại ngày sau đó bấm tìm phòng')
                else{
                    alert('Đặt phòng thành công')    
                }
                $('.overlay.phong').innerHTML = ''
                const chiTietPhongWrapper = $(".chi-tiet-phong-wrapper");
                xuLyAnChiTiet(chiTietPhongWrapper)
            })
            .catch(error => console.log('error', error));

    }
}
function xuLyAnChiTiet(chiTietPhongWrapper) {
    chiTietPhongWrapper.style.display = "none";
    $("body").style.overflowY = "scroll";
}
function anChiTiet(element) {
    const chiTietPhongWrapper = $(".chi-tiet-phong-wrapper");
    chiTietPhongWrapper.querySelector(element).onclick = () => {
        xuLyAnChiTiet(chiTietPhongWrapper);
    };
}

function renderHotelInfo(url, id) {
    const gioiThieu = $(".gioi-thieu")
    fetch(host + url + id)
        .then((response) => response.json())
        .then(hotel => {
            return falsyFilter(hotel &&
                `
            <div class="col l-8 thongtin">
                <h1 class="ten-hotel">${hotel.name}</h1>
                <div class="dia-chi">
                    <h2>Địa chỉ:</h2><span> ${hotel.address}</span>
                </div>
                <p class="mota">${hotel.description}</p>
            </div>
            <div class="col l-4">
                <div class="dacdiem">
                    <h1 class="dacdiem_tieude">Điểm nổi bật của chỗ nghỉ</h1>
                    <ul class="dac-diem-list">
                        <li class="dac-diem-item">
                            <i class="fas fa-heart"></i>
                            Nằm ngay trung tâm Hà Nội, khách sạn này có điểm vị trí tuyệt vời 9,6
                        </li>
                        <li class="dac-diem-item">
                            <i class="fas fa-utensils"></i>
                            Đồ Ăn Ngon Tuyệt: Bạn rất nên dùng các bữa ăn ở đây
                        </li>
                        <li class="dac-diem-item">
                            <i class="fas fa-bed"></i>
                            Bạn muốn ngủ thật ngon? Khách sạn này được đánh giá cao nhờ những chiếc giường thoải mái.
                        </li>
                        <li class="dac-diem-item">
                            
                        </li>
                    </ul>
                    <h2 class="thongtin-buasang">Thông tin về bữa sáng</h2>
                    <p class="thongtin-buasang-chitiet">Kiểu lục địa, Kiểu Á, Tự chọn</p>
                    <div class="button-wrapper">
                        <button class="dat-phong-btn full-width">Đặt phòng ngay</button>                       
                    </div> 
                </div>  
            </div>
            `)
        }
        )
        .then(html => gioiThieu.innerHTML = html)
}

function renderImageSlide(url, id, slideName, order, desktop, tablet, mobile) {
    let imageSlide = $$(".danh-sach-anh");
    console.log(imageSlide[order], 2)
    console.log(host + url + id)
    fetch(host + url + id)
        .then((response) => response.json())
        .then((images) =>
            images.map(
                (image) => `<li class="anh-item slide-item ${slideName} col l-${desktop} c-${tablet} m-${mobile}">
                                <img src="${host + image.link}" alt="" class="anh-item__anh">
                            </li>`
            )
        )
        .then((html) => html.join(""))
        .then((html) => {
            imageSlide[order].innerHTML = `
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
            }
        })
        .catch((err) => console.error("error", err));
}

async function renderRooms(url, checkIn = '', checkOut = '') {

    const danhSachPhong = $(".danh-sach-phong")
    danhSachPhong.innerHTML = ''
    let date = ''
    if (checkIn && checkOut) {
        date = '&checkIn=' + checkIn + '&checkOut=' + checkOut
    }
    console.log(host + url.rooms + hotelId + date)
    await fetch(host + url.rooms + hotelId + date)
        .then(response => response.json())
        .then(rooms => rooms.forEach(room => {
            // console.log(room)
            renderRoomDetail(url, danhSachPhong, room)
        }))

        .catch(err => console.log('error: ', err))

}

async function renderRoomDetail(url, element, room, mode = "tom-tat") {
    const typeTmp = fetch(host + url.type + room.types_id)
        .then(response => response.json())
        .then(type => type)
        .catch(err => console.log('error: ', err))
    const amenitiesTmp = fetch(host + url.amenities + room.id)
        .then(response => response.json())
        .then(amenities_room => amenities_room)
        .catch(err => console.log('error: ', err))
    const type = await typeTmp
    const amenities = await amenitiesTmp
    // room.discount = 0.9
    if (mode === 'tom-tat') {
        element.innerHTML +=
            `<div class="col l-6 m-12 c-12">
            <div class="phong-item">
                <h1 class="phong__loai" roomId="${room.id}">${type.name}</h1>
                <div class="phong__chitiet">
                    <div class="so-nguoi">
                        ${renderSonguoi(room.person_capacity)}
                    </div>
                    <ul class="tien-ich">
                        ${renderAmenities(amenities)}
                    </ul>
                </div>
                <div class="phong__dat">
                    <div>      
                        <p class="gia-mota">Giá phòng</p>
                        ${falsyFilter(room && room.discount && `<span class="gia-cu">${formatPrice(room.price)}</span>:`)}
                        <h1 class="gia-hien-tai" price='${falsyFilter(room && tinhGia2(room.price, room.discount))}'>${falsyFilter(room && tinhGia(room.price, room.discount))}</h1>
                    </div>
                    <select name="so-luong-phong" class="so-luong-phong">
                        ${renderRoomQty(room.qty)}
                    </select>
                    ${room.qty > 0 ? '<button class="dat-phong-btn dat-btn">Đặt phòng</button>' : ''}
                    
                </div>
            </div>
        </div>`
        hienThiChiTiet();
        hienDatPhong();
        
    } else if (mode === 'chi-tiet') {
        element.innerHTML =
            `
        <div class="row chi-tiet-phong">
            <div class="close-chi-tiet-phong">
                <i class="fas fa-times close-icon"></i>
            </div>
            <div class="col l-7">
                <ul class="slide-list danh-sach-anh">
                    
                </ul>
                    </div>
                    <div class="col l-5">
                        <div class="phong-item-an">
                            <h1 class="phong__loai disabled">${type.name}</h1>
                            <div class="phong__chitiet">
                                <div class="so-nguoi">
                                    ${renderSonguoi(room.person_capacity)}
                                </div>
                                <ul class="tien-ich">
                                    ${renderAmenities(amenities)}
                                </ul>
                            </div>
                            <div class="mo-ta-chi-tiet-phong">
                                <h2 class="kich-thuoc-phong-tieude">Kích thước phòng: </h2>
                                <span class="kich-thuoc-phong">${room.area} m2</span>
                                <p class="mota-phong">
                                    ${room.description}
                                </p>
                            </div>
                            <div class="phong__dat">
                                <div>                                
                                    ${falsyFilter(room && room.discount && `<span class="gia-cu">${formatPrice(room.price)}</span>:`)}
                                    <h1 class="gia-hien-tai" >${falsyFilter(room && tinhGia(room.price, room.discount))}</h1>
                                </div>
                                <select name="so-luong-phong" class="so-luong-phong">
                                    ${renderRoomQty(room.qty)}
                                </select>
                                <button class="dat-phong-btn dat-btn">Đặt phòng</button>
                            </div>
                        </div>

                    </div>
        </div>
        `
        renderImageSlide(url.roomImage, room.id, 'slide-2', 1, 12, 6, 6)
        anChiTiet('.close-chi-tiet-phong');

    }



}

function renderAmenities(amenities) {
    return amenities.map(amen => `<li class="tien-ich-item">${amen.amenities}</li>`).join('')
}

function renderSonguoi(songuoi) {
    let html = `<span>Phù hợp cho: </span>`
    for (let i = 0; i < songuoi; i++) {
        html += '<i class="fas fa-user"></i>'
    }
    return html
}

function renderRoomQty(number) {
    let html = `<option value="">Chọn số lượng phòng</option>`
    let hetPhong = true
    for (let i = 1; i <= number; i++) {
        html += `<option value="${i}">${i}</option>`
        hetPhong = false
    }
    if (hetPhong) {
        html = `<option value="">Hết phòng</option>`
    }
    return html
}


