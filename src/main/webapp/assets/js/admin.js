import { getParentElement, formatPrice, tinhGia, host } from './base.js'
const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

const url = {
    hotels: 'api-admin-hotel',
    rooms: 'api-admin-room?hotelId=',
    hotelImage: "api-admin-image?hotelId=",
    roomImage: "api-admin-image?roomId=",
    room: "api-admin-room?roomId=",
    type: "api-admin-type?id=",
    amenities: "api-admin-amenities-room?roomId=",
    amenitiesName: "api-admin-amenities?id=",
    booking: "api-admin-booking",
    image: "api-admin-image",
    slides: "api-admin-slide"

}


renderContent(url, $$('.quan-ly-item'))

function hienThiChiTiet(buttonElement) {
    const buttons = buttonElement
    const overlay = $(".overlay.quan-ly");
    const popUp = $('.quan-ly__pop-up');
    for (const button of buttons) {
        button.onclick = async () => {
            overlay.style.display = "flex";
            if (button.classList.contains('room-hotel')) {
                const hotelId = getParentElement(button, '.body-row').getAttribute('hotelId')
                const hotelName = getParentElement(button, '.body-row').querySelector('.hotel-name').textContent
                renderDanhSachPhong(url, hotelId, hotelName, popUp)
            }
            else if (button.classList.contains('add-hotel-btn')) {
                renderAddHotelDetails(url, popUp)
            } else if (button.classList.contains('edit-hotel-btn')) {
                renderEditHotelDetails(url, button, popUp)
            } else if (button.classList.contains('add-room-btn')) {
                const hotelId = $('.ten-hotel').getAttribute('hotelId')
                renderAddRoomDetails(url, hotelId, popUp)
            } else if (button.classList.contains('add-slide-btn')) {
                renderAddSlideDetails(url, popUp)
            } else if (button.classList.contains('edit-slide-btn')) {
                renderEditSlideDetails(url, button, popUp)
            }
            // await fetch(host + url.hotels)
            //     .then(response => response.json())
            //     .then(hotels => {
            //         console.log(hotels)
            //         // renderRoomDetail(url, $('.overlay.phong'), room, "chi-tiet")
            //     })

            $("body").style.overflow = "hidden";
        };
    }
}
function xuLyAnChiTiet(overlay) {
    overlay.style.display = "none";
    $("body").style.overflowY = "scroll";
}
function anChiTiet(element) {
    const overlay = $(".overlay.quan-ly");
    overlay.querySelector(element).onclick = (e) => {
        e.preventDefault();
        xuLyAnChiTiet(overlay);
    };

}
function renderContent(url, menuItems) {
    const content = $('.content')
    for (const item of menuItems) {
        item.onclick = () => {
            for (const item of menuItems) {
                item.classList.remove('enable')
            }
            item.classList.add('enable')
            if (item.classList.contains('quan-ly-khach-san')) {
                renderQuanLyKhachSan(url, content)
            } else if (item.classList.contains('quan-ly-trang-chu')) {
                renderQuanLyTrangChu(url, content)
            } else if (item.classList.contains('quan-ly-dat-phong')) {
                renderQuanLyDatPhong(url, content)
            }

            ////////////////////////////
        }
    }
}


