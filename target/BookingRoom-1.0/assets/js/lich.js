const date = new Date();
const $ = document.querySelector.bind(document)
const $$ = document.querySelectorAll.bind(document)

const renderCalendar = (className = '') => {
    date.setDate(1);

    const monthDays = $(`.days.${className}`);

    const lastDay = new Date(
        date.getFullYear(),
        date.getMonth() + 1,
        0
    ).getDate();

    const prevLastDay = new Date(
        date.getFullYear(),
        date.getMonth(),
        0
    ).getDate();

    const firstDayIndex = date.getDay();

    const lastDayIndex = new Date(
        date.getFullYear(),
        date.getMonth() + 1,
        0
    ).getDay();

    const nextDays = 7 - lastDayIndex - 1;

    const months = [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
    ];

    $(`.date.${className} h1`).innerHTML = months[date.getMonth()];
    const date_p = $(`.date.${className} p`)
    date_p.innerHTML = new Date(date.getFullYear(), date.getMonth(), new Date().getDate()).toDateString();


    let days = "";

    for (let x = firstDayIndex; x > 0; x--) {
        days += `<div class="prev-date">${prevLastDay - x + 1}</div>`;
    }

    for (let i = 1; i <= lastDay; i++) {
        if (
            i === new Date().getDate()
        ) {
            days += `<div class="day ${className} selected">${i}</div>`;
        } else {
            if (i <= new Date().getDate() && date.getMonth() === new Date().getMonth()) {
                days += `<div class="day invalid-date">${i}</div>`;
            } else {
                days += `<div class="day ${className}">${i}</div>`;
            }
        }
    }

    for (let j = 1; j <= nextDays; j++) {
        days += `<div class="next-date">${j}</div>`;
    }
    monthDays.innerHTML = days;
    //vo hieu hoa ngay nho hon check in
    let checkIn = $('.date.checkIn p').textContent
    let checkOutDay = $$(`.day.checkOut`)
    for (const day of checkOutDay) {
        if (new Date(new Date($('.date.checkOut p').innerHTML).getFullYear(), date.getMonth(), day.innerHTML).getTime() < new Date(checkIn).getTime()) {
            day.classList.add('invalid-date')
            day.classList.remove('selected')
        }
    }
    xuLyChonNgay(date.getMonth(), date.getFullYear(), date_p, className);
};

$(".prev.checkIn").addEventListener("click", () => {
    date.setMonth(date.getMonth() - 1);
    renderCalendar("checkIn");
});

$(".next.checkIn").addEventListener("click", () => {
    date.setMonth(date.getMonth() + 1);
    renderCalendar("checkIn");
});

$(".prev.checkOut").addEventListener("click", () => {
    date.setMonth(date.getMonth() - 1);
    renderCalendar("checkOut");
});

$(".next.checkOut").addEventListener("click", () => {
    date.setMonth(date.getMonth() + 1);
    renderCalendar("checkOut");
});

function xuLyChonNgay(month, year, element, className) {
    let days = $$(`.day.${className}:not(.invalid-date)`)
    let ngay = $(`.ngay.${className}`)
    days.forEach(function (day) {
        day.onclick = function () {
            for (const day of days) {
                day.classList.remove('selected')
            }
            day.classList.add('selected')
            
            element.innerHTML = new Date(year, month, day.textContent).toDateString()//gán lại ngày mới đc chọn
            ngay.innerHTML = new Date(year, month, +day.textContent+1).toISOString().slice(0, 10).split('-').reverse().join('-')
            // console.log(day.textContent)
            if(className ==='checkIn'){
                $('.ngay.checkOut').innerHTML = ''
            }
            
        }
    })

}

function hienThiLich(className1 = '', className2 = '') {
    if (className1) {

        $(`.dat-phong__chon-ngay.${className1}`).onclick = () => {
            const calendar = $(`.calendar.${className1}`)
            calendar.classList.toggle('display-none')
        }
        // $(`.dat-phong__chon-ngay.${className1}`).onblur = () => {
        //     const calendar = $(`.calendar.${className1}`)
        //     calendar.classList.add('display-none')
        // }
    }
    if (className2)
        $(`.dat-phong__chon-ngay.${className2}`).onclick = () => {
            const calendar = $(`.calendar.${className2}`)
            calendar.classList.toggle('display-none')
        }
    // $(`.dat-phong__chon-ngay.${className2}`).onblur = () => {
    //     const calendar = $(`.calendar.${className2}`)
    //     calendar.classList.add('display-none')
    // }
    document.addEventListener('mouseup', function(e) {
        var containers = $$('.calendar');
        for (const container of containers) {
            if (!container.contains(e.target)) {
                container.classList.add('display-none');
            }
            
        }
    });
}
hienThiLich("checkIn", "checkOut")
renderCalendar("checkIn");
renderCalendar("checkOut");