function renderQuanLyKhachSan(url, element) {
    fetch(host + url.hotels)
        .then(response => response.json())
        .then((hotels) => {
            element.innerHTML =
                `
                <button class="btn btn--m add-hotel-btn">Thêm khách sạn</button>
                <div class="row table-head">
                    <div class="col l-1 c-1 m-1 table-column">
                        <span class="head-label">STT</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Tên</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Địa chỉ</span>
                    </div>
                    <div class="col l-3 table-column">
                        <span class="head-label">Mô tả</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Điện thoại</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Sao</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Quản lý</span>
                    </div>
                </div>
                <div class="hotel table-body">
                    ${renderHotels(hotels)}
                </div>
            `
        })
        .then(() => {
            hienThiChiTiet($$(".btn"))
            deleteHotel(url, $$('.delete-hotel'))
            // hienThiChiTiet($$(".btn"))
            // hienThiChiTiet($$('.btn'))

        })
        .catch(err => console.log('error: ', err))
    // .then(() => )
}
function renderHotels(hotels) {
    return hotels.map((hotel, index) =>
        `
        <div class="row body-row ${index % 2 === 0 ? 'le' : 'chan'}" hotelId="${hotel.id}">
            <div class="col l-1 c-1 m-1 table-column">
                <span class="body-label">${index + 1}</span>
            </div>
            <div class="col l-2 table-column hotel-name">
                <span class="body-label">${hotel.name}</span>
            </div>
            <div class="col l-2 table-column hotel-address">
                <span class="body-label">${hotel.address}</span>
            </div>
            <div class="col l-3 table-column hotel-description">
                <span class="body-label">${hotel.description}</span>
            </div>
            <div class="col l-1 table-column hotel-phone">
                <span class="body-label">${hotel.phone}</span>
            </div>
            <div class="col l-1 table-column hotel-star">
                <span class="body-label">${hotel.star}</span>
            </div>
            <div class="col l-2 table-column quan-ly-col">
                <div class="btn quan-ly-btn edit-hotel-btn">
                    <i class="fas fa-edit"></i>
                </div>
                <div class="quan-ly-btn delete-hotel">
                    <i class="fas fa-trash"></i>
                </div>
                <div class="btn quan-ly-btn room-hotel" title="Quản lý phòng">
                    <i class="fas fa-warehouse"></i>
                </div>
            </div>
        </div>
    `).join('')
}
function renderAddHotelDetails(url, element) {

    element.innerHTML =
        `
    <div class="hotel-detail">
        <div  id="hotel-detail-form">
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-name__input">Tên khách sạn</label>
                <input type="text" class="hotel-input__input" id="hotel-name__input"
                    name="hotel-name__input">
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-address__input">Địa chỉ</label>
                <input type="text" id="hotel-address__input" class="hotel-input__input"
                    name="hotel-address__input">
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-description__input">Mô tả</label>
                <textarea id="hotel-description__input" class="hotel-input__input"
                    name="hotel-description__input" rows="6" cols="80"></textarea>
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-phone__input">Điện thoại</label>
                <input type="text" id="hotel-phone__input" class="hotel-input__input"
                    name="hotel-phone__input">
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-star__input">Sao</label>
                <select type="text" id="hotel-star__input" class="hotel-input__input"
                    name="hotel-star__input">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <form class="hotel-input" id="images-input" method="POST" enctype="multipart/form-data">
                <label class="hotel-input__label" for="file">Tải ảnh lên</label>
                <input type="file"  multiple="multiple" accept="image/*" id="hotel-image__input" class="hotel-input__input"
                    name="file">
            </form>
            <div class="button-group">
                <button class="btn--s btn--cancel">Hủy</button>
                <button class="btn--s btn--accept">Xác nhận</button>
            </div>

        </div>

    </div>
    `
    anChiTiet('.btn--cancel')
    addHotel(url, $('.btn--accept'))
}
function renderEditHotelDetails(url, button, element) {

    element.innerHTML =
        `
    <div class="hotel-detail">
        <div  id="hotel-detail-form">
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-name__input">Tên khách sạn</label>
                <input type="text" class="hotel-input__input" id="hotel-name__input"
                    name="hotel-name__input">
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-address__input">Địa chỉ</label>
                <input type="text" id="hotel-address__input" class="hotel-input__input"
                    name="hotel-address__input">
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-description__input">Mô tả</label>
                <textarea id="hotel-description__input" class="hotel-input__input"
                    name="hotel-description__input" rows="6" cols="80"></textarea>
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-phone__input">Điện thoại</label>
                <input type="text" id="hotel-phone__input" class="hotel-input__input"
                    name="hotel-phone__input">
            </div>
            <div class="hotel-input">
                <label class="hotel-input__label" for="hotel-star__input">Sao</label>
                <select type="text" id="hotel-star__input" class="hotel-input__input"
                    name="hotel-star__input">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            <form class="hotel-input" id="images-input" method="POST" enctype="multipart/form-data">
                <label class="hotel-input__label" for="file">Tải ảnh lên</label>
                <input type="file"  multiple="multiple" accept="image/*" id="hotel-image__input" class="hotel-input__input"
                    name="file">
            </form>
            
            <div class="form-image-wrapper">
                
            </div>


            <div class="button-group">
                <button class="btn--s btn--cancel">Hủy</button>
                <button class="btn--s btn--accept">Xác nhận</button>
            </div>

        </div>

    </div>
    `
    anChiTiet('.btn--cancel')
    editHotel(url, button, $('.btn--accept'))

}
function addHotel(url, button) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    button.onclick = (event) => {
        event.preventDefault()
        let name = $('#hotel-name__input').value
        let address = $('#hotel-address__input').value
        let description = $('#hotel-description__input').value
        let phone = $('#hotel-phone__input').value
        let star = $('#hotel-star__input').value


        var hotel = JSON.stringify({
            name,
            address,
            description,
            star,
            phone,
        })
        var requestOptions = {
            method: 'POST',
            header: myHeaders,
            body: hotel,
        };
        fetch(host + url.hotels, requestOptions)
            .then(response => response.json())
            .then(hotel => {
                let form = $('#images-input')
                let inputFile = form.querySelector("#hotel-image__input")
                if (inputFile.files.length > 0) {
                    var header = new Headers();
                    header.append("Content-Type", "multipart/form-data");
                    let formData = new FormData()
                    for (const file of inputFile.files) {
                        formData.append('file', file)
                    }
                    fetch(host + url.image + '?hotelId=' + hotel.id, {
                        method: 'POST',
                        body: formData
                    })
                        // .then(response => response.json())
                        // .then(res => console.log(res))
                        .catch(error => console.log('error', error))
                }
                alert("Thêm thành công")
                $('.btn--cancel').click()

            })
            .then(() => renderQuanLyKhachSan(url, $(".content")))
            .catch(error => console.log('error', error))

    }

}
function editHotel(url, childElementInfo, button) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    let infoElement = getParentElement(childElementInfo, '.body-row')
    let name = $('#hotel-name__input')
    let address = $('#hotel-address__input')
    let description = $('#hotel-description__input')
    let phone = $('#hotel-phone__input')
    let star = $('#hotel-star__input')
    name.value = infoElement.querySelector('.hotel-name').innerText.trim()
    address.value = infoElement.querySelector('.hotel-address').innerText.trim()
    description.value = infoElement.querySelector('.hotel-description').innerText.trim()
    phone.value = infoElement.querySelector('.hotel-phone').innerText.trim()
    star.value = infoElement.querySelector('.hotel-star').innerText.trim()
    let hotelId = infoElement.getAttribute('hotelId')
    renderImage(url, hotelId, $('.form-image-wrapper'), 'hotel')
    button.onclick = (event) => {
        event.preventDefault()

        let hotel = JSON.stringify({
            id: hotelId,
            name: name.value,
            address: address.value,
            description: description.value,
            star: star.value,
            phone: phone.value
        })
        let requestOptions = {
            method: 'PUT',
            header: myHeaders,
            body: hotel,
        };
        fetch(host + url.hotels, requestOptions)
            .then(response => response.json())
            .then(hotel => {
                let form = $('#images-input')
                let inputFile = form.querySelector("#hotel-image__input")
                if (inputFile.files.length > 0) {
                    var header = new Headers();
                    header.append("Content-Type", "multipart/form-data");
                    let formData = new FormData()
                    for (const file of inputFile.files) {
                        formData.append('file', file)
                    }
                    fetch(host + url.image + '?hotelId=' + hotel.id, {
                        method: 'POST',
                        body: formData
                    })
                        // .then(response => response.json())
                        // .then(res => console.log(res))
                        .catch(error => console.log('error', error))
                }
                alert("Sửa thành công")
                $('.btn--cancel').click()

            })
            .then(() => renderQuanLyKhachSan(url, $(".content")))
            .catch(error => console.log('error', error))

    }

}
function deleteHotel(url, buttons) {
    for (const button of buttons) {
        button.onclick = () => {
            const hotelId = getParentElement(button, '.body-row').getAttribute('hotelId')
            console.log(hotelId)
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var hotel = JSON.stringify({
                id: hotelId
            })
            var requestOptions = {
                method: 'DELETE',
                header: myHeaders,
                body: hotel,
                // redirect: 'follow'
            };
            if (confirm('Bạn có chắc chắn muốn xóa không?')) {
                deleteAllImage(url, hotelId, 'hotel')
                fetch(host + url.hotels, requestOptions)
                    .then(() => alert('Xóa thành công'))
                    .then(() => renderQuanLyKhachSan(url, $(".content")))
                    .catch(error => console.log('error', error));
            }
        }

    }
}


function renderQuanLyTrangChu(url, element) {
    fetch(host + url.slides)
        .then(response => response.json())
        .then((slides) => {
            element.innerHTML =
                `
                <button class="btn btn--m add-slide-btn">Thêm Slide</button>
                <div class="row table-head">
                    <div class="col l-1 c-1 m-1 table-column">
                        <span class="head-label">STT</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Link</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Tiêu đề</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Nhóm</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Mô tả</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Tạo</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Cập nhật</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Quản lý</span>
                    </div>
                </div>
                <div class="slide table-body">
                    ${renderSlides(slides)}
                </div>
            `
        })
        .then(() => {
            deleteSlide(url, $$('.delete-slide'))
            hienThiChiTiet($$(".btn"))
            // hienThiChiTiet($$(".add-slide-btn"))
            // hienThiChiTiet($$('.edit-slide-btn'))

        })
        .catch(err => console.log('error: ', err))
    // .then(() => )
}
function renderSlides(slides) {
    return slides.map((slide, index) =>
        `
        <div class="row body-row ${index % 2 === 0 ? 'le' : 'chan'}" slideId="${slide.id}">
            <div class="col l-1 c-1 m-1 table-column">
                <span class="body-label">${index + 1}</span>
            </div>
            <div class="col l-2 table-column slide-link">
                <span class="body-label">${slide.link}</span>
            </div>
            <div class="col l-2 table-column slide-title">
                <span class="body-label">${slide.title}</span>
            </div>
            <div class="col l-1 table-column slide-group">
                <span class="body-label">${slide.group}</span>
            </div>
            <div class="col l-2 table-column slide-description">
                <span class="body-label">${slide.description}</span>
            </div>
            <div class="col l-1 table-column slide-created">
                <span class="body-label" >${xuLyNgay(slide.created_at)}</span>
            </div>
            <div class="col l-1 table-column slide-updated">
                <span class="body-label" >${xuLyNgay(slide.updated_at)}</span>
            </div>
            <div class="col l-2 table-column quan-ly-col">
                <div class="btn quan-ly-btn edit-slide-btn">
                    <i class="fas fa-edit"></i>
                </div>
                <div class="quan-ly-btn delete-slide">
                    <i class="fas fa-trash"></i>
                </div>
            </div>
        </div>
    `).join('')
}
function renderAddSlideDetails(url, element) {

    element.innerHTML =
        `
    <div class="slide-detail">
        <div  id="slide-detail-form">
            <div class="slide-input">
                <label class="slide-input__label" for="slide-title__input">Tiêu đề</label>
                <input type="text" class="slide-input__input" id="slide-title__input"
                    name="slide-title__input">
            </div>
            <div class="slide-input">
                <label class="slide-input__label" for="slide-link__input">Link</label>
                <input type="text" id="slide-link__input" class="slide-input__input"
                    name="slide-link__input">
            </div>
            <div class="slide-input">
                <label class="slide-input__label" for="slide-group__input">Nhóm</label>
                <input type="text" id="slide-group__input" class="slide-input__input"
                    name="slide-group__input">
            </div>
            <div class="slide-input">
                <label class="slide-input__label" for="slide-description__input">Mô tả</label>
                <textarea id="slide-description__input" class="slide-input__input"
                    name="slide-description__input" rows="6" cols="80"></textarea>
            </div>
           
            <form class="slide-input" id="images-input" method="POST" enctype="multipart/form-data">
                <label class="slide-input__label" for="file">Tải ảnh lên</label>
                <input type="file"  accept="image/*" id="slide-image__input" class="slide-input__input"
                    name="file">
            </form>
            <div class="button-group">
                <button class="btn--s btn--cancel">Hủy</button>
                <button class="btn--s btn--accept">Xác nhận</button>
            </div>

        </div>

    </div>
    `
    anChiTiet('.btn--cancel')
    addSlide(url, $('.btn--accept'))
}
function renderEditSlideDetails(url, button, element) {

    element.innerHTML =
        `
        <div class="slide-detail">
        <div  id="slide-detail-form">
            <div class="slide-input">
                <label class="slide-input__label" for="slide-title__input">Tiêu đề</label>
                <input type="text" class="slide-input__input" id="slide-title__input"
                    name="slide-title__input">
            </div>
            <div class="slide-input">
                <label class="slide-input__label" for="slide-link__input">Link</label>
                <input type="text" id="slide-link__input" class="slide-input__input"
                    name="slide-link__input">
            </div>
            <div class="slide-input">
                <label class="slide-input__label" for="slide-group__input">Nhóm</label>
                <input type="text" id="slide-group__input" class="slide-input__input"
                    name="slide-group__input">
            </div>
            <div class="slide-input">
                <label class="slide-input__label" for="slide-description__input">Mô tả</label>
                <textarea id="slide-description__input" class="slide-input__input"
                    name="slide-description__input" rows="6" cols="80"></textarea>
            </div>
           
            <form class="slide-input" id="images-input" method="POST" enctype="multipart/form-data">
                <label class="slide-input__label" for="file">Tải ảnh lên</label>
                <input type="file"  accept="image/*" id="slide-image__input" class="slide-input__input"
                    name="file">
            </form>
            <div class="form-image-wrapper">
                
            </div>
            <div class="button-group">
                <button class="btn--s btn--cancel">Hủy</button>
                <button class="btn--s btn--accept">Xác nhận</button>
            </div>

        </div>

    </div>
    `
    anChiTiet('.btn--cancel')
    editSlide(url, button, $('.btn--accept'))

}
function addSlide(url, button) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    button.onclick = (event) => {
        event.preventDefault()
        let title = $('#slide-title__input').value
        let link = $('#slide-link__input').value
        let description = $('#slide-description__input').value
        let group = $('#slide-group__input').value
        var slide = JSON.stringify({
            title,
            link,
            description,
            group,
        })
        var requestOptions = {
            method: 'POST',
            header: myHeaders,
            body: slide,
        };
        fetch(host + url.slides, requestOptions)
            .then(response => response.json())
            .then(slide => {
                let form = $('#images-input')
                let inputFile = form.querySelector("#slide-image__input")
                if (inputFile.files.length > 0) {
                    var header = new Headers();
                    header.append("Content-Type", "multipart/form-data");
                    let formData = new FormData()
                    for (const file of inputFile.files) {
                        formData.append('file', file)
                    }
                    fetch(host + url.image + '?slideId=' + slide.id, {
                        method: 'POST',
                        body: formData
                    })
                        // .then(response => response.json())
                        // .then(res => console.log(res))
                        .catch(error => console.log('error', error))
                }
                alert("Thêm thành công")
                $('.btn--cancel').click()

            })
            .then(() => renderQuanLyTrangChu(url, $(".content")))
            .catch(error => console.log('error', error))

    }

}
function editSlide(url, childElementInfo, button) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    let infoElement = getParentElement(childElementInfo, '.body-row')
    let title = $('#slide-title__input')
    let link = $('#slide-link__input')
    let description = $('#slide-description__input')
    let group = $('#slide-group__input')
    title.value = infoElement.querySelector('.slide-title').innerText.trim()
    link.value = infoElement.querySelector('.slide-link').innerText.trim()
    description.value = infoElement.querySelector('.slide-description').innerText.trim()
    group.value = infoElement.querySelector('.slide-group').innerText.trim()

    let slideId = infoElement.getAttribute('slideId')
    renderImage(url, slideId, $('.form-image-wrapper'), 'slide')
    button.onclick = (event) => {
        event.preventDefault()

        let slide = JSON.stringify({
            id: slideId,
            title: title.value,
            link: link.value,
            description: description.value,
            group: group.value,
        })
        let requestOptions = {
            method: 'PUT',
            header: myHeaders,
            body: slide,
        };
        fetch(host + url.slides, requestOptions)
            .then(response => response.json())
            .then(slide => {
                let form = $('#images-input')
                let inputFile = form.querySelector("#slide-image__input")
                if (inputFile.files.length > 0) {
                    var header = new Headers();
                    header.append("Content-Type", "multipart/form-data");
                    let formData = new FormData()
                    for (const file of inputFile.files) {
                        formData.append('file', file)
                    }
                    fetch(host + url.image + '?slideId=' + slide.id, {
                        method: 'POST',
                        body: formData
                    })
                        // .then(response => response.json())
                        // .then(res => console.log(res))
                        .catch(error => console.log('error', error))
                }
                alert("Sửa thành công")
                $('.btn--cancel').click()

            })
            .then(() => renderQuanLyTrangChu(url, $(".content")))
            .catch(error => console.log('error', error))

    }

}
function deleteSlide(url, buttons) {
    for (const button of buttons) {
        button.onclick = () => {
            const slideId = getParentElement(button, '.body-row').getAttribute('slideId')
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var slide = JSON.stringify({
                id: slideId
            })
            var requestOptions = {
                method: 'DELETE',
                header: myHeaders,
                body: slide,
                // redirect: 'follow'
            };
            if (confirm('Bạn có chắc chắn muốn xóa không?')) {
                deleteAllImage(url, slideId, 'slide')
                fetch(host + url.slides, requestOptions)
                    .then(() => alert('Xóa thành công'))
                    .then(() => renderQuanLyTrangChu(url, $(".content")))
                    .catch(error => console.log('error', error));
            }
        }

    }
}


function renderDanhSachPhong(url, hotelId, hotelName, element) {
    element.innerHTML =
        `
        <div class="col l-12 c-12 m-12">
            <div class="danh-sach-phong-table">
                <div class="head-wrapper">
                    <button class="btn btn--m add-room-btn">Thêm phòng</button>
                    <div class="ten-hotel" hotelId="${hotelId}">${hotelName}</div>
                    <div class="close-danh-sach-phong">
                        <i class="fas fa-times close-icon"></i>
                    </div>
                </div>
                <div class="row table-head">
                    <div class="col l-1 c-1 m-1 table-column">
                        <span class="head-label">STT</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Loại phòng</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Giảm giá</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Mô tả</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Tiện ích</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Diện tích</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Số lượng</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Giá</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Sức chứa</span>
                    </div>
                    <div class="col l-1 table-column">
                        <div class="btn quan-ly-btn edit-hotel-btn">
                            <i class="fas fa-edit"></i>
                        </div>
                        <div class="quan-ly-btn delete-hotel">
                            <i class="fas fa-trash"></i>
                        </div>

                    </div>
                </div>
                <div class="room table-body">
                </div>
            </div>
        </div>
        `
    anChiTiet('.close-danh-sach-phong')
    renderRooms(url, hotelId)
    hienThiChiTiet($$('.btn'))

}
function renderRooms(url, hotelId) {

    const danhSachPhong = $(".room.table-body")
    danhSachPhong.innerHTML = ''
    fetch(host + url.rooms + hotelId)
        .then(response => response.json())
        .then(rooms => {
            rooms.forEach((room, index) => {
                renderRoomDetail(url, danhSachPhong, room, hotelId, index)
            }
            )
        })
        .catch(err => console.log('error: ', err))

}
async function renderRoomDetail(url, element, room, hotelId, index) {
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

    element.innerHTML += `

        <div class="row body-row" roomId="${room.id}">
            <div class="col l-1 c-1 m-1 table-column">
                <span class="body-label">${index + 1}</span>
            </div>
            <div class="col l-1 table-column room-type">
                <span class="body-label" typeId=${type.id}>${type.name}</span>
            </div>
            <div class="col l-1 table-column room-discount">
                <span class="body-label">${room.discount}</span>
            </div>
            <div class="col l-2 table-column room-description">
                <span class="body-label">${room.description}</span>
            </div>
            <div class="col l-2 table-column">
                <ul class="body-label tien-ich">${renderAmenitiesList(amenities)}</ul>
            </div>
            <div class="col l-1 table-column room-area">
                <span class="body-label">${room.area}</span>
            </div>
            <div class="col l-1 table-column room-qty">
                <span class="body-label">${room.qty}</span>
            </div>
            <div class="col l-1 table-column room-price">
                <span class="body-label">${formatPrice(room.price)}</span>
            </div>
            <div class="col l-1 table-column room-capacity">
                <span class="body-label">${room.person_capacity}</span>
            </div>
            
            <div class="col l-1 table-column">
                <div class="btn quan-ly-btn edit-room">
                    <i class="fas fa-edit"></i>
                </div>
                <div class="quan-ly-btn delete-room">
                    <i class="fas fa-trash"></i>
                </div>

            </div>

        </div>`

    deleteRoom(url, hotelId, $$('.delete-room'))
    renderEditRoomDetails(url, hotelId, $$('.edit-room'), $('.quan-ly__pop-up'))

}
function renderAmenitiesList(amenities) {
    return amenities.map(amen => {
        // amen.sendBySelf = true
        return `<li class="tien-ich-item" id="amenities-${amen.id}">${amen.amenities}</li>`
    }).join('')
}
function renderAddRoomDetails(url, hotelId, element) {

    element.innerHTML =
        `
    <div class="room-details" hotelId="${hotelId}">
        <div id="room-details-form" >
            <div class="room-input">
                <label class="room-input__label" for="room-type__input">Loai Phòng</label>
                <select type="text" id="room-type__input" class="room-input__input"
                    name="room-star__input">
                    
                </select>
            </div>
            <div class="room-input">
                <label class="room-input__label" for="room-price__input">Giá</label>
                <input type="text" id="room-price__input" class="room-input__input"
                    name="room-price__input">
            </div>
            <div class="room-input">
                <label class="room-input__label" for="room-area__input">Diện tích</label>
                <input type="text" id="room-area__input" class="room-input__input"
                    name="room-area__input">
            </div>
            <div class="room-input">
                <label class="room-input__label" for="room-qty__input">Số lượng</label>
                <input type="text" id="room-qty__input" class="room-input__input"
                    name="room-qty__input">
            </div>
            <div class="room-input">
                <label class="room-input__label" for="room-capacity__input">Sức chứa</label>
                <input type="text" id="room-capacity__input" class="room-input__input"
                    name="room-capacity__input">
            </div>
            
            <div class="amenities-form">
                
            </div>

            <div class="room-input">
                <label class="room-input__label" for="room-description__input">Mô tả</label>
                <textarea id="room-description__input" class="room-input__input"
                    name="room-description__input" rows="6" cols="80"></textarea>
            </div>
            <div class="room-input">
                <label class="room-input__label" for="room-discount__input">Giảm giá</label>
                <input type="text" id="room-discount__input" class="room-input__input"
                    name="room-discount__input">
            </div>
            <div class="room-input" id="images-input" method="POST" enctype="multipart/form-data">
                <label class="room-input__label" for="room-image__input">Tải ảnh lên</label>
                <input type="file"  multiple accept="image/*" id="room-image__input" class="room-input__input"
                    name="room-image__input">
            </div>
            
            <div class="button-group">
                <button class="btn--s btn--cancel">Hủy</button>
                <button class="btn--s btn--accept">Xác nhận</button>
            </div>

        </div>

    </div>
    `
    anChiTiet('.btn--cancel')
    renderRoomTypes(url, $('#room-type__input'))
    renderAmenities(url, $('.amenities-form'))
    addRoom(url, $('.btn--accept'))

}
function renderEditRoomDetails(url, hotelId, buttons, element) {

    for (const button of buttons) {

        button.onclick = () => {
            let checkedList = getParentElement(button, '.body-row').querySelectorAll('.tien-ich-item')
            element.innerHTML =
                `
            <div class="room-details" hotelId="${hotelId}">
                <div id="room-details-form" >
                    <div class="room-input">
                        <label class="room-input__label" for="room-type__input">Loai Phòng</label>
                        <select type="text" id="room-type__input" class="room-input__input"
                            name="room-star__input">
                            
                        </select>
                    </div>
                    <div class="room-input">
                        <label class="room-input__label" for="room-price__input">Giá</label>
                        <input type="text" id="room-price__input" class="room-input__input"
                            name="room-price__input">
                    </div>
                    <div class="room-input">
                        <label class="room-input__label" for="room-area__input">Diện tích</label>
                        <input type="text" id="room-area__input" class="room-input__input"
                            name="room-area__input">
                    </div>
                    <div class="room-input">
                        <label class="room-input__label" for="room-qty__input">Số lượng</label>
                        <input type="text" id="room-qty__input" class="room-input__input"
                            name="room-qty__input">
                    </div>
                    <div class="room-input">
                        <label class="room-input__label" for="room-capacity__input">Sức chứa</label>
                        <input type="text" id="room-capacity__input" class="room-input__input"
                            name="room-capacity__input">
                    </div>
                    
                    <div class="amenities-form">
                        
                    </div>

                    <div class="room-input">
                        <label class="room-input__label" for="room-description__input">Mô tả</label>
                        <textarea id="room-description__input" class="room-input__input"
                            name="room-description__input" rows="6" cols="80"></textarea>
                    </div>
                    <div class="room-input">
                        <label class="room-input__label" for="room-discount__input">Giảm giá</label>
                        <input type="text" id="room-discount__input" class="room-input__input"
                            name="room-discount__input">
                    </div>
                    <div class="room-input" id="images-input" method="POST" enctype="multipart/form-data">
                        <label class="room-input__label" for="room-image__input">Tải ảnh lên</label>
                        <input type="file"  multiple accept="image/*" id="room-image__input" class="room-input__input"
                        name="room-image__input">
                    </div>
                    <div class="form-image-wrapper"></div>
                    <div class="button-group">
                        <button class="btn--s btn--cancel">Hủy</button>
                        <button class="btn--s btn--accept">Xác nhận</button>
                    </div>
                    
                    </div>
                    
            </div>
            `
            anChiTiet('.btn--cancel')
            renderRoomTypes(url, $('#room-type__input'))
            renderAmenities(url, $('.amenities-form'), checkedList)
            editRoom(url, button, $('.btn--accept'))
        }
    }


}
function editRoom(url, childElementInfo, button) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    let infoElement = getParentElement(childElementInfo, '.body-row')

    let types_id = $('#room-type__input')
    let price = $('#room-price__input')
    let area = $('#room-area__input')
    let description = $('#room-description__input')
    let qty = $('#room-qty__input')
    let person_capacity = $('#room-capacity__input')
    let discount = $('#room-discount__input')
    let hotel_id = $('.room-details').getAttribute('hotelId')
    let hotelName = getParentElement(infoElement, '.danh-sach-phong-table').querySelector('.ten-hotel').textContent
    // .querySelector('.hotel-name')
    // .textContent.trim()
    let name = ''

    setDefaultValue(types_id, infoElement.getAttribute('typeId'))
    price.value = infoElement.querySelector('.room-price').innerText.trim().replace(/[\.\₫\s+]/g, '')
    area.value = infoElement.querySelector('.room-area').innerText.trim()
    description.value = infoElement.querySelector('.room-description').innerText.trim()
    qty.value = infoElement.querySelector('.room-qty').innerText.trim()
    person_capacity.value = infoElement.querySelector('.room-capacity').innerText.trim()
    discount.value = infoElement.querySelector('.room-discount').innerText.trim()


    let room_id = infoElement.getAttribute('roomId')

    renderImage(url, room_id, $('.form-image-wrapper'), 'room')
    button.onclick = (event) => {
        event.preventDefault()

        let room = JSON.stringify({
            id: room_id,
            types_id: types_id.value,
            price: price.value,
            area: area.value,
            description: description.value,
            qty: qty.value,
            person_capacity: person_capacity.value,
            discount: discount.value,
            hotel_id: hotel_id,
            name,
        })
        let requestOptions = {
            method: 'PUT',
            header: myHeaders,
            body: room,
        };
        fetch(host + url.room.replace('?roomId=', ''), requestOptions)
            .then(response => response.json())
            .then(room => {
                let form = $('#images-input')
                let inputFile = form.querySelector("#room-image__input")
                addAmenities(url, room.id, $$('.amenities-input:checked'))
                if (inputFile.files.length > 0) {
                    var header = new Headers();
                    header.append("Content-Type", "multipart/form-data");
                    let formData = new FormData()
                    for (const file of inputFile.files) {
                        formData.append('file', file)
                    }
                    fetch(host + url.image + '?roomId=' + room.id, {
                        method: 'POST',
                        body: formData
                    })
                        // .then(response => response.json())
                        // .then(res => console.log(res))
                        .catch(error => console.log('error', error))
                }
                alert("Sửa thành công")
                $('.btn--cancel').click()
            })
            .then(() => {
                const overlay = $(".overlay.quan-ly");
                const popUp = $('.quan-ly__pop-up');
                overlay.style.display = "flex";
                renderDanhSachPhong(url, hotel_id, hotelName, popUp)
            })
            .catch(error => console.log('error', error))

    }
}
function deleteRoom(url, hotelId, buttons) {
    for (const button of buttons) {
        const roomId = getParentElement(button, '.body-row').getAttribute('roomId')
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        let room = JSON.stringify({
            id: roomId
        })
        let requestOptions = {
            method: 'DELETE',
            header: myHeaders,
            body: room,
            // redirect: 'follow'
        };
        button.onclick = () => {
            if (confirm('Bạn có chắc chắn muốn xóa không?')) {
                deleteAllImage(url, roomId, 'room')
                fetch(host + url.room, requestOptions)
                    .then(() => alert('Xóa thành công'))
                    .then(() => renderRooms(url, hotelId))
                    .catch(error => {
                        console.log('error', error)
                        alert('Xóa thất bại')
                    });
            }
        }

    }
}
function addRoom(url, button) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    button.onclick = (event) => {
        event.preventDefault()
        let types_id = $('#room-type__input').value
        let price = $('#room-price__input').value
        let area = $('#room-area__input').value
        let description = $('#room-description__input').value
        let qty = $('#room-qty__input').value
        let person_capacity = $('#room-capacity__input').value
        let discount = $('#room-discount__input').value / 100 + ''
        let hotel_id = $('.room-details').getAttribute('hotelId')
        let name = ''

        let room = JSON.stringify({
            hotel_id,
            types_id,
            price,
            name,
            area,
            description,
            qty,
            discount,
            person_capacity
        })

        var requestOptions = {
            method: 'POST',
            header: myHeaders,
            body: room,
        };
        fetch(host + url.room, requestOptions)
            .then(response => response.json())
            .then(room => {
                let form = $('#images-input')
                let inputFile = form.querySelector("#room-image__input")
                addAmenities(url, room.id, $$('.amenities-input:checked'))
                if (inputFile.files.length > 0) {
                    var header = new Headers();
                    header.append("Content-Type", "multipart/form-data");
                    let formData = new FormData()
                    for (const file of inputFile.files) {
                        formData.append('file', file)
                    }
                    fetch(host + url.image + '?roomId=' + room.id, {
                        method: 'POST',
                        body: formData
                    })
                        // .then(response => response.json())
                        // .then(res => console.log(res))
                        .catch(error => console.log('error', error))
                }
                alert("Thêm thành công")
                $('.btn--cancel').click()

            })
            .catch(error => console.log('error', error))

    }

}
function addAmenities(url, roomId, amenitiesElements) {
    fetch(host + url.amenities + roomId, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(res => console.log(res))
        .catch(error => console.log('error', error))
    let amenitiesURL = url.amenities.replace('roomId=', '') + 'mode=multiple'
    let amenitiesList =
        Array.from(amenitiesElements).map(amen => ({
            room_id: roomId,
            amenities_id: +amen.value
        }))
    let amenities = JSON.stringify(amenitiesList)

    fetch(host + amenitiesURL, {
        method: 'POST',
        body: amenities
    })
        .then(response => response.json())
        .catch(err => console.log('error: ', err))
}
function renderAmenities(url, element, checkedList = '') {
    let checked_list = Array.from(checkedList).map(checked => checked.id)
    fetch(host + url.amenitiesName)
        .then(response => response.json())
        .then(amenities => amenities.map(amen =>
            `
        <div class="amenities-item">
            <input type="checkbox"${checked_list.includes(`amenities-${amen.id}`) ? 'checked' : ''} name="amenities" class="amenities-input" id="amenities-${amen.id}" value="${amen.id}">
            <label for="amenities-${amen.id}">${amen.amenities}</label>
        </div>
        `).join(''))
        .then(html => element.innerHTML = html)
        .catch(err => console.log('error: ', err))
}
function renderRoomTypes(url, element, dft = '1') {
    fetch(host + url.type)
        .then(response => response.json())
        .then(types => types.map(type => `
        <option value="${type.id}">${type.name}</option>`
        ).join(''))
        .then(html => element.innerHTML = html)
        .then(() => setDefaultValue($('#room-type__input'), dft))

}


function renderQuanLyDatPhong(url, element){
    fetch(host + url.booking + '?mode=desc&column=created_at&option=details')
        .then(response => response.json())
        .then((booking) => {
            element.innerHTML =
                `
                <button class="btn btn--m add-booking-btn"></button>
                <div class="row table-head">
                    <div class="col l-1 c-1 m-1 table-column">
                        <span class="head-label">STT</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Tên khách</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Điện thoại</span>
                    </div>
                    <div class="col l-2 table-column">
                        <span class="head-label">Tên khách sạn</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Loại phòng</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Giá phòng</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Số lượng</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Giảm giá</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Thành tiền</span>
                    </div>
                    <div class="col l-1 table-column">
                        <span class="head-label">Quản lý</span>
                    </div>
                </div>
                <div class="booking table-body">
                    ${renderBooking(booking)}
                </div>
            `
        })
        .then(() => {
            hienThiChiTiet($$(".btn"))
            deleteBooking(url, $$('.delete-booking'))
        })
        .catch(err => console.log('error: ', err))
}
function renderBooking(bookings) {
    return bookings.map((booking, index) =>
        `
        <div class="row body-row ${index % 2 === 0 ? 'le' : 'chan'}" bookingId="${booking.id}">
            <div class="col l-1 c-1 m-1 table-column">
                <span class="body-label">${index + 1}</span>
            </div>
            <div class="col l-2 table-column booking-username">
                <span class="body-label">${booking.lastName + " " + booking.firstName}</span>
            </div>
            <div class="col l-1 table-column booking-phone">
                <span class="body-label">${booking.phone}</span>
            </div>
            <div class="col l-2 table-column booking-hotel">
                <span class="body-label">${booking.hotelName}</span>
            </div>
            <div class="col l-1 table-column booking-room">
                <span class="body-label">${booking.roomType}</span>
            </div>
            <div class="col l-1 table-column booking-room-price">
                <span class="body-label">${booking.roomPrice}</span>
            </div>
            <div class="col l-1 table-column booking-quantity">
                <span class="body-label">${booking.quantity}</span>
            </div>
            <div class="col l-1 table-column booking-discountPercent">
                <span class="body-label">${booking.discountPercent}</span>
            </div>
            <div class="col l-1 table-column booking-total">
                <span class="body-label">${tinhGia(booking.roomPrice, booking.discountPercent, booking.quantity)}</span>
            </div>
            <div class="col l-1 table-column quan-ly-col">
                <div class="btn quan-ly-btn edit-hotel-btn">
                    <i class="fas fa-edit"></i>
                </div>
                <div class="quan-ly-btn delete-booking">
                    <i class="fas fa-trash"></i>
                </div>
                
            </div>
        </div>
    `).join('')
}
function deleteBooking(url, buttons){
    for (const button of buttons) {
        button.onclick = () => {
            const bookingId = getParentElement(button, '.body-row').getAttribute('bookingId')
            console.log(bookingId)
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var hotel = JSON.stringify({
                id: bookingId
            })
            var requestOptions = {
                method: 'DELETE',
                header: myHeaders,
                body: hotel,
                // redirect: 'follow'
            };
            if (confirm('Bạn có chắc chắn muốn xóa không?')) {
                fetch(host + url.booking, requestOptions)
                    .then(() => alert('Xóa thành công'))
                    .then(() => renderQuanLyDatPhong(url, $(".content")))
                    .catch(error => console.log('error', error));
            }
        }

    }
}
function renderImage(url, id, element, option) {
    let op
    switch (option) {
        case 'hotel':
            op = '?hotelId=' + id
            break;
        case 'room':
            op = '?roomId=' + id
            break;
        case 'slide':
            op = '?slideId=' + id
            break;
    }
    fetch(host + url.image + op)
        .then(response => response.json())
        .then(images => images.map(
            image =>
                `
        <div class="image-wrapper" imageId="${image.id}">
            <div class="delete-image">
                <i class="fas fa-times close-icon white"></i>
            </div>
            <image class="image-item" src="${host + image.link}" alt="Hình ảnh" width="200px" height="200px"></image>
        </div>

        `
        ).join(''))
        .then((html) => element.innerHTML = html)
        .then(() => deleteOneImage(url, $$('.delete-image')))

}
function deleteOneImage(url, buttons) {
    for (const button of buttons) {
        button.onclick = () => {
            let parent = getParentElement(button, '.image-wrapper')

            if (confirm('Bạn có chắc chắn muốn xóa ảnh này không')) {
                let img = JSON.stringify({
                    id: parent.getAttribute('imageId')
                })
                let myHeaders = new Headers();
                myHeaders.append("Content-Type", "application/json");
                let requestOptions = {
                    method: 'DELETE',
                    header: myHeaders,
                    body: img,
                };
                fetch(host + url.image, requestOptions)
                    // .then(response => response.json())
                    // .then(response => console.log(response))
                    .then(() => parent.remove())
            }

        }

    }

}
function deleteAllImage(url, id, option) {
    let op
    switch (option) {
        case 'hotel':
            op = '?hotelId=' + id
            break;
        case 'room':
            op = '?roomId=' + id
            break;
        case 'slide':
            op = '?slideId=' + id
            break;

    }
    console.log(host + url.image + op)
    fetch(host + url.image + op, {
        method: 'DELETE'
    })
        // .then(response => response.json())
        // .then(res => console.log(res))
        .catch(error => console.log('error', error))
}
function setDefaultValue(element, value) {
    for (var i = 0; i < element.options.length; i++) {
        if (element.options[i].value === value) {
            element.selectedIndex = i;
            break;
        }
    }
}

function xuLyNgay(timestamp) {
    let date = new Date(timestamp).toISOString().slice(0, 19).split('T')
    return date[0] + '\n' + date[1]
}
/// fix bugs ảnh slide
// thêm constrain cho loại phòng bị trùng